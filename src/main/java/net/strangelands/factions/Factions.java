package net.strangelands.factions;

import java.util.logging.Level;

import net.strangelands.factions.classes.Config;
import net.strangelands.factions.classes.Faction;
import net.strangelands.factions.classes.Island;
import net.strangelands.factions.classes.Member;
import net.strangelands.factions.classes.Rank;
import net.strangelands.factions.commands.FCommandEx;
import net.strangelands.factions.timers.DailyTimer;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.strangelands.factions.commands.IsCommandEx;
import net.milkbowl.vault.economy.Economy;

public class Factions extends JavaPlugin {
	
	public static Factions instance;
	public Economy economy;
	public DailyTimer dailyTimer;
	public static Config islandsConfig;
	public static Config factionsConfig;
	public static Config upkeepConfig;
	
	public void onEnable() {
		instance = this;
		ConfigurationSerialization.registerClass(Island.class);
		ConfigurationSerialization.registerClass(Faction.class);
		ConfigurationSerialization.registerClass(Member.class);
		ConfigurationSerialization.registerClass(Rank.class);
		islandsConfig = new Config("islands.yml");
		factionsConfig = new Config("factions.yml");
		upkeepConfig = new Config("upkeep.yml");
		if(!setupEconomy()) {
			Bukkit.getLogger().log(Level.SEVERE, "[FIslands] Economy not found.");
        }
		getCommand("island").setExecutor(new IsCommandEx());
		getCommand("faction").setExecutor(new FCommandEx());
		this.saveDefaultConfig();
		getConfig().options().copyDefaults(true);
		dailyTimer = new DailyTimer();
	//	Bukkit.getPluginManager().registerEvents(new AssaultEventListener(), this);
	}
	
	public void onDisable() {

	}
	
	public Economy getEconomy() {
		return economy;
	}
	
	private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }
	
	public DailyTimer getIsUpkTimer() {
		return dailyTimer;
	}
	
}
