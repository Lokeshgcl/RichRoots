package com.example.richroots.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.example.richroots.MainActivity;
import com.example.richroots.R;

import java.util.ArrayList;

/**
 * Created by IM021 on 4/21/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<String> mDataList;
    private Context context;

    public RecyclerViewAdapter(Context mainActivity, ArrayList<String> mDataList) {
        this.context = mainActivity;
        this.mDataList = mDataList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
//        TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
//            mTextView = (TextView) itemView.findViewById(R.id.textview);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.mTextView.setText(mDataList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }


}
