package info.preva1l.customitems.utils;

import info.preva1l.customitems.CustomItems;
import info.preva1l.customitems.config.Config;
import info.preva1l.customitems.utils.exceptions.FileReaderGotFuckedException;
import lombok.experimental.UtilityClass;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@UtilityClass
public class StorageHelper {
    private HashMap<String, String> map;
    private File file;
    public void load() {
        file = new File(CustomItems.i().getDataFolder(), "storage.json");
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    throw new FileReaderGotFuckedException("This shouldnt happen, if it is, your java is weird idk bro");
                }
            } catch (IOException e) {
                CustomItems.i().getPluginLoader().disablePlugin(CustomItems.i());
                return;
            }
        }

        try (FileReader fileReader = new FileReader(file);
                BufferedReader reader = new BufferedReader(fileReader)) {
            String collect = reader.lines().collect(Collectors.joining());
            if (collect.isEmpty()) {
                map = new HashMap<>();
                return;
            }

            map = (HashMap<String, String>) new JSONParser().parse(collect);

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void save() {
        try (FileWriter fileWriter = new FileWriter(file)) {
            JSONObject jsonObject = new JSONObject(map);
            fileWriter.write(jsonObject.toJSONString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminate(OfflinePlayer player) {
        map.put(player.getUniqueId().toString(), "7");
        Player onlinePlayer = player.getPlayer();
        if (onlinePlayer != null) {
            onlinePlayer.kickPlayer(Config.MESSAGE_ELIMINATED.toFormattedString());
        }
    }

    public void revive(UUID uuid) {
        map.remove(uuid.toString(), "7");
    }

    public boolean isEliminated(UUID uuid) {
        return Integer.valueOf(map.getOrDefault(uuid.toString(), "0")) >= 7;
    }

    public List<String> getElims() {
        return map.entrySet().stream().filter((e)-> Integer.parseInt(e.getValue()) >= 7).map(Map.Entry::getKey).toList();
    }
}
