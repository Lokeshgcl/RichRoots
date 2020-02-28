package com.example.richroots.ui.modifyItemCenter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.richroots.Adapter.SearchItemAdapter;
import com.example.richroots.Adapter.ViewPagerAdapter;
import com.example.richroots.CenterFragment;
import com.example.richroots.ItemFragment;
import com.example.richroots.R;
import com.example.richroots.databinding.SearchLayoutBinding;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ModifyItemCenterFragment extends Fragment {

    private ModifyICFViewModel changeViewModel;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    SearchLayoutBinding searchLayoutBinding;
    SearchItemAdapter searchAdapter;
    List<String> arrayList= new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        searchLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.search_layout,container,false);
        changeViewModel =
                ViewModelProviders.of(this).get(ModifyICFViewModel.class);
        View root = inflater.inflate(R.layout.fragment_modifyitemcenter, container, false);

        initSearchBinding(root);

        changeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });

        initTabLayout(root);

        return root;
    }

    private  void  initSearchBinding(View root){
        arrayList.add("January");
        arrayList.add("February");
        arrayList.add("March");
        arrayList.add("April");
        arrayList.add("May");
        arrayList.add("June");
        arrayList.add("July");
        arrayList.add("August");
        arrayList.add("September");
        arrayList.add("October");
        arrayList.add("November");
        arrayList.add("December");

        searchAdapter= new SearchItemAdapter(arrayList);
        searchLayoutBinding.listView.setAdapter(searchAdapter);

        searchLayoutBinding.search.setActivated(true);
        searchLayoutBinding.search.setQueryHint("Type your keyword here");
        searchLayoutBinding.search.onActionViewExpanded();
        searchLayoutBinding.search.setIconified(false);
        searchLayoutBinding.search.clearFocus();

        searchLayoutBinding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                searchAdapter.getFilter().filter(newText);

                return false;
            }
        });
    }

    private void initTabLayout(View root) {
        viewPager = (ViewPager) root.findViewById(R.id.modifyViewpager);
        setupViewPager(viewPager);

        if (tabLayout == null) {
            tabLayout = (TabLayout) root.findViewById(R.id.modifyTabs);
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(new ModifyItemFragment(), "Selected Items");
        adapter.addFragment(new ModifyCenterFragment(), "Selected Centers");
        viewPager.setAdapter(adapter);
    }
}