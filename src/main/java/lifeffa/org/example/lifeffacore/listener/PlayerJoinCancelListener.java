package lifeffa.org.example.lifeffacore.listener;

import lifeffa.org.example.lifeffacore.LifeFFACore;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinCancelListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        if ( e.getPlayer().getWorld().getName().equalsIgnoreCase("lifeFFA") ) {

            String prefix = "§8[§aLifeFFACore§8] ";

            e.getPlayer().sendMessage(prefix + "§flifeFFA会場内にいた為、spawnしました");
            Bukkit.getScheduler().runTaskLater(LifeFFACore.getPlugin(LifeFFACore.class), () -> Bukkit.dispatchCommand(e.getPlayer(), "spawn"), 3);

        }
    }
}
