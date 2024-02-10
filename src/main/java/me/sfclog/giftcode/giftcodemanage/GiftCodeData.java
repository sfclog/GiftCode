package me.sfclog.giftcode.giftcodemanage;

import me.sfclog.giftcode.config.PluginConfig;
import me.sfclog.giftcode.playerdata.PlayerDataManage;
import me.sfclog.giftcode.util.Command;
import org.bukkit.entity.Player;

import java.util.List;

public class GiftCodeData {

    public String code;
    public List<String> commands;

    public String perm;

    public GiftCodeData(String code , List<String> commands) {
        this.code = code;
        this.commands = commands;
        this.perm = null;
    }
    public void setPerm(String perm) {
        this.perm = perm;
    }
    public String getPerm() {
        return this.perm;
    }


    public boolean is_max_using() {
        int max = PluginConfig.getint("GiftCode." + getCode() + ".MaxUsing");
        if(!(max <= -1)) {
            int i = max - 1;
            if(!(i < 0)) {
                PluginConfig.setforcelang("GiftCode." + getCode() + ".MaxUsing",i);
                PluginConfig.save();
                return false;
            }
        }
        return true;
    }

    public String getCode() {
        return this.code;
    }
    public void use_code(Player p) {
        if(!this.commands.isEmpty()) {
            for(String cmd : this.commands) {
                if(cmd != null) {
                    Command.dispatchCommand(p,cmd);
                }
            }
        }
        PlayerDataManage.save_use_code(p,this.code);
    }
}
