package lifeffa.org.example.lifeffacore.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlayerCheckCommand implements CommandExecutor {

    public static int checkPlayerNum() {

        int people = 0;

        for (Player p: Bukkit.getOnlinePlayers() ) {

            if ( p.getWorld().getName().equals("lifeFFA") ) {

                people++;

            }

        }
        return people;
    }
    public static void checkPlayers() {

        for (Player p: Bukkit.getOnlinePlayers() ) {

            if ( p.getWorld().getName().equals("lifeFFA") ) {

                p.sendMessage("§6oOo--------------------oOo");
                p.sendMessage("§d       Heal Areaが出現した");

            }
        }
    }
    public static void randomCrateMessage() {

        for (Player p: Bukkit.getOnlinePlayers() ) {

            if ( p.getWorld().getName().equals("lifeFFA") ) {

                p.sendMessage("§6oOo--------------------oOo");
                p.sendMessage("§d     Random crateが出現した");

            }
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = (Player) sender;
        String prefix = "§8[§aLifeFFACore§8] ";

        player.sendMessage( prefix + "§f現在LifeFFA内に §e" + checkPlayerNum() + "§b人 §fのプレイヤーがいます");
        return true;

    }
}
