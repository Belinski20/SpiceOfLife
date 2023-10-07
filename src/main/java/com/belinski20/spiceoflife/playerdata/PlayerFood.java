package com.belinski20.spiceoflife.playerdata;

import com.belinski20.spiceoflife.utils.FoodInfo;
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
        if(FoodInfo.foodValues.containsKey(item.getType().name()))
            return FoodInfo.foodValues.get(item.getType().name());
        return null;
    }

    public int amountTimeEaten(Material material) {
        return Collections.frequency(foodMaterials, material.name());
    }

    public void addFood(Material material) {
        if (foodMaterials.size() >= FoodInfo.foodHistoryAmount)
            while (foodMaterials.size() >= FoodInfo.foodHistoryAmount)
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
