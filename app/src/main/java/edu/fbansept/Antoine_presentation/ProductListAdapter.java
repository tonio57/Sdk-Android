package edu.fbansept.Antoine_presentation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {

    private ArrayList<Product> productList;

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView itemProductTitle;
        TextView itemProductDescription;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            itemProductTitle = itemView.findViewById(R.id.itemProductTitle);
            itemProductDescription = itemView.findViewById(R.id.itemProductDescription);
        }
    }

    public ProductListAdapter(ArrayList<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_product,parent,false);

        ProductViewHolder viewHolder = new ProductViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.itemProductTitle.setText(productList.get(position).getTitle());
        holder.itemProductDescription.setText(productList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
