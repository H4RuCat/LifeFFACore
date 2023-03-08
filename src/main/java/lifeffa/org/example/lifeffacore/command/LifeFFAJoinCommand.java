package lifeffa.org.example.lifeffacore.command;

import lifeffa.org.example.lifeffacore.LifeFFACore;
import lifeffa.org.example.lifeffacore.listener.PlayerKillListener;
import lifeffa.org.example.lifeffacore.util.KillData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class LifeFFAJoinCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label, String[] args) {

        String prefix = "§8[§aLifeFFACore§8] ";

        if ( !(sender instanceof Player) ) return true;
        Player player = (Player) sender;

        if ( player.getWorld().getName().equalsIgnoreCase("lifeFFA") ) {

            player.sendMessage(prefix + "§cLifeFFAにいるのでTeleport出来ません");
            return true;

        } else {

            KillData joinPlayer = PlayerKillListener.map.computeIfAbsent(Objects.requireNonNull(player).getUniqueId(), k -> new KillData());
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

            joinPlayer.remain = 3;
            player.teleport(location);

            LifeFFACore ffacore = LifeFFACore.getPlugin(LifeFFACore.class);

            ffacore.getConfig().set("players." + player.getUniqueId() + ".remain", joinPlayer.remain);
            ffacore.saveConfig();

        }

        return true;
    }

}
