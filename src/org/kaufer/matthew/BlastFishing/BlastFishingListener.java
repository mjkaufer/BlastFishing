package org.kaufer.matthew.BlastFishing;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

public class BlastFishingListener implements Listener{
	private BlastFishing plugin;
	
	String[] messages = new String[]{"SPLOOSH!","Flying fish!","KABLOOIE!", "Now that's what I call fishing!"};
	
	public BlastFishingListener(BlastFishing instance){
		plugin = instance;
	}
	
	@EventHandler(priority=EventPriority.HIGH)
	public void onRightClick(PlayerInteractEvent event){
		
		Player p = event.getPlayer();
		if(!p.isSneaking())//player must be sneaking to use
			return;
		BlockIterator bit = new BlockIterator(p, 8);
		Block end = null;
		while(bit.hasNext())
		{
		    end = bit.next();
		    if(!end.getType().equals(Material.AIR))//we've hit something, and thus our block iterator stops
		    	break;
		}
				
		if(end == null || (!end.getType().equals(Material.WATER) && !end.getType().equals(Material.STATIONARY_WATER))){//if we didn't hit water
			return;
		}
		//so now we know we hit water
//		p.sendMessage("Hit water");
	    if(p.getItemInHand().getType().equals(Material.TNT)){
	    	Location explosionLoc = end.getLocation().subtract(0, 1, 0);
	    	p.getWorld().createExplosion(explosionLoc.getX(), explosionLoc.getY(), explosionLoc.getZ(), 1.5f, false, false);//we can't use a location here because no methods for location with two falses
	    	
	    	int xSize = (int)(Math.random() * 3 + 2);
	    	int zSize = (int)(Math.random() * 3 + 2);
	    	
	    	for(int x = -1*xSize; x <= xSize; x++)//check blocks in a radius
	    		for(int z = -1*zSize; z <= zSize; z++){
	    			
	    			Location flyFrom = end.getLocation().clone().add(x, 0, z);
	    			Material type = p.getWorld().getBlockAt(flyFrom).getType();
	    			
	    			if(!type.equals(Material.WATER) && !type.equals(Material.STATIONARY_WATER))//the block isn't water
	    				continue;//move on
	    			
	    	    	for(int i = 0; i < (int)(Math.random() * Math.random() * Math.random() * Math.random() * 5 + 2); i++){
		    			Item fish = p.getWorld().dropItem(flyFrom.add(Math.random() - 0.5, Math.random() - 0.5, Math.random() - 0.5), new ItemStack(Material.RAW_FISH, 1));
						fish.setVelocity(new Vector(Math.random() - 0.5, Math.random() + 0.5, Math.random() - 0.5).multiply(0.75));
	    	    	}
	    		}

//	    	p.getInventory().remove(new ItemStack(Material.TNT, 1));
	    	ItemStack i = p.getItemInHand();
	    	if(i.getAmount() <= 1)
	    		p.getInventory().removeItem(i);
	    	else
	    		i.setAmount(i.getAmount() - 1);
	    	p.setItemInHand(i);
	    	p.sendMessage(ChatColor.AQUA + messages[(int)(Math.random() * messages.length)]);
	    	
	    }

	}
	
//    @EventHandler

}
