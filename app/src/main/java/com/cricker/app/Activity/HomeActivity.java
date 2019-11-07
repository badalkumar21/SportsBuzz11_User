package com.cricker.app.Activity;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.cricker.app.Adapter.ViewPagerAdapter;
import com.cricker.app.BuildConfig;
import com.cricker.app.Fragment.HomeFragment;
import com.cricker.app.R;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.material.navigation.NavigationView;

import static com.cricker.app.Config.PATH;

public class HomeActivity extends AppCompatActivity {


    Toolbar toolbar;
    String answer;
    String[] list = {"Home", "Predictions", "Ranking", "Tab4", "Team Maker"};
    private ViewPager viewPager;
    private FragmentManager fragmentManager;
    private TextView category;
    private RelativeLayout relativeLayout;
    private ValueAnimator valueAnimator;
    private DrawerLayout drawerLayout;
    private boolean isRunning;
    Context context;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        context = this;
        getSharedPreferences("prefs", MODE_PRIVATE).edit()
                .putBoolean("firstStart", false).apply();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        toolbar = findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setTitle(null);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
//                    actionBar.setBackgroundDrawable(new ColorDrawable(R.drawable.offers_background));
        getWindow().setStatusBarColor(0x00000000);

        drawerLayout = findViewById(R.id.container);
        NavigationView navigationView = findViewById(R.id.navigation);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                item.setChecked(false);

                drawerLayout.closeDrawers();

                int id = item.getItemId();

                if (id == R.id.nav_share) {

                    try {
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("text/plain");
                        i.putExtra(Intent.EXTRA_SUBJECT, "All Sports Predictions");
                        String sAux = "\nDownlload this  App and get predictions for all fantasy matches \n\n";
                        sAux = sAux + "https://play.google.com/store/apps/details?id=com.cricker.app\n";
                        i.putExtra(Intent.EXTRA_TEXT, sAux);
                        startActivity(Intent.createChooser(i, "Share with"));
                    } catch (Exception e) {
                        //e.toString();
                    }

                } else if (id == R.id.nav_rate) {

                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.cricker.app")));

                } else if (id == R.id.nav_feedback) {

                    Intent i = new Intent(getApplicationContext(), FeedbackActivity.class);
                    startActivity(i);

                } else if (id == R.id.nav_report) {

                    Intent i = new Intent(getApplicationContext(), FeedbackActivity.class);
                    startActivity(i);

                } else if (id == R.id.nav_privacy_policy) {

                    Intent i = new Intent(getApplicationContext(), PrivacyPolicyActivity.class);
                    startActivity(i);

                } else if (id == R.id.nav_disclosure) {

                    Intent i = new Intent(getApplicationContext(), DisclosureActivity.class);
                    startActivity(i);

                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


        relativeLayout = findViewById(R.id.relativeLayout);
        valueAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), Color.parseColor("#0094a4"), Color.parseColor("#ed0082"), Color.parseColor("#d66f0e"), Color.parseColor("#ff0558"), Color.parseColor("#1ca055"));
        valueAnimator.setDuration((5 - 1) * 100000000);


        category = findViewById(R.id.category);
        viewPager = findViewById(R.id.viewPager);


        fragmentManager = getSupportFragmentManager();
        ViewPagerAdapter adapter = new ViewPagerAdapter(fragmentManager);

        adapter.AddFragment(new HomeFragment(), "Cricket");
//        adapter.AddFragment(new Tab6(), "Football");
//        adapter.AddFragment(new Tab7(), "Kabaddi");
//        adapter.AddFragment(new Tab8(), "NBA");
//        adapter.AddFragment(new Tab9(), "Smart Team Maker");

        viewPager.setClipToPadding(false);
        viewPager.setPadding(50, 50, 50, 0);
        viewPager.setPageMargin(10);


        viewPager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(viewPager);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                valueAnimator.setCurrentPlayTime((long) ((v + i) * 100000000));
                String data = list[i];
                category.setText(data);
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                relativeLayout.setBackgroundColor((Integer) animation.getAnimatedValue());
            }
        });

        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                answer = "You are connected to a WiFi Network";
            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                answer = "You are connected to a Mobile Network";

        //   Toast.makeText(getApplicationContext(), answer, Toast.LENGTH_LONG).show();

        } else {


            answer = "No internet Connectivity. Please Connect to the Internet";

            new AlertDialog.Builder(context)
                    .setTitle("No internet")
                    .setMessage("Please make sure that you are connected to Internet.")

                  .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setIcon(R.drawable.ic_signal_wifi_off_black_24dp)
                    .show();

        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        isRunning = true;

        UpdateChecker();
    }

    @Override
    protected void onStop() {
        super.onStop();
        isRunning = false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.container);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    public void UpdateChecker() {


        Firebase myFirebase;

        Firebase.setAndroidContext(getApplicationContext());

        String url = "https://crickerdemo-f5ce7.firebaseio.com/" + PATH + "/Version/currentVersion";
        myFirebase = new Firebase(url);

        myFirebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {

                Integer version = Integer.valueOf(dataSnapshot.getValue(String.class));

                Integer appVersion = BuildConfig.VERSION_CODE;

                if (version > appVersion) {


                    new AlertDialog.Builder(HomeActivity.this).setTitle(Html.fromHtml("<font color='#00838F'>Update Available</font>")).setMessage("New Version is available....Download and enjoy the new features").setPositiveButton("Update", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.cricker.app")));

                        }
                    }).show();


                } else {


                }


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }


}
