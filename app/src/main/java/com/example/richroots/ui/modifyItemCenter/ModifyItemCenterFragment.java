package com.example.richroots.ui.modifyItemCenter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.richroots.Adapter.ViewPagerAdapter;
import com.example.richroots.CenterFragment;
import com.example.richroots.ItemFragment;
import com.example.richroots.R;
import com.google.android.material.tabs.TabLayout;

public class ModifyItemCenterFragment extends Fragment {

    private ModifyICFViewModel changeViewModel;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        changeViewModel =
                ViewModelProviders.of(this).get(ModifyICFViewModel.class);
        View root = inflater.inflate(R.layout.fragment_modifyitemcenter, container, false);

        changeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });

        initTabLayout(root);

        return root;
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