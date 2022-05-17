package fr.minitel.uhc_event;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PluginListener implements Listener {
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		player.sendMessage("§6Bienvenue preux combattant !");
		player.sendTitle("Bienvenue !", "§6-= §cEvenement Minitel §6=-", 10, 50, 20);
	}
	
	@EventHandler
	public void onSignInteract(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		Action action = e.getAction();
		ItemStack item = e.getItem();
		if(item != null && (action == Action.LEFT_CLICK_BLOCK || action == Action.RIGHT_CLICK_BLOCK) && !VariablesGlobales.gameStarted) {
			int l = VariablesGlobales.equipes.size();
			if(item.getType() == Material.BLUE_WOOL) {
				VariablesGlobales.equipes.add(new Equipe("Bleue", "§9"));
				VariablesGlobales.equipes.get(l).addPlayer(VariablesGlobales.equipes, player);
			} else if(item.getType() == Material.RED_WOOL) {
				VariablesGlobales.equipes.add(new Equipe("Rouge", "§c"));
				VariablesGlobales.equipes.get(l).addPlayer(VariablesGlobales.equipes, player);
			} else if(item.getType() == Material.GREEN_WOOL) {
				VariablesGlobales.equipes.add(new Equipe("Verte", "§a"));
				VariablesGlobales.equipes.get(l).addPlayer(VariablesGlobales.equipes, player);
			} else if(item.getType() == Material.ORANGE_WOOL) {
				VariablesGlobales.equipes.add(new Equipe("Orange", "§6"));
				VariablesGlobales.equipes.get(l).addPlayer(VariablesGlobales.equipes, player);
			}
		}
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		Equipe equipePlayer = Equipe.getEquipe(VariablesGlobales.equipes, player);
		String color = "";
		if(equipePlayer == null) {
				color = "§f";
		} else {
			color = equipePlayer.getCouleurEquipe();
		}
		e.setFormat("[" + color + player.getName() + "§f] > " + e.getMessage());
	}
	
	@EventHandler
	public void onPlayerAttacked(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			if(!VariablesGlobales.pvpOn) {
				Bukkit.broadcastMessage("§2PVP pas encore actif !");
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		Bukkit.broadcastMessage("§c" + e.getEntity().getName() + "§a a été tué.");
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1.0f, 1.0f);
		}
	}

	@EventHandler
	public void onUnlockedAchievment(PlayerAdvancementDoneEvent e) {
			Player player = e.getPlayer();
			Equipe equipe = Equipe.getEquipe(VariablesGlobales.equipes, player);
			if(equipe != null && !VariablesGlobales.achi_check.containsKey(e.getAdvancement())) {
				equipe.updateScore(10);
				VariablesGlobales.achi_check.put(e.getAdvancement(), equipe);
				Bukkit.broadcastMessage("§aNouvel achievement débloqué par " + equipe.getCouleurEquipe() + equipe.getNomEquipe() + ".");
				Bukkit.broadcastMessage(equipe.getCouleurEquipe() + equipe.getNomEquipe() + "§f a désormais " + equipe.getScore() + " points.");
				for(Player p : Bukkit.getServer().getOnlinePlayers()) {
					p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_CELEBRATE, 1.0f, 1.0f);
				}
			}
	}
}
