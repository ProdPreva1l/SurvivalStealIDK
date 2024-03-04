package info.preva1l.customitems.items;

import fr.mrmicky.fastinv.ItemBuilder;
import info.preva1l.customitems.config.Config;
import info.preva1l.customitems.guis.RevivalStoneGUI;
import info.preva1l.customitems.utils.Sound;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class RevivalStone extends CustomItem {
    @Override
    public String id() {
        return "revival_stone";
    }

    @Override
    public String name() {
        return Config.REVIVAL_STONE_NAME.toFormattedString();
    }

    @Override
    public List<String> lore() {
        return Config.REVIVAL_STONE_LORE.toLore();
    }

    @Override
    public int model_data() {
        return Config.REVIVAL_STONE_DATA.toInteger();
    }

    @Override
    public ItemStack itemStack() {
        ItemStack itemStack = new ItemBuilder(Material.valueOf(Config.REVIVAL_STONE_ITEM.toString()))
                .name(Config.REVIVAL_STONE_NAME.toFormattedString())
                .lore(Config.REVIVAL_STONE_LORE.toLore())
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
        recipe.shape("NDN","DWD","NDN");
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        recipe.setIngredient('D', Material.DIAMOND_BLOCK);
        recipe.setIngredient('W', Material.WITHER_ROSE);
        return recipe;
    }

    @Override
    public void execute(PlayerEvent e) {
        Sound.growl(e.getPlayer());
        new RevivalStoneGUI(0).open(e.getPlayer());
    }

    @EventHandler
    public void rightClickListener(PlayerInteractEvent e) {
        if (e.getItem() == null) {
            return;
        }
        String pdc = e.getItem().getItemMeta().getPersistentDataContainer().get(namespacedKey(), PersistentDataType.STRING);
        if (pdc == null || !pdc.equalsIgnoreCase(id())) {
            return;
        }
        e.setCancelled(true);
        execute(e);
    }

}
