package com.example.richroots.Service;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;

import com.example.richroots.ViewModel.ItemCenterVM;
import com.example.richroots.ViewModel.ItemVarient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeService {
    private static List<ItemCenterVM> lstItems = new ArrayList<ItemCenterVM>();

    public static List<ItemCenterVM> getLstItems() {
        return lstItems;
    }

    public static void setLstItems(List<ItemCenterVM> lstItems) {
        HomeService.lstItems = lstItems;
    }

    private static HashMap<Integer, ItemCenterVM> grpItemCenterVM;

    public static HashMap<Integer, ItemCenterVM> getGrpItemCenterVM() {
        return grpItemCenterVM;
    }

    public static void setGrpItemCenterVM(HashMap<Integer, ItemCenterVM> grpItemCenterVM) {
        HomeService.grpItemCenterVM = grpItemCenterVM;
    }



    @TargetApi(Build.VERSION_CODES.O)
    public static List<String> GetProductMarkets(final String prefix){
        final List<String> lstSuggestion = new ArrayList<String>();
        getLstItems().stream().forEach(x -> {
            if(x.getProductName().contains(prefix)){
                lstSuggestion.add(x.getProductName());
            }
        });
        return lstSuggestion;
    }

    @TargetApi(Build.VERSION_CODES.O)
    public static List<String> GetSearchAutcomplete(final String prefix){
        final List<String> lstSuggestion = new ArrayList<String>();
//        getLstItems().stream().forEach(x -> {
//            if(x.getProductName().contains(prefix)){
//                lstSuggestion.add(x.getProductName());
//            }
//        });

        grpItemCenterVM.forEach((k,v)->{
                if(v.getProductName().startsWith(prefix)){
                    lstSuggestion.add(v.getProductName());
                }
        });
        return lstSuggestion;
    }


    public static List<ItemCenterVM> AppendProductMarkets(List<ItemCenterVM> itemCenters){
        lstItems.addAll(itemCenters);
        return lstItems;
    }

    public  static List<ItemCenterVM> getItemCenters(int page){
        List<ItemCenterVM> itemCenters = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ItemCenterVM itemCenterVM = new ItemCenterVM();
            itemCenterVM.setMarketName("test Market " + i);
            itemCenterVM.setProductName("test Product " + i);
            itemCenterVM.setQuantity(25 + i);
            itemCenterVM.setQuantityQualifier("pieces " + i);
            itemCenterVM.setMinPrice(200 + i);
            itemCenterVM.setMaxPrice(1500 + i);
            itemCenters.add(itemCenterVM);
        }

        return  itemCenters;
    }

    public  static HashMap<Integer, ItemCenterVM> getGrpItemCenters(int page){
        HashMap<Integer, ItemCenterVM> hashIC = new HashMap<>();
        int startIndex = page * 20 + 1;
        for (int i = startIndex; i < startIndex + 20; i++) {
            ItemCenterVM itemCenterVM = new ItemCenterVM();
            itemCenterVM.setProductId("" + i);
            itemCenterVM.setMarketName("test Market " + i);
            itemCenterVM.setProductName("test Product " + i);
            itemCenterVM.setQuantity(25 + i);
            itemCenterVM.setQuantityQualifier("pieces " + i);
            itemCenterVM.setMinPrice(200 + i);
            itemCenterVM.setMaxPrice(1500 + i);

            List<ItemVarient> varients = new ArrayList<>();
            for (int j=0;j<5; j++){
                ItemVarient varient = new ItemVarient();
                varient.setVariety("Variety " + j);
                varient.setSubVariety("SubVariety " + j);
                varient.setGrade("Grade " + j);
                varient.setMinPrice(200 + i);
                varient.setMaxPrice(1500 + i);
                varients.add(varient);
            }
            itemCenterVM.setItemVarients(varients);
            hashIC.put(Integer.parseInt(itemCenterVM.getProductId()),itemCenterVM);
        }
        if(page == 0){
            grpItemCenterVM = hashIC;
        }
        grpItemCenterVM.putAll(hashIC);
        return  hashIC;
    }


    @TargetApi(Build.VERSION_CODES.O)
    public  static HashMap<Integer, ItemCenterVM> searchGrpItemCenters(String query, int page){
        HashMap<Integer, ItemCenterVM> modifiedItems = new HashMap<>();
        grpItemCenterVM.forEach((k,v)->{
            if (v.getProductName().startsWith(query)){
                modifiedItems.put(k,v);
            }
        });
        grpItemCenterVM = modifiedItems;
        return modifiedItems;
    }

    public  static List<Integer> getGrpItemIds(int page){
        return new ArrayList<Integer>(grpItemCenterVM.keySet());
    }
}
