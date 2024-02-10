package me.sfclog.giftcode;

import me.sfclog.giftcode.command.GiftCode;
import me.sfclog.giftcode.config.PluginConfig;
import me.sfclog.giftcode.giftcodemanage.GiftCodeManage;
import me.sfclog.giftcode.util.Color;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static Plugin pl;

    public static void sendlog(String s) {
        Bukkit.getConsoleSender().sendMessage(Color.tran(s));
    }

    public static boolean pe_support;
    @Override
    public void onEnable() {
        pl = this;
        sendlog(" ");
        sendlog("&eGiftCode &fMã Quà Tặng");
        sendlog("&fAuthor: &bSFC_Log");
        sendlog(" ");
        sendlog("&aPlugin is Enable");
        sendlog(" ");

        if(Bukkit.getPluginManager().getPlugin("floodgate") != null) {
            pe_support = true;
            sendlog("&eFloodGate support!");
        } else {
            pe_support = false;
        }


        PluginConfig.loadlang();
        GiftCodeManage.load();
        this.getCommand("giftcode").setExecutor(new GiftCode());
        this.getCommand("giftcode").setTabCompleter(new GiftCode());


    }


    @Override
    public void onDisable() {
        sendlog(" ");
        sendlog("&eGiftCode &fMã Quà Tặng");
        sendlog("&fAuthor: &bSFC_Log");
        sendlog(" ");
        sendlog("&aPlugin is Disable");
        sendlog(" ");
    }
}
