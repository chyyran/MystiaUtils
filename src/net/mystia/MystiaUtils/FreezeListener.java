package net.mystia.MystiaUtils;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class FreezeListener implements Listener
{

	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerMove(PlayerMoveEvent e)
	{

		if (e.getPlayer().hasMetadata("frozen"))
		{
			e.getPlayer().sendMessage(ChatColor.RED + "You have been frozen");
			e.getPlayer().teleport(e.getPlayer());
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerBreakBlock(BlockBreakEvent e)
	{

		if (e.getPlayer().hasMetadata("frozen"))
		{
			e.getPlayer().sendMessage(ChatColor.RED + "You have been frozen");
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerBreakBlock(BlockPlaceEvent e)
	{

		if (e.getPlayer().hasMetadata("frozen"))
		{
			e.getPlayer().sendMessage(ChatColor.RED + "You have been frozen");
			e.setCancelled(true);
		}
	}

}
