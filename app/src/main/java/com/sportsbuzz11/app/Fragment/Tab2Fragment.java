package com.sportsbuzz11.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sportsbuzz11.app.Activity.MatchDetailsActivity;
import com.sportsbuzz11.app.Model.ModelSquad;
import com.sportsbuzz11.app.R;
import com.sportsbuzz11.app.ViewHolder.ViewHolderSquad;

import java.util.Objects;

import static com.sportsbuzz11.app.Config.PATH;


public class Tab2Fragment extends Fragment {

    public MatchDetailsActivity matchDetailsActivity;
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    ProgressBar pb;
    String team2;

    FirebaseRecyclerAdapter<ModelSquad, ViewHolderSquad> firebaseRecyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_two, container, false);

        matchDetailsActivity = (MatchDetailsActivity) getActivity();
        team2 = matchDetailsActivity.team2;

        pb = view.findViewById(R.id.pb_fr2);
        mRecyclerView = view.findViewById(R.id.myRecycleView_fr2);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference(PATH + "Teams/" + team2);
        Query query = mRef.orderByChild("isPlaying").equalTo(true);

        pb.setVisibility(View.VISIBLE);

        FirebaseRecyclerOptions fbOptions = new FirebaseRecyclerOptions.Builder<ModelSquad>().setQuery(query, ModelSquad.class).build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ModelSquad, ViewHolderSquad>(fbOptions) {
            @Override
            protected void onBindViewHolder(ViewHolderSquad viewHolder, final int position, final ModelSquad model) {
                viewHolder.setAds(getActivity().getBaseContext(), model.getAds());

                viewHolder.setName(model.getName());
                viewHolder.setRole(model.getRole());
                viewHolder.setPoints(model.getPoints());
                viewHolder.setTeam(model.getTeam());

                pb.setVisibility(View.GONE);

            }

            @Override
            public ViewHolderSquad onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.squad_row, parent, false);

                return new ViewHolderSquad(view);
            }
        };

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pb.setVisibility(View.GONE);
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
