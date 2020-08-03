package fr.mecopi.votereward.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.mecopi.votereward.objects.DroppableItem;
import fr.mecopi.votereward.objects.Effect;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class GlobalManager 
{
	//Static class (Sample Code)
	//Thas, suis des cours, sinon même avec des commentaires, tu y pigeras rien.
	
	public static List<Effect> ongoingPotion = new ArrayList<Effect>();
	public static List<DroppableItem> droppableItems = new ArrayList<DroppableItem>();
	
	public static void Init(List<Effect> effectsEntry, List<DroppableItem> droppableItemsEntry)
	{
		ongoingPotion = effectsEntry;
		droppableItems = droppableItemsEntry;
		for(DroppableItem item : droppableItems)
			Bukkit.getConsoleSender().sendMessage(item.getType().name());
	}
	
	
	public static String sendSucessMessage(String Message) { // Success sample message
		return new String(ChatColor.translateAlternateColorCodes('&', "[&aVoteReward&f] " + Message));
	}
	public static String sendErrorMessage(String Message) { // Error sample message
		return new String(ChatColor.translateAlternateColorCodes('&', "[&cVoteReward&f] " + Message));
	}
	public static Effect getPlayerEffects(UUID Player)
	{
		for(Effect effect : ongoingPotion)
		{
			if(effect.getOwner().equals(Player))
			{
				return effect;
			}
		}
		return null;
	}
	public static void sendBarMessage(Player player, String Message)
	{
		player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(Message));
	}
	
}
