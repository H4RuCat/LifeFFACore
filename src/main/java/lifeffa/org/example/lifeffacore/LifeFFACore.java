package lifeffa.org.example.lifeffacore;

import lifeffa.org.example.lifeffacore.command.*;
import lifeffa.org.example.lifeffacore.listener.*;
import lifeffa.org.example.lifeffacore.listener.item.TeleportItemListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class LifeFFACore extends JavaPlugin {

    @Override
    public void onEnable() {

        getLogger().info("LifeFFACore起動");

        getServer().getPluginManager().registerEvents(new PlayerKillListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerSpawningListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinCancelListener(), this);
        getServer().getPluginManager().registerEvents(new KillLogListener(), this);
        getServer().getPluginManager().registerEvents(new TeleportItemListener(), this);

        Objects.requireNonNull(getServer().getPluginCommand("ffajoin")).setExecutor(new LifeFFAJoinCommand());
        Objects.requireNonNull(getServer().getPluginCommand("pointsconvert")).setExecutor(new PointsConvertCommand());
        Objects.requireNonNull(getServer().getPluginCommand("ffapoints")).setExecutor(new PointsManagementCommand());
        Objects.requireNonNull(getServer().getPluginCommand("killranking")).setExecutor(new KillRankingCommand());
        Objects.requireNonNull(getServer().getPluginCommand("ffagetitem")).setExecutor(new ItemGetCommand());

        getServer().getScheduler().runTaskTimer(this, PlayerKillListener::actionbar, 10, 10);

        PlayerKillListener.readConfig();
    }

    @Override
    public void onDisable() {

        getLogger().info("LifeFFACore終了");

    }
}
