package info.preva1l.customitems.items;

import fr.mrmicky.fastinv.ItemBuilder;
import info.preva1l.customitems.config.Config;
import info.preva1l.customitems.utils.Console;
import info.preva1l.customitems.utils.Sound;
import info.preva1l.customitems.utils.Text;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Random;

public class MysticalPurple extends CustomItem {
    private final Random random = new Random();

    @Override
    public String id() {
        return "mystical_purple";
    }

    @Override
    public String name() {
        return Config.PURPLE_NAME.toFormattedString();
    }

    @Override
    public List<String> lore() {
        return Config.PURPLE_LORE.toLore();
    }

    @Override
    public int model_data() {
        return Config.PURPLE_DATA.toInteger();
    }

    @Override
    public ItemStack itemStack() {
        ItemStack itemStack = new ItemBuilder(Material.valueOf(Config.PURPLE_ITEM.toString()))
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
        return null;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void run(Player p, Object... other) {
        Sound.success(p);
        int id = random.nextInt((PotionEffectType.values().length) + 1);
        PotionEffectType effectType = PotionEffectType.getById(id);
        if (effectType == null) {
            Console.severe("Potion type does not exist! {0}", id);
            return;
        }
        PotionEffect potionEffect = new PotionEffect(effectType, 15, 1);

        p.sendMessage(Text.pluginMessage("&fYou have killed &c{0} &fand have received the effect &a{1}&f!",
                ((Player) other[0]).getName(), potionEffect.getType().getName()));
    }

    @EventHandler
    public void playerKillEvent(PlayerDeathEvent e) {
        Player attacker = e.getEntity().getKiller();
        if (attacker != null) run(e.getEntity(), attacker);
    }
}
