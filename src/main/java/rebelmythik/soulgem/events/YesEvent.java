package rebelmythik.soulgem.events;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import rebelmythik.soulgem.SoulGem;

public class YesEvent implements Listener {

    private SoulGem soulGem;
    private NamespacedKey key;

    public YesEvent(SoulGem soulGem, NamespacedKey key){
        this.soulGem = soulGem;
        this.key = key;
    }

    @EventHandler(ignoreCancelled = true)
    public void onDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();
        for (ItemStack item : player.getInventory().getContents()) {
            if (item == null) continue;
            if (!item.getType().equals(Material.EMERALD)) continue;
            ItemMeta meta = item.getItemMeta();
            if (meta == null) continue;
            if (meta.getPersistentDataContainer().has(key, PersistentDataType.DOUBLE)) {
                e.setKeepInventory(true);
                e.setKeepLevel(true);
                player.getInventory().remove(item);
                e.setDroppedExp(0);
                e.getDrops().clear();
                return;
            }
        }
    }
}
