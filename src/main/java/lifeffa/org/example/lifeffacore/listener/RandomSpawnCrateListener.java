package lifeffa.org.example.lifeffacore.listener;

import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import io.lumine.xikage.mythicmobs.mobs.MythicMob;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.Listener;

import java.util.Objects;
import java.util.Random;

import static lifeffa.org.example.lifeffacore.command.PlayerCheckCommand.randomCrateMessage;

public class RandomSpawnCrateListener implements Listener {

    private static final Random random = new Random();

    private static ActiveMob randomCrateSpawn;

    public static void summonCrate() {

        int i = random.nextInt(750);

        if ( i == 1 ) {

            randomCrateMessage();

            MythicMob randomCrate = MythicMobs.inst().getMobManager().getMythicMob("lifeFFA_randomCrate");
            Location spawnLocation = Objects.requireNonNull(Bukkit.getWorld("lifeFFA")).getSpawnLocation();

            int x = spawnLocation.getBlockX() + ((int) Math.floor(Math.random() * 210) - 105);
            int z = spawnLocation.getBlockZ() + ((int) Math.floor(Math.random() * 210) - 105);
            Location location = new Location(Bukkit.getWorld("lifeFFA"), x, 255, z);

            if ( randomCrateSpawn != null ) {

                MythicMobs.inst().getMobManager().unregisterActiveMob(randomCrateSpawn);
                randomCrateSpawn.getEntity().getBukkitEntity().remove();

            }
            randomCrateSpawn = randomCrate.spawn(BukkitAdapter.adapt(location),1);

        }


    }

}
