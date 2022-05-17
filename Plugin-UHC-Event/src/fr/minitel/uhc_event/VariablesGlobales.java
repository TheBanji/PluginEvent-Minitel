package fr.minitel.uhc_event;

import java.util.*;

import org.bukkit.advancement.Advancement;
import org.bukkit.scoreboard.Scoreboard;

public class VariablesGlobales {
	public static boolean gameStarted;
	public static boolean pvpOn;
	public static boolean registrationOn;
	public static Scoreboard SB;
	public static ArrayList<Equipe> equipes;
	public static HashMap<Advancement, Equipe> achi_check;
}
