package com.cricker.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cricker.app.Activity.MatchDetailsActivity;
import com.cricker.app.Model.ModelTips;
import com.cricker.app.R;
import com.cricker.app.ViewHolder.ViewHolderTips;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static com.cricker.app.Config.PATH;

public class TipsFragment extends Fragment {

    public MatchDetailsActivity matchDetailsActivity;
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    ProgressBar pb;
    FirebaseRecyclerAdapter<ModelTips, ViewHolderTips> firebaseRecyclerAdapter;
    private String id;
    TextView textViewNoTips;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_tips, container, false);

        textViewNoTips = view.findViewById(R.id.no_tips_text);
        matchDetailsActivity = (MatchDetailsActivity) getActivity();

        pb = view.findViewById(R.id.pb_tips);
        mRecyclerView = view.findViewById(R.id.myRecycleView_tips);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        matchDetailsActivity = (MatchDetailsActivity) getActivity();
        id = matchDetailsActivity.id;

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference(PATH + "FantasySquad/Team" + "/" + id + "/" + "tips");
        Query query = mRef.orderByKey();

        pb.setVisibility(View.VISIBLE);

        FirebaseRecyclerOptions fbOptions = new FirebaseRecyclerOptions.Builder<ModelTips>().setQuery(query, ModelTips.class).build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ModelTips, ViewHolderTips>(fbOptions) {
            @Override
            protected void onBindViewHolder(ViewHolderTips viewHolder, final int position, final ModelTips model) {

                viewHolder.setTips(model.getTips());

                pb.setVisibility(View.GONE);

            }

            @Override
            public ViewHolderTips onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tips_row, parent, false);
                return new ViewHolderTips(view);
            }
        };

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pb.setVisibility(View.GONE);
                if (dataSnapshot.getChildrenCount() == 0) {
                    textViewNoTips.setVisibility(View.VISIBLE);
                } else {
                    textViewNoTips.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pb.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);


        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseRecyclerAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        firebaseRecyclerAdapter.stopListening();
    }
}
