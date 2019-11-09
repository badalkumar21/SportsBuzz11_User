package com.cricker.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.cricker.app.Activity.MatchDetailsActivity;
import com.cricker.app.Adapter.TabAdapter;
import com.cricker.app.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.cricker.app.Config.PATH;

public class SquadFragment extends Fragment {

    private String team1;
    private String team2;
    private MatchDetailsActivity matchDetailsActivity;
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView textSquadType;

    private DatabaseReference mRef;
    private FirebaseDatabase mFirebaseDatabase;
    private String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_squad, container, false);

        matchDetailsActivity = (MatchDetailsActivity) getActivity();
        team1 = matchDetailsActivity.team1;
        team2 = matchDetailsActivity.team2;
        id = matchDetailsActivity.id;

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference(PATH + "FantasySquad/Team" + "/" + id + "/" + "squadType");

        textSquadType = view.findViewById(R.id.squad_type);

        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);

        adapter = new TabAdapter(getChildFragmentManager());

        adapter.addFragment(new Tab1Fragment(), team1);
        adapter.addFragment(new Tab2Fragment(), team2);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        mRef.child("type").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String type = dataSnapshot.getValue(String.class);
                textSquadType.setText(type);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;

    }
}
