package lifeffa.org.example.lifeffacore.command;

import lifeffa.org.example.lifeffacore.listener.PlayerKillListener;
import lifeffa.org.example.lifeffacore.util.KillData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class PointsManagementCommand implements CommandExecutor {

    @Override
    public boolean onCommand (@NotNull CommandSender sender,@NotNull Command command,@NotNull String label, String[] args) {

        if ( !(sender instanceof Player) ) return true;

        String prefix = "§8[§aLifeFFACore§8] ";
        Player player = (Player) sender;
        KillData data = PlayerKillListener.map.computeIfAbsent(Objects.requireNonNull(player).getUniqueId(), k -> new KillData());

        if ( args[0].equals("set") ) {

            data.points = Integer.parseInt(args[1]);
            sender.sendMessage(prefix + "§f現在のポイント量§8 " + data.points);

        }
        if ( args[0].equals("add") ) {

            data.points += Integer.parseInt(args[1]);
            sender.sendMessage(prefix + "§f現在のポイント量§8 " + data.points);

        }
        if ( args[0].equals("remove") ) {

            data.points -= Integer.parseInt(args[1]);
            sender.sendMessage(prefix + "§f現在のポイント量§8 " + data.points);

        }
        return true;
    }
}
