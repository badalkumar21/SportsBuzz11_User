package com.sportsbuzz11.app.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.sportsbuzz11.app.Adapter.TabAdapter;
import com.sportsbuzz11.app.Fragment.InfoFragment;
import com.sportsbuzz11.app.Fragment.NewsFragment;
import com.sportsbuzz11.app.Fragment.SquadFragment;
import com.sportsbuzz11.app.Fragment.TeamGeneratorFragment;
import com.sportsbuzz11.app.Fragment.TipsFragment;
import com.sportsbuzz11.app.R;

public class MatchDetailsActivity extends AppCompatActivity {

    public String team1;
    public String team2;
    public String id;
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details);

        NestedScrollView scrollView = (NestedScrollView) findViewById(R.id.nest_scrollview);
        scrollView.setFillViewport(true);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        team1 = getIntent().getExtras().getString("team1");
        team2 = getIntent().getExtras().getString("team2");
        id = getIntent().getExtras().getString("id");

        toolbar.setTitle(team1 + " vs " + team2);

        adapter = new TabAdapter(getSupportFragmentManager());

        adapter.addFragment(new TeamGeneratorFragment(), "Teams");
        adapter.addFragment(new InfoFragment(), "Info");
        adapter.addFragment(new SquadFragment(), "Squad");
        adapter.addFragment(new NewsFragment(), "News");
        adapter.addFragment(new TipsFragment(), "Tips");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
