package net.mystia.MystiaUtils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class UnlockFlyCommand implements CommandExecutor
{

	@SuppressWarnings("unused")
	private MystiaUtilsMain plugin;

	public UnlockFlyCommand(MystiaUtilsMain plugin)
	{
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			String permission = "essentials.fly";
			if (p.hasPermission(permission))
			{
				p.sendMessage("You can already fly");
			}
			else
			{
				if (p.getInventory().contains(Material.GOLD_BLOCK))
				{
					ItemStack item = new ItemStack(Material.GOLD_BLOCK, 1);
					p.getInventory().remove(item);
					System.out.println("Added permission "+ permission+ " to player "+p.getName()+" because of fly unlock");
					MystiaUtilsMain.perms.playerAdd(p, permission);
					p.sendMessage(ChatColor.GREEN + "You can now fly!");
					MystiaUtilsMain.plugin.getServer().broadcastMessage(p.getDisplayName()+ChatColor.GREEN+" can now fly!");
					return true;
				}
				else
				{
					p.sendMessage(ChatColor.YELLOW + "You need a Gold Block to unlock fly privilages");
					return true;
				}

			}
		}else{
			sender.sendMessage("You need to be a player to run this command");
			return true;
		}
		return false;
	}

}
