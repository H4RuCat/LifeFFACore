package lifeffa.org.example.lifeffacore.command;

import lifeffa.org.example.lifeffacore.item.TeleportItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.Bukkit.getPlayer;

public class ItemGetCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        try {

            if (sender instanceof Player) {

                Player sender1 = (Player) sender;

                if (!sender1.hasPermission("lifecore.admin")) return true;

                String playerName = args[0];
                Player player;

                try {

                    player = getPlayer(playerName);

                    if (player == null) {

                        sender1.sendMessage("§cプレイヤーが存在しません");
                        return true;

                    }

                } catch (Exception e) {

                    sender1.sendMessage("§cプレイヤーが存在しません");
                    return true;

                }

                player.getInventory().addItem(new TeleportItem().teleportItem());
                return true;

            }

        } catch (Exception e){

            sender.sendMessage("§cエラーが発生しました");
            return true;

        }

        return true;
    }
}
