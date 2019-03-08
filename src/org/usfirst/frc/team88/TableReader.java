package org.usfirst.frc.team88;

import java.io.*;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class TableReader {
	
	private Player player;
	private NetworkTable table;
	private NetworkTableEntry sound;
	
	public static void main(String[] args) throws IOException {
		new TableReader().run();
	}
	
	public TableReader () {
		player = new Player();
	}
	
	public void run() {

		NetworkTableInstance inst = NetworkTableInstance.getDefault();
	    inst.startClientTeam(88); 
	    inst.startDSClient();

	    table = inst.getTable("alerts");
	    sound = table.getEntry("sound");
	    
	    sound.addListener((notification) -> {
	    	player.playSound(notification.value.getString());
	    	sound.setString("");
	    }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
	    
		while (true) {
			try {
				
				if (inst.isConnected()) {
					player.indicateConnected();
				} else {
					player.indicateDisconnected();
				}
				
				Thread.sleep(50);
			} catch (InterruptedException ex) {
			}
		}
	}
	
}