package com.sportsbuzz11.app.ViewHolder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sportsbuzz11.app.R;
import com.squareup.picasso.Picasso;

public class ViewHolderFantasyXI extends RecyclerView.ViewHolder {

    View mView;
    private ClickListener mClickListener;


    public ViewHolderFantasyXI(final View itemView) {
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

    }

    public void setName(String title) {
        TextView fantasy_xi_title = (TextView) mView.findViewById(R.id.fantasy_xi_name);
        fantasy_xi_title.setText(title);
    }


    public void setTeam(String text, String team) {
        TextView fantasy_xi_team = (TextView) mView.findViewById(R.id.fantasy_xi_team);
        fantasy_xi_team.setText(text);

        if (team.equals(text)){
            fantasy_xi_team.setBackgroundResource(R.drawable.team_bg_1);
        } else {
            fantasy_xi_team.setBackgroundResource(R.drawable.team_bg_2);
        }
    }



    public void setOnclickListener(ClickListener clickListener) {

        mClickListener = clickListener;

    }

    public interface ClickListener {

        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);

    }


}
