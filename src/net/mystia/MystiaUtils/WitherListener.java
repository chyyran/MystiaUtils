package net.mystia.MystiaUtils;

import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;


public class WitherListener implements Listener
{

	@EventHandler
	public void onWitherSpawn(CreatureSpawnEvent e){
	
		if(e.getEntityType().equals(EntityType.WITHER) && !e.getEntity().getWorld().getEnvironment().equals(World.Environment.NETHER)){
			e.setCancelled(true);
		}
	}
}
