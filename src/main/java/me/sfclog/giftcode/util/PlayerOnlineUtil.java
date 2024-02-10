package me.sfclog.giftcode.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerOnlineUtil {

    public static List<String> getOnlinePlayer() {

        List<String> list = new ArrayList<>();
        for(Player p : Bukkit.getOnlinePlayers()) {
            list.add(p.getName());
        }
        return list;
    }
    public static List<String> getValue() {

        List<String> list = new ArrayList<>();

        for(int i = 0 ; i <= 100 ; i++) {
            list.add(String.valueOf(i));
        }

        return list;
    }
}
