package info.preva1l.customitems.utils.commands;


import info.preva1l.customitems.CustomItems;
import info.preva1l.customitems.utils.Text;
import lombok.Getter;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
@Getter
public class CommandManager {

    private final CustomItems plugin;

    private final List<Command> loadedCommands = new ArrayList<>();

    public CommandManager(CustomItems plugin) {
        this.plugin = plugin;
    }

    private CommandMap getCommandMap() {
        PluginManager manager = plugin.getServer().getPluginManager();
        try {
            Field field = SimplePluginManager.class.getDeclaredField("commandMap");
            field.setAccessible(true);
            return (CommandMap) field.get(manager);
        } catch (IllegalArgumentException | SecurityException | NoSuchFieldException | IllegalAccessException e) {
            plugin.getLogger().severe(Text.stackTraceToString(e));
        }
        return null;
    }

    public void registerCommand(Command base, Command.CommandExecutor command) {
        if (this.getCommandMap() != null) {
            this.getCommandMap().register(this.plugin.getDescription().getName().toLowerCase(), command);
        }
        this.loadedCommands.add(base);
    }

    public void registerCommand(Command base, Command.CommandExecutor command, List<String> aliases) {
        if (base.getAssigned() == null) return;

        command.getAliases().addAll(aliases);

        if (this.getCommandMap() != null) {
            this.getCommandMap().register(this.plugin.getDescription().getName().toLowerCase(), command);
        }

        this.loadedCommands.removeIf(loaded -> loaded.getAssigned().name().equalsIgnoreCase(base.getAssigned().name()));
        this.loadedCommands.add(base);
    }
}
