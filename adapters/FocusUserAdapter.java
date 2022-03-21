package com.example.zyfypt_no7_406ml.adapters;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zyfypt_no7_406ml.R;
import com.example.zyfypt_no7_406ml.bean.FocusResult;
import com.example.zyfypt_no7_406ml.bean.User;
import com.example.zyfypt_no7_406ml.iface.FocusListener;
import com.example.zyfypt_no7_406ml.model.FocusModel;

import java.util.List;

public class FocusUserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<FocusResult<User>> list;//数据源
    private Context context;//上下文
    private FocusModel focusmodel;//关注model
    private SharedPreferences sp;//简单存储
    private String sessionID="";  //sessionid
    private boolean result=false;  //

    public FocusUserAdapter(Context context,List<FocusResult<User>> list) {
        this.context=context;
        this.list=list;
    }

    private FocusListener focuslistener=new FocusListener() {
        @Override
        public void onResponse(String msg) {

            //根据mode中response返回的字符串区分返回结果
            switch (msg){
                case "4":System.out.println("----取消关注成功");
                    result=true;
                    break;
                case "3":System.out.println("----取消关注失败");
                    result=false;
                    break;
                default:
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        public void onFail(String msg) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    };


    private void readSP() {
        sessionID=sp.getString("sessionID",null);
    }
    //与布局文件中的控件绑定
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tvusername,tvrealname,tvrolename;
        Button btdelfocus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv);
            tvusername = itemView.findViewById(R.id.tvusername);
            tvrealname = itemView.findViewById(R.id.tvrealname);
            tvrolename = itemView.findViewById(R.id.tvrolename);
            btdelfocus = itemView.findViewById(R.id.btdelfocus);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_user,parent,false);
        sp=context.getSharedPreferences("login",MODE_PRIVATE);
        readSP();

        return new ViewHolder(v);
    }
    //用于捆绑信息，设置ViewHolder里的控件信息
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        User user = list.get(position).getBean();
        if (user == null) return;
        FocusUserAdapter.ViewHolder viewHolder = (FocusUserAdapter.ViewHolder) holder;
        viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
        viewHolder.tvusername.setText(user.getUsername());
        viewHolder.tvrealname.setText(user.getRealname());
        viewHolder.tvrolename.setText(user.getRolename());

        //item条目点击事件
        viewHolder.btdelfocus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //取出当前item的id
                int position = viewHolder.getAdapterPosition();

                int userid =list.get(position).getBean().getId();
                Toast.makeText(context, "focus position"+position+" sessionID"+sessionID+" userid"+userid, Toast.LENGTH_SHORT).show();
                focusmodel=new FocusModel();//实例化对象
                focusmodel.unfocus("userfocus",userid, sessionID, focuslistener);
                if(result){
                    list.remove(position);
                    notifyDataSetChanged();
                }
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
