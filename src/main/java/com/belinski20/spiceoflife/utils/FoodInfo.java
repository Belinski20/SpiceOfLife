package com.belinski20.spiceoflife.utils;

import com.belinski20.spiceoflife.playerdata.FoodStats;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public class FoodInfo {
    public static boolean optIn = true;
    public static int foodHistoryAmount = 12;
    public static Map<String, FoodStats> foodValues = new HashMap<>();

    // Math is taken from https://docs.google.com/spreadsheets/d/1JR_ayn8a20y36vjmuMflqKQ2RXR5BW6fwNZr1-SYp1s/edit#gid=0
    public static double getCurrentNutritionalValueForItem(Material material, int eatenAmount)
    {
        String foodName = material.name();
        FoodStats stat = foodValues.get(foodName);
        if(stat == null)
            return 0;

        return Math.pow(Math.max(0.0, 1.0 - (double)eatenAmount / (double)foodHistoryAmount), Math.min(8.0, stat.getFoodLevel()));
    }

    // Math is taken from https://docs.google.com/spreadsheets/d/1JR_ayn8a20y36vjmuMflqKQ2RXR5BW6fwNZr1-SYp1s/edit#gid=0
    public static double getCurrentFoodValueForItem(Material material, int eatenAmount)
    {
        String foodName = material.name();
        FoodStats stat = foodValues.get(foodName);
        if(stat == null)
            return 0;

        return (double)Math.round(getCurrentNutritionalValueForItem(material, eatenAmount) * stat.getFoodLevel()) / (double)2;
    }
}
