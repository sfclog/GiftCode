package me.sfclog.giftcode.util;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class GetHead {



    public static ItemStack getHead(String texture) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        UUID hashAsId = new UUID(texture.hashCode(), texture.hashCode());
        Bukkit.getUnsafe().modifyItemStack(head, "{SkullOwner:{Id:\"" + hashAsId + "\",Properties:{textures:[{Value:\"" + texture + "\"}]}}}");
        return head;
    }
}
