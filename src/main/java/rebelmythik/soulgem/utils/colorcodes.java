package rebelmythik.soulgem.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class colorcodes implements Listener {

    private final Pattern pattern = Pattern.compile("#[a-fa-f0-9]{6}");

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        event.setMessage(format(event.getMessage()));

    }

    private String format(String msg) {
        if (Bukkit.getVersion().contains("1.16")) {
            Matcher match = pattern.matcher(msg);
            while (match.find()) {
                String color = msg.substring(match.start(), match.end());
                msg = msg.replace(color, ChatColor.of(color) + "");
                match = pattern.matcher(msg);
            }
        }
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}



