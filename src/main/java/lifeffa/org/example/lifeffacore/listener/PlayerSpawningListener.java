package lifeffa.org.example.lifeffacore.listener;

import lifeffa.org.example.lifeffacore.LifeFFACore;
import lifeffa.org.example.lifeffacore.util.KillData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.Objects;

public class PlayerSpawningListener implements Listener {

    @EventHandler
    public void onSpawn(PlayerRespawnEvent e) {

        String prefix = "§8[§aLifeFFACore§8] ";
        KillData death = PlayerKillListener.map.computeIfAbsent(Objects.requireNonNull(e.getPlayer()).getUniqueId(), k -> new KillData());

        if ( death.remain >= 1 ) {

            Location spawnlocation = Objects.requireNonNull(Bukkit.getWorld("lifeFFA")).getSpawnLocation();

            int x = spawnlocation.getBlockX() + ((int) Math.floor(Math.random() * 210) - 105);
            int z = spawnlocation.getBlockZ() + ((int) Math.floor(Math.random() * 210) - 105);
            Location location = new Location(Bukkit.getWorld("lifeFFA"), x, 255, z);

            while (true) {

                if ( location.getBlock().getType().isAir() ) {
                    location.subtract(0, 1, 0);
                } else {
                    location.add(0, 1, 0);
                    break;
                }

            }

            Bukkit.getScheduler().runTaskLater(LifeFFACore.getPlugin(LifeFFACore.class), () -> e.getPlayer().teleport(location), 1);

        } else {

            e.getPlayer().sendMessage(prefix + "§c残機が無くなりました");

        }

    }

}
