package info.preva1l.customitems.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;

@UtilityClass
public class Sound {
    /**
     * Plays a "success" sound.
     * @param player Player to play the sound for
     */
    public void success(Player player) { player.playSound(player.getLocation(), org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, 20, 2); }

    /**
     * Plays a "fail" sound.
     * @param player Player to play the sound for
     */
    public void fail(Player player) { player.playSound(player.getLocation(), org.bukkit.Sound.ENTITY_VILLAGER_NO, 20, 2); }

    /**
     * Plays a "click" sound.
     * @param player Player to play the sound for
     */
    public void click(Player player) { player.playSound(player.getLocation(), org.bukkit.Sound.UI_BUTTON_CLICK, 20, 2); }

    /**
     * Plays a "level up" sound.
     * @param player Player to play the sound for
     */
    public void levelUp(Player player) { player.playSound(player.getLocation(), org.bukkit.Sound.ENTITY_PLAYER_LEVELUP, 20, 2); }
    /**
     * Plays a "growl" sound.
     * @param player Player to play the sound for
     */
    public void growl(Player player) { player.playSound(player.getLocation(), org.bukkit.Sound.ENTITY_ENDER_DRAGON_GROWL, 20, 2); }
}