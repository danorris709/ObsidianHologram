package com.envyful.obsidianhologram.listener;

import com.envyful.obsidianhologram.ObsidianHologram;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockFromToEvent;

public class BlockFromToListener implements Listener {

    private final ObsidianHologram plugin;

    public BlockFromToListener(ObsidianHologram plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockFromTo(BlockFormEvent event) {
        if(event.getBlock().getType() != Material.LAVA || event.getNewState().getType() != Material.OBSIDIAN) {
            return;
        }

        this.plugin.spawnHologram(event.getBlock().getLocation());
    }
}
