package com.belinski20.spiceoflife.playerdata;

public class FoodStats {
    private int foodLevel;
    private float saturation;

    public FoodStats(int foodLevel, float saturation)
    {
        this.foodLevel = foodLevel;
        this.saturation = saturation;
    }

    public int getFoodLevel() {
        return foodLevel;
    }

    public float getSaturation() {
        return saturation;
    }

}
