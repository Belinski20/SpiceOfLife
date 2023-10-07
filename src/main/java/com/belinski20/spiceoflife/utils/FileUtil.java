package com.belinski20.spiceoflife.utils;


import com.belinski20.spiceoflife.playerdata.FoodStats;
import com.belinski20.spiceoflife.playerdata.PlayerFood;
import com.belinski20.spiceoflife.SpiceOfLife;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class FileUtil{

    private static Plugin plugin = SpiceOfLife.spiceOfLife;
    private String foodAmount = "Food.material.foodAmount";
    private String saturation = "Food.material.saturation";
    public FileUtil()
    {
        createDirectory();
    }

    private void createDirectory()
    {
        plugin.getDataFolder().mkdir();
        createConfigFile();
        loadConfigFile();
    }

    public void createConfigFile(){
        FileConfiguration config;
        File file = new File(plugin.getDataFolder(),  "config.yml");
        try {
            if(file.createNewFile())
            {
                config = YamlConfiguration.loadConfiguration(file);
                config.set("OptIn", true);
                config.set("FoodAmounts", 12);
                for(Material mat : Material.values())
                {
                    if(mat.isEdible())
                    {
                        config.set(foodAmount.replace("material", mat.toString()), 1);
                        config.set(saturation.replace("material", mat.toString()), 1);
                    }
                }
                config.save(file);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadConfigFile(){
        FileConfiguration config;
        File file = new File(plugin.getDataFolder(),  "config.yml");
        if(file.exists())
        {
            config = YamlConfiguration.loadConfiguration(file);
            FoodInfo.optIn = config.getBoolean("OptIn");
            FoodInfo.foodHistoryAmount = config.getInt("FoodAmounts");

            FoodInfo.foodValues.clear();
            for(String matName : config.getConfigurationSection("Food").getKeys(false))
            {
                FoodStats foodStats = new FoodStats(config.getInt(foodAmount.replace("material", matName)), (float)config.getDouble(saturation.replace("material", matName)));
                FoodInfo.foodValues.put(matName, foodStats);
            }
        }
    }

    public void savePlayerFile(PlayerFood playerFood){
        FileConfiguration config;
        File file = new File(plugin.getDataFolder(), "players.yml");
        try {
            file.createNewFile();
            if(file.exists())
            {
                config = YamlConfiguration.loadConfiguration(file);
                int i = 0;
                Iterator<String> itr = playerFood.foodsEaten().iterator();
                while(itr.hasNext())
                {
                    config.set("Player." + playerFood.getPlayer().getUniqueId().toString() + "." + i, itr.next());
                    i++;
                }
                config.save(file);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public PlayerFood loadPlayerFile(Player player){
        FileConfiguration config;
        File file = new File(plugin.getDataFolder(),  "players.yml");
        if(file.exists())
        {
            PlayerFood playerFood = new PlayerFood(player);
            config = YamlConfiguration.loadConfiguration(file);
            if(config.getConfigurationSection("Player." + player.getUniqueId().toString()) == null)
                return playerFood;
            for(String index : config.getConfigurationSection("Player." + player.getUniqueId().toString()).getKeys(false))
            {
                playerFood.addFood(Material.valueOf(config.getString("Player." + player.getUniqueId().toString() + "." + index)));
            }
            return playerFood;
        }
        else
        {
            PlayerFood playerFood = new PlayerFood(player);
            return playerFood;
        }
    }
}
