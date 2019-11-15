package com.sportsbuzz11.app.Activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sportsbuzz11.app.Adapter.TabAdapter;
import com.sportsbuzz11.app.Fragment.NewsFragment;
import com.sportsbuzz11.app.Fragment.SquadFragment;
import com.sportsbuzz11.app.Fragment.TeamGeneratorFragment;
import com.sportsbuzz11.app.Fragment.TipsFragment;
import com.sportsbuzz11.app.Model.MyModel;
import com.sportsbuzz11.app.R;

import static com.sportsbuzz11.app.Config.PATH;

public class MatchDetailsActivity extends AppCompatActivity {

    public String team1;
    public String team2;
    public String id;
    public String matchDesc;

    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;

    private TextView textMatchDate;
    private TextView textMatchTime;
    private TextView textMatchVenue;

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String matchTime = "";
    String matchVenue = "";
    String matchDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details);

        team1 = getIntent().getExtras().getString("team1");
        team2 = getIntent().getExtras().getString("team2");
        id = getIntent().getExtras().getString("id");
        matchDesc = getIntent().getExtras().getString("match");

        textMatchDate = findViewById(R.id.text_match_date);
        textMatchTime = findViewById(R.id.text_match_time);
        textMatchVenue = findViewById(R.id.text_match_venue);

        NestedScrollView scrollView = (NestedScrollView) findViewById(R.id.nest_scrollview);
        scrollView.setFillViewport(true);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle(team1 + " vs " + team2 + ", " + matchDesc);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(PATH + "Cricket");
        Query query = databaseReference.orderByChild("id").equalTo(id);

        updateMatchDetails();

        adapter = new TabAdapter(getSupportFragmentManager());

        adapter.addFragment(new TeamGeneratorFragment(), "Teams");
//        adapter.addFragment(new InfoFragment(), "Info");
        adapter.addFragment(new SquadFragment(), "Squad");
        adapter.addFragment(new NewsFragment(), "News");
        adapter.addFragment(new TipsFragment(), "Tips");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    MyModel model = snapshot.getValue(MyModel.class);

                    if (model != null) {
                        matchTime = model.getTime();
                        matchVenue = model.getVenue();
                        matchDate = model.getDate();

                        updateMatchDetails();

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void updateMatchDetails() {

        textMatchDate.setText(matchDate);
        textMatchTime.setText(matchTime);
        textMatchVenue.setText(matchVenue);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

}
