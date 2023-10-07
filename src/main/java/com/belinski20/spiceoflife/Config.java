package com.belinski20.spiceoflife;

import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public class Config {
    public static boolean optIn = true;
    public static int foodHistoryAmount = 12;
    public static Map<String, FoodStats> foodValues = new HashMap<>();

    public static double getCurrentNutritionalValueForItem(Material material, int eatenAmount)
    {
        String foodName = material.name();
        FoodStats stat = foodValues.get(foodName);
        if(stat == null)
            return 0;

        return Math.pow(Math.max(0.0, 1.0 - (double)eatenAmount / (double)foodHistoryAmount), Math.min(8.0, stat.getFoodLevel()));
    }

    public static double getCurrentFoodValueForItem(Material material, int eatenAmount)
    {
        String foodName = material.name();
        FoodStats stat = foodValues.get(foodName);
        if(stat == null)
            return 0;

        return (double)Math.round(getCurrentNutritionalValueForItem(material, eatenAmount) * stat.getFoodLevel()) / (double)2;
    }
}
