package com.belinski20.spiceoflife.listeners;

import com.belinski20.spiceoflife.*;
import com.belinski20.spiceoflife.playerdata.FoodStats;
import com.belinski20.spiceoflife.playerdata.PlayerFood;
import com.belinski20.spiceoflife.utils.FoodInfo;
import com.belinski20.spiceoflife.utils.Messages;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import io.papermc.paper.event.player.PlayerInventorySlotChangeEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SpiceOfLifeListeners implements Listener {

    private SpiceOfLife spiceOfLife;
    private NumberFormat foodFormat;
    private NumberFormat nutritionFormat;

    public SpiceOfLifeListeners(SpiceOfLife spiceOfLife)
    {
        this.spiceOfLife = spiceOfLife;
        foodFormat = new DecimalFormat("#.#");
        nutritionFormat = new DecimalFormat("###.##");

        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(spiceOfLife, ListenerPriority.HIGHEST, PacketType.Play.Server.WINDOW_ITEMS) {
            @Override
            public void onPacketSending(PacketEvent event)
            {
                Player player = event.getPlayer();
                if(!spiceOfLife.manager.contains(player))
                    return;

                PlayerFood playerFood = SpiceOfLife.spiceOfLife.manager.getPlayerFood(player);

                PacketContainer packetContainer = event.getPacket().deepClone();

                List<ItemStack> items = packetContainer.getItemListModifier().read(0);

                for(ItemStack item : items)
                {
                    if(item.getType().isEdible())
                    {

                        ItemMeta meta = item.getItemMeta();
                        List<Component> lore = new LinkedList<>();
                        lore.add(Messages.makeMessage(Messages.foodFoodValue
                                .replace("#", "" + foodFormat.format(FoodInfo.getCurrentFoodValueForItem(item.getType(), playerFood.amountTimeEaten(item.getType())))))
                                .color(NamedTextColor.GRAY)
                                .decoration(TextDecoration.ITALIC, false)
                                .append(Messages.makeMessage(Messages.foodFoodValueIcon)
                                        .color(NamedTextColor.RED)
                                        .decoration(TextDecoration.ITALIC, false)));

                        lore.add(Messages.makeMessage(Messages.foodNutrition)
                                .color(NamedTextColor.GRAY)
                                .decoration(TextDecoration.ITALIC, false)
                                .append(Messages.makeMessage("" + nutritionFormat.format(FoodInfo.getCurrentNutritionalValueForItem(item.getType(), playerFood.amountTimeEaten(item.getType())) * 100) + "%"))
                                .color(NamedTextColor.GOLD)
                                .decoration(TextDecoration.ITALIC, false));

                        lore.add(Messages.makeMessage(Messages.lastEatenAmount
                                .replace("#", "" + playerFood.amountTimeEaten(item.getType()))
                                .replace("TOTAL", "" + FoodInfo.foodHistoryAmount))
                                .color(NamedTextColor.AQUA));

                        meta.lore(lore);
                        item.setItemMeta(meta);
                    }
                }
                packetContainer.getItemListModifier().write(0, items);
                event.setPacket(packetContainer);
            }
        });
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event)
    {
        Player player = event.getPlayer();

        if(!spiceOfLife.manager.contains(player))
            return;

        spiceOfLife.fileUtil.savePlayerFile(spiceOfLife.manager.getPlayerFood(player));
        spiceOfLife.manager.remove(player);
    }

    @EventHandler
    public void onShiftClick(InventoryClickEvent event){
        if(!event.getClick().isShiftClick())
            return;

        Player player = (Player)event.getWhoClicked();

        if(!spiceOfLife.manager.contains(player))
            return;

        sendPacket(player);
    }

    @EventHandler
    public void onInventorySlotChange(PlayerInventorySlotChangeEvent event){
        Player player = event.getPlayer();

        if(!spiceOfLife.manager.contains(player))
            return;

        sendPacket(player);
    }

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event){
        if(!(event.getEntity() instanceof Player))
            return;

        Player player = (Player)event.getEntity();

        if(!spiceOfLife.manager.contains(player))
            return;

        sendPacket(player);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event)
    {
        Player player = (Player)event.getEntity();

        if(!spiceOfLife.manager.contains(player))
            return;

        PlayerFood pf = SpiceOfLife.spiceOfLife.manager.getPlayerFood(player);
        FoodStats foodStats = pf.foodStats(event.getItem());
        Material mat = event.getItem().getType();
        event.setFoodLevel(player.getFoodLevel() + (int)(FoodInfo.getCurrentFoodValueForItem(mat, pf.amountTimeEaten(mat)) * 2));
        player.setSaturation(player.getSaturation() + foodStats.getSaturation());

        pf.addFood(event.getItem().getType());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        if(FoodInfo.optIn == true)
            return;

        Player player = event.getPlayer();

        player.sendMessage(Messages.makeMessage(Messages.configDenyOptInSpiceOfLife));
        SpiceOfLife.spiceOfLife.manager.add(player);

        sendPacket(player);
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
}
