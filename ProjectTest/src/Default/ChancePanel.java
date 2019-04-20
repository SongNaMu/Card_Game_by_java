package Default;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChancePanel {
	JPanel chanceStack, chanceStack1, chanceStack2, chanceStack3;
	JPanel panelChance = new JPanel();
	JLabel labelStack = new JLabel();
	JLabel labelChanceOnOff = new JLabel();
	ChanceLabel chanceLabel = new ChanceLabel();
	Font f1 = new Font(null, Font.BOLD, 25);
	
	
	public ChancePanel() {
		chanceStack = new JPanel();
		chanceStack1 = new JPanel();
		chanceStack2 = new JPanel();
		chanceStack3 = new JPanel();
		
		panelChance.setLayout(new BorderLayout());
		chanceStack.setLayout(new GridLayout(1,3,3,3));
		
		panelChance.setMaximumSize(new Dimension(200,200));
		panelChance.setPreferredSize(new Dimension(200, 200));
		labelStack.setMaximumSize(new Dimension(200, 50));
		labelStack.setPreferredSize(new Dimension(200, 50));
		chanceStack.setMaximumSize(new Dimension(200, 50));
		chanceStack.setPreferredSize(new Dimension(200, 50));
		chanceStack1.setMaximumSize(new Dimension(65, 50));
		chanceStack1.setPreferredSize(new Dimension(65, 50));
		chanceStack2.setMaximumSize(new Dimension(65, 50));
		chanceStack2.setPreferredSize(new Dimension(65, 50));
		chanceStack3.setMaximumSize(new Dimension(65, 50));
		chanceStack3.setPreferredSize(new Dimension(65, 50));
		
		labelStack.setFont(f1);
		labelStack.setText("Âù½º");
		
		chanceStack.add(chanceStack1, BorderLayout.WEST);
		chanceStack.add(chanceStack2, BorderLayout.CENTER);
		chanceStack.add(chanceStack3, BorderLayout.EAST);
		
		chanceStack.setBackground(Color.BLACK);
		chanceStack1.setBackground(Color.BLACK);
		chanceStack2.setBackground(Color.BLACK);
		chanceStack3.setBackground(Color.BLACK);
		
		panelChance.setBackground(new Color(118,113,113));
		
		panelChance.add(labelStack, BorderLayout.NORTH);
		panelChance.add(chanceStack, BorderLayout.CENTER);
		
		
		chanceLabel.start();
	}
	
	class ChanceLabel extends Thread {
		public void run() {
			while(true) {
				if(ChanceStack.chanceStack == 0) {
					chanceStack1.setBackground(Color.BLACK);
					chanceStack2.setBackground(Color.BLACK);
					chanceStack3.setBackground(Color.BLACK);
					try {
						Thread.sleep(300);
					}catch(InterruptedException e) {
					}
				}
				else if(ChanceStack.chanceStack == 1) {
					chanceStack1.setBackground(new Color(234,0,44));
					chanceStack2.setBackground(Color.BLACK);
					chanceStack3.setBackground(Color.BLACK);
				}
				else if(ChanceStack.chanceStack == 2) {
					chanceStack1.setBackground(new Color(234,0,44));
					chanceStack2.setBackground(new Color(234,0,44));
					chanceStack3.setBackground(Color.BLACK);
				}
				if(ChanceStack.chanceStack == 3 || ChanceOnOff.chanceOnOff == 1) {
					chanceStack1.setBackground(new Color(234,0,44));
					chanceStack2.setBackground(new Color(234,0,44));
					chanceStack3.setBackground(new Color(234,0,44));
					try {
						Thread.sleep(300);
					}catch(InterruptedException e) {
					}
				}
			}
			
		}
	}

}
