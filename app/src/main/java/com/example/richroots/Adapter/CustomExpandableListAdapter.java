package com.example.richroots.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.richroots.R;
import com.example.richroots.ViewModel.ItemCenterVM;

import java.util.HashMap;
import java.util.List;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    List<Integer> mainItemsIds;
    private HashMap<Integer, ItemCenterVM> grpItemCenterVM;
    public CustomExpandableListAdapter(Context context, HashMap<Integer,ItemCenterVM> grpItemCenterVM,
                                       List<Integer> mainItemsIds) {
        this.context = context;
        this.grpItemCenterVM = grpItemCenterVM;
        this.mainItemsIds = mainItemsIds;
//        this.mainItemCenters = mainItemCenters;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        Log.v("listPosition : ", "" + listPosition);
        return this.grpItemCenterVM.get(this.mainItemsIds.get(listPosition)).getItemVarients()
                .get(expandedListPosition).getVariety();
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItem);
        expandedListTextView.setText(expandedListText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.grpItemCenterVM.get(this.mainItemsIds.get(listPosition)).getItemVarients().size();
    }

    @Override
    public Object getGroup(int listPosition) {
//        this.mainItemsIds.get(listPosition);
        return this.grpItemCenterVM.get(this.mainItemsIds.get(listPosition));
//        return  this.mainItemCenters.get(this.mainItemsIds.get(listPosition));
    }

    @Override
    public int getGroupCount() {
        return this.mainItemsIds.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        ItemCenterVM itemCenterVM = (ItemCenterVM) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.recyclerview_row, null);
        }

        ImageView icon = (ImageView) convertView.findViewById(R.id.itemImage);
        TextView price = (TextView) convertView.findViewById(R.id.txtPrice);
        price.setText(itemCenterVM.getMinPrice() + " to " + itemCenterVM.getMaxPrice());
        TextView center = (TextView) convertView.findViewById(R.id.txtcenter);
        center.setText(itemCenterVM.getMarketName());
        TextView txtitem = (TextView) convertView.findViewById(R.id.txtItem);
        txtitem.setText(itemCenterVM.getProductName());
        TextView quantity = (TextView) convertView.findViewById(R.id.txtQuantity);
        quantity.setText(itemCenterVM.getQuantity() + " " + itemCenterVM.getQuantityQualifier());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }

    public void removeAllGroup(int group) {
    this.mainItemsIds.clear();
    this.grpItemCenterVM.clear();
        Log.v("Adapter", "Removing group"+group);
        notifyDataSetChanged();
    }

}
