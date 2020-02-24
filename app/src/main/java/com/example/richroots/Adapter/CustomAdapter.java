package com.example.richroots.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.richroots.Model.ItemDetail;
import com.example.richroots.R;
import com.example.richroots.ViewModel.ItemCenterVM;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.RecyclerViewHolder> {
    Context context;
    String countryList[];
    int flags[];
    List<ItemCenterVM> lstItemCenterVM;

    public CustomAdapter(Context applicationContext, List<ItemCenterVM> lstItemCenterVM) {
        this.context = applicationContext;
        this.lstItemCenterVM = lstItemCenterVM;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_row, parent, false);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        final RecyclerViewHolder holder = new RecyclerViewHolder(view);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        View view = holder.linearLayout;
        ImageView icon = (ImageView) view.findViewById(R.id.itemImage);
        Picasso.with(this.context)
                .load("https://cdn.britannica.com/17/196817-050-6A15DAC3/vegetables.jpg")
                .placeholder(R.drawable.fruits)
                .into(icon);
        ItemCenterVM item = lstItemCenterVM.get(position);
        TextView price = (TextView) view.findViewById(R.id.txtPrice);
        price.setText(item.getMinPrice() + " to " + item.getMaxPrice());
        TextView center = (TextView) view.findViewById(R.id.txtcenter);
        center.setText(item.getMarketName());
        TextView txtitem = (TextView) view.findViewById(R.id.txtItem);
        txtitem.setText(item.getProductName());
        TextView quantity = (TextView) view.findViewById(R.id.txtQuantity);
        quantity.setText(item.getQuantity() + " " + item.getQuantityQualifier());
    }

    @Override
    public int getItemCount() {
        return lstItemCenterVM.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout linearLayout;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            linearLayout= (LinearLayout) itemView.findViewById(R.id.recyclerRowLL);
        }
    }
}

