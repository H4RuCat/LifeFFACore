package lifeffa.org.example.lifeffacore.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;
import java.util.Random;

public class KillLogListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {

        if (!Objects.requireNonNull(e.getEntity().getPlayer()).getWorld().getName().equalsIgnoreCase("lifeFFA")) return;

        String prefix = "§8[§aLifeFFA§8] ";
        Player killer = e.getEntity().getKiller();
        Player killed = e.getEntity().getPlayer();
        String killerItem = Objects.requireNonNull(killer).getInventory().getItemInMainHand().getItemMeta().getDisplayName();

        Random rand = new Random();
        int num = rand.nextInt(4);

        if (num == 0) {
            e.setDeathMessage(prefix + "§f" + killer.getDisplayName() + " §7が §8[ " + killerItem + " §8] §7で §f" + killed.getDisplayName() + " §7をキル");
        }
        if (num == 1) {
            e.setDeathMessage(prefix + "§f" + killer.getDisplayName() + " §7が §8[ " + killerItem + " §8] §7で §f" + killed.getDisplayName() + " §7を倒した");
        }
        if (num == 2) {
            e.setDeathMessage(prefix + "§f" + killer.getDisplayName() + " §7が §8[ " + killerItem + " §8] §7で §f" + killed.getDisplayName() + " §7をやった");
        }
        if (num == 3) {
            e.setDeathMessage(prefix + "§f" + killer.getDisplayName() + " §7が §f" + killed.getDisplayName() + " §7を §8[ " + killerItem + " §8] §7で刺した");
        }

    }
}
