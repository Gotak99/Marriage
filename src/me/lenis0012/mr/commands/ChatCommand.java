package me.lenis0012.mr.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.lenis0012.mr.MPlayer;
import me.lenis0012.mr.Marriage;

public class ChatCommand
{
	public static void perform(Player player, Marriage plugin)
	{
		MPlayer mp = new MPlayer(player);
		if(!mp.isMarried())
		{
			player.sendMessage(ChatColor.RED + "You dont have a partner.");
			return;
		}
		Player op = Bukkit.getServer().getPlayer(mp.getPartner());
		if(op == null)
		{
			player.sendMessage(ChatColor.RED + "Your partner is not online");
			return;
		}
		if(!op.isOnline())
		{
			player.sendMessage(ChatColor.RED + "Your partner is not online");
			return;
		}
		if(!player.hasPermission("marry.chat") && !player.hasPermission("marry.*"))
		{
			player.sendMessage(ChatColor.RED + "No permission.");
			return;
		}
		
		String user = player.getName();
		
		if(plugin.chat.contains(user))
		{
			plugin.chat.remove(user);
			player.sendMessage(ChatColor.RED+"Left partner chat");
		}
		else
		{
			plugin.chat.add(user);
			player.sendMessage(ChatColor.GREEN+"Joined partner chat");
		}
	}
}
