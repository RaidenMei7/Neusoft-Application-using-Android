package com.example.zyfypt_no7_406ml.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zyfypt_no7_406ml.R;
import com.example.zyfypt_no7_406ml.activities.ViewVideoActivity;
import com.example.zyfypt_no7_406ml.bean.Video;
import com.example.zyfypt_no7_406ml.common.Common;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Latest_VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Video> list;
    private Context context;

    public Latest_VideoAdapter(List<Video> list, Context context) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item, parent, false);//用来获得中载ViewHolder inflatte 解析
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Video video = list.get(position);
        if (video == null) return;
        Latest_VideoAdapter.ViewHolder viewHolder = (Latest_VideoAdapter.ViewHolder) holder;
        Picasso.with(context)
                .load(Common.IMAGEURL + video.getThumb())
                .placeholder(R.mipmap.ic_launcher)
                .into(viewHolder.imageView);
        viewHolder.tvtitle.setText(video.getName());
        viewHolder.tvauthor.setText(video.getAuthor());
        viewHolder.tvtime.setText(video.getUpdate_time());
        //item条目点击事件
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ViewVideoActivity.class);
                intent.putExtra("videopath", video.getVideopath());
                intent.putExtra("userid",video.getUserid());
                intent.putExtra("resid",video.getId());
                context.startActivity(intent);

            }
        });
    }

    @Override
    //执行多少次，数据的条目，进行执行
    public int getItemCount() {
        if (list != null)
            return list.size();
        else
            return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvtitle, tvauthor, tvtime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tvtitle = itemView.findViewById(R.id.title);
            tvauthor = itemView.findViewById(R.id.author);
            tvtime = itemView.findViewById(R.id.time);
        }
    }
}
