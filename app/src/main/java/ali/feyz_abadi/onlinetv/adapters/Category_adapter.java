package ali.feyz_abadi.onlinetv.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import ali.feyz_abadi.onlinetv.R;
import ali.feyz_abadi.onlinetv.models.Category_model;
import ali.feyz_abadi.onlinetv.views.Channels_activity;

public class Category_adapter extends RecyclerView.Adapter<Category_adapter.MyViewHolder> {

    List<Category_model> list;
    Context context;

    public Category_adapter(List<Category_model> list, Context context) {
        this.list = list;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.items_category,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Category_model lists=list.get(position);

        Glide.with(context).load(lists.getImg_link()).into(holder.imageView);
        holder.textView_title.setText(lists.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout layout;
        ImageView imageView;
        TextView textView_title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.layout);
            imageView = itemView.findViewById(R.id.imageView);
            textView_title = itemView.findViewById(R.id.textView_title);

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Channels_activity.class);
                    intent.putExtra("id",list.get(getAdapterPosition()).getId());
                    intent.putExtra("name",list.get(getAdapterPosition()).getName());
                    intent.putExtra("img_link",list.get(getAdapterPosition()).getImg_link());
                    intent.putExtra("type",list.get(getAdapterPosition()).getType());
                    context.startActivity(intent);

                }
            });

        }
    }

}
