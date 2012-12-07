package net.mystia.MystiaUtils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.ChatColor;

public class FreezeCommand implements CommandExecutor
{
	private MystiaUtilsMain plugin;

	public FreezeCommand(MystiaUtilsMain plugin)
	{
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender.hasPermission("mystia.freeze"))
		{
			if (args.length<1)
			{
				return false;
			}
			else
			{
				Player p = Bukkit.getPlayerExact(args[0]);
				if (p != null)
				{
				if (!p.hasPermission("mystia.freeze"))
				{

						if (!p.hasMetadata("frozen"))
						{

							p.setMetadata("frozen", new FixedMetadataValue(plugin, true));
							p.sendMessage(ChatColor.RED + "You have been frozen by " + sender.getName());
							sender.sendMessage(ChatColor.GOLD + "Froze " + p.getName());

						}
						else
						{
							if (p.hasMetadata("frozen"))
							{
								p.removeMetadata("frozen", plugin);
								p.sendMessage(ChatColor.GREEN + "You have been unfrozen");
								sender.sendMessage(ChatColor.GOLD + "Unfroze " + p.getName());
							}
						}
						return true;
					}else{
						sender.sendMessage("You cannot freeze this player");
						return true;
					}
				}else{
					sender.sendMessage("Cannot find player");
					return true;
				}
			}
		}
		return false;
	}
}
