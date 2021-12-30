package rebelmythik.soulgem.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import rebelmythik.soulgem.SoulGem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SoulGemCMD implements TabExecutor {

    private SoulGem soulGem;
    private NamespacedKey key;

    private List <String> result = new ArrayList<>();
    private List <String> args = new ArrayList<>();


    public SoulGemCMD(NamespacedKey key, SoulGem soulGem) {
        this.key = key;
        this.soulGem = soulGem;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("soulgem.control")) { sender.sendMessage("you're bad"); return true; }
        ItemStack item = soulGem.generateGem(key);
        if (!(sender instanceof Player)) {
            if (args.length > 0 && Bukkit.getPlayer(args[0]) == null) {
                Player otherPlayer = Bukkit.getPlayer(args[0]);
                HashMap<Integer, ItemStack> hashMap = otherPlayer.getInventory().addItem(item);
                if (!hashMap.isEmpty()){
                    otherPlayer.getWorld().dropItem(otherPlayer.getLocation(), item);
                }
            } else {
                sender.sendMessage("you're bad, please send a player to give the thing to yes");
            }
            return true;
        }

        Player player = (Player) sender;
        if (args.length == 0 || Bukkit.getPlayer(args[0]) == null) {

            HashMap<Integer, ItemStack> hashMap = player.getInventory().addItem(item);
            if (!hashMap.isEmpty()) {
                player.getWorld().dropItem(player.getLocation(), item);
            }
            return true;

        }

        Player otherPlayer = Bukkit.getPlayer(args[0]);

        HashMap<Integer, ItemStack> hashMap = otherPlayer.getInventory().addItem(item);
        if (!hashMap.isEmpty()) {

            otherPlayer.getWorld().dropItem(player.getLocation(), item);
            System.out.println(otherPlayer.getName() + " has been given a Soul Gem.");
            otherPlayer.sendMessage(ChatColor.YELLOW + "You have been given a Soul Gem!");
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (sender.hasPermission("soulgem.control")) this.args.add("give");

        if (args.length == 1) {
            for (String ar : this.args) {
                if (ar.toLowerCase().startsWith(args[0].toLowerCase())) result.add(ar);
            }
            return result;
        }

        return null;
    }
}
