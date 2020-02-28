package com.example.richroots;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.richroots.ui.home.HomeFragment;
import com.example.richroots.ui.modifyItemCenter.ModifyCenterFragment;
import com.example.richroots.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private ActionBar toolbarMain;
//    private MainActivityBinding mainActivityBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        DataBindingUtil
        toolbarMain = getSupportActionBar();
        toolbarMain.setTitle("Rich Roots");
        // load the store fragment by default
//        toolbarMain.setTitle("Home 11");
//        loadFragment(new HomeFragment());
//
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_settings, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        //this.getSupportActionBar().hide();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbarMain.setTitle("Shop");
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_Preference:
                    toolbarMain.setTitle("My Gifts");
                    fragment = new ModifyCenterFragment();
                    loadFragment(fragment);
                    return true;
//                case R.id.navigation_Profile:
//                    toolbarMain.setTitle("Cart");
//                    fragment = new NotificationsFragment();
//                    loadFragment(fragment);
//                    return true;
            }
            return false;
        }
    };

    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
