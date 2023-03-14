package lifeffa.org.example.lifeffacore.item;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class TeleportItem {

    public ItemStack teleportItem() {

        ItemStack bookStack = new ItemStack(Material.BOOK);
        ItemMeta bookMeta = bookStack.getItemMeta();

        bookMeta.setDisplayName("§clifeFFA Teleport Item");
        List<String> bookList = new ArrayList<>();
        bookList.add("§8[§aLifeFFACore§8]");
        bookList.add("§7開放時間じゃないと使えない");

        bookMeta.setLore(bookList);

        bookMeta.getPersistentDataContainer().set(NamespacedKey.minecraft("lifeffa_teleporstitem"), PersistentDataType.STRING, "true");

        bookStack.setItemMeta(bookMeta);

        return bookStack;
    }


}
