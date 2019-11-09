package com.cricker.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.cricker.app.Activity.MatchDetailsActivity;
import com.cricker.app.Model.ModelInfo;
import com.cricker.app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.cricker.app.Config.PATH;

public class InfoFragment extends Fragment {


    public MatchDetailsActivity matchDetailsActivity;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    ArrayList<ModelInfo> infoArrayList = new ArrayList<>();
    private TextView text_avg_score_1st_inns;
    private TextView text_avg_score_2nd_inns;
    private TextView text_avg_score_3rd_inns;
    private TextView text_avg_score_4th_inns;
    private TextView text_capacity;
    private TextView text_city;
    private TextView text_date;
    private TextView text_duration;
    private TextView text_match;
    private TextView text_series;
    private TextView text_seriesDetails;
    private TextView text_time;
    private TextView text_venue;
    private TextView text_weather;
    private String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_info, container, false);

        text_avg_score_1st_inns = view.findViewById(R.id.avg_score_1st_inns);
        text_avg_score_2nd_inns = view.findViewById(R.id.avg_score_2nd_inns);
        text_avg_score_3rd_inns = view.findViewById(R.id.avg_score_3rd_inns);
        text_avg_score_4th_inns = view.findViewById(R.id.avg_score_4th_inns);
        text_capacity = view.findViewById(R.id.capacity);
        text_city = view.findViewById(R.id.city);
        text_date = view.findViewById(R.id.date);
        text_duration = view.findViewById(R.id.duration);
        text_match = view.findViewById(R.id.match);
        text_series = view.findViewById(R.id.series);
        text_seriesDetails = view.findViewById(R.id.series_details);
        text_time = view.findViewById(R.id.time);
        text_venue = view.findViewById(R.id.venue);
        text_weather = view.findViewById(R.id.weather);

        matchDetailsActivity = (MatchDetailsActivity) getActivity();
        id = matchDetailsActivity.id;

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference(PATH + "FantasySquad/Team" + "/" + id + "/" + "info");


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    infoArrayList.add(snapshot.getValue(ModelInfo.class));

                }

                if (infoArrayList.size() == 0) {
                    return;
                }
                ModelInfo modelInfo = infoArrayList.get(0);

                text_avg_score_1st_inns.setText(String.valueOf(modelInfo.getAvg_score_1st_inns()));
                text_avg_score_2nd_inns.setText(String.valueOf(modelInfo.getAvg_score_2nd_inns()));
                text_avg_score_3rd_inns.setText(String.valueOf(modelInfo.getAvg_score_3rd_inns()));
                text_avg_score_4th_inns.setText(String.valueOf(modelInfo.getAvg_score_4th_inns()));

                text_capacity.setText(String.valueOf(modelInfo.getCapacity()));
                text_city.setText(modelInfo.getCity());
                text_date.setText(modelInfo.getDate());
                text_duration.setText(modelInfo.getDuration());
                text_match.setText(modelInfo.getMatch());
                text_series.setText(modelInfo.getSeries());
                text_seriesDetails.setText(modelInfo.getSeriesDetails());
                text_time.setText(modelInfo.getTime());
                text_venue.setText(modelInfo.getVenue());
                text_weather.setText(modelInfo.getWeather());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;

    }
}
