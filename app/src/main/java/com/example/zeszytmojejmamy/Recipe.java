package com.example.zeszytmojejmamy;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.HashMap;

@Entity(tableName = "recipes_table")
public class Recipe {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    @Nullable
    private byte[] image;

    private HashMap<String, Float> ingredients;
    private int portions;
    private ArrayList<String> preparationInstructions;
   @Nullable
    private String category;
    @Nullable
    private ArrayList<String> tags;

    public Recipe(String name, byte[] image, HashMap<String, Float> ingredients, int portions, ArrayList<String> preparationInstructions, String category, ArrayList<String> tags) {
        this.name = name;
        this.image = image;
        this.ingredients = ingredients;
        this.portions = portions;
        this.preparationInstructions = preparationInstructions;
        this.category = category;
        this.tags = tags;
    }


    /************ different constructors ***********************************************************
    public Recipe(String name, HashMap<String, Float> ingredients, int portions, ArrayList<String> preparationInstructions, String category, ArrayList<String> tags) {
        this.name = name;
        this.ingredients = ingredients;
        this.portions = portions;
        this.preparationInstructions = preparationInstructions;
        this.category = category;
        this.tags = tags;
    }

    public Recipe(String name, HashMap<String, Float> ingredients, int portions, ArrayList<String> preparationInstructions) {
        this.name = name;
        this.ingredients = ingredients;
        this.portions = portions;
        this.preparationInstructions = preparationInstructions;
    }

    public Recipe(String name, HashMap<String, Float> ingredients, ArrayList<String> preparationInstructions) {
        this.name = name;
        this.ingredients = ingredients;
        this.preparationInstructions = preparationInstructions;
    }

    public Recipe(String name, HashMap<String, Float> ingredients, ArrayList<String> preparationInstructions, String category, ArrayList<String> tags) {
        this.name = name;
        this.ingredients = ingredients;
        this.preparationInstructions = preparationInstructions;
        this.category = category;
        this.tags = tags;
    } *********************************************************************************************/

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public byte[] getImage() {
        return image;
    }

    public HashMap<String, Float> getIngredients() {
        return ingredients;
    }

    public int getPortions() {
        return portions;
    }

    public ArrayList<String> getPreparationInstructions() {
        return preparationInstructions;
    }

    public String getCategory() {
        return category;
    }

    public ArrayList<String> getTags() {
        return tags;
    }
}
