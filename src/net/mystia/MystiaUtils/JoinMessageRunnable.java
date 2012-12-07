package net.mystia.MystiaUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerJoinEvent;




public class JoinMessageRunnable implements Runnable
{
    Event event;
    
    public JoinMessageRunnable(MystiaUtilsMain instance, Event event)
    {
        this.event = event;
    }
    public void run()
    {
        //Somebody is connecting
        if (this.event instanceof PlayerJoinEvent)
        {
            PlayerJoinEvent joinevent = (PlayerJoinEvent) event;
            Player thePlayer = joinevent.getPlayer();
           MystiaUtilsMain.plugin.getServer().broadcastMessage(thePlayer.getDisplayName()+ ChatColor.YELLOW + " has joined the game!");
           joinevent.setJoinMessage(thePlayer.getDisplayName() +ChatColor.YELLOW+ "has joined the game!");
           //Fire IRC Message
           if(MystiaUtilsMain.plugin.getServer().getPluginManager().getPlugin("CraftIRC") != null){
        	MystiaUtilsMain.endpoint.messageOut(thePlayer,"join");
        	   
   			}

    }
}
}