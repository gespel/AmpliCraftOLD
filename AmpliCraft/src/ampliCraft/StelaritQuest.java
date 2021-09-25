package ampliCraft;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;

public class StelaritQuest {
	String id;
	FileConfiguration config;
	String name;
	public QuestType type;
	int rewardExp;
	StelaritPlayer sp;
	Levelsystem questExp;
	public EntityType targetMob;
	int targetMobNumber;
	int killCount = 0;
	String questStartText;
	
	public StelaritQuest(String name, StelaritPlayer sp, FileConfiguration config) {
		if(name.equals("ersteSchritte")) {
			this.id = name;
			this.type = QuestType.KILLQUEST;
			this.name = "Erste Schritte";
			this.sp = sp;
			this.questExp = new Levelsystem(this.sp.getPlayer(), config);
			this.config = config;
			rewardExp = 100;
			targetMob = EntityType.SPIDER;
			targetMobNumber = 3;
			questStartText = ChatColor.BLUE + "Du siehst so aus als würdest du Arbeit suchen? Wir haben vor der Taverne immer wieder Probeme mit Spinnen. Töte 3 davon und du erhälst eine Belohnung!";
		}
	}
	@SuppressWarnings("deprecation")
	public void killedOneQuestMob() {
		killCount++;
		sp.getPlayer().sendMessage(ChatColor.GRAY + "Du hast (" + killCount + "/" + targetMobNumber + ") " + targetMob.getName() + " getötet!");
		if(killCount >= targetMobNumber) {
			this.questFinished();
		}
	}
	public void questFinished() {
		this.sp.getPlayer().sendMessage(ChatColor.GREEN + "Du hast die Quest beendet!");
		questExp.addExp(rewardExp);
		this.sp.removeQuest(id);
	}
	public void triggerStartText() {
		this.sp.getPlayer().sendMessage(questStartText);
	}
}
