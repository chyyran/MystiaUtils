package net.mystia.MystiaUtils;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener
{

	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerCommand(PlayerCommandPreprocessEvent e)
	{
					if (e.getPlayer().hasMetadata("cmdblock"))
					{
						e.getPlayer().sendMessage(ChatColor.RED + "Commands have been disabled for you");
						e.setCancelled(true);
					}
				}

	

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e)
	{
		if (e.getEntity().getType() == EntityType.PLAYER && e.getDamager().getType() == EntityType.PLAYER)
		{
			Player damager = (Player) e.getDamager();
			if (damager.hasMetadata("nohit"))
			{
				e.setCancelled(true);
				damager.sendMessage(ChatColor.RED + "You have been temporarily prevented from damaging other players");
			}
		}
	}
}
