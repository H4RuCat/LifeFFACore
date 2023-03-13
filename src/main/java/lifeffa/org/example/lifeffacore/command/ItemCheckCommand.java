package lifeffa.org.example.lifeffacore.command;

import lifeffa.org.example.lifeffacore.LifeFFACore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ItemCheckCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        try {
            if ( sender instanceof Player ) {

                Player player = (Player) sender;

                player.sendMessage("§6oOo-----持ち込み可能なアイテムリスト-----oOo");
                player.sendMessage("§9§nItem List");

                for (String string: LifeFFACore.inst().getConfig().getStringList("Item")) {

                    player.sendMessage(string);

                }

                player.sendMessage("§9§nArmor List");

                for (String string: LifeFFACore.inst().getConfig().getStringList("Armor")) {

                    player.sendMessage(string);

                }

            } else {

                sender.sendMessage(ChatColor.RED + "Player実行のみ表示可能です。");

            }

            return true;

        } catch (Exception e) {

            sender.sendMessage(ChatColor.RED + "正しく入力してください。");
            return true;

        }
    }
}

