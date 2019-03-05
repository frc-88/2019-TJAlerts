package org.usfirst.frc.team88;

import java.awt.Color;
import java.awt.Graphics;
import java.net.*;

import javax.sound.sampled.*;
import javax.swing.*;

public class Player extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ConnectionIndicator connectionIndicator;
	

	public Player() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("TJAlerts");
        this.setSize(100, 100);
        this.setVisible(true);
        
        connectionIndicator = new ConnectionIndicator();
        this.add(connectionIndicator);
        connectionIndicator.setConnected(false);
        connectionIndicator.setVisible(true);
    }

    public void playSound(String fileName){
        URL url = null;

        System.out.println("fileNickname = " + fileName);
        
        if (fileName == "") {
        	return;
        }
        
        url = this.getClass().getClassLoader().getResource("sounds/" + fileName + ".wav");
        

        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.setFramePosition(0);
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }
    
    public void indicateConnected() {
    	connectionIndicator.setConnected(true);
    }
    
    public void indicateDisconnected() {
    	connectionIndicator.setConnected(false);
    }
    
    class ConnectionIndicator extends JComponent {
    	
    	private boolean m_connected = false;
    	
    	private static final long serialVersionUID = 2L;

    	public void paint(Graphics g) {
    		g.setColor((m_connected ? Color.GREEN : Color.RED));
    		g.fillRect(0, 0, 100, 100);
    	}
    	
    	public void setConnected(boolean connected) {
    		m_connected = connected;
    		repaint();
    	}
	}
    
}
