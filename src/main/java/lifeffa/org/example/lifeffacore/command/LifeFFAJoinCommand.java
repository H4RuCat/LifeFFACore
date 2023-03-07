package lifeffa.org.example.lifeffacore.command;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class LifeFFAJoinCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String prefix = "§8[§aLifeFFACore§8] ";

        if ( !(sender instanceof Player) ) return true;
        Player player = (Player) sender;

        if ( player.getWorld().getName().equalsIgnoreCase("lifeFFA") ) {

            player.sendMessage(prefix + "§cLifeFFAにいるのでTeleport出来ません");
            return true;

        } else {

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

        }

        return true;
    }

}