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

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.richroots.Adapter.CustomAdapter;
import com.example.richroots.Adapter.RecyclerViewAdapter;
import com.example.richroots.Adapter.ViewPagerAdapter;

import com.example.richroots.CenterFragment;
import com.example.richroots.ItemFragment;
import com.example.richroots.MainActivity;
import com.example.richroots.R;
import com.example.richroots.Util.Utility;
import com.example.richroots.ui.modifyItemCenter.ModifyItemCenterFragment;
import com.google.android.material.tabs.TabLayout;


import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private static final String TAG = "MainActivity";
    private HomeViewModel homeViewModel;
    private Button modifyCenter;
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

        return root;
    }

    private void  initButtons(View root){
        modifyCenter = (Button) root.findViewById(R.id.btnModifyCenter);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(new ItemFragment(), "Item");
        adapter.addFragment(new CenterFragment(), "Center");
        viewPager.setAdapter(adapter);
    }

    private void  initButtonClicks(View root){
        this.modifyCenter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity mainActivity = new MainActivity();
                mainActivity.loadFragment(new ModifyItemCenterFragment());
            }
        });
    }

    private void initTabLayout(View root) {
        viewPager = (ViewPager) root.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        if (tabLayout == null) {
            tabLayout = (TabLayout) root.findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);
        }
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
        CustomAdapter customAdapter = new CustomAdapter(getContext(), countryList, flags);
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