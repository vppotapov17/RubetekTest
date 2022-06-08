package com.example.rubetektest.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rubetektest.R;
import com.example.rubetektest.models.camera;

import java.util.List;

public class CamerasAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<camera> cameraList;

    public CamerasAdapter(List<camera> cameraList){
        this.cameraList = cameraList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_camera, parent, false)) {};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        // инициализация элементов CardView
        TextView name = holder.itemView.findViewById(R.id.name);
        ImageView snapshot = holder.itemView.findViewById(R.id.snapshot);
        ImageView rec = holder.itemView.findViewById(R.id.rec);
        ImageView favorites = holder.itemView.findViewById(R.id.favorites);


        camera current_camera = cameraList.get(position);

        name.setText(current_camera.getName());

        Glide
                .with(holder.itemView)
                .load(current_camera.getSnapshot())
                .into(snapshot);

        if (current_camera.isRec()) rec.setVisibility(View.VISIBLE);
        else rec.setVisibility(View.INVISIBLE);

        if (current_camera.isFavorites()) favorites.setVisibility(View.VISIBLE);
        else favorites.setVisibility(View.INVISIBLE);



    }

    @Override
    public int getItemCount() {
        return cameraList.size();
    }
}
