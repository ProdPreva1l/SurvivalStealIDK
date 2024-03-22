package info.preva1l.customitems.utils;

import info.preva1l.customitems.CustomItems;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Console {
    private final CustomItems plugin = CustomItems.i();
    public void info(String message, Object... extra) {
        plugin.getLogger().info(Text.message(message,extra));
    }
    public void warn(String message, Object... extra) {
        plugin.getLogger().warning(Text.message(message,extra));
    }
    public void severe(String message, Object... extra) {
        plugin.getLogger().severe(Text.message(message,extra));
    }
}
