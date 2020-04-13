package com.example.viewpage3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.viewpage3.R;
import com.example.viewpage3.recycleview.text2;

import java.util.List;

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.ViewHolder> {

    private List<text2> textList;
    private StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager
            (2,StaggeredGridLayoutManager.VERTICAL);

    public TextAdapter(List<text2> textList) {
        this.textList = textList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_item2,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        text2 text = textList.get(i);
        viewHolder.textImage.setImageResource(text.getPic());
        ViewGroup.LayoutParams params = viewHolder.textImage.getLayoutParams();
        params.height = params.height ;


        viewHolder.textImage.setLayoutParams(params);

        viewHolder.textTitle.setText(text.getTitle());
    }

    @Override
    public int getItemCount() {
        return textList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView textImage;
        TextView textTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textImage = itemView.findViewById(R.id.textPic);
           textTitle = itemView.findViewById(R.id.textTitle);
        }
    }
}
