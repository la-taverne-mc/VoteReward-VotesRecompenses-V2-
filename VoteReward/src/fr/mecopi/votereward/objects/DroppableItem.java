package fr.mecopi.votereward.objects;

import org.bukkit.Material;

public class DroppableItem 
{
	private Material Type;
	private int Amount;
	private double Percentage;
	
	public DroppableItem(Material typeEntry, int amountEntry, double percentageEntry)
	{
		Type = typeEntry;
		Amount = amountEntry;
		Percentage = percentageEntry;
	}
	
	//Getters
	
	public Material getType() {
		return Type;
	}
	public int getAmount() {
		return Amount;
	}
	public double getPercentage() {
		return Percentage;
	}
}
