package net.mystia.MystiaUtils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ReadRulesCommand implements CommandExecutor
{
	private MystiaUtilsMain plugin;

	public ReadRulesCommand(MystiaUtilsMain plugin)
	{
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (cmd.getName().equalsIgnoreCase("readrules"))
		{
			if (!(sender instanceof Player))
			{
				sender.sendMessage("This command can only be run by a player.");
			}
			else
			{
				Player player = (Player) sender;
				if (!plugin.readRules.contains(player.getName()))
				{
					player.sendMessage(ChatColor.YELLOW + "Thanks for reading the rules!");
					player.sendMessage(ChatColor.GOLD + "If you have any more questions, feel free to ask someone");
					player.sendMessage(ChatColor.GREEN + "Here's your stone tools!");
					player.getInventory().addItem(new ItemStack(Material.STONE_PICKAXE, 1));
					player.getInventory().addItem(new ItemStack(Material.STONE_AXE, 1));
					player.getInventory().addItem(new ItemStack(Material.STONE_SPADE, 1));
					player.getInventory().addItem(new ItemStack(Material.STONE_SWORD, 1));
					player.getInventory().addItem(new ItemStack(Material.STONE_HOE, 1));
					player.getInventory().addItem(new ItemStack(Material.TORCH, 16));
					player.getInventory().addItem(new ItemStack(Material.BREAD, 3));
					plugin.readRules.add(player.getName());
				}
				else
				{
					player.sendMessage(ChatColor.RED + "You already received your starter set!");
					player.sendMessage(ChatColor.GREEN + "If you want to read the rules again, go to spawn");
				}
			}
			return true;
		}
		return false;
	}
}
