package me.lewi.deathtimer.managers;

import me.lewi.deathtimer.Death;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ReviveManager {

    private static Death plugin = Death.plugin;

    public static ItemStack getItem() {
        ItemStack item = new ItemStack(Material.valueOf(plugin.getConfig().getString("revive.item.material")));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("revive.item.name")));
        List<String> lore = plugin.getConfig().getStringList("revive.item.lore");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        item.setItemMeta(meta);

        return item;
    }

    public static void useItem(Player p) {
        ItemStack item = p.getInventory().getItemInMainHand();

        if(item.getItemMeta().hasLore() && item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("revive.item.name")))) {
            p.getInventory().getItem(EquipmentSlot.HAND).setAmount(0);
        }
    }

}
