package com.example.zeszytmojejmamy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeHolder> {
    private List<Recipe> recipes = new ArrayList<>();

    @NonNull
    @Override
    public RecipeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_item, parent, false);

        return new RecipeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeHolder holder, int position) {
        Recipe currentRecipe = recipes.get(position);
        holder.textViewName.setText(currentRecipe.getName());
        holder.textViewPortions.setText(String.valueOf(currentRecipe.getPortions()));
        holder.textViewIngredients.setText(Converters.fromStringMap(currentRecipe.getIngredients()));
        holder.textViewPreparationInstructions.setText(Converters.fromArrayList(currentRecipe.getPreparationInstructions()));
        //holder.imageViewImage.setImageBitmap(currentRecipe.getImage()); jak zwrócić zdjęcie?
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    class RecipeHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewPortions;
        private TextView textViewIngredients;
        private TextView textViewPreparationInstructions;
        private ImageView imageViewImage;


        public RecipeHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewPortions = itemView.findViewById(R.id.text_view_portions);
            textViewIngredients = itemView.findViewById(R.id.text_view_ingredients);
            textViewPreparationInstructions = itemView.findViewById(R.id.text_view_preparationInstructions);
            imageViewImage = itemView.findViewById(R.id.image_view_image);
        }
    }
}
