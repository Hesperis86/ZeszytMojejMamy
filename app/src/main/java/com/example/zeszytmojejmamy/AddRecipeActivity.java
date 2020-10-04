package com.example.zeszytmojejmamy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class AddRecipeActivity extends AppCompatActivity {
    public static final String EXTRA_TITLE = "com.example.zeszytmojejmamy.EXTRA_TITLE";
    public static final String EXTRA_PORTIONS_NUM = "com.example.zeszytmojejmamy.EXTRA_PORTIONS_NUM";
    public static final String EXTRA_INGREDIENTS = "com.example.zeszytmojejmamy.EXTRA_INGREDIENTS";
    public static final String EXTRA_PREP_INSTRUCTIONS = "com.example.zeszytmojejmamy.EXTRA_PREP_INSTRUCTIONS";

    private EditText editTextTitle;
    private EditText editTextPortions;
    private EditText editTextIngredients;
    private EditText editTextPreparationInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextPortions = findViewById(R.id.edit_text_portions);
        editTextIngredients = findViewById(R.id.edit_text_ingredients);
        editTextPreparationInstructions = findViewById(R.id.edit_text_preparationInstructions);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle(getString(R.string.addRecipe));
    }

    private void saveRecipe() {
        String title = editTextTitle.getText().toString();
        int portionNumber = Integer.parseInt(String.valueOf(editTextPortions.getText()));
        //int portionNumber = editTextPortions.toString().getValue();
        HashMap<String, Float> ingredients = Converters.fromStr(editTextIngredients.getText().toString());
        ArrayList<String> preparationInstructions = Converters.fromString(editTextPreparationInstructions.getText().toString());

        if (title.trim().isEmpty() || Converters.fromStringMap(ingredients).trim().isEmpty() || Converters.fromArrayList(preparationInstructions).trim().isEmpty()) {
            Toast.makeText(this, getString(R.string.emptyFieldsWarning), Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_PORTIONS_NUM, portionNumber);
        data.putExtra(EXTRA_INGREDIENTS, ingredients);
        data.putExtra(EXTRA_PREP_INSTRUCTIONS, preparationInstructions);

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_recipe_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_recipe:
                saveRecipe();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}