package com.arnawajuan.tubes_uts.makanan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodResponse {
    @SerializedName("data")
    @Expose
    private List<FoodDAO> food;

    @SerializedName("message")
    @Expose
    private String message;

    public List<FoodDAO> getFood() {
        return food;
    }

    public void setFood(List<FoodDAO> food) {
        this.food = food;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
