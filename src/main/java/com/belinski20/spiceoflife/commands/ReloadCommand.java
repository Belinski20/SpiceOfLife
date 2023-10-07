package com.belinski20.spiceoflife.commands;

import com.belinski20.spiceoflife.LoreComponents;
import com.belinski20.spiceoflife.SpiceOfLife;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ReloadCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        SpiceOfLife.spiceOfLife.fileUtil.loadConfigFile();

        sender.sendMessage(LoreComponents.makeMessage("[SpiceOfLife] reloaded!"));
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }
}
