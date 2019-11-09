package com.cricker.app.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cricker.app.R;

public class ViewHolderTips extends RecyclerView.ViewHolder {

    View mView;
    private ClickListener mClickListener;

    public ViewHolderTips(final View itemView) {
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

    public void setTips(String title) {
        TextView squad_title = (TextView) mView.findViewById(R.id.tips);
        squad_title.setText(title);
    }

    public void setOnclickListener(ClickListener clickListener) {

        mClickListener = clickListener;

    }

    public interface ClickListener {

        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);

    }


}
