package ali.feyz_abadi.onlinetv.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ali.feyz_abadi.onlinetv.R;
import ali.feyz_abadi.onlinetv.adapters.Favorie_adapter;
import ali.feyz_abadi.onlinetv.app.DbConnector;
import ali.feyz_abadi.onlinetv.app.app;
import ali.feyz_abadi.onlinetv.models.Channels_model;

public class Favorite_fragment extends Fragment {

    List<Channels_model> list;

    RecyclerView recyclreView_favorite;

    Favorie_adapter adapter;

    DbConnector dbConnector;

    LinearLayout layout_noItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_favorite,container,false);

        list =new ArrayList<>();
        recyclreView_favorite=view.findViewById(R.id.recyclreView_favorite);
        layout_noItem=view.findViewById(R.id.layout_noItem);

        adapter=new Favorie_adapter(list,getContext());
        recyclreView_favorite.setAdapter(adapter);
        recyclreView_favorite.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        recyclreView_favorite.setHasFixedSize(true);

        dbConnector=new DbConnector(getContext(),null);

        getData();

        return view;
    }

    private void getData() {
        Cursor cursor=dbConnector.getSqLiteDatabase().rawQuery(" SELECT * FROM "+app.TB_favorite,null,null);
        while(cursor.moveToNext())
        {
            Channels_model channels_model=new Channels_model();
            channels_model.setId(cursor.getInt(cursor.getColumnIndex(app.ID)));
            channels_model.setName(cursor.getString(cursor.getColumnIndex(app.NAME)));
            channels_model.setImg_link(cursor.getString(cursor.getColumnIndex(app.IMG_LINK)));
            channels_model.setType(cursor.getString(cursor.getColumnIndex(app.TYPE)));
            channels_model.setCategory_id(cursor.getString(cursor.getColumnIndex(app.CATEGORY_ID)));
            channels_model.setPlay_link(cursor.getString(cursor.getColumnIndex(app.PLAY_LINK)));
            channels_model.setCategory(cursor.getString(cursor.getColumnIndex(app.CATEGORY)));
            list.add(channels_model);
        }
        adapter.notifyDataSetChanged();

        cursor.close();

        if(list.size()==0)
        {
            layout_noItem.setVisibility(View.VISIBLE);
            recyclreView_favorite.setVisibility(View.GONE);
        }
        else
        {
            layout_noItem.setVisibility(View.GONE);
            recyclreView_favorite.setVisibility(View.VISIBLE);
        }

    }

}
