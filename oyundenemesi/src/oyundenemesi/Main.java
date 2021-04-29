package oyundenemesi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
	
	
	public static void main(String[] args) {
		
		JFrame settings = new JFrame("SETTINGS SCREEN");
		JPanel panel = new JPanel();
		Game game = new Game();
		JButton enter = new JButton("ENTER GAME");
		JButton howtop = new JButton("HOW TO PLAY");
		JButton exit = new JButton("EXIT");
		String labelText = "                 SETTINGS";
		JLabel label = new JLabel(labelText);
		JLabel emptylabel = new JLabel();
		JLabel emptylabel2 = new JLabel();
		
		// SETTINGS SCREEN
		settings.setResizable(false);
		settings.setVisible(true);
		settings.setSize(500,500);
		settings.setLocationRelativeTo(null);
		
		panel.setLayout(new GridLayout(10,500,5,1));
		panel.setBackground(Color.CYAN);
			
		label.setFont(new Font("SanSerif",Font.BOLD,30));
		label.setForeground(Color.BLACK);
		label.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5,true));
		
		enter.setBackground(Color.YELLOW);
		enter.setForeground(Color.BLACK);
		enter.setFont(new Font("SanSerif",Font.BOLD,20));
		
		howtop.setBackground(Color.YELLOW);
		howtop.setForeground(Color.BLACK);
		howtop.setFont(new Font("SanSerif",Font.BOLD,20));
		howtop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new JOptionPane().showMessageDialog(null, "To move use the arrow keys\n To rotate the gun use 'A' and 'D' keys: 'A'-> counter-clockwise --- 'D'-> clockwise\n"
						+ " More you kill enemy more chance to get reward (like ammo and health)");
				
			}
		});
		
		exit.setBackground(Color.YELLOW);
		exit.setForeground(Color.BLACK);
		exit.setFont(new Font("SanSerif",Font.BOLD,20));
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				
			}
		});
		
		
		panel.add(label);
		panel.add(emptylabel);
		panel.add(emptylabel2);
		panel.add(enter);
		panel.add(howtop);
		panel.add(exit);
		
		settings.add(panel);
		
		// ENTERING THE GAME
		enter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				settings.setVisible(false);
				
				JFrame mainframe = new JFrame("PENCERE");
				mainframe.add(game);
				
				//mainframe.setResizable(false);
				//mainframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
				mainframe.setSize(600, 600);
				mainframe.setLocation(200,200);
				mainframe.setVisible(true);
				mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
				
			}
		});
		
		
	}

}
