package com.belinski20.spiceoflife;

import com.belinski20.spiceoflife.commands.OptCommand;
import com.belinski20.spiceoflife.commands.ReloadCommand;
import com.belinski20.spiceoflife.listeners.SpiceOfLifeListeners;
import com.belinski20.spiceoflife.playerdata.PlayerManager;
import com.belinski20.spiceoflife.utils.FoodInfo;
import com.belinski20.spiceoflife.utils.FileUtil;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpiceOfLife extends JavaPlugin {

    public static SpiceOfLife spiceOfLife;
    public FoodInfo foodInfo;
    public FileUtil fileUtil;
    public PlayerManager manager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        spiceOfLife = this;
        foodInfo = new FoodInfo();
        manager = new PlayerManager();
        fileUtil = new FileUtil();
        getCommand("opt").setExecutor(new OptCommand());
        getCommand("solreload").setExecutor(new ReloadCommand());
        getServer().getPluginManager().registerEvents(new SpiceOfLifeListeners(this), this);
    }

    @Override
    public void onDisable() {
        manager.saveAllPlayers();
    }
}
