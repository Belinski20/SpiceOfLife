package com.belinski20.spiceoflife;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class PlayerFood {

    private Player player;
    private Queue<String> foodMaterials;

    public PlayerFood(Player player) {
        this.player = player;
        foodMaterials = new LinkedList<>();
    }

    public FoodStats foodStats(ItemStack item) {
        if(Config.foodValues.containsKey(item.getType().name()))
            return Config.foodValues.get(item.getType().name());
        return null;
    }

    public int amountTimeEaten(Material material) {
        return Collections.frequency(foodMaterials, material.name());
    }

    public void addFood(Material material) {
        if (foodMaterials.size() >= Config.foodHistoryAmount)
            while (foodMaterials.size() >= Config.foodHistoryAmount)
                foodMaterials.poll();
        foodMaterials.add(material.name());
    }

    public boolean equals(Player player)
    {
        if(player == null)
            return false;
        return this.player == player;
    }
    public Player getPlayer()
    {
        return player;
    }

    public Queue<String> foodsEaten()
    {
        return foodMaterials;
    }
}
