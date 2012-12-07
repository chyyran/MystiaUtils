package net.mystia.MystiaUtils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveListener implements Listener
{


	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player p = event.getPlayer();
		if (!p.hasPlayedBefore())
		{
			p.sendMessage(ChatColor.GREEN + "Welcome to Mystia!");
			p.sendMessage(ChatColor.YELLOW + "Read the rules with /rules and info to get a starter kit!");

		}
		event.setJoinMessage(null);
		MystiaUtilsMain.plugin.getServer().getScheduler().scheduleAsyncDelayedTask(MystiaUtilsMain.plugin, new JoinMessageRunnable(MystiaUtilsMain.plugin, event), 1);

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		event.setQuitMessage(event.getPlayer().getDisplayName() + ChatColor.YELLOW + " has left the game!");
		MystiaUtilsMain.endpoint.messageOut(event.getPlayer(), "quit");
	}
}
