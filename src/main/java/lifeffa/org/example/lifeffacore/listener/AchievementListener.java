package lifeffa.org.example.lifeffacore.listener;

import lifeffa.org.example.lifeffacore.util.KillData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;

public class AchievementListener implements Listener {

    @EventHandler
    public void onKill(PlayerDeathEvent e) {

        Player killer = e.getEntity().getKiller();
        Player killed = e.getEntity().getPlayer();

        KillData killerEntry = PlayerKillListener.map.computeIfAbsent(Objects.requireNonNull(killer).getUniqueId(), k -> new KillData());
        KillData killedEntry = PlayerKillListener.map.computeIfAbsent(Objects.requireNonNull(killed).getUniqueId(), k -> new KillData());

        if ( killedEntry.death == 10 ) {
            Bukkit.dispatchCommand(killed, "/achievements progress " + killed.getName() + " azisaba:life/ffa/death_10 1");
        }
        if ( killedEntry.death == 100 ) {
            Bukkit.dispatchCommand(killed, "/achievements progress " + killed.getName() + " azisaba:life/ffa/death_100 1");
        }
        if ( killedEntry.death == 1000 ) {
            Bukkit.dispatchCommand(killed, "/achievements progress " + killed.getName() + " azisaba:life/ffa/death_1000 1");
        }
        if ( killerEntry.kill == 10 ) {
            Bukkit.dispatchCommand(killer, "/achievements progress " + killer.getName() + " azisaba:life/ffa/kill_10 1");
        }
        if ( killerEntry.kill == 100 ) {
            Bukkit.dispatchCommand(killer, "/achievements progress " + killed.getName() + " azisaba:life/ffa/kill_100 1");
        }
        if ( killerEntry.kill == 1000 ) {
            Bukkit.dispatchCommand(killer, "/achievements progress " + killed.getName() + " azisaba:life/ffa/kill_1000 1");
        }
    }

}
