package org.kaufer.matthew.BlastFishing;


import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class BlastFishing extends JavaPlugin{
	public final Logger logger = Logger.getLogger("Minecraft");
	private BlastFishingListener listener = null;
	private PluginDescriptionFile p;
	@Override
	public void onEnable(){
		p = this.getDescription();
		this.logger.info(p.getName() + " V" + p.getVersion() + " has been enabled.");
        PluginManager pm = this.getServer().getPluginManager();
        listener = new BlastFishingListener(this);
        pm.registerEvents((Listener)listener , this);
	}
	
	@Override
	public void onDisable(){
//		PluginDescriptionFile p = this.getDescription();
		this.logger.info(p.getName() + " V" + p.getVersion() + " has been disabled.");   
		getServer().clearRecipes();
	}
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
            Player player = (Player)sender;
            if(cmd.getName().equalsIgnoreCase("blastfishing"))
            {
                player.sendMessage(ChatColor.AQUA + p.getName() + ChatColor.GREEN + " V" + p.getVersion() + ChatColor.AQUA + " , by " + ChatColor.RED + "mjkaufer");
            }
            return false;
            
    }
	
}
