package com.belinski20.spiceoflife.commands;

import com.belinski20.spiceoflife.Config;
import com.belinski20.spiceoflife.LoreComponents;
import com.belinski20.spiceoflife.SpiceOfLife;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OptCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(Config.optIn == false)
        {
            sender.sendMessage(Component.text().content("Cannot opt out of SpiceOfLife").color(NamedTextColor.RED).build());
            return true;
        }

        if(!(sender instanceof Player))
        {
            sender.sendMessage(Component.text().content("SpiceOfLife cannot be started from console").color(NamedTextColor.RED).build());
            return true;
        }
        Player player = (Player) sender;

        if(SpiceOfLife.spiceOfLife.manager.contains(player))
        {
            player.sendMessage(LoreComponents.makeMessage("Opt out of SpiceOfLife"));
            SpiceOfLife.spiceOfLife.fileUtil.savePlayerFile(SpiceOfLife.spiceOfLife.manager.getPlayerFood(player));
            SpiceOfLife.spiceOfLife.manager.remove(player);
        }
        else
        {
            player.sendMessage(LoreComponents.makeMessage("Opt into SpiceOfLife"));
            SpiceOfLife.spiceOfLife.manager.add(player);
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }
}
