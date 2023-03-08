package lifeffa.org.example.lifeffacore.listener;

import lifeffa.org.example.lifeffacore.LifeFFACore;
import lifeffa.org.example.lifeffacore.util.KillData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class PlayerSpawningListener implements Listener {

    private static final Map<UUID, KillData> map = new HashMap<>();

    @EventHandler
    public void onSpawn(PlayerRespawnEvent e) {

        String prefix = "§8[§aLifeFFACore§8] ";
        KillData death = map.computeIfAbsent(Objects.requireNonNull(e.getPlayer()).getUniqueId(), k -> new KillData());
        Player player = e.getPlayer();

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

            player.teleport(location);

            LifeFFACore ffacore = LifeFFACore.getPlugin(LifeFFACore.class);

            ffacore.getConfig().set("players." + player.getUniqueId() + ".remain", death.remain);
            ffacore.saveConfig();

        } else {

            e.getPlayer().sendMessage(prefix + "§c残機が無くなりました");

        }

    }

}
