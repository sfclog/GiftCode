package me.sfclog.giftcode.giftcodemanage;

import me.sfclog.giftcode.Main;
import me.sfclog.giftcode.config.PluginConfig;
import me.sfclog.giftcode.playerdata.PlayerDataManage;
import me.sfclog.giftcode.util.Send;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GiftCodeManage {

    public static HashMap<String,GiftCodeData> cddata = new HashMap<>();

    public static void check_code_and_give(Player p , String code) {
        if(p != null && code != null && !code.isEmpty()) {
            if(cddata.containsKey(code)) {
                GiftCodeData data = cddata.get(code);
                if(data != null ) {
                    if(data.getPerm() != null ? p.hasPermission(data.getPerm()) : true) {
                        if(!PlayerDataManage.player_have_code(p,code)) {

                            if(!data.is_max_using()) {
                                data.use_code(p);
                                Send.send(p, PluginConfig.getlang("Lang.UseCode"));
                                p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 100, 1);
                            } else {
                                Send.send(p, PluginConfig.getlang("Lang.CodeIsMaxUse"));
                                p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK,100,1);
                            }
                        } else {
                            Send.send(p,PluginConfig.getlang("Lang.CodeIsUse"));
                            p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK,100,1);
                        }
                    } else {
                        Send.send(p,PluginConfig.getlang("Lang.NotPerm"));
                        p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK,100,1);
                    }
                }
            } else {
                Send.send(p,PluginConfig.getlang("Lang.InvaidCode"));
                p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK,100,1);
            }
        }
    }


    public static void load() {
        cddata.clear();
        if (PluginConfig.DataFile.getConfigurationSection("GiftCode") != null) {
            for (String s : PluginConfig.DataFile.getConfigurationSection("GiftCode").getKeys(false)) {
                if (s != null) {
                    List<String> commands = PluginConfig.getarray("GiftCode." + s + ".Commands");
                    String perm = PluginConfig.getlang("GiftCode." + s + ".Permission");
                    GiftCodeData data = new GiftCodeData(s,commands != null ? commands : new ArrayList<>());
                    if(data != null) {
                        if(perm == null) {
                            Main.sendlog("&8(&eMã Quà Tặng&8) &aTải mã quà tặng &f" + s + " &a!");
                        } else {
                            data.setPerm(perm);
                            Main.sendlog("&8(&eMã Quà Tặng&8) &aTải mã quà tặng &f" + s + " &avới quyền &f" + perm + "&a!");
                        }
                        cddata.put(s,data);
                    }
                }
            }
        }
    }

    public static boolean notaCode(String code) {
        if(cddata.containsKey(code)) {
            return false;
        }
        return true;
    }
}
