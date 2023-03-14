package lifeffa.org.example.lifeffacore.command;

import lifeffa.org.example.lifeffacore.listener.PlayerKillListener;
import lifeffa.org.example.lifeffacore.util.KillData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class CheckPointsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if ( !(sender instanceof Player) ) return true;

        String prefix = "§8[§aLifeFFACore§8] ";
        Player player = (Player) sender;
        KillData data = PlayerKillListener.map.computeIfAbsent(Objects.requireNonNull(player).getUniqueId(), k -> new KillData());

        sender.sendMessage(prefix + "§f現在の所持ポイント§8:§e " + data.points);

        return true;
    }
}
