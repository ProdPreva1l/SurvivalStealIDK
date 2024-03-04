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
public enum Menus {
    /*
        Titles
     */
    MAIN_GUI_TITLE("main.title", "&8[&a?&8] &a&lClaim"),
    CONFIRM_GUI_TITLE("confirm.title", "&8[&a?&8] &a&lClaim &8\u00BB &aConfirm?"),

    /*
        Buttons
     */
    BACK_BUTTON_NAME("back.back.name", "&cGo Back"),
    BACK_BUTTON_LORE("back.back.lore", Collections.singletonList("&7Click to go back")),

    PREVIOUS_BUTTON_NAME("back.previous_page.name", "&c&lPrevious Page"),
    PREVIOUS_BUTTON_LORE("back.previous_page.lore", Collections.singletonList("&7Click to go to the previous page")),

    NEXT_BUTTON_NAME("back.next_page.name", "&a&lNext Page"),
    NEXT_BUTTON_LORE("back.next_page.lore", Collections.singletonList("&7Click to go to the next page")),

    CLOSE_BUTTON_NAME("back.close.name", "&c&l✗ Close"),
    CLOSE_BUTTON_LORE("back.close.lore", Collections.singletonList("&7Click to close the menu")),

    CONFIRM_BUTTON_NAME("back.confirm.name", "&a&lCONFIRM"),
    CONFIRM_BUTTON_LORE("back.confirm.lore", Collections.singletonList("&7Click to confirm")),

    CANCEL_BUTTON_NAME("back.cancel.name", "&c&lCANCEL"),
    CANCEL_BUTTON_LORE("back.cancel.lore", Collections.singletonList("&7Click to cancel")),
    ;

    private final String path;
    private final Object defaultValue;

    public String toString() {
        String str = CustomItems.i().getMenusFile().getString(path);
        if (str.equals(path)) {
            return defaultValue.toString();
        }
        return str;
    }

    public String toFormattedString() {
        String str = CustomItems.i().getMenusFile().getString(path);
        if (str.equals(path)) {
            return Text.message(defaultValue.toString());
        }
        return Text.message(str);
    }

    public List<String> toStringList() {
        List<String> str = CustomItems.i().getMenusFile().getStringList(path);
        if (str.isEmpty() || str.get(0).equals(path)) {
            return (List<String>) defaultValue;
        }
        if (str.get(0).equals("null")) {
            return ImmutableList.of();
        }
        return str;
    }

    public List<String> toLore() {
        List<String> str = CustomItems.i().getMenusFile().getStringList(path);
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
        BasicConfig configFile = CustomItems.i().getMenusFile();

        for (Menus config : Menus.values()) {
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
