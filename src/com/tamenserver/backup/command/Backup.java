package com.tamenserver.backup.command;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;



public class Backup extends BackupCommand {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        plugin.getLogger().info("Starting backuping...");
        sender.sendMessage("开始备份");
        Calendar c=Calendar.getInstance();
        String s=c.get(Calendar.YEAR)+"-"+c.get(Calendar.MONTH)+"-"+c.get(Calendar.DAY_OF_MONTH)+"--"+c.get(Calendar.HOUR_OF_DAY)+"-"+c.get(Calendar.MINUTE)+"-"+c.get(Calendar.SECOND);
        File mfolder = plugin.getDataFolder().getAbsoluteFile().getParentFile().getParentFile(), sfolder = new File(plugin.getDataFolder(),s);
        List<File> allfiles = new ArrayList<File>(), add = new ArrayList<File>();
        for (File file:mfolder.listFiles()) {
            if (!(file.getName().endsWith("logs") || file.getName().endsWith("plugins") || file.getName().endsWith(".bat") || file.getName().endsWith(".jar"))) {
                allfiles.add(file);
            }
        }
        while (allfiles.size()!=0) {
            for (File file:allfiles) {
                File temp = new File(file.getAbsolutePath().replace(mfolder.getAbsolutePath(),sfolder.getAbsolutePath()));
                if (file.isFile()) {
                    if (!temp.exists()) {
                        try {
                            if(!temp.getAbsoluteFile().getParentFile().exists()){
                                temp.getAbsoluteFile().getParentFile().mkdirs();
                            }
                            temp.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    InputStreamReader isr = null;
                    OutputStreamWriter osw = null;
                    try {
                        isr = new InputStreamReader(new FileInputStream(file));
                        osw =new OutputStreamWriter(new FileOutputStream(temp));
                        char[] buffer=new char[1024];
                        isr.read(buffer,0,buffer.length);
                        osw.write(buffer, 0, buffer.length);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            isr.close();
                            osw.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }else if (file.isDirectory()) {
                    if (!temp.exists()) {
                        temp.mkdirs();
                    }
                    add.addAll(Arrays.asList(file.listFiles()));
                 }
            }
            allfiles.clear();
            allfiles.addAll(add);
            add.clear();
        }
        plugin.getLogger().info("Backup is finished!");
        sender.sendMessage("备份完成");
        return true;
    }
    
}
