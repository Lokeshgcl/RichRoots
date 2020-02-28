//package com.example.richroots.ui.home;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.Nullable;
//import androidx.annotation.NonNull;
//import androidx.appcompat.widget.Toolbar;
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.Observer;
//import androidx.lifecycle.ViewModelProviders;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.richroots.Adapter.RecyclerViewAdapter;
//import com.example.richroots.R;
//
//import java.util.ArrayList;
//
//public class HomeFragment extends Fragment {
//
//    private HomeViewModel homeViewModel;
//
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel =
//                ViewModelProviders.of(this).get(HomeViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//        return root;
//    }
//}
//
package com.example.richroots.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.richroots.Adapter.CustomAdapter;
import com.example.richroots.Adapter.ViewPagerAdapter;

import com.example.richroots.CenterFragment;
import com.example.richroots.ItemFragment;
import com.example.richroots.R;
import com.example.richroots.ViewModel.ItemCenterVM;
import com.google.android.material.tabs.TabLayout;


import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private static final String TAG = "MainActivity";
    private HomeViewModel homeViewModel;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    String countryList[] = {"India", "China", "australia", "Portugle", "America", "NewZealand", "Srilanka", "Nepal"};
    int flags[] = {R.drawable.fruits, R.drawable.fruits, R.drawable.fruits, R.drawable.fruits, R.drawable.fruits,
            R.drawable.fruits, R.drawable.fruits, R.drawable.fruits};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        initButtons(root);
        initRecView(root);
        initTabLayout(root);
        final SearchView searchView = (SearchView) root.findViewById(R.id.search);
        final SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete)searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchAutoComplete.setTextColor(Color.GREEN);
        searchAutoComplete.setDropDownBackgroundResource(android.R.color.holo_blue_light);

        return root;
    }

    private void  initButtons(View root){

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(new ItemFragment(), "Item");
        adapter.addFragment(new CenterFragment(), "Center");
        viewPager.setAdapter(adapter);
    }

    private void initTabLayout(View root) {
//        viewPager = (ViewPager) root.findViewById(R.id.viewpager);
//        setupViewPager(viewPager);
//
//        if (tabLayout == null) {
//            tabLayout = (TabLayout) root.findViewById(R.id.tabs);
//            tabLayout.setupWithViewPager(viewPager);
//        }
//        if (((AppCompatActivity)getActivity()) != null && ((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
//            ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
//            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        }
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
    }

    //method for initializing recycler view
    private void initRecView(View root) {
        RecyclerView rView = (RecyclerView) root.findViewById(R.id.recyclerView);
        rView.invalidate();
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rView.setLayoutManager(layoutManager);

        //Here code
        List<ItemCenterVM> lstItemCenterVM = new ArrayList<ItemCenterVM>();
        ItemCenterVM itemCenterVM = new ItemCenterVM();
        itemCenterVM.setMarketName("test Market");
        itemCenterVM.setProductName("test Product");
        itemCenterVM.setQuantity(25);
        itemCenterVM.setQuantityQualifier("pieces");
        itemCenterVM.setMinPrice(200);
        itemCenterVM.setMaxPrice(1500);
        lstItemCenterVM.add(itemCenterVM);
        CustomAdapter customAdapter = new CustomAdapter(getContext(), lstItemCenterVM);
        rView.setAdapter(customAdapter);

    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void addDummmyData(ArrayList<String> mDataList) {
        for (int i = 0; i < 100; i++) {
            mDataList.add("Item " + i);
        }
    }
}