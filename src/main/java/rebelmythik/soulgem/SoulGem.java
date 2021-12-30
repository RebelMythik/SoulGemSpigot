package rebelmythik.soulgem;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import rebelmythik.soulgem.cmds.SoulGemCMD;
import rebelmythik.soulgem.events.YesEvent;
import rebelmythik.soulgem.utils.colorcodes;

import java.util.ArrayList;

public final class SoulGem extends JavaPlugin implements Listener {

    private NamespacedKey key;

    @Override
    public void onEnable() {
        key = new NamespacedKey(this, "gem");
        this.getServer().getPluginManager().registerEvents(new YesEvent(this, key), this);
        this.getCommand("soulgem").setExecutor(new SoulGemCMD(key, this));
        this.getServer().getPluginManager().registerEvents(new colorcodes(), this);
    }

    public ItemStack generateGem(NamespacedKey key) {
        ItemStack soulGem = new ItemStack(Material.EMERALD);
        ItemMeta meta = soulGem.getItemMeta();
        meta.getPersistentDataContainer().set(key, PersistentDataType.DOUBLE, Math.random());
        meta.setDisplayName(ChatColor.GREEN + "Soul Gem");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Keep items and experience upon death at the expense of this item.");
        meta.setLore(lore);
        soulGem.setItemMeta(meta);
        return soulGem;
    }

}
