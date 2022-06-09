package com.example.rubetektest.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rubetektest.DomophoneActivity;
import com.example.rubetektest.R;
import com.example.rubetektest.models.door;

import java.io.Serializable;
import java.util.List;

public class DoorsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // список дверей
    List<door> doorList;

    public DoorsAdapter (List<door> doorList)
    {
        this.doorList = doorList;
    }

    // определение ViewType
    @Override
    public int getItemViewType(int position) {
        if (doorList.get(position).getSnapshot() == null) return 0;
        return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View current_view;

        if (viewType == 0) current_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_door_text, parent, false);
        else current_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_door_image, parent, false);

        return new RecyclerView.ViewHolder(current_view) {};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        door current_door = doorList.get(position);

        // имя
        TextView name = holder.itemView.findViewById(R.id.name);
        name.setText(current_door.getName());

        // snapshot
        if (getItemViewType(position) == 1)
        {
            ImageView snapshot = holder.itemView.findViewById(R.id.snapshot);

            // загрузка изображения в ImageView
            Glide
                    .with(holder.itemView)
                    .load(current_door.getSnapshot())
                    .into(snapshot);

            holder.itemView.setOnClickListener(view ->{
                Intent intent = new Intent(holder.itemView.getContext(), DomophoneActivity.class);
                intent.putExtra("current_door", (Serializable) current_door);
                holder.itemView.getContext().startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return doorList.size();
    }
}
