package com.macjiji.marcus.menuswithtabfragmentjava;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;



public class MainActivity extends AppCompatActivity {

    protected FragmentHome fragmentHome;
    protected FragmentAccount fragmentAccount;
    protected FragmentSettings fragmentSettings;

    protected TabLayout tabLayout;
    protected ViewPager viewPager;
    protected LinearLayout.LayoutParams layoutParamsSelected, layoutParamsDefault;
    protected View viewHome, viewAccount, viewSettings;


    private int[] tabIcons = {
            R.drawable.custom_tab_icon_home,
            R.drawable.custom_tab_icon_account,
            R.drawable.custom_tab_icon_settings
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialiseLayoutParams();
        initialiseTabLayout();
    }


    @SuppressWarnings("ConstantConditions")
    private void setupTabIcons() {

        viewHome = getLayoutInflater().inflate(R.layout.custom_tab_icon, tabLayout, false);
        viewHome.findViewById(R.id.icon).setBackgroundResource(tabIcons[0]);
        viewHome.setLayoutParams(layoutParamsSelected);

        viewAccount = getLayoutInflater().inflate(R.layout.custom_tab_icon, tabLayout, false);
        viewAccount.findViewById(R.id.icon).setBackgroundResource(tabIcons[1]);
        viewAccount.setLayoutParams(layoutParamsDefault);

        viewSettings = getLayoutInflater().inflate(R.layout.custom_tab_icon, tabLayout, false);
        viewSettings.findViewById(R.id.icon).setBackgroundResource(tabIcons[2]);
        viewSettings.setLayoutParams(layoutParamsDefault);

        if (tabLayout != null) {
            tabLayout.getTabAt(0).setCustomView(viewHome);
            tabLayout.getTabAt(1).setCustomView(viewAccount);
            tabLayout.getTabAt(2).setCustomView(viewSettings);
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                changeTabSelected(tab.getCustomView());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                changeTabDefault(tab.getCustomView());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void createViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        fragmentHome = new FragmentHome();
        fragmentAccount = new FragmentAccount();
        fragmentSettings = new FragmentSettings();
        adapter.addFrag(fragmentHome);
        adapter.addFrag(fragmentAccount);
        adapter.addFrag(fragmentSettings);
        viewPager.setAdapter(adapter);

        viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

    }


    /**
     * Méthode permettant d'initialiser le ViewPager et la TabLayout.
     */
    private void initialiseTabLayout() {
        tabLayout = (TabLayout) findViewById(R.id.main_tabs);
        viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        viewPager.setDrawingCacheEnabled(true);
        createViewPager(viewPager);
    }

    /**
     * Méthode permettant d'initialiser les paramètres de taille pour les icones.
     */
    private void initialiseLayoutParams() {
        layoutParamsSelected = new LinearLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen.value_50dp), getResources().getDimensionPixelSize(R.dimen.value_50dp));
        layoutParamsDefault = new LinearLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen.value_30dp), getResources().getDimensionPixelSize(R.dimen.value_30dp));
    }


    /**
     * Méthode permettant de changer la vue présent dans la barre de navigation. Lorsqu'elle est sélectionnée, elle s'agrandit.
     *
     * @param view l'icone à agrandir.
     */
    private void changeTabSelected(View view) {
        view.setLayoutParams(layoutParamsSelected);
    }

    /**
     * Méthode permettant de réduire la taille d'une icone lorsqu'elle n'est plus sélectionnée.
     *
     * @param view l'icone à réduire.
     */
    private void changeTabDefault(View view) {
        view.setLayoutParams(layoutParamsDefault);
    }




}
