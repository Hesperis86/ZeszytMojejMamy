package com.example.zeszytmojejmamy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecipeViewModel recipeViewModel;
    public static final int ADD_RECIPE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton buttonAddRecipe = findViewById(R.id.button_add_recipe);
        buttonAddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddRecipeActivity.class);
                startActivityForResult(intent, ADD_RECIPE_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final RecipeAdapter adapter = new RecipeAdapter();
        recyclerView.setAdapter(adapter);

        recipeViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(RecipeViewModel.class);
        recipeViewModel.getAllRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                adapter.setRecipes(recipes);
                //update RecyclerView
                //Toast.makeText(MainActivity.this,"onChanged", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_RECIPE_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddRecipeActivity.EXTRA_TITLE);
            //int portions = Integer.parseInt(data.getStringExtra(AddRecipeActivity.EXTRA_PORTIONS_NUM));
            int portions = data.getIntExtra(AddRecipeActivity.EXTRA_PORTIONS_NUM, 1);
            HashMap<String, Float> ingredients = Converters.fromStr(data.getStringExtra(AddRecipeActivity.EXTRA_INGREDIENTS));
            ArrayList<String> preparationInstructions = Converters.fromString(data.getStringExtra(AddRecipeActivity.EXTRA_PREP_INSTRUCTIONS));


            Recipe recipe = new Recipe(title, null, ingredients, portions, preparationInstructions, null, null);
            //Recipe recipe = new Recipe("Tytuł", null, null, 0, null, null, null);
            recipeViewModel.insert(recipe);

            Toast.makeText(this, getString(R.string.recipeSaved), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.recipeNotSaved), Toast.LENGTH_SHORT).show();
        }
    }
}