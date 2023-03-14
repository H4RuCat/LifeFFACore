package lifeffa.org.example.lifeffacore.command;

import lifeffa.org.example.lifeffacore.LifeFFACore;
import lifeffa.org.example.lifeffacore.listener.PlayerKillListener;
import lifeffa.org.example.lifeffacore.util.InventoryCheckUtil;
import lifeffa.org.example.lifeffacore.util.KillData;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

public class LifeFFAJoinCommand implements CommandExecutor {

    public boolean LifeFFACheck() {

        LocalDateTime nowTime = LocalDateTime.now();
        Integer hour  = nowTime.getHour();

        return !hour.equals(16) && !hour.equals(18);
    }

    public void LifeFFATeleport(Player player) {

        String prefix = "§8[§aLifeFFACore§8] ";
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
        player.sendMessage(prefix + "§aLifeFFAにteleportしました");

        LifeFFACore ffacore = LifeFFACore.getPlugin(LifeFFACore.class);

        ffacore.getConfig().set("players." + player.getUniqueId() + ".remain", joinPlayer.remain);
        ffacore.saveConfig();

    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label, String[] args) {

        String prefix = "§8[§aLifeFFACore§8] ";
        String playerName = args[0];

        if ( !(sender instanceof Player) ) return true;

        Player player = (Player) sender;
        Player name;

        try {
            name = Bukkit.getPlayer(playerName);
            if ( name == null ) {

                player.sendMessage(ChatColor.RED + "そのプレイヤーは存在していません。");
                return true;

            }
        } catch (Exception e) {

            player.sendMessage(ChatColor.RED + "そのプレイヤーは存在していません。");
            return true;

        }

        if ( player.hasPermission("lifecore.admin") ) {

            player.sendMessage("§c権限を持っているから処理すっとばしたよ！");

            LifeFFATeleport(player);

            return true;
        }

        if ( player.getWorld().getName().equalsIgnoreCase("lifeFFA") ) {

            player.sendMessage(prefix + "§cLifeFFAにいるのでTeleport出来ません");
            return true;

        } else if ( LifeFFACheck() ) {

            player.sendMessage(prefix + "§c現在開放時間外です\n§6.oOo----開放時間----oOo.\n§716:§700§f-§716:§759 §e| §719:§700§f-§719:§759");
            return true;

        } else if (new InventoryCheckUtil().invCheck(name)) {

            LifeFFATeleport(player);

        } else {

            name.sendMessage("§7LifeFFAには指定アイテム以外のアイテムは持ち込めません。\n§n/fci§7で持ち込み可能なアイテムを確認できます");
            return true;

        }

        return true;

    }

}
