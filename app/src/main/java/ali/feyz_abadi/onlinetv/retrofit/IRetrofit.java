package ali.feyz_abadi.onlinetv.retrofit;

import java.util.List;

import ali.feyz_abadi.onlinetv.models.Category_model;
import ali.feyz_abadi.onlinetv.models.Channels_model;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IRetrofit {
    @POST("get_category.php")
    Call<List<Category_model>> getCategory(@Query("key") String key) ;

    @POST("get_channels.php")
    Call<List<Channels_model>> getChannels(@Query("category_id") int category_id);
}
