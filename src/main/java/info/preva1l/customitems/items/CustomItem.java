package info.preva1l.customitems.items;

import info.preva1l.customitems.CustomItems;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.List;
public abstract class CustomItem implements Listener {
    private final NamespacedKey key = new NamespacedKey(CustomItems.i(), id());
    public CustomItem() {
        CustomItems.i().getServer().getPluginManager().registerEvents(this, CustomItems.i());
        if (craft() != null) {
            CustomItems.i().getServer().addRecipe(craft());
        }
    }
    public abstract String id();
    public abstract String name();
    public abstract List<String> lore();
    public abstract int model_data();
    public abstract ItemStack itemStack();
    public abstract ShapedRecipe craft();
    public abstract void run(Player p, Object... other);
    public NamespacedKey namespacedKey() {
        return key;
    }
}