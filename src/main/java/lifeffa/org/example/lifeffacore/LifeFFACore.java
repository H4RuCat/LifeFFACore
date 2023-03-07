package lifeffa.org.example.lifeffacore;

import lifeffa.org.example.lifeffacore.listener.PlayerKillListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class LifeFFACore extends JavaPlugin {

    @Override
    public void onEnable() {

        getLogger().info("LifeFFACore起動");

        getServer().getPluginManager().registerEvents(new PlayerKillListener(), this);

    }

    @Override
    public void onDisable() {

        getLogger().info("LifeFFACore終了");

    }
}
