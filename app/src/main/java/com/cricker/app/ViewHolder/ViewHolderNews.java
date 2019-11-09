package com.cricker.app.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cricker.app.R;

public class ViewHolderNews extends RecyclerView.ViewHolder {

    View mView;
    private ClickListener mClickListener;

    public ViewHolderNews(final View itemView) {
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

    public void setNews(String title) {
        TextView text_news = (TextView) mView.findViewById(R.id.news);
        text_news.setText(title);
    }

    public void setOnclickListener(ClickListener clickListener) {

        mClickListener = clickListener;

    }

    public interface ClickListener {

        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);

    }


}
