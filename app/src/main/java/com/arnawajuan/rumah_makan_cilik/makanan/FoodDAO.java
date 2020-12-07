package com.arnawajuan.rumah_makan_cilik.makanan;

import com.google.gson.annotations.SerializedName;

public class FoodDAO {
    @SerializedName("id")
    private String id;

    @SerializedName("food_name")
    private String food_name;

    @SerializedName("price")
    private double price;

    @SerializedName("image_food")
    private String image_food;

    @SerializedName("desc")
    private String description;


    public FoodDAO(String id, String name, double price , String email, String description) {
        this.id = id;
        this.food_name = name;
        this.price = price;
        this.image_food = email;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getFood_name() {
        return food_name;
    }

    public double getPrice() {
        return price;
    }

    public String getImage_food() {
        return image_food;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setImage_food(String image_food) {
        this.image_food = image_food;
    }

}
