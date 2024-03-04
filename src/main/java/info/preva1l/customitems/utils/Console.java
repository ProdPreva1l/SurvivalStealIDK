package info.preva1l.customitems.utils;

import info.preva1l.customitems.CustomItems;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Console {
    private final CustomItems plugin = CustomItems.i();
    public void info(String message) {
        plugin.getLogger().info(message);
    }
    public void warn(String message) {
        plugin.getLogger().warning(message);
    }
    public void severe(String message) {
        plugin.getLogger().severe(message);
    }
}
