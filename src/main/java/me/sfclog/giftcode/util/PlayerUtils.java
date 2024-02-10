package me.sfclog.giftcode.util;

import org.bukkit.entity.Player;
import org.geysermc.floodgate.api.FloodgateApi;

public class PlayerUtils {

    public static boolean is_pe(Player p) {
        return FloodgateApi.getInstance().isFloodgatePlayer(p.getUniqueId());
    }
}
