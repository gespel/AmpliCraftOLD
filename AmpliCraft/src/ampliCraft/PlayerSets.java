package ampliCraft;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.entity.Player;

public class PlayerSets {
	public static Set<Player> jnrPlayer = new HashSet<Player>();
	public static Set<Player> elytraPlayer = new HashSet<Player>();
	public static Set<Player> blueTeam = new HashSet<Player>();
	public static Set<Player> redTeam = new HashSet<Player>();
	public static Set<Player> folterkammerPlayer = new HashSet<Player>();
	public static HashMap<Player, Long> folterkammerTimer = new HashMap<Player, Long>();
	public static HashMap<Player, Long> jnrTimer = new HashMap<Player, Long>();
}
