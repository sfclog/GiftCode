package me.sfclog.giftcode.gui;

import me.sfclog.giftcode.Main;
import me.sfclog.giftcode.config.PluginConfig;
import me.sfclog.giftcode.giftcodemanage.GiftCodeManage;
import me.sfclog.giftcode.util.BuildInfo;
import me.sfclog.giftcode.util.Color;
import me.sfclog.giftcode.util.GetHead;
import me.sfclog.giftcode.util.Send;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.geysermc.cumulus.form.CustomForm;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.floodgate.api.player.FloodgatePlayer;

import java.util.Arrays;
import java.util.Collections;

public class Gui {

    public static  void send_pe(Player p) {
        p.playSound(p.getLocation(), Sound.BLOCK_BELL_USE,100,1);
        FloodgatePlayer player = FloodgateApi.getInstance().getPlayer(p.getUniqueId());
        if(player != null) {

            CustomForm.Builder form = CustomForm.builder();
            form.title(PluginConfig.getlang("Gui.GuiPE.Title"));
            form.label(BuildInfo.build(PluginConfig.getarray("Gui.GuiPE.Info")));
            form.input(PluginConfig.getlang("Gui.GuiPE.InPut"), Color.tran("&f"));
            form.validResultHandler(response -> {
               String string = response.asInput();
                if(string != null) {
                    GiftCodeManage.check_code_and_give(p, string);
                }
            });

            player.sendForm(form);
        }

    }
    public static void send(Player p) {
        p.playSound(p.getLocation(), Sound.BLOCK_BELL_USE,100,1);
        ItemStack item = GetHead.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWJkOTg3OTJkZDkyZDk3MTk4OTQzNDFhYzkwMTJhNTg0YzQ0Mjg1NThmZDJjNzEyZjc4ZTVmMGQ0ZGE4NTQ3MCJ9fX0=");
        ItemMeta meta = item.getItemMeta();
        meta.setLore(PluginConfig.getarray("Gui.GuiPC.Info"));
        item.setItemMeta(meta);
        new AnvilGUI.Builder()

                .onClick((slot, stateSnapshot) -> {
                    if(slot != AnvilGUI.Slot.OUTPUT) {
                        return Collections.emptyList();
                    }
                    String code = stateSnapshot.getText();
                    if(code != null) {
                        GiftCodeManage.check_code_and_give(p, code);
                    }
                    return Arrays.asList(AnvilGUI.ResponseAction.close());
          })
         .interactableSlots(AnvilGUI.Slot.INPUT_RIGHT)
         .text(Color.tran("&f"))
         .itemLeft(item)
         .title(PluginConfig.getlang("Gui.GuiPC.Title"))
         .plugin(Main.pl)
         .open(p);
    }
}
