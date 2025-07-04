package net.strangelands.factions.classes;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import net.strangelands.factions.Factions;

public class Config extends YamlConfiguration {
	
	public File file;
	public String path;
	
	public Config(String path) {
		this.path = path;
		file = new File(Factions.instance.getDataFolder(), path);
		try {
			this.load(file);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

	public void save() {
		try {
			this.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void reload() {
		try {
			this.load(file);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
}
