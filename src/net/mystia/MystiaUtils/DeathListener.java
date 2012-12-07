package net.mystia.MystiaUtils;

import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class DeathListener implements Listener

{
	private HashMap<String, ItemStack> netherstar = new HashMap<String, ItemStack>();
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e)
	{
		ItemStack star = null;
		Boolean removeStar = false;
		Player p = e.getEntity();
		String deathMessageColoured = ChatColor.YELLOW + e.getDeathMessage();
		String deathMessageFormatted = deathMessageColoured.replace(p.getName(), p.getDisplayName());
		e.setDeathMessage(deathMessageFormatted);
		List<ItemStack> drops = e.getDrops();
		for (ItemStack item : drops){
			if(item.getType() == Material.NETHER_STAR){
				netherstar.put(p.getPlayer().getName(),item);
				removeStar = true;
				star = item;
			}
			
		}
		if (removeStar){
			drops.remove(star);
		}
		
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e){
		if(netherstar.containsKey(e.getPlayer().getName())){
			e.getPlayer().getInventory().addItem(netherstar.get(e.getPlayer().getName()));
		}
	}

}
