package info.preva1l.customitems.guis;

import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import info.preva1l.customitems.CustomItems;
import info.preva1l.customitems.config.Config;
import info.preva1l.customitems.config.Menus;
import info.preva1l.customitems.utils.Console;
import info.preva1l.customitems.utils.Sound;
import info.preva1l.customitems.utils.StorageHelper;
import info.preva1l.customitems.utils.Text;
import info.preva1l.customitems.utils.exceptions.GuiButtonException;
import info.preva1l.customitems.utils.gui.GuiButtonType;
import info.preva1l.customitems.utils.gui.GuiHelper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Skull;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class RevivalStoneGUI extends FastInv {
    private final int page;
    private int index = 0;
    private final List<String> list;

    public RevivalStoneGUI(int page) {
        super(54, Config.REVIVAL_STONE_NAME.toFormattedString() + Text.message(" &8> &fSelect Player"));
        this.page = page;

        this.list = StorageHelper.getElims();

        setItems(getBorders(), new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name(" ").build());

        try {
            int maxItemsPerPage = 28;
            for (int i = 0; i < maxItemsPerPage; i++) {
                index = maxItemsPerPage * page + i;
                if (index >= list.size()) break;
                String playerUUID = list.get(index);
                OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString(playerUUID));
                ItemStack itemStack = new ItemBuilder(Material.PLAYER_HEAD)
                        .name(Text.message("&a" + player.getName()))
                        .lore(Text.message("&fClick to revive this player!")).build();
                SkullMeta meta = (SkullMeta) itemStack.getItemMeta();
                if (player.getName() == null) {
                    meta.setOwningPlayer(Bukkit.getOfflinePlayer("Preva1l"));
                } else {
                    meta.setOwningPlayer(player);
                }
                itemStack.setItemMeta(meta);
                addItem(itemStack, e -> {
                    Sound.click((Player) e.getWhoClicked());
                    e.getWhoClicked().sendMessage(Text.pluginMessage("&f{0} &ahas been revived!", player.getName()));
                    StorageHelper.revive(UUID.fromString(playerUUID));
                    new RevivalStoneGUI(page).open(((Player) e.getWhoClicked()));
                });
            }
            placeNavButtons();
        } catch (GuiButtonException e) { e.printStackTrace(); }
    }

    private void placeNavButtons() throws GuiButtonException {
        setItem(getInventory().getSize() - 5, GuiHelper.constructButton(GuiButtonType.CLOSE), e -> {
            Sound.fail(((Player) e.getWhoClicked()));
            e.getWhoClicked().closeInventory();
        });
        if (page > 0) {
            setItem(getInventory().getSize() - 9, GuiHelper.constructButton(GuiButtonType.PREVIOUS_PAGE), e -> {
                Sound.click((Player) e.getWhoClicked());
                new RevivalStoneGUI(page - 1).open((Player) e.getWhoClicked());
            });
        }
        if (list.size() >= index + 2) {
            setItem(getInventory().getSize() - 1, GuiHelper.constructButton(GuiButtonType.NEXT_PAGE), e -> {
                Sound.click((Player) e.getWhoClicked());
                new RevivalStoneGUI(page + 1).open((Player) e.getWhoClicked());
            });
        }
    }
}
