package me.sfclog.giftcode.config;


import me.sfclog.giftcode.Main;
import me.sfclog.giftcode.util.Color;
import me.sfclog.giftcode.util.Send;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PluginConfig {

    public static File locate = new File("plugins/GiftCode/", "config.yml");
    public static FileConfiguration DataFile = (FileConfiguration)YamlConfiguration.loadConfiguration(locate);


    public static void loadlang() {
        addlang("Plugin.Prefix","&8(&eMã Quà Tặng&8) ");

        addlang("Gui.GuiPC.Title","&6&lNhập Mã Quà Tặng");

        List<String> line = new ArrayList<>();
        line.add(" ");
        line.add(" &c&lMã Quà Tặng Là Gì?");
        line.add(" ");
        line.add(" &7&oLà một đoạn mã ngẫu nhiên");
        line.add(" &7&odo các Staff có thẩm quyền");
        line.add(" &7&ocấp cho bạn nhân sự kiện");
        line.add(" &7&olớn nào đó hoặc là một sự");
        line.add(" &7&okiện nhỏ!");
        line.add(" ");
        line.add(" &aNhập mã để nhận quà.");
        line.add(" ");
        addlang("Gui.GuiPC.Info",line);


        addlang("Gui.GuiPE.Title","&6&lNhập Mã Quà Tặng");

        List<String> info = new ArrayList<>();
        info.add("&eMã quà tặng là gì?");
        info.add("&7Mã quà tặng là một đoạn mã ngẫu nhiên");
        info.add("&7thường được các Staff có thẩm quyền cấp");
        info.add("&7trong các sự kiện lớn hoặc nhỏ khác nhau");
        info.add("&7chứa một món quà ngẫu nhiên cho người");
        info.add("&7sử dụng mã quà tặng.");

        addlang("Gui.GuiPE.Info",info);
        addlang("Gui.GuiPE.InPut","&bNhập mã quà tặng:");

        addlang("Lang.InvaidCode","&cLỗi &fMã quà tặng không hợp lệ hoặc đã được sử dụng. &c✘");
        addlang("Lang.UseCode","&fBạn đã sử dụng thành công mã quà tặng &a✔");
        addlang("Lang.NotPerm","&cLỗi &fBạn không đủ quyền hạn để dùng mã quà tặng này, vui lòng nâng cấp Rank. &c✘");
        addlang("Lang.CodeIsUse","&cLỗi &fBạn đã sử dụng mã quà tặng này rồi. &c✘");
        addlang("Lang.CodeIsMaxUse","&cLỗi &fMã quà tặng này đã hết lượt sử dụng. &c✘");


        List<String> command = new ArrayList<>();
        command.add("console:points give {player} 1");
        command.add("console:eco give {player} 1000");
        command.add("op:points give {player} 1");
        command.add("player:menu");
        addlang("GiftCode.NOEL2022.Permission","giftcode.vip");
        addlang("GiftCode.NOEL2022.MaxUsing",100);
        addlang("GiftCode.NOEL2022.Commands",command);



        try {
            DataFile.save(locate);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void save() {
        try {
            DataFile.save(locate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<String> getarray(String s) {
        FileConfiguration DataFile = (FileConfiguration)YamlConfiguration.loadConfiguration(locate);
        if(DataFile.contains(s)) {
            List<String> ss = new ArrayList<String>();
            for(String ok : DataFile.getStringList(s)) {
                ss.add(Color.tran(ok));
            }
            return ss;
        }
        return null;
    }
    public static int getint(String s) {
        FileConfiguration DataFile = (FileConfiguration)YamlConfiguration.loadConfiguration(locate);
        if(DataFile.contains(s)) {
            return DataFile.getInt(s);
        }
        return -1;
    }
    public static double getdoubl(String s) {
        FileConfiguration DataFile = (FileConfiguration)YamlConfiguration.loadConfiguration(locate);
        if(DataFile.contains(s)) {
            return DataFile.getDouble(s);
        }
        return 0;
    }
    public static boolean getb(String s) {
        FileConfiguration DataFile = (FileConfiguration)YamlConfiguration.loadConfiguration(locate);
        if(DataFile.contains(s)) {
            return DataFile.getBoolean(s);
        }
        return false;
    }
    public static String getlang(String s) {
        FileConfiguration DataFile = (FileConfiguration)YamlConfiguration.loadConfiguration(locate);
        if(DataFile.contains(s)) {
            return Color.tran(DataFile.getString(s));
        }
        return null;
    }

    public static String getlang_nocolor(String s) {
        FileConfiguration DataFile = (FileConfiguration)YamlConfiguration.loadConfiguration(locate);
        if(DataFile.contains(s)) {
            return DataFile.getString(s);
        }
        return null;
    }

    public static void addlang(String s , double s2) {
        if(!DataFile.contains(s)) {
            DataFile.set(s, s2);
        }
    }
    public static void addlang(String s , Boolean s2) {
        if(!DataFile.contains(s)) {
            DataFile.set(s, s2);
        }
    }
    public static void reload() {
        try {
            DataFile.load(locate);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
    public static void addlang(String s , List<String> s2) {
        if(!DataFile.contains(s)) {
            DataFile.set(s, s2);
        }
    }
    public static void setforcelang(String s , String s2) {
        DataFile.set(s, s2);
    }
    public static void setforcelang(String s, int x) {
        DataFile.set(s, x);
    }
    public static void addlang(String s , String s2) {
        if(!DataFile.contains(s)) {
            DataFile.set(s, s2);
        }
    }
    public static void addlang(String s , int s2) {
        if(!DataFile.contains(s)) {
            DataFile.set(s, s2);
        }
    }


}
