package fr.minitel.uhc_event;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.advancement.Advancement;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	@Override
	public void onEnable() {
		VariablesGlobales.gameStarted = false;
		VariablesGlobales.pvpOn = false;
		VariablesGlobales.registrationOn = true;
		VariablesGlobales.SB = Bukkit.getScoreboardManager().getMainScoreboard();
		VariablesGlobales.equipes = new ArrayList<Equipe>();
		VariablesGlobales.achi_check = new HashMap<Advancement, Equipe>();
		
		System.out.println("[Plugin-UHC-Event] Plugin démarré.");
		
		getCommand("all").setExecutor(new Commandes());
		getCommand("go").setExecutor(new Commandes());
		getServer().getPluginManager().registerEvents(new PluginListener(), this);
	}
	
	@Override
	public void onDisable() {
		System.out.println("[Plugin-UHC-Event] Plugin arrêté.");
	}
}
