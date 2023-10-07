package com.belinski20.spiceoflife;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;

public class LoreComponents {

    public static TextComponent makeMessage(String message)
    {
        return Component.text().content(message).build();
    }

    public static String foodFoodValue = "# ";
    public static String foodFoodValueIcon = "\uD83C\uDF56";
    public static String foodNutrition = "Nutritional value: ";
    public static String lastEatenAmount = "Eaten # out of the last TOTAL foods";
}
