package com.sportsbuzz11.app.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.sportsbuzz11.app.R;
import com.google.android.material.tabs.TabLayout;
import com.sportsbuzz11.app.Adapter.TabAdapter;
import com.sportsbuzz11.app.Fragment.InfoFragment;
import com.sportsbuzz11.app.Fragment.NewsFragment;
import com.sportsbuzz11.app.Fragment.SquadFragment;
import com.sportsbuzz11.app.Fragment.TeamGeneratorFragment;
import com.sportsbuzz11.app.Fragment.TipsFragment;

public class MatchDetailsActivity extends AppCompatActivity {

    public String team1;
    public String team2;
    public String id;
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        team1 = getIntent().getExtras().getString("team1");
        team2 = getIntent().getExtras().getString("team2");
        id = getIntent().getExtras().getString("id");

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
