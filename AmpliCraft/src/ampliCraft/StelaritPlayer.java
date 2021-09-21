package ampliCraft;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class StelaritPlayer {
	private Player p;
	private int playerProgress = 0;
	private FileConfiguration config;
	
	public StelaritPlayer(Player p, FileConfiguration config) {
		this.setPlayer(p);
		this.config = config;
		if(config.contains(p.getName() + ".stelaritProgress")) {
			this.playerProgress = config.getInt(p.getName() + ".stelaritProgress");
		}
		else {
			config.set(p.getName() + ".stelaritProgress", 0);
		}
	}
	
	public int getPlayerProgress() {
		return this.playerProgress;
	}
	
	public void setPlayerProgress(int input) {
		this.playerProgress = input;
		config.set(p.getName() + ".stelaritProgress", this.playerProgress);
	}


	public Player getPlayer() {
		return p;
	}


	public void setPlayer(Player p) {
		this.p = p;
	}
}
