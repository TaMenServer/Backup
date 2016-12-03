package com.tamenserver.backup;

import org.bukkit.plugin.java.JavaPlugin;

import com.tamenserver.backup.command.Backup;
import com.tamenserver.backup.command.BackupCommand;
import com.tamenserver.backup.command.Save;

public class Main extends JavaPlugin {
    
    @Override
    public void onEnable() {
        getLogger().info("Backup Plugin is enabled!");
        BackupCommand.setPlugin(this);
        getCommand("save").setExecutor(new Save());
        getCommand("backup").setExecutor(new Backup());
    }
    
    @Override
    public void onDisable() {
        getLogger().info("Backup Plugin is disabled!");
    }
    
}
