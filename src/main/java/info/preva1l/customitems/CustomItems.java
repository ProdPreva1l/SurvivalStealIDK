package info.preva1l.customitems;

import fr.mrmicky.fastinv.FastInvManager;
import info.preva1l.customitems.commands.EliminateCommand;
import info.preva1l.customitems.config.Config;
import info.preva1l.customitems.config.Menus;
import info.preva1l.customitems.items.RevivalStone;
import info.preva1l.customitems.utils.BasicConfig;
import info.preva1l.customitems.utils.StorageHelper;
import info.preva1l.customitems.utils.commands.CommandManager;
import lombok.Getter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;


@Getter
public final class CustomItems extends JavaPlugin implements Listener {
    private static CustomItems INSTANCE;

    private BasicConfig configFile;
    private BasicConfig menusFile;

    private CommandManager commandManager;
    @Override
    public void onEnable() {
        INSTANCE = this;

        FastInvManager.register(this);

        loadFiles();
        loadItems();
        loadCommands();

        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        StorageHelper.save();
    }

    private void loadItems() {
        new RevivalStone();
    }

    private void loadCommands() {
        commandManager = new CommandManager(this);

        new EliminateCommand();
    }

    private void loadFiles() {
        configFile = new BasicConfig(this, "config.yml");
        menusFile = new BasicConfig(this, "menus.yml");

        Config.loadDefault();
        Menus.loadDefault();

        StorageHelper.load();
    }


    public static CustomItems i() {
        return INSTANCE;
    }

    @EventHandler
    public void loginListener(AsyncPlayerPreLoginEvent e) {
        if (StorageHelper.isEliminated(e.getUniqueId())) {
            e.setKickMessage(Config.MESSAGE_ELIMINATED.toFormattedString());
            e.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_BANNED);
        }
    }
}
