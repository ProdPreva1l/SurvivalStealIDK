package info.preva1l.customitems.items;

import info.preva1l.customitems.CustomItems;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.List;
public abstract class CustomItem implements Listener {
    private final NamespacedKey key = new NamespacedKey(CustomItems.i(), id());
    public CustomItem() {
        register();
    }
    public abstract String id();
    public abstract String name();
    public abstract List<String> lore();
    public abstract int model_data();
    public abstract ItemStack itemStack();
    public abstract ShapedRecipe craft();

    public abstract void execute(PlayerEvent e);
    public NamespacedKey namespacedKey() {
        return key;
    }
    public void register() {
        CustomItems.i().getServer().getPluginManager().registerEvents(this, CustomItems.i());
        CustomItems.i().getServer().addRecipe(craft());
    }
}