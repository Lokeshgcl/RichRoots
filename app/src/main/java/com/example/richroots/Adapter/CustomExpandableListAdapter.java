package com.example.richroots.Adapter;

import android.content.Context;
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
    //    List<ItemCenterVM> grpItemCenterVM;
    List<Integer> mainItemsIds;
    List<ItemCenterVM> mainItemCenters;
    private HashMap<Integer, List<ItemCenterVM>> grpItemCenterVM;
    public CustomExpandableListAdapter(Context context, HashMap<Integer,List<ItemCenterVM>> grpItemCenterVM,
                                       List<Integer> mainItemsIds, List<ItemCenterVM> mainItemCenters) {
        this.context = context;
        this.grpItemCenterVM = grpItemCenterVM;
        this.mainItemsIds = mainItemsIds;
        this.mainItemCenters = mainItemCenters;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {

        return this.grpItemCenterVM.get(this.mainItemsIds.get(listPosition))
                .get(expandedListPosition).getProductName();
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
        return this.grpItemCenterVM.get(this.mainItemsIds.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return  this.mainItemCenters.get(this.mainItemsIds.get(listPosition));
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
        int i = listPosition;
        itemCenterVM.setMarketName("test Market " + i);
        itemCenterVM.setProductName("test Product " + i);
        itemCenterVM.setQuantity(25 + i);
        itemCenterVM.setQuantityQualifier("pieces " + i);
        itemCenterVM.setMinPrice(200 + i);
        itemCenterVM.setMaxPrice(1500 + i);

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

}
