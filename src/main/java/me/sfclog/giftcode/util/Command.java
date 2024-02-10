package me.sfclog.giftcode.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Command {


    public static void dispatchCommand(Player player, String command) {
        String type = command.replaceAll("(?ium)^(player:|op:|console:|)(.*)$", "$1").replace(":", "").toLowerCase();
        String cmd = command.replaceAll("(?ium)^(player:|op:|console:|)(.*)$", "$2").replaceAll("(?ium)([{]Player[}])", player.getName());
        byte var5 = -1;
        switch(type.hashCode()) {
            case -985752863:
                if (type.equals("player")) {
                    var5 = 2;
                }
                break;
            case 0:
                if (type.equals("")) {
                    var5 = 1;
                }
                break;
            case 3553:
                if (type.equals("op")) {
                    var5 = 0;
                }
                break;
            case 951510359:
                if (type.equals("console")) {
                    var5 = 3;
                }
        }

        switch(var5) {
            case 0:
                if (player.isOp()) {
                    player.performCommand(cmd);
                } else {
                    player.setOp(true);
                    player.performCommand(cmd);
                    player.setOp(false);
                }
                break;
            case 1:
            case 2:
                player.performCommand(cmd);
                break;
            case 3:
            default:
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
        }
    }
}

