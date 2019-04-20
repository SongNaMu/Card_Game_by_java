package Default;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel1 {
	JPanel panelBoard = new JPanel();
	JButton[] buttons;	
	
	Listen listen = new Listen();
	CardShuffle cardShuffle = new CardShuffle(4);
	ClickCard clickCard = new ClickCard();
	CheckGameEnd gameend = new CheckGameEnd();
	ChanceStack chance = new ChanceStack();
	Search search = new Search();
	
	int check = 0;
	int stopclick = 0;
	
	ImageIcon img = new ImageIcon(".\\img\\card3.gif");//카드뒷면 이미지
	ImageIcon img1 = new ImageIcon(".\\img\\card21.png");
	ImageIcon img2 = new ImageIcon(".\\img\\card22.jpg");
	ImageIcon img3 = new ImageIcon(".\\img\\card23.jpg");
	ImageIcon img4 = new ImageIcon(".\\img\\card24.jpg");
	ImageIcon img5 = new ImageIcon(".\\img\\card25.jpg");
	ImageIcon img6 = new ImageIcon(".\\img\\card26.jpg");
	ImageIcon img7 = new ImageIcon(".\\img\\card27.jpg");
	ImageIcon img8 = new ImageIcon(".\\img\\card28.jpg");
	
	
	
	public GamePanel1() {
		buttons = new JButton[16];
		
		panelBoard.setLayout(new GridLayout(4,4,3,3));
		panelBoard.setMaximumSize(new Dimension(800, 700));
		panelBoard.setPreferredSize(new Dimension(800, 700));
		
		for(int i=0; i<16; i++) {
			buttons[i] = new JButton(""+i);
			buttons[i].setIcon(img);
			buttons[i].setBackground(Color.black);
			buttons[i].addActionListener(listen);
			panelBoard.add(buttons[i]);
		}
	}
	
	
	
	
	private class Listen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int cardNum = Integer.parseInt(((JButton)e.getSource()).getText());
			int pattern = cardShuffle.card[cardNum].getCardPattern();
			int state ;
			int car1num, car2num;
			
			
			clickCard.click(pattern, cardNum);
			
			car1num = clickCard.getFirstNum();
			car2num = clickCard.getSecondNum();
			state = clickCard.checkCard();
			
			Timer m_timer = new Timer();
			TimerTask m_task = new TimerTask() {		// 몇초 후에 실행
				public void run() {
					//System.out.println("카드 틀림!!!");	
					buttons[car1num].setIcon(img);
					buttons[car2num].setIcon(img);
					clickCard.setAll();
					stopclick =0;//다시 버튼 클릭 활성화
				}
			};
			
			if(stopclick ==0) {
				
				clickCard.click(pattern, cardNum);
				openCard(cardNum, pattern);
				
				//찬스가 있으면 찬스 작동
				if(ChanceOnOff.chanceOnOff == 1) {
					int num2 = search.searchPattern(cardShuffle.card, car1num);
					
					//System.out.println("카드 버튼에 들어있는 번호  = " + cardNum +", " );
					
					openCard(num2,cardShuffle.card[num2].getCardPattern());
					buttons[car1num].setEnabled(false);
					buttons[num2].setEnabled(false);
					cardShuffle.card[car1num].setCardState(1);
					cardShuffle.card[num2].setCardState(1);
					clickCard.setAll();
				}
			
			//오픈된 카드들의 짝이 맞는지 확인후 카드삭제, 맞지않으면 다시 뒤집기
				if(state == 1 && ChanceOnOff.chanceOnOff == 0) {
					ClickTime.addClicktime();
					buttons[car1num].setEnabled(false);
					buttons[car2num].setEnabled(false);
					
					cardShuffle.card[car1num].setCardState(1);//카드상태를 뒤집음으로 표시
					cardShuffle.card[car2num].setCardState(1);
					clickCard.setAll();
					chance.increaseStack(); // 찬스스택증가
				}
				
				else if (state == 0 && ChanceOnOff.chanceOnOff == 0) {
					ClickTime.addClicktime();
					stopclick = 1; //카드 보여주는 동안 버튼 클릭 비활성
					m_timer.schedule(m_task, 700);
					chance.decreaseStack(); // 찬스스택 감소
				}
			}
				//System.out.println("클릭한 카드번호 : "+cardNum+" 클릭한카드 패턴: "+pattern + " 카드상태: "+state+" fistnum : "+car1num + " secondnum : "+car2num+"\n\n");
				
				//카드가 모두 뒤집혔는지 아닌지 확인
				check = gameend.checkgameend(cardShuffle.card, 4);
				if(check ==1) {
					JOptionPane.showMessageDialog(null, "스테이지 1 클리어!!!");
					for(int i=0; i<16; i++) {
						buttons[i].setEnabled(true);
						cardShuffle.card[i].setCardState(0);
						panelBoard.remove(buttons[i]);
					}
				}
			}	
		//카드를 뒤집어 주는 메서드
		public void openCard(int cardNum, int pattern) {
			
			switch(pattern) {//카드의 앞면을 보여준다
			case 0 : buttons[cardNum].setIcon(img1);
					break;
			case 1 : buttons[cardNum].setIcon(img2);
					break;
			case 2 : buttons[cardNum].setIcon(img3);
					break;
			case 3 : buttons[cardNum].setIcon(img4);
					break;
			case 4 : buttons[cardNum].setIcon(img5);
					break;
			case 5 : buttons[cardNum].setIcon(img6);
					break;
			case 6 : buttons[cardNum].setIcon(img7);
					break;
			case 7 : buttons[cardNum].setIcon(img8);
					break;
			default :
					break;
			}
		}
	}
}
