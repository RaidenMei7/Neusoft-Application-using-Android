package com.example.zyfypt_no7_406ml.adapters;

import android.annotation.SuppressLint;
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
import com.example.zyfypt_no7_406ml.activities.ViewArticleActivity;
import com.example.zyfypt_no7_406ml.bean.Article;
import com.example.zyfypt_no7_406ml.common.Common;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Article> list;//数据源
    private Context context;//上下文
    private LayoutInflater layoutInflater;//动态布局


    public ArticleAdapter(Context context) {
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
    }

    public void setList(List<Article> list) {
        this.list=list;
        notifyDataSetChanged();//通知RV刷新数据
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
    //创建ViewHolder
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.article_item,parent,false);
        return new ViewHolder(v);
    }
    //用于捆绑信息，设置ViewHolder里的控件信息
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Article article = list.get(position);
        if (article == null)  return;
        ViewHolder viewHolder = (ViewHolder) holder;
        Picasso.with(context)//新版的Picasso方法改为get()
                .load(Common.IMAGEURL+article.getThumb())
                .placeholder(R.mipmap.ic_launcher)
                .into(viewHolder.imageView);
        viewHolder.tvtitle.setText(article.getName());
        viewHolder.tvauthor.setText(article.getAuthor());
        viewHolder.tvtime.setText(article.getUpdate_time());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //取出当前item的id
                int id=article.getId();
                Toast.makeText(context, ""+id, Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(context, ViewArticleActivity.class);
                intent.putExtra("resid",article.getId());
                intent.putExtra("userid",article.getUserid());
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
