package net.mystia.MystiaUtils;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BlockInfoCommand implements CommandExecutor
{

	public BlockInfoCommand(MystiaUtilsMain plugin)
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(!(sender instanceof Player)){
			sender.sendMessage("You cannot run this command from console");
			return true;
		}else{
			Player player = (Player) sender;
			ItemStack item = player.getItemInHand();
			if(item == null){
				player.sendMessage("Error: No item in hand");
			}else{
				player.sendMessage("You are holding a "+ChatColor.DARK_AQUA+item.getType().toString().replace('_',' ').toLowerCase());
				player.sendMessage("This item has an ID of "+ChatColor.DARK_AQUA+item.getTypeId());
				player.sendMessage("This item has a damage value of "+ChatColor.DARK_AQUA+item.getDurability());
				player.sendMessage("This item has a maximum stack of "+ChatColor.DARK_AQUA+item.getMaxStackSize());
				return true;
			}
				
		}
		return true;
	}
	
}
