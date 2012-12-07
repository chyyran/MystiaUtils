package net.mystia.MystiaUtils;

import java.io.File;
import java.util.List;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.ensifera.animosity.craftirc.CraftIRC;

public class MystiaUtilsMain extends JavaPlugin
{
	public List<String> readRules = null;
	public static Permission perms = null;
	public static MystiaUtilsMain plugin;
	public static JoinIRCEndpoint endpoint;
	public static CraftIRC craftIRC;
	public void onEnable()
	{
		plugin = this;
		if(Bukkit.getServer().getPluginManager().getPlugin("CraftIRC") != null){
			craftIRC = (CraftIRC) Bukkit.getServer().getPluginManager().getPlugin("CraftIRC");
			endpoint = new JoinIRCEndpoint();
			craftIRC.registerEndPoint("customevent", endpoint);
			getLogger().info("Registered MystiaUtils endpoint");
			}
		File configfile = new File(this.getDataFolder(), "config.yml");
		if (!configfile.exists())
		{
			this.saveDefaultConfig();
		}
		readRules = this.getConfig().getStringList("readrules");
		getCommand("freeze").setExecutor(new FreezeCommand(this));
		getCommand("readrules").setExecutor(new ReadRulesCommand(this));
		getCommand("censor").setExecutor(new CensorCommand(this));
		getCommand("blockcmd").setExecutor(new BlockCommand(this));
		getCommand("stophit").setExecutor(new StopHitCommand(this));
		getCommand("blockinfo").setExecutor(new BlockInfoCommand(this));
		getCommand("list").setExecutor(new ListCommand(this));
		getCommand("unlockfly").setExecutor(new UnlockFlyCommand(this));
		Bukkit.getServer().getPluginManager().registerEvents(new ChatListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new JoinLeaveListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new DeathListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new FreezeListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new CommandListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new WitherListener(),this);
		Bukkit.getServer().getPluginManager().registerEvents(new FlyListener(),this);
		setupPermissions();
	}

	public void onDisable()
	{
		this.getConfig().set("readrules", readRules);
		this.saveConfig();
	}

	private boolean setupPermissions()
	{
		RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
		perms = rsp.getProvider();
		return perms != null;
	}

}
