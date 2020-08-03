package fr.mecopi.votereward.managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import fr.mecopi.votereward.Main;
import fr.mecopi.votereward.objects.DroppableItem;
import fr.mecopi.votereward.objects.Effect;

public class FileManager 
{
	public static void Init()
	{
		File globalFolder = Main.Instance.getDataFolder();
		if(!globalFolder.exists()) //Global folder doesn't exist
			globalFolder.mkdir();
		InitSave(globalFolder);
	}
	private static void InitSave(File globalFolder)
	{
		File saveFile = new File(globalFolder.getPath().replace("\\", "/") + "/Save.yml"); //replace \ by / (error path parsing on distant machine)
		if(!(saveFile.exists()))
		{
			try 
			{
				saveFile.createNewFile();
				GlobalManager.Init(buildPotionEffects(saveFile), getDroppableItems());
			} catch (IOException e) { e.printStackTrace();}
		}
		else
			GlobalManager.Init(buildPotionEffects(saveFile), getDroppableItems());
	}
	private static List<DroppableItem> getDroppableItems() //Getting all items from 'config.yml'
	{
		Main.Instance.saveDefaultConfig();
		FileConfiguration configurationFile = Main.Instance.getConfig();
		List<DroppableItem> getDroppableList = new ArrayList<DroppableItem>();
		for(String key : configurationFile.getKeys(false))
		{
			getDroppableList.add(new DroppableItem(Material.getMaterial(key.toUpperCase()), configurationFile.getInt(new String(key + ".amount")), configurationFile.getInt(new String(key + ".percent"))));
		}
		return getDroppableList;
	}

	private static List<Effect> buildPotionEffects(File saveFile) //Create new pot effect from save file (only effective)
	{
		List<Effect> Temp = new ArrayList<Effect>();
		BufferedReader fileReader;
		String Line = "";
		try 
		{ 
			fileReader = new BufferedReader(new FileReader(saveFile)); 
			while((Line = fileReader.readLine()) != null)
			{
				Line = Line.replace(" ", "");
				Temp.add(new Effect(UUID.fromString(Line.split(":")[0]), Line.split(":")[1], Integer.parseInt(Line.split(",")[1])));
			}
			fileReader.close();
		} catch (IOException e) { e.printStackTrace(); }
		return Temp;
	}	
	public static void saveFile() //Save file for a reload issue
	{
		try
		{
			PrintWriter fileWriter = new PrintWriter(new File(Main.Instance.getDataFolder().getPath().replace("\\", "/") + "/Save.yml"), "UTF-8");
			LocalDate NOW = LocalDate.now(); NOW.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
			for(Effect effect : GlobalManager.ongoingPotion)
				fileWriter.write(effect.getOwner().toString() + " : " + effect.getType() + ", " + effect.getRemainingTime());
			fileWriter.close();
		} 
		catch (IOException e) { e.printStackTrace(); }	
	}
	
	
}
