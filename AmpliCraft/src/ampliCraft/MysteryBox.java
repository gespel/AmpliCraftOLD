package ampliCraft;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class MysteryBox {
	Player p;
	AmpliCraft plugin;
	public MysteryBox(Player p, AmpliCraft plugin) {
		this.p = p;
		this.plugin = plugin;
	}
	public void openMysteryBox() {
		p.getWorld().spawnParticle(Particle.SPELL, p.getLocation(), 100);
		p.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, p.getLocation(), 100);
		p.getWorld().spawnParticle(Particle.DRIP_LAVA, p.getLocation(), 100);
		p.getWorld().playEffect(p.getLocation(), Effect.SMOKE, 0);
		p.getWorld().playEffect(p.getLocation(), Effect.INSTANT_POTION_BREAK, 0);
		ItemStack LeatherHelmet1 = new ItemStack(Material.LEATHER_HELMET); 
		LeatherArmorMeta meta = (LeatherArmorMeta) LeatherHelmet1.getItemMeta();
		meta.setColor(Color.PURPLE);
		LeatherHelmet1.setItemMeta(meta);

		
	    ItemStack item = new ItemStack(Material.DIAMOND_SWORD, 1);
	    ItemMeta im = item.getItemMeta();
	    im.setDisplayName(ChatColor.RED + "Nashorum");
	    List<String> loreList = new ArrayList<String>();
	    loreList.add(ChatColor.GOLD + "Das legendäre Schwert!");//This is the first line of lore
	    loreList.add(ChatColor.GOLD + "Es wird erzählt es hätte bereits einen Drachen besiegt");//This is the second line of lore
	    im.setLore(loreList);
	    item.setItemMeta(im);
	    p.getInventory().addItem(item);
	    //p.getInventory().addItem(LeatherHelmet1);
	}
}
