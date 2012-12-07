package net.mystia.MystiaUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListCommand implements CommandExecutor
{

	public ListCommand(MystiaUtilsMain plugin)
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		int playerCurrent = Bukkit.getOnlinePlayers().length;
		int playerMax = Bukkit.getMaxPlayers();
		StringBuilder motd = new StringBuilder();
		StringBuilder user = new StringBuilder();
		StringBuilder vip = new StringBuilder();
		StringBuilder superadmin = new StringBuilder();
		StringBuilder admin = new StringBuilder();
		StringBuilder mod = new StringBuilder();
		StringBuilder formerstaff = new StringBuilder();
		Set<Player> onlinePlayers = new HashSet<Player>(Arrays.asList(Bukkit.getServer().getOnlinePlayers()));
		for (Player p : onlinePlayers)
		{
			String group = MystiaUtilsMain.perms.getPrimaryGroup(p);
			if (group.equals("user"))
			{
				user.append(p.getDisplayName() + ChatColor.RESET + " (" + ChatColor.GRAY + p.getName() + ChatColor.RESET + ")\n");
			}
			else
			{
				if (group.equals("vip"))
				{
					vip.append(p.getDisplayName() + ChatColor.RESET + " (" + ChatColor.GOLD + p.getName() + ChatColor.RESET + ")\n");
				}
				else
				{
					if (group.equals("superadmin"))
					{
						superadmin.append(p.getDisplayName() + ChatColor.RESET + " (" + ChatColor.BLUE + p.getName() + ChatColor.RESET + ")\n");
					}
					else
					{
						if (group.equals("admin"))
						{
							admin.append(p.getDisplayName() + ChatColor.RESET + " (" + ChatColor.DARK_RED + p.getName() + ChatColor.RESET + ")\n");
						}
						else
						{
							if (group.equals("mod"))
							{
								mod.append(p.getDisplayName() + ChatColor.RESET + " (" + ChatColor.DARK_GREEN + p.getName() + ChatColor.RESET + ")\n");
							}
							else
							{
								if (group.equals("formerstaff"))
								{
									formerstaff.append(p.getDisplayName() + ChatColor.RESET + " (" + ChatColor.DARK_GRAY + p.getName()
										+ ChatColor.RESET + ")\n");
								}
								else
								{
									user.append(p.getDisplayName() + ChatColor.RESET + " (" +ChatColor.GRAY+ p.getName() + ChatColor.RESET + ")\n");
								}
							}

						}
					}
				}

			}

		}
		
		motd.append(ChatColor.BLUE+"Currently "+ChatColor.RED+playerCurrent+ChatColor.BLUE+" out of "+ChatColor.RED+playerMax+ChatColor.BLUE+" players online at"+ChatColor.GREEN+" Mystia Freebuild\n\n");
		if(superadmin.length()>0){
		motd.append(ChatColor.BLUE+""+ChatColor.ITALIC+"SuperAdmins\n"+superadmin);
		}
		if(admin.length()>0){
		motd.append(ChatColor.DARK_RED+""+ChatColor.ITALIC+"Admins\n"+admin);
		}
		if(mod.length()>0){
		motd.append(ChatColor.DARK_GREEN+""+ChatColor.ITALIC+"Mods\n"+mod);
		}
		if (formerstaff.length()>0){
		motd.append(ChatColor.DARK_GRAY+""+ChatColor.ITALIC+"Former Staff\n"+formerstaff);
		}
		if (vip.length()>0){
		motd.append(ChatColor.GOLD+""+ChatColor.ITALIC+"VIP\n"+vip);
		}
		if (user.length()>0){
		motd.append(ChatColor.GRAY+""+ChatColor.ITALIC+"Users\n"+user);
		}
		sender.sendMessage(motd.toString());
		return true;
	}

}
