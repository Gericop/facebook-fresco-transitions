package com.takisoft.frescotransitions.recyclerview;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.takisoft.frescotransitions.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> implements View.OnClickListener {
    private final List<Uri> images;
    private final ImageSelectListener selectListener;

    public ListAdapter(List<Uri> images, ImageSelectListener selectListener) {
        this.images = images;
        this.selectListener = selectListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(holder);

        holder.draweeView.setImageURI(images.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    @Override
    public void onClick(View view) {
        ViewHolder holder = (ViewHolder) view.getTag();
        selectListener.onItemSelected(holder);
    }

    public interface ImageSelectListener {
        void onItemSelected(ViewHolder viewHolder);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public SimpleDraweeView draweeView;

        public ViewHolder(View itemView) {
            super(itemView);
            draweeView = itemView.findViewById(R.id.image);
        }
    }
}
