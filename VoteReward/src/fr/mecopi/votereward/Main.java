package fr.mecopi.votereward;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import fr.mecopi.votereward.managers.EventsManager;
import fr.mecopi.votereward.managers.FileManager;
import fr.mecopi.votereward.managers.GlobalManager;

public class Main extends JavaPlugin
{
	public static Plugin Instance;
	@Override
	public void onEnable()
	{
		Instance = this;
		Bukkit.getPluginManager().registerEvents(new EventsManager(), this);
		FileManager.Init();
		Bukkit.getConsoleSender().sendMessage(GlobalManager.sendSucessMessage("Enabled sucessfully"));
	}
	@Override
	public void onDisable()
	{
		Bukkit.getConsoleSender().sendMessage(GlobalManager.sendSucessMessage("Disabled successfully"));
	}
}
