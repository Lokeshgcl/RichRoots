package com.example.richroots.ui.profile;

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

import com.example.richroots.R;
import com.example.richroots.ui.notifications.NotificationsViewModel;

public class ProfileFragment extends Fragment {
    private ProfileVM profileVM;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileVM =
                ViewModelProviders.of(this).get(ProfileVM.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        profileVM.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }
}
