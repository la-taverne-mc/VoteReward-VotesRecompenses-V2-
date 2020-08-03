package fr.mecopi.votereward.managers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import fr.mecopi.votereward.objects.Effect;
import net.md_5.bungee.api.ChatColor;

public class EventsManager implements Listener
{
	@EventHandler
	public void onConsume(PlayerItemConsumeEvent e)
	{
		Player player = e.getPlayer();
		Effect playerEffect = GlobalManager.getPlayerEffects(player.getUniqueId());
		if(playerEffect != null)
		{
			playerEffect.onConsume(e);
		}
		else
		{
			GlobalManager.sendBarMessage(player, new String(ChatColor.translateAlternateColorCodes('&', "&cVous êtes déjà sous l'effet d'une potion")));
			e.setCancelled(true);
		}
	}
}
