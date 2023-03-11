package lifeffa.org.example.lifeffacore.listener.item;

import lifeffa.org.example.lifeffacore.command.LifeFFAJoinCommand;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;

public class TeleportItemListener implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {

        String prefix = "§8[§aLifeFFACore§8] ";

        if ( !e.getAction().equals(Action.RIGHT_CLICK_AIR) ) return;
        if ( e.getItem() == null ) return;
        if ( !e.getItem().hasItemMeta() ) return;
        if ( !e.getItem().getItemMeta().getPersistentDataContainer().has(NamespacedKey.minecraft("lifeffa_teleporstitem"), PersistentDataType.STRING)) return;
        if ( e.getPlayer().getWorld().getName().equalsIgnoreCase("lifeFFA") ) {

            e.getPlayer().sendMessage(prefix + "§cLifeFFAにいるのでTeleport出来ません");
            return;

        }

        if ( !new LifeFFAJoinCommand().LifeFFACheck() ) {

            new LifeFFAJoinCommand().LifeFFATeleport(e.getPlayer());

            e.getItem().subtract(1);
            e.getPlayer().sendMessage("§cteleportしました");

        } else {

            e.getPlayer().sendMessage(prefix + "§c現在開放時間外です\n§6.oOo----開放時間----oOo.\n§716:§700§f-§716:§759 §e| §719:§700§f-§719:§759");

        }

    }
}
