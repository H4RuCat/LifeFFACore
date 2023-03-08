package lifeffa.org.example.lifeffacore.listener;

import lifeffa.org.example.lifeffacore.LifeFFACore;
import lifeffa.org.example.lifeffacore.util.KillData;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemorySection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.*;

public class PlayerKillListener implements Listener {


    public static void actionbar(Player p, Integer kill, Integer death, Integer killStreak) {

        p.sendActionBar("§cKill§8: §f" + kill + " §e/ §cDeath§8: §f" + death + " §e/ §cKillStreak§8: §f" + killStreak);

    }

    private static final Map<UUID, KillData> map = new HashMap<>();

    public static void actionbar() {

        Bukkit.getOnlinePlayers().forEach( p -> {

            if ( p.getWorld().getName().equalsIgnoreCase("lifeFFA") ) {

                KillData entry = map.computeIfAbsent(p.getUniqueId(), k -> new KillData());

                actionbar(p, entry.kill, entry.death, entry.killStreak);
            }

        } );

    }

    public static void readConfig() {

        LifeFFACore ffacore = LifeFFACore.getPlugin(LifeFFACore.class);

        ConfigurationSection section = ffacore.getConfig().getConfigurationSection("players");

        if ( section == null ) return;

        Objects.requireNonNull(section).getValues(false).forEach((key, value) -> {

            Map<String, Object> dataMap = ((MemorySection) value).getValues(false);

            int kill = (int) dataMap.get("kill");
            int death = (int) dataMap.get("death");
            int killStreak = (int) dataMap.get("killStreak");
            UUID datakey = UUID.fromString(key);

            map.put(datakey, new KillData(kill, death, killStreak));

        } );

    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {

        Player killer = e.getEntity().getKiller();
        Player killed = e.getEntity().getPlayer();

        if ( killer == null ) return;
        if ( killed == null ) return;
        if ( !Objects.requireNonNull(killer).getWorld().getName().equalsIgnoreCase("lifeFFA") ) return;
        if ( !Objects.requireNonNull(killed).getWorld().getName().equalsIgnoreCase("lifeFFA") ) return;

        // 前 kill | 後 death //
        KillData killerEntry = map.computeIfAbsent(killer.getUniqueId(), k -> new KillData());
        killerEntry.kill++;
        killerEntry.killStreak++;
        if ( killerEntry.killStreak <= 4 ) killerEntry.points = killerEntry.points + 10;
        if ( killerEntry.killStreak == 5 ) killerEntry.points = killerEntry.points + 20;
        if ( killerEntry.killStreak >= 6 ) killerEntry.points = killerEntry.points + 15;

        KillData killedEntry = map.computeIfAbsent(killed.getUniqueId(), k -> new KillData());
        killedEntry.death++;
        killedEntry.killStreak = 0;

        LifeFFACore ffacore = LifeFFACore.getPlugin(LifeFFACore.class);

        Bukkit.getScheduler().runTaskAsynchronously(ffacore, () -> {

            ffacore.getConfig().set("players." + killer.getUniqueId() + ".kill", killerEntry.kill);
            ffacore.getConfig().set("players." + killer.getUniqueId() + ".death", killerEntry.death);
            ffacore.getConfig().set("players." + killer.getUniqueId() + ".killStreak", killerEntry.killStreak);

            ffacore.getConfig().set("players." + killed.getUniqueId() + ".kill", killedEntry.kill);
            ffacore.getConfig().set("players." + killed.getUniqueId() + ".death", killedEntry.death);
            ffacore.getConfig().set("players." + killed.getUniqueId() + ".killStreak", killedEntry.killStreak);
            ffacore.getConfig().set("players." + killed.getUniqueId() + ".killStreak", killedEntry.points);

            ffacore.saveConfig();

            int killedNum = killedEntry.kill / killedEntry.death;

            killed.sendMessage("§6.oOo--------------------------oOo.");
            killed.sendMessage("§7K/D§8: §f" + killedNum + "");
            killed.sendMessage("§6.oOo--------------------------oOo.");

        });

    }
}
