package com.belinski20.spiceoflife;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PlayerManager {

    private ArrayList<PlayerFood> players;

    public PlayerManager()
    {
        players = new ArrayList<>();
    }

    public void add(Player p)
    {
        PlayerFood playerFood = SpiceOfLife.spiceOfLife.fileUtil.loadPlayerFile(p);
        players.add(playerFood);
    }

    public boolean contains(Player p)
    {
        for(PlayerFood pf : players)
        {
            if(pf.equals(p))
                return true;
        }
        return false;
    }

    public void remove(Player p)
    {
        PlayerFood pf = getPlayerFood(p);
        SpiceOfLife.spiceOfLife.fileUtil.savePlayerFile(pf);
        players.remove(pf);
    }

    public PlayerFood getPlayerFood(Player p)
    {
        for(PlayerFood pf : players)
        {
            if(pf.equals(p))
                return pf;
        }
        return null;
    }

}
