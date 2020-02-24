package com.example.richroots.Util;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.richroots.R;

class RecyclerViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout linearLayout;
    public RecyclerViewHolder(View itemView) {
        super(itemView);
        linearLayout= (LinearLayout) itemView.findViewById(R.id.recyclerRowLL);
    }
}
