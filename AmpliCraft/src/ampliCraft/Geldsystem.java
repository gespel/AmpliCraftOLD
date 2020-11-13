package ampliCraft;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Geldsystem {
	Player p; 
	FileConfiguration config;
	public Geldsystem(Player p, FileConfiguration config) {
		this.p = p;
		this.config = config;
	}
	public void getMoney() {
		float geld = Float.parseFloat(config.getString(p.getName() + ".Money"));
		p.sendMessage("Du hast " + ChatColor.GREEN + geld + ChatColor.RESET + " €");
	}
	public static void zinsenTick(FileConfiguration config) {
		for(String player : config.getConfigurationSection("").getKeys(false)) {
			float geld = Float.parseFloat(config.getString(player + ".Money"));
			geld *= 1.002;
			config.set(player + ".Money", Float.toString(geld));
		}
	}
	public void sendMoney(String recvname, float menge) {
		Player recv = Bukkit.getPlayer(recvname);
		float sendergeld = Float.parseFloat(config.getString(p.getName() + ".Money"));
		float empfgeld = Float.parseFloat(config.getString(recv.getName() + ".Money"));
		if(sendergeld - menge >= 0) {
			empfgeld += menge;
			sendergeld -= menge;
			config.set(p.getName() + ".Money", Float.toString(sendergeld));
			config.set(recv.getName() + ".Money", Float.toString(empfgeld));
			p.sendMessage("Du hast " + recv.getName() + " " + ChatColor.GREEN + menge + ChatColor.RESET + " € geschickt!");
			recv.sendMessage("Du hast von " + p.getName() + " " + ChatColor.GREEN + menge + ChatColor.RESET + " € erhalten!");
		}
		else {
			p.sendMessage(ChatColor.RED + "So viel Geld hast du nicht!");
		}
	}
	public void setMoney(String clientname, float betrag) {
		Player client = Bukkit.getPlayer(clientname);
		config.set(client.getName() + ".Money", Float.toString(betrag));
		p.sendMessage("Das Geld von " + client.getName() + " wurde auf " + ChatColor.RED + betrag + ChatColor.RESET + " gesetzt!");
		client.sendMessage("Dein Geld wurde auf " + ChatColor.RED + betrag + ChatColor.RESET + " gesetzt!");
	}
}
