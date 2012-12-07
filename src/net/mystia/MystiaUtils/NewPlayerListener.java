package net.mystia.MystiaUtils;

import org.bukkit.ChatColor;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class NewPlayerListener implements Listener
{

	@EventHandler
	public void onFirstJoin(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		if (!p.hasPlayedBefore())
		{
			p.sendMessage(ChatColor.GREEN + "Welcome to Mystia!");
			p.sendMessage(ChatColor.YELLOW + "Read the rules and info to get a stone kit!");

		}

	}

}