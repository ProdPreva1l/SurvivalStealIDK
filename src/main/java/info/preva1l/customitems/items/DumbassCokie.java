package info.preva1l.customitems.items;

import fr.mrmicky.fastinv.ItemBuilder;
import info.preva1l.customitems.utils.Text;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collections;
import java.util.List;

public class DumbassCokie extends CustomItem {
    @Override
    public String id() {
        return "cookie_rocket";
    }

    @Override
    public String name() {
        return "&4Cookie Rocket";
    }

    @Override
    public List<String> lore() {
        return Collections.singletonList("&7Click to launch someone");
    }

    @Override
    public int model_data() {
        return 0;
    }

    @Override
    public ItemStack itemStack() {
        ItemStack itemStack = new ItemBuilder(Material.COOKIE)
                .name(name())
                .lore(lore())
                .data(model_data())
                .build();
        ItemMeta meta = itemStack.getItemMeta();
        meta.getPersistentDataContainer().set(namespacedKey(), PersistentDataType.STRING, id());
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    @Override
    public ShapedRecipe craft() {
        ShapedRecipe recipe = new ShapedRecipe(namespacedKey(), itemStack());
        recipe.shape(".S.","SCS",".S.");
        recipe.setIngredient('S', Material.SUGAR);
        recipe.setIngredient('C', Material.COOKIE);
        recipe.setIngredient('.', Material.AIR);
        return recipe;
    }

    @Override
    public void run(Player p, Object... other) {
        Location loc = p.getLocation().clone();
        loc.setY(loc.getY()+300);
        p.teleportAsync(loc).thenAcceptAsync((worked)->{
            if (worked) {
                ((Player) other[0]).sendMessage(Text.message("&aLmfao just got fucking launched {0}", p.getName()));
            } else {
                ((Player) other[0]).sendMessage(Text.message("&cwomp womp {0} just is better ig", p.getName()));
            }
        });
    }
    
    @EventHandler
    public void playerHitListener(EntityDamageByEntityEvent e) {
        run((Player) e.getEntity(), e.getDamager());
    }

    @EventHandler
    public void playerClickListener(PlayerInteractEntityEvent e) {
        run((Player) e.getRightClicked(), e.getPlayer());
    }
}
