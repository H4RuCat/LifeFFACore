package lifeffa.org.example.lifeffacore.listener;

import com.destroystokyo.paper.ParticleBuilder;
import lifeffa.org.example.lifeffacore.LifeFFACore;
import lifeffa.org.example.lifeffacore.util.MathUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitTask;

import java.util.Objects;
import java.util.Random;

import static lifeffa.org.example.lifeffacore.command.PlayerCheckCommand.checkPlayers;

public class HealAreaListener implements Listener {

    public static void healAreaLoc(Location l) {

        for ( Player p: Bukkit.getOnlinePlayers() ) {

            if ( p.getWorld().getName().equals("lifeFFA") ) {

                double x = Math.abs(l.getX() - p.getLocation().getX());
                double y = Math.abs(l.getY() - p.getLocation().getY());
                double z = Math.abs(l.getZ() - p.getLocation().getZ());

                double dis = (x * x) + (y * y) + (z * z);

                if ( dis <= 36.75 ) {

                    double maxHealth = Objects.requireNonNull(Objects.requireNonNull(p.getPlayer()).getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue();

                    p.getPlayer().setHealth(Math.min(p.getPlayer().getHealth() + 2, maxHealth));

                }

            }
        }
    }

    private static void summonHealArea(Location l) {

        Random random = new Random();
        ParticleBuilder builder = Particle.END_ROD.builder().extra(0);

        BukkitTask task = Bukkit.getScheduler().runTaskTimer(LifeFFACore.inst(), () -> {

            for (int i = 0; i < 750; i++ ) {

                Location sphere = MathUtil.getSpherePoint(l.toVector() , 3.5 , Math.toRadians(random.nextInt(360)) , Math.toRadians(random.nextInt(360))).toLocation(Objects.requireNonNull(Bukkit.getWorld("lifeFFA")));
                builder.location(sphere).spawn();

            }

            healAreaLoc(l);

        }, 0, 20);

        Bukkit.getScheduler().runTaskLater(LifeFFACore.inst(), task::cancel, 400);

    }

    private static final Random random = new Random();

    public static void healAreaLottery() {

        int x = random.nextInt(350);

        if ( x == 1 ) {

            checkPlayers();

            int y = random.nextInt(4);

            if ( y == 0 ) {

                Location location = new Location(Bukkit.getWorld("lifeFFA"),104.5, 32.5, -183.5);
                summonHealArea(location);

            }
            if ( y == 1 ) {

                Location location = new Location(Bukkit.getWorld("lifeFFA"),22.5, 39.5, -147.5);
                summonHealArea(location);

            }
            if ( y == 2 ) {

                Location location = new Location(Bukkit.getWorld("lifeFFA"),51.5, 24.5, -257.5);
                summonHealArea(location);

            }

        }
    }
}
