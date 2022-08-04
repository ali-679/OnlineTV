package ali.feyz_abadi.onlinetv.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ali.feyz_abadi.onlinetv.R;
import ali.feyz_abadi.onlinetv.adapters.Channels_adapter;
import ali.feyz_abadi.onlinetv.app.app;
import ali.feyz_abadi.onlinetv.models.Channels_model;
import ali.feyz_abadi.onlinetv.retrofit.Retrofit_client;
import ali.feyz_abadi.onlinetv.retrofit.IRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Channels_activity extends AppCompatActivity implements View.OnClickListener {

    IRetrofit retrofit_interface;

    List<Channels_model> list;
    Channels_adapter adapter;

    RelativeLayout appbar;
    RecyclerView recyclreView_channels;
    ImageView imageView_back;
    TextView textView_title;

    NestedScrollView nestedScrollView;


    int id;
    String name, type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channels);

        getExtras();
        init();
        getChannels();
        onclick();
    }

    private void getExtras() {
        id = getIntent().getIntExtra("id", 0);
        name = getIntent().getStringExtra("name");
        type = getIntent().getStringExtra("type");
    }

    private void init() {
//        appbar = findViewById(R.id.appBar);
        recyclreView_channels = findViewById(R.id.recyclreView_channels);
        imageView_back = findViewById(R.id.imageView_back);
        textView_title = findViewById(R.id.textView_title);
        nestedScrollView = findViewById(R.id.nestedScrollView);
        appbar = findViewById(R.id.appbar);
        list = new ArrayList<>();
        adapter = new Channels_adapter(list, this);

        recyclreView_channels.setAdapter(adapter);
        recyclreView_channels.setLayoutManager(new LinearLayoutManager(this));


        retrofit_interface = Retrofit_client.getRetrofit(app.BASE_URL).create(IRetrofit.class);


        textView_title.setText(name);
    }

    private void getChannels() {
        retrofit_interface.getChannels(id).enqueue(new Callback<List<Channels_model>>() {
            @Override
            public void onResponse(Call<List<Channels_model>> call, Response<List<Channels_model>> response) {

                list.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Channels_model>> call, Throwable t) {
                app.toast(getString(R.string.toast_ErrorNet));
            }
        });
    }

    private void onclick() {
        imageView_back.setOnClickListener(this);
        nestedScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY < oldScrollY)
                    animateAppBar(false);
                else if(scrollY > oldScrollY)
                    animateAppBar(true);
            }
        });
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    boolean isAppBarHide = false ;

    private void animateAppBar(boolean hide) {
        if (isAppBarHide && hide || !isAppBarHide && !hide)
            return;

        isAppBarHide = hide ;

        int moveY ;
        if (hide)
            moveY = -(2 * appbar.getHeight()) ;
        else
            moveY = 0 ;

        appbar.animate().translationY(moveY).setStartDelay(100).setDuration(300).start() ;
    }
}