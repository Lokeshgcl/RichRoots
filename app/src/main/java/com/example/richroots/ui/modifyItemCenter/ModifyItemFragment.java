package com.example.richroots.ui.modifyItemCenter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.richroots.R;

import org.apmem.tools.layouts.FlowLayout;

public class ModifyItemFragment extends Fragment implements View.OnClickListener {
    public ModifyItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_modifyitem, container, false);
        // Inflate the layout for this fragment
        loadButtons(root);
        return root;
    }

    private void loadButtons(View root){
        FlowLayout flowLayoutItems = (FlowLayout) root.findViewById(R.id.flowLayoutItems);
        for (int i=0;i<40;i++){
            Button btn = new Button(getContext());
            btn.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            btn.setText("Button test btn " + i);
            btn.setId(i);
            btn.setOnClickListener(this);
            flowLayoutItems.addView(btn);
        }

    }

    @Override
    public void onClick(View v) {
        // show a message with the button's ID
        Toast.makeText(getContext(), "you clicked " + v.getId(), Toast.LENGTH_LONG).show();

        // get the parent layout and remove the clicked button
//        LinearLayout parentLayout = (LinearLayout)v.getParent();
//        parentLayout.removeView(v);
    }

    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
