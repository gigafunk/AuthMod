package com.auth.mod.events;

import com.auth.mod.Main;
import net.minecraft.util.text.TextFormatting;

import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class PlayerLoggedIn {
	@SubscribeEvent
	  public void PlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent evt) {
		//System.out.println("hfdsuifhdsuifhsdufdshifu");
		Main.posX.put(evt.player.getName(), evt.player.posX);
		Main.posY.put(evt.player.getName(), evt.player.posY);
		Main.posZ.put(evt.player.getName(), evt.player.posZ);
		if(Main.passwords.containsKey(evt.player.getName())){
		evt.player.addChatMessage(new TextComponentString(TextFormatting.RED + "Please use /login <password>"));
		} else {
		evt.player.addChatMessage(new TextComponentString(TextFormatting.RED + "Please use /register <password>"));
		}
	}
}
