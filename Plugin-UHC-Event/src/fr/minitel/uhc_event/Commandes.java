package fr.minitel.uhc_event;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commandes implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			String command = cmd.getName();
			int length = args.length;
			
			if(command.equalsIgnoreCase("all")) {
				if(length == 0) {
					player.sendMessage("§a/all <msg> §9: §fEnvoi un broadcast à l'ensemble des joueurs.");
				} else {
					StringBuilder str = new StringBuilder();
					for(String s : args) {
						str.append(s + " ");
					}
					Bukkit.broadcastMessage("§c[ADMIN]§f > §6" + str.toString());
				}
				return true;
				
			} else if(command.equalsIgnoreCase("go")) {
				if(!VariablesGlobales.gameStarted) {
					VariablesGlobales.gameStarted = true;
					VariablesGlobales.registrationOn = false;
					Bukkit.broadcastMessage("§aLa partie commence ! :p");
					for(Player p : Bukkit.getServer().getOnlinePlayers()) {
						p.sendTitle("§aGo ! Go !", "§6-= §cBonne chance §6=-", 10, 50, 20);
					}
				} else {
					player.sendMessage("§cLa partie est déjà commencée !");
				}
				return true;
			}
		}
		return false;
	}
}
