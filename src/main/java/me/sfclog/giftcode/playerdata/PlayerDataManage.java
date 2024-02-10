package me.sfclog.giftcode.playerdata;

import me.sfclog.giftcode.giftcodemanage.GiftCodeManage;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerDataManage {


    public static void save_use_code(Player p,String code) {
        List<String> listcode = getCodeUsing(p);
        listcode.add(code);
        PlayerDataSave.setforcearray("Data." + p.getName() + ".CodeSave",listcode);
        PlayerDataSave.save();
    }

    public static void reset(Player p) {
        PlayerDataSave.setforcearray("Data." + p.getName() + ".CodeSave",null);
        PlayerDataSave.save();
    }

    public static boolean player_have_code(Player p,String code) {
        List<String> list = getCodeUsing(p);
        if(list  != null && !list.isEmpty()) {
            if(list.contains(code)) {
                return true;
            }
        }
        return false;
    }

    public static List<String> getCodeUsing(Player p) {
        List<String> code = PlayerDataSave.getarray("Data." + p.getName() + ".CodeSave");
        if(code != null) {
            code.removeIf(c -> GiftCodeManage.notaCode(c));
            return code;
        }
        return new ArrayList<>();
    }

}
