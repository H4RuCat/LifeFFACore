package lifeffa.org.example.lifeffacore.command;

import lifeffa.org.example.lifeffacore.LifeFFACore;
import lifeffa.org.example.lifeffacore.listener.PlayerKillListener;
import lifeffa.org.example.lifeffacore.util.KillData;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class PointsConvertCommand implements CommandExecutor {

    public static final Map<UUID, KillData> map = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label, String[] args) {


        Player player = (Player) sender;
        String prefix = "§8[§aLifeFFACore§8] ";

        if ( args.length == 0 ) {

            player.sendMessage(prefix + "§fチケットの枚数を指定してください \n§7/pointsconvert <amount>");
            return true;

        }

        KillData senderKillData = PlayerKillListener.map.computeIfAbsent(player.getUniqueId(), k -> new KillData());

        int args0 = Integer.parseInt(args[0]);
        if ( args0 <= 0 ) {

            player.sendMessage(prefix + "§cマイナスは指定できません。");
            return true;

        }

        if ( senderKillData.points >= 20 * args0 ) {

            LifeFFACore ffacore = LifeFFACore.getPlugin(LifeFFACore.class);
            senderKillData.points -= 20 * args0;
            KillData playerEntry = map.computeIfAbsent(player.getUniqueId(), k -> new KillData());
            player.sendMessage(prefix + "§c現在の所持Points量§8:§f " + senderKillData.points);

            ItemStack itemStack = new ItemStack(Material.PAPER, args0);
            ItemMeta itemMeta = itemStack.getItemMeta();
            List<String> itemList = new ArrayList<>();
            itemList.add("§8[§aLifeFFACore§8]");
            itemList.add("§710ポイント分の紙");

            itemMeta.setDisplayName("§cLifeFFA Points ");
            itemMeta.setLore(itemList);
            itemStack.setItemMeta(itemMeta);

            player.getInventory().addItem(itemStack);


            ffacore.getConfig().set("players." + player.getUniqueId() + ".points", playerEntry.points);

            ffacore.saveConfig();

        } else {

            player.sendMessage(prefix + "§cPointsが足りません！");

        }

        return true;
    }
}
