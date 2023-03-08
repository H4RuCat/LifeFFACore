package lifeffa.org.example.lifeffacore.command;

import lifeffa.org.example.lifeffacore.listener.PlayerKillListener;
import lifeffa.org.example.lifeffacore.util.KillData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class KillRankingCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = (Player) sender;
        List<Map.Entry<UUID, KillData>> list = PlayerKillListener.map.entrySet().stream().sorted(Comparator.comparing(t -> t.getValue().kill) ).collect(Collectors.toList());
        Collections.reverse(list);

        player.sendMessage("§6.oOo---------- Kill Ranking -----------oOo.");

        for (int i = 0; i < Math.min(10, list.size()); i++) {

            Map.Entry<UUID, KillData> entry = list.get(i);
            String name = Bukkit.getOfflinePlayer(entry.getKey()).getName();

            player.sendMessage((i + 1) + "§f位§8: §e" + name + " §8- §f" + entry.getValue().kill + "§f Kill");

        }

        return true;
    }
}
