package ali.feyz_abadi.onlinetv.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ali.feyz_abadi.onlinetv.R;
import ali.feyz_abadi.onlinetv.adapters.Category_adapter;
import ali.feyz_abadi.onlinetv.app.app;
import ali.feyz_abadi.onlinetv.models.Category_model;
import ali.feyz_abadi.onlinetv.retrofit.Retrofit_client;
import ali.feyz_abadi.onlinetv.retrofit.IRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Radio_fragment extends Fragment {

    IRetrofit iRetrofit;

    RecyclerView recyclerView_cat;

    List<Category_model> list;

    Category_adapter adapter;

    LinearLayout layout_net;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_radio,container,false);


        iRetrofit = Retrofit_client.getRetrofit(app.BASE_URL).create(IRetrofit.class);


        layout_net=view.findViewById(R.id.layout_net);
        recyclerView_cat=view.findViewById(R.id.recyclerView_cat);

        if(app.isConnected())
        {
            layout_net.setVisibility(View.GONE);
            recyclerView_cat.setVisibility(View.VISIBLE);
        }
        else
        {
            recyclerView_cat.setVisibility(View.GONE);
            layout_net.setVisibility(View.VISIBLE);
        }

        list = new ArrayList<>();
        adapter=new Category_adapter(list,getContext());
        recyclerView_cat.setAdapter(adapter);
        recyclerView_cat.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView_cat.setHasFixedSize(true);

        getData();


        return view;
    }

    private void getData()
    {
        iRetrofit.getCategory("radio").enqueue(new Callback<List<Category_model>>() {
            @Override
            public void onResponse(Call<List<Category_model>> call, Response<List<Category_model>> response) {
                list.addAll(response.body());

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Category_model>> call, Throwable t) {
                layout_net.setVisibility(View.VISIBLE);
                app.log(t.getMessage());
            }
        });
    }


}
