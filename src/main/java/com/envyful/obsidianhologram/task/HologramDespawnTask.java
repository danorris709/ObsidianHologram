package com.envyful.obsidianhologram.task;

import com.envyful.obsidianhologram.ObsidianHologram;
import com.gmail.filoghost.holographicdisplays.api.Hologram;

import java.util.Iterator;
import java.util.Map;

public class HologramDespawnTask implements Runnable{

    private final ObsidianHologram plugin;

    public HologramDespawnTask(ObsidianHologram plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        Iterator<Map.Entry<Hologram, Long>> entryIterator = this.plugin.getPlacedHolograms().entrySet().iterator();

        while(entryIterator.hasNext()) {
            Map.Entry<Hologram, Long> entry = entryIterator.next();

            if(System.currentTimeMillis() <= entry.getValue()) {
                continue;
            }

            entry.getKey().delete();
            entryIterator.remove();
        }
    }
}
