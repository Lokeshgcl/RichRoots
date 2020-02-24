package com.example.richroots;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.richroots.ui.modifyItemCenter.ModifyItemCenterFragment;

public class ItemFragment extends Fragment {
    public ItemFragment() {
        // Required empty public constructor
    }

    private Button modifyCenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootIF = inflater.inflate(R.layout.fragment_item, container, false);
        modifyCenter = (Button) rootIF.findViewById(R.id.btnModifyCenter);
        this.modifyCenter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loadFragment(new ModifyItemCenterFragment());
            }
        });

        // Inflate the layout for this fragment
        return rootIF;
    }

    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
