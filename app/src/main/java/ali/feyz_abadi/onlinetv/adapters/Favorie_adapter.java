package ali.feyz_abadi.onlinetv.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import ali.feyz_abadi.onlinetv.R;
import ali.feyz_abadi.onlinetv.app.DbConnector;
import ali.feyz_abadi.onlinetv.app.app;
import ali.feyz_abadi.onlinetv.models.Channels_model;
import ali.feyz_abadi.onlinetv.views.Radio_player;
import ali.feyz_abadi.onlinetv.views.TV_player;

public class Favorie_adapter extends RecyclerView.Adapter<Favorie_adapter.MyViewHolder> {

    List<Channels_model> list;
    Context context;

    public Favorie_adapter(List<Channels_model> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.items_channel,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        Channels_model lists = list.get(position);
        Glide.with(context).load(lists.getImg_link()).into(holder.imageView);
        holder.textView_channelName.setText(lists.getName());
        holder.textView_description.setText(lists.getCategory());
        holder.imageView_favorite.setImageResource(R.drawable.ic_baseline_favorite_24);


        DbConnector dbConnector=new DbConnector(context,null);


        holder.imageView_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String where = app.ID+"=?";
                String[] whereArgs={lists.getId()+""};
                dbConnector.getSqLiteDatabase().delete(app.TB_favorite,where,whereArgs);

                list.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,list.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout layout;
        ImageView imageView,imageView_favorite;
        TextView textView_channelName,textView_description;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);



            layout=itemView.findViewById(R.id.layout);
            imageView=itemView.findViewById(R.id.imageView);
            imageView_favorite=itemView.findViewById(R.id.imageView_favorite);
            textView_channelName=itemView.findViewById(R.id.textView_channelName);
            textView_description=itemView.findViewById(R.id.textView_description);

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(list.get(getAdapterPosition()).getType().equals("radio"))
                    {
                        Intent intent=new Intent(context, Radio_player.class);
                        intent.putExtra("link",list.get(getAdapterPosition()).getPlay_link());
                        context.startActivity(intent);
                    }
                    else
                    {
                        Intent intent=new Intent(context, TV_player.class);
                        intent.putExtra("link",list.get(getAdapterPosition()).getPlay_link());
                        context.startActivity(intent);
                    }

                }
            });


        }
    }
}
