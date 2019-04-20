package Default;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class View extends JFrame {
	
	public JPanel panel1, panelStage, panel, panelGame;
	private JButton[] buttons;
	private JLabel labelStage, labelTime, labelclick;
	private Font f1, f2, f3;
	//게임시작시 시간을 저장
	private int firstTime = (int) System.currentTimeMillis() / 1000;
	

	
	GamePanel1 gamepanel1 = new GamePanel1();
	GamePanel2 gamepanel2 = new GamePanel2();
	ChancePanel chance = new ChancePanel();
	Thread checkTime = new CheckTime();
	Thread nextStage = new NextStage();
	Thread opentime = new OpenTime();
	Thread checkend = new CheckEnd();
	
	public View() {
		setTitle("test");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 830);
		
		panel = new JPanel();
		panel1 = new JPanel();
		panelStage = new JPanel();
		panelGame = new JPanel();
		
		labelStage = new JLabel("스테이지 1");		
		labelTime = new JLabel("시간");
		labelclick = new JLabel("카드 오픈 : 0");
		
		f1 = new Font(null, Font.BOLD, 25);
		f2 = new Font(null, Font.BOLD, 55);
		panel.setLayout(new BorderLayout());
		panel1.setLayout(new BorderLayout());
		
		//setLayout(new BorderLayout());
		//font
		labelTime.setFont(f1);
		labelclick.setFont(f1);
		labelStage.setFont(f2);
		
		//borderlayout에서 레이아웃사이즈 조정
		panel1.setMaximumSize(new Dimension(200, 800));
		panel1.setPreferredSize(new Dimension(200, 800));
		panelStage.setMaximumSize(new Dimension(800, 100));
		panelStage.setPreferredSize(new Dimension(800, 100));
		panelGame.setMaximumSize(new Dimension(800, 700));
		panelGame.setPreferredSize(new Dimension(800, 700));
		
		
		panelStage.setBackground(new Color(255,122,000));
		panel1.setBackground(new Color(118,113,113));
		panel.setBackground(new Color(29,017,96));
		
		panel1.add(labelTime, BorderLayout.NORTH);
		panel1.add(labelclick, BorderLayout.CENTER);
		panel1.add(chance.panelChance, BorderLayout.SOUTH);
		
		panelStage.add(labelStage);
		
		panelGame.add(gamepanel1.panelBoard);
		
		panel.add(panel1,BorderLayout.EAST);
		panel.add(panelStage,BorderLayout.NORTH);
		panel.add(panelGame, BorderLayout.CENTER);
		add(panel);
		
		setVisible(true);
		setResizable(false);
		nextStage.start();
		checkTime.start();
		opentime.start();
		checkend.start();
	}
	
	
	class CheckTime extends Thread{
		public void run() {
			while(true) {
				int secs = ((int)System.currentTimeMillis() / 1000)-firstTime;
				int sec = secs%60;
				int min = secs/60;
				int hour = secs/3600;
				labelTime.setText("시간 : "+hour+":"+min+":"+sec);
				try {
					sleep(500);
				}catch(InterruptedException e) {
				}
			}
		}
	}
	
	class NextStage extends Thread{
		public void run() {
			while(true) {
				
				if(gamepanel1.check == 1) {
					
					change();
					break;
				}
				try{
					sleep(1000);
				} catch(InterruptedException e){
				}
			}
			
		}
	}
	
	class OpenTime extends Thread{
		public void run() {
			while(true) {
				labelclick.setText("카드 오픈 : "+ClickTime.getClicktime());
				try {
					sleep(500);
				} catch(InterruptedException e) {
				}
			}
		}
	}
	
	class CheckEnd extends Thread{
		int secs;
		int score;
		public void run() {
			while(true) {
				
				int secs = ((int)System.currentTimeMillis() / 1000)-firstTime;
				int sec = secs%60;
				int min = secs/60;
				int hour = secs/3600;
				
				if(gamepanel2.check ==1) {
					//System.exit(0);
					dispose();
					score = 10000 - (ClickTime.clicktime*50) - 500*(secs/30);
					JOptionPane.showMessageDialog(null,"클리어 시간 : "+hour+":"+min+":"+sec+"\n카드 오픈 횟수 : "+ClickTime.clicktime + "\n점수 : "+score );
					break;
				}
				try{
					sleep(1000);
				} catch(InterruptedException e){
				}
			}
			
		}
	}
	
	
	
	
	
	
	
	public void change() {
		
		panel.remove(panelGame);
		panel.add(gamepanel2.panelBoard, BorderLayout.CENTER);
		panel.invalidate();
		panel.validate();
		panel.repaint();
		labelStage.setText("스테이지 2");
	}
}