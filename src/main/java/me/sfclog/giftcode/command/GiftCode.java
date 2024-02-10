package me.sfclog.giftcode.command;

import me.sfclog.giftcode.Main;
import me.sfclog.giftcode.config.PluginConfig;
import me.sfclog.giftcode.giftcodemanage.GiftCodeManage;
import me.sfclog.giftcode.gui.Gui;
import me.sfclog.giftcode.playerdata.PlayerDataManage;
import me.sfclog.giftcode.util.Color;
import me.sfclog.giftcode.util.PlayerOnlineUtil;
import me.sfclog.giftcode.util.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.geysermc.floodgate.api.FloodgateApi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GiftCode implements TabExecutor, CommandExecutor {

    @Override
    public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {

            if (arg1.getName().equalsIgnoreCase("giftcode")) {
            if (arg3.length < 1) {
                if (arg0 instanceof Player) {
                    Player p = (Player) arg0;
                    if(Main.pe_support && PlayerUtils.is_pe(p)) {
                        Gui.send_pe(p);
                    } else {
                        Gui.send(p);
                    }
                }
            } else if (arg3[0].equalsIgnoreCase("reset")) {
                if (arg0 instanceof Player) {
                    Player pu = (Player) arg0;
                    if(pu.isOp() || pu.hasPermission("*")) {
                        if (arg3.length == 2) {
                            Player pl = Bukkit.getPlayer(arg3[1]);
                            if (pl != null) {
                                PlayerDataManage.reset(pl);
                                send(arg0,"&aĐã reset lại tất cả mã quà tặng của người chơi &f" + pl.getName() +"&a!");
                            }
                        } else {
                            send(arg0, "&aDùng &f/giftcode reset <player> &ađể xoá tất cả mã quà tặng mà người chơi đã dùng!");
                        }
                    } else {
                        send(arg0,"&cBạn không có quyền.");
                    }
                }
                if (!(arg0 instanceof Player)) {
                    if (arg3.length == 2) {
                        Player pl = Bukkit.getPlayer(arg3[1]);
                        if (pl != null) {
                            PlayerDataManage.reset(pl);
                            send(arg0,"&aĐã reset lại tất cả mã quà tặng của người chơi &f" + pl.getName() +"&a!");
                        }
                    } else {
                        send(arg0, "&aDùng &f/giftcode reset <player> &ađể xoá tất cả mã quà tặng mà người chơi đã dùng!");
                    }
                }

            } else if (arg3[0].equalsIgnoreCase("reload")) {
                if (arg0 instanceof Player) {
                    Player pu = (Player) arg0;
                    if (pu.isOp() || pu.hasPermission("*")) {
                        GiftCodeManage.load();
                        send(arg0,"&aĐã tải lại config.");
                    }
                }
                if (!(arg0 instanceof Player)) {
                    PluginConfig.save();
                    GiftCodeManage.load();
                    send(arg0,"&aĐã tải lại config.");
                }

            } else {
                if (arg0 instanceof Player) {
                    Player pu = (Player) arg0;
                        String giftcode = arg3[0];
                        if (giftcode != null) {
                         GiftCodeManage.check_code_and_give(pu, giftcode);
                   }
                }
             }
           }

        return true;
    }

    private void send(CommandSender arg0, String s) {
        arg0.sendMessage(Color.tran(s));
    }



    @Override
    public List<String> onTabComplete(CommandSender arg0, org.bukkit.command.Command arg1, String arg2, String[] arg3) {
        ArrayList<String> s = new ArrayList<String>();
        s.add("reset");
        s.add("reload");
       if (arg3[0].equalsIgnoreCase("reset")) {
         if (arg3.length == 2) {
            return PlayerOnlineUtil.getOnlinePlayer().stream()
                    .filter(name -> name.startsWith(arg3[1]))
                    .collect(Collectors.toList());
        } else {
            return null;
         }
       }
        return s.stream()
                .filter(name -> name.startsWith(arg3[0]))
                .collect(Collectors.toList());
    }
}
