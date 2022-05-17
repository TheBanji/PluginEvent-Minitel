package fr.minitel.uhc_event;

import java.util.ArrayList;

import org.bukkit.Bukkit;
//import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class Equipe {
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private String nom;
	private int score;
	private String couleur;
	private ArrayList<Player> membres;
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static Equipe getEquipe(ArrayList<Equipe> equipes, Player p) {
		for(Equipe e : equipes) {
			if(e.membres.contains(p)) {
				Bukkit.broadcastMessage(p.getName() + " est dans l'équipe " + e.getNomEquipe() + ".");
				return e;
			}
		}
		Bukkit.broadcastMessage(p.getName() + " n'appartient à aucune équipe.");
		return null;
	}
	
	
	@SuppressWarnings("deprecation")
	public static void popPlayer(ArrayList<Equipe> equipes, Player p) {
		for(Equipe e : equipes) {
			if(e.membres.contains(p)) {
				int i =	e.membres.indexOf(p);
				e.membres.remove(i);
				Team t = VariablesGlobales.SB.getTeam(e.getNomEquipe());
				t.removePlayer(p);
				return;
			}
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Equipe(String nomEquipe, String couleurEquipe) {
		nom = nomEquipe;
		score = 0;
		couleur = couleurEquipe;
		membres = new ArrayList<Player>();
		VariablesGlobales.SB.registerNewTeam(nom);
		Team t = VariablesGlobales.SB.getTeam(nom);
		//t.setColor(ChatColor.valueOf(couleur));
		t.setPrefix("[" + couleur + nom + "§f] ");
		t.setAllowFriendlyFire(false);
	}
	
	
	@SuppressWarnings("deprecation")
	public void addPlayer(ArrayList<Equipe> equipes, Player p) {
		Bukkit.broadcastMessage(p.getName() + " a rejoint l'équipe " + this.couleur + this.nom);
		popPlayer(equipes, p);
		VariablesGlobales.SB.getTeam(this.nom).addPlayer(p);
		this.membres.add(p);
	}
	
	
	public void updateScore(int pointsGagnes) {
		this.score += pointsGagnes;
	}
	
	
	public int getScore() {
		return this.score;
	}
	
	
	public String getCouleurEquipe() {
		return this.couleur;
	}
	
	
	public String getNomEquipe() {
		return this.nom;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
}
