package info.preva1l.customitems.commands;

import info.preva1l.customitems.utils.StorageHelper;
import info.preva1l.customitems.utils.Text;
import info.preva1l.customitems.utils.commands.Command;
import info.preva1l.customitems.utils.commands.CommandArgs;
import info.preva1l.customitems.utils.commands.CommandArguments;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class EliminateCommand extends Command {
    @CommandArgs(name = "eliminate", aliases = {"elim"})
    public void execute(CommandArguments command) {
        String[] args = command.getArgs();
        if (args.length == 0) {
            command.getSender().sendMessage(Text.pluginMessage("&cUsage: /eliminate <player>"));
            return;
        }
        OfflinePlayer player;
        if (args[0].length() > 16) {
            player = Bukkit.getOfflinePlayer(UUID.fromString(args[0]));
        } else {
            player = Bukkit.getOfflinePlayer(args[0]);
        }
        StorageHelper.eliminate(player);
        command.getSender().sendMessage(Text.pluginMessage("&aEliminated &f{0}&a!", player.getName()));
    }
}
