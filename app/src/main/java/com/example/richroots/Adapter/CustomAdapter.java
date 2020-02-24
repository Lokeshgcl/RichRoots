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

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.RecyclerViewHolder> {
    Context context;
    String countryList[];
    int flags[];

    public CustomAdapter(Context applicationContext, String[] countryList, int[] flags) {
        this.context = context;
        this.countryList = countryList;
        this.flags = flags;
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
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        final int i = position;
        icon.setImageResource(flags[i]);
        TextView price = (TextView) view.findViewById(R.id.txtPrice);
        price.setText(countryList[i]);
        TextView center = (TextView) view.findViewById(R.id.txtcenter);
        center.setText(countryList[i]);
        TextView item = (TextView) view.findViewById(R.id.txtItem);
        item.setText(countryList[i]);
        TextView quantity = (TextView) view.findViewById(R.id.txtQuantity);
        quantity.setText(countryList[i]);

//        List<ItemDetail> itemDetails = new ArrayList<>();
//        ItemDetail itemDetail = new ItemDetail();
//        itemDetail.setImage(R.drawable.fruits);
//        itemDetail.setCenter("center1");
//        itemDetail.setItem("Item1");
//        itemDetail.setQuantity("Qua1");
//        itemDetail.setPriceRange("100 t0 200");
//        itemDetails.add(itemDetail);
//        ItemDetail itemDetail1 = new ItemDetail();
//        itemDetail.setImage(R.drawable.fruits);
//        itemDetail.setCenter("center2");
//        itemDetail.setItem("Item2");
//        itemDetail.setQuantity("Qua2");
//        itemDetail.setPriceRange("101 t0 203");
//        itemDetails.add(itemDetail1);
    }

    @Override
    public int getItemCount() {
        return countryList.length;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout linearLayout;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            linearLayout= (LinearLayout) itemView.findViewById(R.id.recyclerRowLL);
        }
    }
//    @Override
//    public int getCount() {
//        return countryList.length;
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }

//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        view = inflter.inflate(R.layout.recyclerview_row, null);
////        TextView country = (TextView) view.findViewById(R.id.textView);
//        ImageView icon = (ImageView) view.findViewById(R.id.icon);
////        country.setText(countryList[i]);
//        icon.setImageResource(flags[i]);
//        TextView price = (TextView) view.findViewById(R.id.txtPrice);
//        price.setText(countryList[i]);
//        TextView center = (TextView) view.findViewById(R.id.txtcenter);
//        center.setText(countryList[i]);
//        TextView item = (TextView) view.findViewById(R.id.txtItem);
//        item.setText(countryList[i]);
//        TextView quantity = (TextView) view.findViewById(R.id.txtQuantity);
//        quantity.setText(countryList[i]);
//
//        return view;
//    }
}

