package ampliCraft;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class Events implements Listener {
	private AmpliCraft plugin;
	public Events(AmpliCraft plugin) {
		this.plugin = plugin;
	}/*
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		
	}
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		
	}*/
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if(plugin.config.getString(p.getName() + ".Rank") != null) {
        	plugin.ranks.login(p);
        }
        else {
	        plugin.config.set(p.getName() + ".Exp", 0);
	        plugin.config.set(p.getName() + ".Level", 1);
	        plugin.config.set(p.getName() + ".Rank", "guest");
	        plugin.config.set(p.getName() + ".Money", 100);
	        plugin.saveConfig();
	        plugin.ranks.login(p);
        }
    }
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player p = event.getEntity();
		PVPArena.onDeathCheckForArenaFight(p, event, plugin.config);
	}
	@EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
		Player opfer = (Player) event.getEntity();
		Player angreifer = (Player) event.getDamager();
		if(PlayerSets.blueTeam.contains(opfer) && PlayerSets.blueTeam.contains(angreifer)) {
			event.setCancelled(true);
		}
		if(PlayerSets.redTeam.contains(opfer) && PlayerSets.redTeam.contains(angreifer)) {
			event.setCancelled(true);
		}
        
    }
	@EventHandler
	public void onPlayermove(PlayerMoveEvent event) {
		event.getPlayer().getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, event.getPlayer().getLocation(), 10);
	}
	@EventHandler
	public void onActivate(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getClickedBlock().getLocation().equals(Locations.jnrChest)) {
				if(PlayerSets.jnrPlayer.contains(p)) {
					Game.finishedJnr(p, event, plugin.config);
					event.setCancelled(true);
				}
			}
			else if(event.getClickedBlock().getLocation().equals(Locations.folterkammerChest)) {
				if(PlayerSets.folterkammerPlayer.contains(p)) {
					Game.finishedFolterkammer(p, event, plugin.config);
					event.setCancelled(true);
				}
			}
			else if(event.getClickedBlock().getLocation().equals(Locations.teleporterMainToGamesDoorLower) || event.getClickedBlock().getLocation().equals(Locations.teleporterMainToGamesDoorUpper)) {
				Teleporter.teleportToGamesLobby(p);
				event.setCancelled(true);
			}
			else if(event.getClickedBlock().getLocation().equals(Locations.teleporterGamesToMainDoorLower) || event.getClickedBlock().getLocation().equals(Locations.teleporterGamesToMainDoorUpper)) {
				Teleporter.teleportToWorldDoorToGames(p);
				event.setCancelled(true);
			}
			else if(event.getClickedBlock().getLocation().equals(Locations.teleporterGamesToJnrDoorLower) || event.getClickedBlock().getLocation().equals(Locations.teleporterGamesToJnrDoorUpper)) {
				Game game = new Game(p);
				game.startGame("jnr");
				event.setCancelled(true);
			}
			else if(event.getClickedBlock().getLocation().equals(Locations.teleporterGamesToFolterKammerDoorLower) || event.getClickedBlock().getLocation().equals(Locations.teleporterGamesToFolterKammerDoorUpper)) {
				Game game = new Game(p);
				game.startGame("folterkammer");
				event.setCancelled(true);
			}
			else if(event.getClickedBlock().getLocation().equals(Locations.teleporterGamesToPveArenaDoorLower) || event.getClickedBlock().getLocation().equals(Locations.teleporterGamesToPveArenaDoorUpper)) {
				PVEArena arena = new PVEArena(p, plugin.getConfig(), plugin);
				arena.startFight();
				event.setCancelled(true);
			}
		}
	}
}
