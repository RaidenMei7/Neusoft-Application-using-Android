package com.example.zyfypt_no7_406ml.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zyfypt_no7_406ml.R;
import com.example.zyfypt_no7_406ml.activities.ViewTcaseActivity;
import com.example.zyfypt_no7_406ml.bean.CollectResult;
import com.example.zyfypt_no7_406ml.bean.Tcase;
import com.example.zyfypt_no7_406ml.common.Common;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CollectTcaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<CollectResult<Tcase>> list;//数据源
    private Context context;//上下文
    public CollectTcaseAdapter(Context context, List<CollectResult<Tcase>> list) {
        this.context=context;
        this.list=list;
    }

    //与布局文件中的控件绑定
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tvtitle,tvauthor,tvtime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tvtitle = itemView.findViewById(R.id.title);
            tvauthor = itemView.findViewById(R.id.author);
            tvtime = itemView.findViewById(R.id.time);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item,parent,false);
        return new CollectTcaseAdapter.ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Tcase tcase= list.get(position).getBean();
        if (tcase == null)  return;
        CollectTcaseAdapter.ViewHolder viewHolder = (CollectTcaseAdapter.ViewHolder) holder;
        Picasso.with(context)//新版的Picasso方法改为get()
                .load(Common.IMAGEURL+tcase.getThumb())
                .placeholder(R.mipmap.ic_launcher)
                .into(viewHolder.imageView);
        viewHolder.tvtitle.setText(tcase.getName());
        viewHolder.tvauthor.setText(tcase.getAuthor());
        viewHolder.tvtime.setText(tcase.getUpdate_time());


        //item条目点击事件
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //取出当前item的id
                int id=tcase.getId();//bean需要增加final
                Toast.makeText(context, ""+id, Toast.LENGTH_SHORT).show();
                //跳转到文章详情页
                Intent intent=new Intent(context, ViewTcaseActivity.class);
                intent.putExtra("tcaseid",tcase.getId());
                intent.putExtra("userid",tcase.getUserid());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        if(list!=null)
            return list.size();
        else
            return 0;
    }
}
