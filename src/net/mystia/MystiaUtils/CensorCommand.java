package net.mystia.MystiaUtils;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CensorCommand implements CommandExecutor
{
	private MystiaUtilsMain plugin;

	public CensorCommand(MystiaUtilsMain plugin)
	{
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (cmd.getName().equalsIgnoreCase("censor"))
		{

			if (!(sender instanceof Player))
			{
				plugin.getLogger().info("Only players are supported for this Example Plugin, but you should not do this!!!");
				return true;
			}

			Player player = (Player) sender;
			String permission = "mystia.censor";
			if (!player.hasPermission(permission))
			{
				MystiaUtilsMain.perms.playerAdd(player, permission);
				player.sendMessage(ChatColor.YELLOW + "Profanities are now censored for you");

			}
			else
			{
				MystiaUtilsMain.perms.playerRemove(player, permission);
				player.sendMessage(ChatColor.YELLOW + "Profanities have been uncensored for you");

			}
			return true;

		}
		return false;
	}
}
