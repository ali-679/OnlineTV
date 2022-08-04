package ali.feyz_abadi.onlinetv.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import ali.feyz_abadi.onlinetv.R;

public class AboutUs_activity extends AppCompatActivity implements View.OnClickListener {

    ImageView imageView_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        init();
        onClick();

    }

    private void init()
    {
        imageView_back=findViewById(R.id.imageView_back);
    }

    private void onClick() {
        imageView_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==imageView_back)
        {
            finish();
        }
    }
}