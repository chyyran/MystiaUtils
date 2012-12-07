package net.mystia.MystiaUtils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.ChatColor;

public class BlockCommand implements CommandExecutor
{
	private MystiaUtilsMain plugin;

	public BlockCommand(MystiaUtilsMain plugin)
	{
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender.hasPermission("mystia.blockcmd"))
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
				if (!p.hasPermission("mystia.blockcmd"))
				{

						if (!p.hasMetadata("cmdblock"))
						{

							p.setMetadata("cmdblock", new FixedMetadataValue(plugin, true));
							p.sendMessage(ChatColor.RED + "You have been frozen by " + sender.getName());
							sender.sendMessage(ChatColor.GOLD + "Disabled all commands for " + p.getName());

						}
						else
						{
							if (p.hasMetadata("cmdblock"))
							{
								p.removeMetadata("cmdblock", plugin);
								p.sendMessage(ChatColor.GREEN + "You may now use commands again");
								sender.sendMessage(ChatColor.GOLD + "Enabled commands for " + p.getName());
							}
						}
						return true;
					}else{
						sender.sendMessage("You cannot block commands for this player");
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
