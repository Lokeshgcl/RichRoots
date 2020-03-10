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

import android.annotation.TargetApi;
import android.content.ClipData;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.richroots.Adapter.AutoCompleteAdapter;
import com.example.richroots.Adapter.CustomAdapter;
import com.example.richroots.Adapter.CustomExpandableListAdapter;
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
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class HomeFragment extends Fragment {

    private static final String TAG = "MainActivity";
    private HomeViewModel homeViewModel;
    private AutoCompleteAdapter autoCompleteAdapter;
    private FragmentHomeBinding fragmentHomeBinding;
    SearchView searchView;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    private HashMap<Integer, ItemCenterVM> grpItemCenterVM;
    List<Integer> grpItemIds;
    CustomExpandableListAdapter customExpandableListAdapter;
    SearchView.SearchAutoComplete searchAutoComplete;
    int page = 0;

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
        initHomeSearch(root);

        expandableListView = (ExpandableListView) root.findViewById(R.id.expandableListView);
        grpItemCenterVM = HomeService.getGrpItemCenters(0);
        grpItemIds = HomeService.getGrpItemIds(0);

        customExpandableListAdapter = new CustomExpandableListAdapter(getContext(), grpItemCenterVM, grpItemIds);
        expandableListAdapter = customExpandableListAdapter;
        expandableListView.setAdapter(expandableListAdapter);

        NestedScrollView scroller = (NestedScrollView) root.findViewById(R.id.nestedScrollHome);
        if (scroller != null) {

            scroller.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                @Override
                public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    View view = (View) v.getChildAt(v.getChildCount() - 1);
                    if (scrollY > oldScrollY) {
//                        Log.i("TAG", "Scroll DOWN ScrollY " + scrollY);
                    }
                    if (scrollY < oldScrollY) {
//                        Log.i("TAG", "Scroll Up ScrollY " + scrollY);
                    }

                    if (scrollY == 0) {
//                        Log.i("TAG", "TOP SCROLL ");
                    }

                    if (view.getBottom() == scrollY + v.getMeasuredHeight()) {
                        Log.i("TAG", "BOTTOM SCROLL");
                        page = page + 1;
                        loadNextDataFromApi(page,20,expandableListView);
                    }
                }
            });
        }

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getContext(),
                        grpItemIds.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getContext(),
                        grpItemIds.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getContext(),
                        grpItemIds.get(groupPosition)
                                + " -> "
                                + grpItemCenterVM.get(
                                grpItemIds.get(groupPosition)).getItemVarients().get(childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });

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
            @TargetApi(Build.VERSION_CODES.O)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String query=(String)parent.getItemAtPosition(position);
                SearchItemCenter(query);
            }
        });

        fragmentHomeBinding.homeSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            @TargetApi(Build.VERSION_CODES.O)
            public boolean onQueryTextSubmit(String query) {
//                List<ItemCenterVM> searchedItems = mainItemCenters.stream().filter(x-> x.getProductName().startsWith(query) ||
//                                    x.getMarketName().startsWith(query)).collect(Collectors.toList());
//                for (ItemCenterVM item : searchedItems){
//                    Log.i("items :" , item.getProductName());
//                }
//                customExpandableListAdapter.notifyDataSetInvalidated();
//                mainItemCenters = searchedItems;
//                customExpandableListAdapter.notifyDataSetChanged();
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

    private void initHomeSearch(View root){
        searchView = (SearchView) root.findViewById(R.id.homeSearch);
        autoCompleteAdapter = new AutoCompleteAdapter(getContext(), android.R.layout.simple_dropdown_item_1line, android.R.id.text1, new ArrayList<String>());
        searchAutoComplete = (SearchView.SearchAutoComplete) searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchAutoComplete.setTextColor(Color.BLACK);
        searchAutoComplete.setDropDownBackgroundResource(android.R.color.holo_blue_light);

        searchAutoComplete.setAdapter(autoCompleteAdapter);
        fragmentHomeBinding.homeSearch.setActivated(true);
        fragmentHomeBinding.homeSearch.setQueryHint("Type your keyword here");
        fragmentHomeBinding.homeSearch.onActionViewExpanded();
        fragmentHomeBinding.homeSearch.setIconified(false);
        fragmentHomeBinding.homeSearch.clearFocus();
    }

    //method for initializing recycler view
    private void initRecView(View root) {

    }

    @TargetApi(Build.VERSION_CODES.O)
    public void loadNextDataFromApi(int offset, int totalItemsCount, ExpandableListView view) {
        Log.v(" loading ", "loading more Items " + offset);
        Log.v("adapter.getGroupCount ", " Items " + customExpandableListAdapter.getGroupCount());

        grpItemCenterVM.putAll(HomeService.getGrpItemCenters(offset));
        grpItemCenterVM.forEach((k,v)->
                Log.i("v.getProductName() " , v.getProductName())
        );
        List<Integer> itemIds = HomeService.getGrpItemIds(0).stream().filter(x->
                !grpItemIds.contains(x)).collect(Collectors.toList());
        itemIds.forEach(x-> Log.v("Items " , "" + x));
        grpItemIds.addAll(itemIds);
        customExpandableListAdapter.notifyDataSetChanged();
        Log.v("adapter.getGroupCount ", "after Items " + customExpandableListAdapter.getGroupCount());
    }

    public  void  SearchItemCenter(String query){
        HashMap<Integer, ItemCenterVM> searchGrpItems;
        List<Integer> searchGrpIds;
        searchGrpItems = HomeService.searchGrpItemCenters(query,0);
        searchGrpIds = HomeService.getGrpItemIds(0);
        grpItemCenterVM.clear();
        grpItemIds.clear();
        grpItemCenterVM.putAll(searchGrpItems);
        grpItemIds.addAll(searchGrpIds);
        customExpandableListAdapter.notifyDataSetChanged();
    }

}