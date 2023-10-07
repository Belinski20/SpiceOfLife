package com.belinski20.spiceoflife.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;

public class Messages {

    public static TextComponent makeMessage(String message)
    {
        return Component.text().content(message).build();
    }

    private static String prefix = "[SpiceOfLife] ";
    //Food Lore
    public static String foodFoodValue = "# ";
    public static String foodFoodValueIcon = "\uD83C\uDF56";
    public static String foodNutrition = "Nutritional value: ";
    public static String lastEatenAmount = "Eaten # out of the last TOTAL foods";
    // Opt in to SpiceOfLife
    public static String startSpiceOfLife = prefix + "Activated";
    public static String quitSpiceOfLife = prefix + "Deactivated";
    public static String reloadSpiceOfLife = prefix + "Reloaded!";
    public static String configDenyOptInSpiceOfLife = prefix + "SpiceOfLife is forced on this server.";
}
