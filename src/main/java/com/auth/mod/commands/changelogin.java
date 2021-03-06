package com.auth.mod.commands;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import com.auth.mod.Main;
import net.minecraft.util.text.TextFormatting;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;


public class changelogin extends CommandBase {
	
	@Override
	public String getCommandName()
	{
	return "changelogin";
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender)
	{
	return "/changelogin <password> <newpassword>";
	}
	
	 public boolean func_184882_a(MinecraftServer server, ICommandSender sender)
	  {
	    return true;
	  }
	 
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		EntityPlayer player = (EntityPlayer) sender;
		// Turn the sender into a player entity
		if(Main.passwords.get(player.getName()).equals(args[0])){
		Main.passwords.remove(player.getName());
		player.addChatMessage(new TextComponentString(TextFormatting.GREEN + "Password changed !"));
		Main.passwords.put(player.getName(), args[1]);
		} else {
			player.addChatMessage(new TextComponentString(TextFormatting.RED + "Wrong password."));
		}
		
		try{

    		File file = new File("passwords.properties");

    		if(file.delete()){
    			System.out.println(file.getName() + "Removed passwords list for update");
    		}else{
    			System.out.println("A AuthMod operation failed !");
    		}

    	}catch(Exception e){

    		e.printStackTrace();
         }
		
		Map<String, String> ldapContent = Main.passwords;
		Properties properties = new Properties();

		for (Map.Entry<String,String> entry : ldapContent.entrySet()) {
		    properties.put(entry.getKey(), entry.getValue());
		}

		try {
			properties.store(new FileOutputStream("passwords.properties"), null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}


