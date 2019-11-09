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
import com.cricker.app.Model.ModelNews;
import com.cricker.app.R;
import com.cricker.app.ViewHolder.ViewHolderNews;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static com.cricker.app.Config.PATH;

public class NewsFragment extends Fragment {

    public MatchDetailsActivity matchDetailsActivity;
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    ProgressBar pb;
    FirebaseRecyclerAdapter<ModelNews, ViewHolderNews> firebaseRecyclerAdapter;
    private String id;
    TextView textViewNoNews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_news, container, false);

        textViewNoNews = view.findViewById(R.id.no_news_text);
        matchDetailsActivity = (MatchDetailsActivity) getActivity();

        pb = view.findViewById(R.id.pb_news);
        mRecyclerView = view.findViewById(R.id.myRecycleView_news);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        matchDetailsActivity = (MatchDetailsActivity) getActivity();
        id = matchDetailsActivity.id;

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference(PATH + "FantasySquad/Team" + "/" + id + "/" + "news");
        Query query = mRef.orderByKey();

        pb.setVisibility(View.VISIBLE);

        FirebaseRecyclerOptions fbOptions = new FirebaseRecyclerOptions.Builder<ModelNews>().setQuery(query, ModelNews.class).build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ModelNews, ViewHolderNews>(fbOptions) {
            @Override
            protected void onBindViewHolder(ViewHolderNews viewHolder, final int position, final ModelNews model) {

                viewHolder.setNews(model.getNews());
                pb.setVisibility(View.GONE);

            }

            @Override
            public ViewHolderNews onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_row, parent, false);
                return new ViewHolderNews(view);
            }
        };

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pb.setVisibility(View.GONE);
                if (dataSnapshot.getChildrenCount() == 0) {
                    textViewNoNews.setVisibility(View.VISIBLE);
                } else {
                    textViewNoNews.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        mRef.addValueEventListener(new ValueEventListener() {
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
