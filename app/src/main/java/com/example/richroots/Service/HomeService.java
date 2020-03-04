package com.example.richroots.Service;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;

import com.example.richroots.ViewModel.ItemCenterVM;

import java.util.ArrayList;
import java.util.List;

public class HomeService {
    private static List<ItemCenterVM> lstItems = new ArrayList<ItemCenterVM>();

    public static List<ItemCenterVM> getLstItems() {
        return lstItems;
    }

    public static void setLstItems(List<ItemCenterVM> lstItems) {
        HomeService.lstItems = lstItems;
    }

    @TargetApi(Build.VERSION_CODES.O)
    public static List<String> GetProductMarkets(final String prefix){
        final List<String> lstSuggestion = new ArrayList<String>();
        Log.v("","");
        getLstItems().stream().forEach(x -> {
            if(x.getProductName().contains(prefix)){
                lstSuggestion.add(x.getProductName());
            }
        });
        return lstSuggestion;
    }
}
