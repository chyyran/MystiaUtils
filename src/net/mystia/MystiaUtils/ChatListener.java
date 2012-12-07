package net.mystia.MystiaUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.AuthorNagException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredListener;
import com.gmail.nossr50.datatypes.PlayerProfile;
import com.gmail.nossr50.util.Users;

public class ChatListener implements Listener
{
	public static Field fieldRegisteredListenerDotPriority;
	static
	{
		try
		{
			fieldRegisteredListenerDotPriority = RegisteredListener.class.getDeclaredField("priority");
			fieldRegisteredListenerDotPriority.setAccessible(true);
		}
		catch (Exception e)
		{
			Bukkit.getServer().getLogger().severe("A reflection trick is broken!");
		}

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerChat(AsyncPlayerChatEvent e)
	{

		Player p = e.getPlayer();

		e.setCancelled(true);
		e.setFormat(ChatColor.RESET+"[" + ChatColor.DARK_AQUA + "Craft" + ChatColor.RESET + "] " + e.getPlayer().getDisplayName() + ChatColor.RESET + ": ");
		String censorMessage = e.getMessage();
		String origMessage = e.getMessage();
		String permission = "mystia.censor";

		censorMessage = censorMessage.replaceAll("(?i)dick", "male sexual organ");
		censorMessage = censorMessage.replaceAll("(?i)penis", "male sexual organ");
		censorMessage = censorMessage.replaceAll("(?i)cock", "chicken");
		censorMessage = censorMessage.replaceAll("(?i)arse", "buttocks");
		censorMessage = censorMessage.replaceAll("(?i)asshole", "butthole");
		censorMessage = censorMessage.replaceAll("(?i)\\bass\\b", "buttocks");
		censorMessage = censorMessage.replaceAll("(?i)nigger", "dude");
		censorMessage = censorMessage.replaceAll("(?i)bastard", "dude");
		censorMessage = censorMessage.replaceAll("(?i)bitches", "dawgs");
		censorMessage = censorMessage.replaceAll("(?i)bitch", "dawg");
		censorMessage = censorMessage.replaceAll("(?i)fuck", "fawg");
		censorMessage = censorMessage.replaceAll("(?i)damn", "dang");
		censorMessage = censorMessage.replaceAll("(?i)hell", "heck");
		censorMessage = censorMessage.replaceAll("(?i)rape", "rip");
		censorMessage = censorMessage.replaceAll("(?i)cunt", "punt");
		censorMessage = censorMessage.replaceAll("(?i)shite", "feces");
		censorMessage = censorMessage.replaceAll("(?i)shit", "feces");
		censorMessage = censorMessage.replaceAll("(?i)crap", "poop");

		if (Bukkit.getServer().getPluginManager().getPlugin("mcMMO") != null)
		{
			PlayerProfile profile = Users.getProfile(e.getPlayer());
			if (profile.getPartyChatMode() || profile.getAdminChatMode())
			{

				return;
			}
			else
			{
				AsyncPlayerChatEvent monitorOnlyEvent = new AsyncPlayerChatEvent(false, p, ChatColor.translateAlternateColorCodes('&',origMessage), new HashSet<Player>(Arrays.asList(Bukkit
					.getOnlinePlayers())));

				monitorOnlyEvent.setFormat(e.getFormat() + ChatColor.translateAlternateColorCodes('&',origMessage));
				callEventAtMonitorOnly(monitorOnlyEvent);
				Bukkit.getConsoleSender().sendMessage(
					String.format(monitorOnlyEvent.getFormat(), monitorOnlyEvent.getPlayer().getDisplayName(), monitorOnlyEvent.getMessage()));
				for (Player to : e.getRecipients())
				{
					if (to.hasPermission(permission))
					{
						to.sendMessage(e.getFormat() + ChatColor.translateAlternateColorCodes('&',censorMessage));
					}
					else
					{
						to.sendMessage(e.getFormat() + ChatColor.translateAlternateColorCodes('&',origMessage));
					}
				}
			}

		}
		else
		{

			AsyncPlayerChatEvent monitorOnlyEvent = new AsyncPlayerChatEvent(false, p, ChatColor.translateAlternateColorCodes('&',origMessage), new HashSet<Player>(Arrays.asList(Bukkit
				.getOnlinePlayers())));

			monitorOnlyEvent.setFormat(e.getFormat() + origMessage);
			callEventAtMonitorOnly(monitorOnlyEvent);
			Bukkit.getConsoleSender().sendMessage(
				String.format(monitorOnlyEvent.getFormat(), monitorOnlyEvent.getPlayer().getDisplayName(), monitorOnlyEvent.getMessage()));
			for (Player to : e.getRecipients())
			{
				if (to.hasPermission(permission))
				{

					to.sendMessage(e.getFormat() + ChatColor.translateAlternateColorCodes('&', censorMessage));
				}
				else
				{
					to.sendMessage(e.getFormat() + ChatColor.translateAlternateColorCodes('&', origMessage));
				}
			}
		}
	}

	public static void callEventAtMonitorOnly(Event event)
	{
		synchronized (Bukkit.getPluginManager())
		{
			HandlerList handlers = event.getHandlers();
			RegisteredListener[] listeners = handlers.getRegisteredListeners();

			for (RegisteredListener registration : listeners)
			{
				try
				{
					EventPriority priority = (EventPriority) fieldRegisteredListenerDotPriority.get(registration);
					if (priority != EventPriority.MONITOR)
						continue;
				}
				catch (Exception e)
				{
					e.printStackTrace();
					continue;
				}

				// This rest is almost copy pasted from SimplePluginManager in
				// Bukkit:

				if (!registration.getPlugin().isEnabled())
				{
					continue;
				}

				try
				{
					registration.callEvent(event);
				}
				catch (AuthorNagException ex)
				{
					Plugin plugin = registration.getPlugin();

					if (plugin.isNaggable())
					{
						plugin.setNaggable(false);

						String author = "<NoAuthorGiven>";

						if (plugin.getDescription().getAuthors().size() > 0)
						{
							author = plugin.getDescription().getAuthors().get(0);
						}
						Bukkit
							.getServer()
							.getLogger()
							.log(
								Level.SEVERE,
								String.format("Nag author: '%s' of '%s' about the following: %s", author, plugin.getDescription().getName(),
									ex.getMessage()));
					}
				}
				catch (Throwable ex)
				{
					Bukkit
						.getServer()
						.getLogger()
						.log(Level.SEVERE,
							"Could not pass event " + event.getEventName() + " to " + registration.getPlugin().getDescription().getName(), ex);
				}
			}
		}
	}

}
