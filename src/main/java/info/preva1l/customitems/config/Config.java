package info.preva1l.customitems.config;

import com.google.common.collect.ImmutableList;
import info.preva1l.customitems.CustomItems;
import info.preva1l.customitems.utils.BasicConfig;
import info.preva1l.customitems.utils.Text;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor
public enum Config {
    /*
        Revival Stone
     */
    REVIVAL_STONE_ITEM("items.revival-stone.item", "BEACON"),
    REVIVAL_STONE_NAME("items.revival-stone.name", "&b&lRevival Stone"),
    REVIVAL_STONE_LORE("items.revival-stone.lore", Collections.singletonList("&7Use this item to revive someone!")),
    REVIVAL_STONE_DATA("items.revival-stone.model-data", 0),
    REVIVAL_STONE_COMMAND("items.revival-stone.command", Collections.singletonList("unban {0} You have been revived!")),


    MESSAGE_NO_PERMISSION("messages.no-permission", "&cInsufficient Permission!"),
    MESSAGE_ELIMINATED("messages.eliminated", "&cYou are eliminated! &f(You will need to be revived)")
    ;

    private final String path;
    private final Object defaultValue;

    public String toString() {
        String str = CustomItems.i().getConfigFile().getString(path);
        if (str.equals(path)) {
            return defaultValue.toString();
        }
        return str;
    }

    public String toFormattedString() {
        String str = CustomItems.i().getConfigFile().getString(path);
        if (str.equals(path)) {
            return Text.message(defaultValue.toString());
        }
        return Text.message(str);
    }

    public List<String> toStringList() {
        List<String> str = CustomItems.i().getConfigFile().getStringList(path);
        if (str.isEmpty() || str.get(0).equals(path)) {
            return (List<String>) defaultValue;
        }
        if (str.get(0).equals("null")) {
            return ImmutableList.of();
        }
        return str;
    }

    public List<String> toLore() {
        List<String> str = CustomItems.i().getConfigFile().getStringList(path);
        if (str.isEmpty() || str.get(0).equals(path)) {
            return Text.lore((List<String>) defaultValue);
        }
        if (str.get(0).equals("null")) {
            return ImmutableList.of();
        }
        return Text.lore(str);
    }

    public boolean toBoolean() {
        return Boolean.parseBoolean(toString());
    }

    public int toInteger() {
        return Integer.parseInt(toString());
    }

    public double toDouble() {
        return Double.parseDouble(toString());
    }

    public static void loadDefault() {
        BasicConfig configFile = CustomItems.i().getConfigFile();

        for (Config config : Config.values()) {
            String path = config.getPath();
            String str = configFile.getString(path);
            if (str.equals(path)) {
                configFile.getConfiguration().set(path, config.getDefaultValue());
            }
        }

        configFile.save();
        configFile.load();
    }
}
