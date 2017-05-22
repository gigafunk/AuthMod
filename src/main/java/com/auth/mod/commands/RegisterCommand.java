package com.auth.mod.commands;
import java.awt.TextComponent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import com.auth.mod.Main;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentBase;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;


public class RegisterCommand extends CommandBase {
	
	@Override
	public String getCommandName()
	{
	return "register";
	// Name of the command "test" will be called by "/test"
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender)
	{
	return "/register <password>";
	// Message to show when the user uses "/help test"
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		EntityPlayer player = (EntityPlayer) sender;
		// Turn the sender into a player entity
		if(!Main.passwords.containsKey(player.getName())){
		Main.passwords.put(player.getName(), args[0]);
		player.addChatMessage(makesimpletext("Succefully registered !"));
		Main.logged.add(player.getName());
		} else {
			Main.passwords.put(player.getName(), args[0]);
			player.addChatMessage(makesimpletext("Allready registered."));
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
	
	public static ITextComponent makesimpletext(String text)
	  {
	    return new TextComponentString(text);
	  }
}

