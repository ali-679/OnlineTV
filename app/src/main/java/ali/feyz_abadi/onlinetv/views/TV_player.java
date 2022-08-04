package ali.feyz_abadi.onlinetv.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.VideoView;

import ali.feyz_abadi.onlinetv.R;

public class TV_player extends AppCompatActivity implements View.OnClickListener {

    String link;

    VideoView videoView;
    ProgressBar progressBar;
    ImageView imageView_fullScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tv_player);

        getExtras();
        init();
        onClick();

    }

    private void getExtras() {
        link = getIntent().getStringExtra("link");
    }

    private void init()
    {
        videoView=findViewById(R.id.videoView);
        progressBar=findViewById(R.id.progressBar);
        imageView_fullScreen=findViewById(R.id.imageView_fullScreen);

        videoView.setVideoURI(Uri.parse(link));
        videoView.start();

        videoView.setOnPreparedListener(new OnPreparedListener() {
            @Override
            public void onPrepared() {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void onClick() {
        imageView_fullScreen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == imageView_fullScreen)
        {
            orientation();
        }
    }

    private void orientation()
    {
        if(getResources().getConfiguration().orientation== Surface.ROTATION_180)//portrait
        {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        else if(getResources().getConfiguration().orientation== Surface.ROTATION_90)//landscape
        {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }
}