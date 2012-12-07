package net.mystia.MystiaUtils;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import net.mystia.MystiaUtils.SetExpFix;

public class FlyListener implements Listener
{
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e)
	{
		Player p = e.getPlayer();
		if (p.isFlying())
		{
			if (!p.hasPermission("mystia.flybuild"))
			{
				if (!p.getInventory().contains(Material.NETHER_STAR) && p.getGameMode() != GameMode.CREATIVE)
				{

					e.setCancelled(true);
					p.sendMessage(ChatColor.RED + "You cannot build while flying without a Nether Star");
				}
				else
				{
					if (p.getInventory().contains(Material.NETHER_STAR))
					{
						int exp = SetExpFix.getTotalExperience(p);
						if (exp > 0)
						{
							exp = exp - 1;
							SetExpFix.setTotalExperience(p, exp);
						}
						else
						{

							{
								e.setCancelled(true);
								p.sendMessage(ChatColor.RED + "You do not have enough exp to build while flying");
							}

						}
					}
				}
			}
		}

	}

	@EventHandler
	public void onBlockPlace(BlockBreakEvent e)
	{
		Player p = e.getPlayer();
		if (p.isFlying())
		{
			if (!p.hasPermission("mystia.flybuild"))
			{
				if (!p.getInventory().contains(Material.NETHER_STAR) && p.getGameMode() != GameMode.CREATIVE)
				{

					e.setCancelled(true);
					p.sendMessage(ChatColor.RED + "You cannot build while flying without a Nether Star");
				}
				else
				{
					if (p.getInventory().contains(Material.NETHER_STAR))
					{
						int exp = SetExpFix.getTotalExperience(p);
						System.out.println(exp);
						if (exp > 0)
						{
							exp = exp - 1;
							SetExpFix.setTotalExperience(p, exp);
						}
						else
						{

							{
								e.setCancelled(true);
								p.sendMessage(ChatColor.RED + "You do not have enough exp to build while flying");
							}

						}
					}
				}
			}
		}
	}
}
