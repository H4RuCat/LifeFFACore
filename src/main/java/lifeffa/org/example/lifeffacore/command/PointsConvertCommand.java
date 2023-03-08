package lifeffa.org.example.lifeffacore.command;

import lifeffa.org.example.lifeffacore.util.KillData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PointsConvertCommand implements CommandExecutor {

    private static final Map<UUID, KillData> map = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        KillData senderKillData = map.computeIfAbsent(player.getUniqueId(), k -> new KillData());

        if ( senderKillData.points >= 20 ) {

            senderKillData.points = senderKillData.points - 20;
            player.sendActionBar("§c現在の所持Points量§8: " + senderKillData.points);
            // ここでItemを付与するようにする

        } else {

            player.sendActionBar("§cPointsが足りません！");

        }

        return true;
    }
}
