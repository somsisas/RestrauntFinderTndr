package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Map;

public class CardStackAdapter extends RecyclerView.Adapter<CardStackAdapter.ViewHolder> {

    private List<MyModel> items;

    public CardStackAdapter(List<MyModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyModel item = items.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<MyModel> getItems() {
        return items;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView ageTextView;
        ImageView profileImageView;
        int currentImageIndex = 0;

        ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
//            ageTextView = itemView.findViewById(R.id.ageTextView);
            profileImageView = itemView.findViewById(R.id.profileImageView);


            profileImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentImageIndex++;
                    if (currentImageIndex >= items.get(getAdapterPosition()).getImageUrls().size()) {
                        currentImageIndex = 0; // reset to first image
                    }
                    loadImage(items.get(getAdapterPosition()).getImageUrls().get(currentImageIndex));
                }
            });
        }

        void bind(MyModel item) {
            nameTextView.setText(item.getName());
//            ageTextView.setText(String.valueOf(item.getLatitude()));
            currentImageIndex = 0; // Reset to the first image on new bind
            loadImage(item.getImageUrls().get(currentImageIndex));
        }

        void loadImage(String url) {
            Glide.with(profileImageView.getContext())
                    .load(url)
                    .into(profileImageView);
        }

    }
}

