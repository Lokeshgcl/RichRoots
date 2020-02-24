package com.example.richroots.Util;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.richroots.R;

public class Utility {
    public static void loadFragment(Fragment fragment, FragmentTransaction transaction,int hostId ) {
        transaction.replace(hostId, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
