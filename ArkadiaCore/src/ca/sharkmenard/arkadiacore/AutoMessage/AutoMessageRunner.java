package ca.sharkmenard.arkadiacore.AutoMessage;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import ca.sharkmenard.arkadiacore.Main;

public class AutoMessageRunner {
	
	private static FileConfiguration messagesFile;
	private static ArrayList<String> messages;
	private static long timebetween;
	private static long timer;
	private static int messageCounter;
	public static void startRunner() {
		timer = System.currentTimeMillis();
		messagesFile = Main.getInstance().getMessages();
		messages = new ArrayList<String>(messagesFile.getStringList("messages"));
		timebetween = messagesFile.getLong("timer");
		messageCounter = 0;
		Bukkit.getScheduler().runTaskTimer(Main.getInstance(),new Runnable() {
			
			@Override
			public void run() {
				if(timer + timebetween * 1000 <= System.currentTimeMillis()) {
					timer = System.currentTimeMillis();
					if(messageCounter >= messages.size()) {
						messageCounter = 0;
					}
					if(!messages.isEmpty())
						Bukkit.broadcastMessage(messages.get(messageCounter).replace('&', '§'));
					messageCounter++;
				}
			}
		}, 20, 20);
	}
	
	public static void updateMessages() {
		messagesFile = Main.getInstance().getMessages();
		messages = new ArrayList<String>(messagesFile.getStringList("messages"));
		timebetween = messagesFile.getLong("timer");
	}

}
