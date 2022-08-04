package ali.feyz_abadi.onlinetv.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import ali.feyz_abadi.onlinetv.R;
import ali.feyz_abadi.onlinetv.app.DbConnector;
import ali.feyz_abadi.onlinetv.app.app;
import ali.feyz_abadi.onlinetv.fragments.Favorite_fragment;
import ali.feyz_abadi.onlinetv.fragments.Radio_fragment;
import ali.feyz_abadi.onlinetv.fragments.Sat_fragment;
import ali.feyz_abadi.onlinetv.fragments.TV_fragment;

public class Home_activity extends AppCompatActivity implements View.OnClickListener {

    TextView textView_title;
    ImageView imageView_menu;
    ChipNavigationBar navigationBar;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
        onclick();
    }

    public void init() {
        textView_title = findViewById(R.id.textView_title);
        imageView_menu = findViewById(R.id.imageView_menu);
        navigationBar = findViewById(R.id.navigationBar);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        navigationView.bringToFront();


    }

    public void onclick() {
        imageView_menu.setOnClickListener(this);

        FragmentTransaction fragmentTransaction;
        TV_fragment tv_fragment=new TV_fragment();
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,tv_fragment);
        fragmentTransaction.commit();

        textView_title.setText(getString(R.string.textView_TV));

        navigationBar.setItemSelected(R.id.menu_tv,true);
        navigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                FragmentTransaction fragmentTransaction;
                switch (i)
                {
                    case R.id.menu_tv:
                        TV_fragment tv_fragment=new TV_fragment();
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frameLayout,tv_fragment);
                        fragmentTransaction.commit();

                        textView_title.setText(getString(R.string.textView_TV));
                        break;

                    case R.id.menu_sat:
                        Sat_fragment sat_fragment=new Sat_fragment();
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frameLayout,sat_fragment);
                        fragmentTransaction.commit();

                        textView_title.setText(getString(R.string.textView_sat));
                        break;

                    case R.id.menu_radio:
                        Radio_fragment radio_fragment=new Radio_fragment();
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frameLayout,radio_fragment);
                        fragmentTransaction.commit();

                        textView_title.setText(getString(R.string.textView_radio));
                        break;

                    case R.id.menu_favorite:
                        Favorite_fragment favorite_fragment=new Favorite_fragment();
                        fragmentTransaction=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frameLayout,favorite_fragment);
                        fragmentTransaction.commit();

                        textView_title.setText(getString(R.string.textView_favorite));
                        break;
                }
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_telegram:
                        Intent intentTelegram=new Intent(Intent.ACTION_VIEW);
                        intentTelegram.setData(Uri.parse("https://t.me/ali_679"));
                        startActivity(intentTelegram);
                        break;
                    case R.id.menu_rate:
                        app.toast(getString(R.string.toast_notInMarket));
                        break;
                    case R.id.menu_share:
                        Intent intentShare=new Intent();
                        intentShare.setAction(Intent.ACTION_SEND);
                        intentShare.putExtra(Intent.EXTRA_TEXT,"جهت دریافت برنامه به آیدی تلگرام زیر پیام بدید\n" +
                                "https://t.me/ali_679");
                        intentShare.setType("text/plain");

                        Intent intent=Intent.createChooser(intentShare,"Send");
                        startActivity(intent);


                        break;
                    case R.id.menu_aboutUs:
                        Intent intentAboutUs=new Intent(Home_activity.this,AboutUs_activity.class);
                        startActivity(intentAboutUs);
                        break;
                    case R.id.menu_exit:
                        if(drawerLayout.isDrawerOpen(Gravity.RIGHT))
                            drawerLayout.closeDrawers();
                        finish();
                        break;
                }

                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v==imageView_menu)
        {
            if(drawerLayout.isDrawerOpen(Gravity.RIGHT))
            {
                drawerLayout.closeDrawers();
            }
            else
            {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(Gravity.RIGHT))
            drawerLayout.closeDrawers();
        super.onBackPressed();
    }

}