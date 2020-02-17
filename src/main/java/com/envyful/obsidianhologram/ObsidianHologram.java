package com.envyful.obsidianhologram;

import com.envyful.obsidianhologram.listener.BlockFromToListener;
import com.envyful.obsidianhologram.task.HologramDespawnTask;
import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ObsidianHologram extends JavaPlugin {

    private static ObsidianHologram instance;

    public static ObsidianHologram getInstance() {
        return instance;
    }

    private Map<Hologram, Long> placedHolograms = Maps.newConcurrentMap();

    @Override
    public void onEnable() {
        instance = this;

        Bukkit.getPluginManager().registerEvents(new BlockFromToListener(this), this);
        Bukkit.getScheduler().runTaskTimer(this, new HologramDespawnTask(this), 0, 20);
    }

    @Override
    public void onDisable() {
        this.placedHolograms.forEach((hologram, aLong) -> hologram.delete());
        this.placedHolograms.clear();
    }

    public void spawnHologram(Location blockLocation) {
        blockLocation.add(0.5, 2, 0.5);

        Hologram hologram = HologramsAPI.createHologram(this, blockLocation);

        hologram.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&4&l[!]"));
        hologram.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&c&lRight click with a bucket"));
        hologram.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&c&lto turn back to lava!"));
        hologram.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&4&l[!]"));

        this.placedHolograms.put(hologram, System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(10));
    }

    public Map<Hologram, Long> getPlacedHolograms() {
        return this.placedHolograms;
    }
}
