package com.belinski20.spiceoflife.commands;

import com.belinski20.spiceoflife.utils.FoodInfo;
import com.belinski20.spiceoflife.utils.Messages;
import com.belinski20.spiceoflife.SpiceOfLife;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class OptCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(FoodInfo.optIn == false)
        {
            sender.sendMessage(Messages.configDenyOptInSpiceOfLife);
            return true;
        }

        if(!(sender instanceof Player))
        {
            sender.sendMessage(Component.text().content("SpiceOfLife cannot be opted from console").color(NamedTextColor.RED).build());
            return true;
        }
        Player player = (Player) sender;

        if(!SpiceOfLife.spiceOfLife.manager.contains(player))
        {
            player.sendMessage(Messages.makeMessage(Messages.startSpiceOfLife));
            SpiceOfLife.spiceOfLife.manager.add(player);
            sendPacket(player);
        }
        else
        {
            player.sendMessage(Messages.makeMessage(Messages.quitSpiceOfLife));
            SpiceOfLife.spiceOfLife.fileUtil.savePlayerFile(SpiceOfLife.spiceOfLife.manager.getPlayerFood(player));
            SpiceOfLife.spiceOfLife.manager.remove(player);
            removeStuckLoreFromFood(player);
            player.updateInventory();
        }
        return true;
    }

    private void sendPacket(Player player)
    {
        PacketContainer packetWindow = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.WINDOW_ITEMS);
        packetWindow.getItemListModifier().write(0, Arrays.asList(player.getInventory().getContents()));
        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, packetWindow);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        player.updateInventory();
    }

    private void removeStuckLoreFromFood(Player player)
    {
        for(ItemStack item : player.getInventory().getContents())
        {
            if(item == null)
                continue;
            if(item.getType().isEdible())
            {
                ItemMeta meta = item.getItemMeta();
                if(meta.hasLore())
                {
                    TextComponent component = (TextComponent) meta.lore().get(0).children().get(0);
                    String text = component.content();
                    if(text.contains(Messages.foodFoodValueIcon))
                    {
                        meta.lore(null);
                        item.setItemMeta(meta);
                    }
                }
            }
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return null;
    }
}
