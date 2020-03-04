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
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.richroots.Adapter.AutoCompleteAdapter;
import com.example.richroots.Adapter.CustomAdapter;
import com.example.richroots.Adapter.ViewPagerAdapter;

import com.example.richroots.CenterFragment;
import com.example.richroots.ItemFragment;
import com.example.richroots.R;
import com.example.richroots.Service.HomeService;
import com.example.richroots.Util.EndlessRecyclerViewScrollListener;
import com.example.richroots.ViewModel.ItemCenterVM;
import com.example.richroots.databinding.FragmentHomeBinding;
import com.example.richroots.databinding.SearchLayoutBinding;
import com.google.android.material.tabs.TabLayout;


import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private static final String TAG = "MainActivity";
    private HomeViewModel homeViewModel;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    List<ItemCenterVM> lstItemCenterVM;
    private CustomAdapter customAdapter;
    private EndlessRecyclerViewScrollListener scrollListener;
    private AutoCompleteAdapter autoCompleteAdapter;
    private FragmentHomeBinding fragmentHomeBinding;
    SearchView searchView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);

        fragmentHomeBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_home, container, false);
//        View root = inflater.inflate(R.layout.fragment_home, container, false);
        View root = fragmentHomeBinding.getRoot();

        initButtons(root);
        initRecView(root);
        initTabLayout(root);
        searchView = (SearchView) root.findViewById(R.id.homeSearch);
        autoCompleteAdapter = new AutoCompleteAdapter(getContext(), android.R.layout.simple_dropdown_item_1line, android.R.id.text1, new ArrayList<String>());
        final SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete) searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchAutoComplete.setTextColor(Color.BLACK);
        searchAutoComplete.setDropDownBackgroundResource(android.R.color.holo_blue_light);

        searchAutoComplete.setAdapter(autoCompleteAdapter);
        fragmentHomeBinding.homeSearch.setActivated(true);
        fragmentHomeBinding.homeSearch.setQueryHint("Type your keyword here");
        fragmentHomeBinding.homeSearch.onActionViewExpanded();
        fragmentHomeBinding.homeSearch.setIconified(false);
        fragmentHomeBinding.homeSearch.clearFocus();

        searchAutoComplete.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
//                ViewGroup.LayoutParams layoutParams = searchView.getLayoutParams();
//                if (b){
//                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//                }else{
//                    layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics());
//                }
//                view.setLayoutParams(layoutParams);
            }
        });

        searchAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String queryString=(String)parent.getItemAtPosition(position);
                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                alertDialog.setMessage("Search keyword is " + queryString);
                alertDialog.show();
                searchAutoComplete.setText("" + queryString);
                Toast.makeText(getContext(), "you clicked " + queryString, Toast.LENGTH_LONG).show();
            }
        });

        fragmentHomeBinding.homeSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                alertDialog.setMessage("Search keyword is " + query);
                alertDialog.show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                autoCompleteAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return root;
    }

    private void initButtons(View root) {

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

        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                Log.v(" loading more Data ", " have to load more Items page: " + page + " totalItemsCount: " + totalItemsCount);
                loadNextDataFromApi(page, totalItemsCount, view);
            }
        };

        rView.addOnScrollListener(scrollListener);

        lstItemCenterVM = new ArrayList<ItemCenterVM>();
        for (int i = 0; i<20; i++){
            ItemCenterVM itemCenterVM = new ItemCenterVM();
            itemCenterVM.setMarketName("test Market " + i);
            itemCenterVM.setProductName("test Product " + i);
            itemCenterVM.setQuantity(25 + i);
            itemCenterVM.setQuantityQualifier("pieces " + i);
            itemCenterVM.setMinPrice(200 + i);
            itemCenterVM.setMaxPrice(1500 + i);
            lstItemCenterVM.add(itemCenterVM);
        }

        customAdapter = new CustomAdapter(getContext(), lstItemCenterVM);
        rView.setAdapter(customAdapter);
    }

    public void loadNextDataFromApi(int offset, int totalItemsCount, RecyclerView view) {
        Log.v(" loading ", "loading more Items " + offset);
        Log.v(" totalItemsCount ", " Items " + totalItemsCount);
        Log.v("adapter.getItemCount ", " Items " + customAdapter.getItemCount());
        final int curSize = customAdapter.getItemCount();
        int start = offset * 20;
        List<ItemCenterVM> items = new ArrayList<ItemCenterVM>();
        for (int i = start; i < start + 20; i++) {
            ItemCenterVM itemCenterVM = new ItemCenterVM();
            itemCenterVM.setMarketName("test Market " + i);
            itemCenterVM.setProductName("test Product " + i);
            itemCenterVM.setQuantity(25 + i);
            itemCenterVM.setQuantityQualifier("pieces " + i);
            itemCenterVM.setMinPrice(200 + i);
            itemCenterVM.setMaxPrice(1500 + i);
            items.add(itemCenterVM);
        }
        lstItemCenterVM.addAll(items);
        HomeService.setLstItems(lstItemCenterVM);
        view.post(new Runnable() {
            @Override
            public void run() {
                customAdapter.notifyItemRangeInserted(curSize, lstItemCenterVM.size() - 1);
            }
        });
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