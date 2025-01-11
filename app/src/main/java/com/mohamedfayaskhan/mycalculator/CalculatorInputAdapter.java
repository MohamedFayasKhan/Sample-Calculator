package com.mohamedfayaskhan.mycalculator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.function.Consumer;

public class CalculatorInputAdapter extends RecyclerView.Adapter<CalculatorInputAdapter.CalculatorViewHolder> {

    private final List<String> items;
    Consumer<String> clickListener;
    public CalculatorInputAdapter(List<String> items, Consumer<String> clickListener) {
        this.items = items;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public CalculatorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_input, parent, false);
        return new CalculatorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalculatorViewHolder holder, int position) {
        holder.itemText.setText(items.get(position));
        holder.itemCard.setOnClickListener(v -> clickListener.accept(items.get(position)));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class CalculatorViewHolder extends RecyclerView.ViewHolder {
        CardView itemCard;
        TextView itemText;
        public CalculatorViewHolder(@NonNull View itemView) {
            super(itemView);
            itemCard = itemView.findViewById(R.id.item_card);
            itemText = itemView.findViewById(R.id.item_text);
        }
    }
}
