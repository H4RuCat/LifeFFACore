package lifeffa.org.example.lifeffacore.util;

import org.bukkit.util.Vector;

public class MathUtil {

    public static Vector getSpherePoint(Vector c, double r, double rad1, double rad2) {
        return c.clone().add(new Vector(Math.cos(rad1) * Math.cos(rad2), Math.sin(rad2), Math.sin(rad1) * Math.cos(rad2)).multiply(r));
    }

}
