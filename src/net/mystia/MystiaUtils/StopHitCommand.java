package net.mystia.MystiaUtils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.ChatColor;

public class StopHitCommand implements CommandExecutor
{
	private MystiaUtilsMain plugin;

	public StopHitCommand(MystiaUtilsMain plugin)
	{
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender.hasPermission("mystia.stophit"))
		{
			if (args.length<1)
			{
				return false;
			}
			else
			{
				Player p = Bukkit.getPlayer(args[0]);
				if (p != null)
				{
					if (!p.hasMetadata("nohit"))
					{
						p.setMetadata("nohit", new FixedMetadataValue(plugin, true));
						p.sendMessage(ChatColor.RED + "You have temporarily been disabled from harming players by " + sender.getName());
						sender.sendMessage(ChatColor.GOLD + p.getName() + " can no longer harm players");
					}
					else
					{
						if (p.hasMetadata("nohit"))
						{
							p.removeMetadata("nohit", plugin);
							p.sendMessage(ChatColor.GREEN + "Your ability to harm players has been restored.\n"+ChatColor.DARK_GREEN+"We advise you not to abuse it.");
							sender.sendMessage(ChatColor.GOLD + p.getName() + " can harm players again");
						}
					}
					return true;
				}
				else
				{
					sender.sendMessage("Cannot find player");
					return true;
				}

			}
		}

		return false;
	}
}
