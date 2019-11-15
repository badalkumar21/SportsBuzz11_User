package com.sportsbuzz11.app.ViewHolder;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sportsbuzz11.app.R;
import com.squareup.picasso.Picasso;

public class ViewHolderSquad extends RecyclerView.ViewHolder {

    View mView;
    private ClickListener mClickListener;
    public CheckBox checkBox;

    public ViewHolderSquad(final View itemView) {
        super(itemView);

        mView = itemView;

//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mClickListener.onItemClick(v, getAdapterPosition());
//            }
//        });
//
//        itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//
//                mClickListener.onItemLongClick(v, getAdapterPosition());
//                return true;
//            }
//        });

        checkBox = mView.findViewById(R.id.checkBox);


    }


    public void setName(String title) {
        TextView squad_title = (TextView) mView.findViewById(R.id.squad_name);
        squad_title.setText(title);
    }

    public void setRole(String role) {
        TextView squad_role = (TextView) mView.findViewById(R.id.squad_role);
        squad_role.setText(role);
    }

    public void setPoints(float points) {
        TextView squad_points = (TextView) mView.findViewById(R.id.squad_points);
        squad_points.setText(String.valueOf(points));
    }

      public void setTeam(String team) {
        TextView squad_team = (TextView) mView.findViewById(R.id.squad_team);
          squad_team.setText(String.valueOf(team));
    }



    public void setAds(Context ctx, String ads) {
        ImageView adImage = (ImageView) mView.findViewById(R.id.adImage);
        Picasso.with(ctx).load(ads).into(adImage);
    }

    public void setOnclickListener(ClickListener clickListener) {

        mClickListener = clickListener;

    }

    public interface ClickListener {

        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);

    }


}
