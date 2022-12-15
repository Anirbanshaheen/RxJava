package com.example.rxjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rxjava.Model.PostModel;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    Context context;
    List<PostModel> postModelList;

    public PostAdapter(Context context, List<PostModel> postModelList) {
        this.context = context;
        this.postModelList = postModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView1.setText(postModelList.get(position).title);
        holder.textView2.setText(String.valueOf(postModelList.get(position).userId));
        holder.textView3.setText(new StringBuilder(postModelList.get(position).body.substring(0, 20))
                .append("...").toString());
    }

    @Override
    public int getItemCount() {
        return postModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1, textView2, textView3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.textTitle);
            textView2 = itemView.findViewById(R.id.textContent);
            textView3 = itemView.findViewById(R.id.textAuthor);
        }
    }
}
