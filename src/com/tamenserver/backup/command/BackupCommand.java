package com.tamenserver.backup.command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class BackupCommand implements CommandExecutor {
    protected static JavaPlugin plugin;
    
    public static void setPlugin(JavaPlugin plugin) {
        BackupCommand.plugin = plugin;
    }
    
}
