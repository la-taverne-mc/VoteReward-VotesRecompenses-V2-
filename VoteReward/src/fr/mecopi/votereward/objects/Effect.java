package fr.mecopi.votereward.objects;

import java.util.UUID;

import org.bukkit.event.player.PlayerItemConsumeEvent;

public class Effect 
{
	private UUID Owner;
	private String Type;
	private int remainingTime;
	
	public Effect(UUID ownerEntry, String typeEntry, int remainingEntry) //ownerEntry = player name, type entry = creeper, fly .., evident
	{
		Owner = ownerEntry;
		Type = typeEntry;
		remainingTime = remainingEntry;
	}
	
	//Set getters
	
	public UUID getOwner() {
		return Owner;
	}
	public String getType() {
		return Type;
	}
	public int getRemainingTime() {
		return remainingTime;
	}
	
	//Event
	
	public void onConsume(PlayerItemConsumeEvent e)
	{
		
	}
	
}
