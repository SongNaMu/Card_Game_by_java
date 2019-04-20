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
	
	ImageIcon img = new ImageIcon(".\\img\\card3.gif");//ī��޸� �̹���
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
			TimerTask m_task = new TimerTask() {		// ���� �Ŀ� ����
				public void run() {
					//System.out.println("ī�� Ʋ��!!!");	
					buttons[car1num].setIcon(img);
					buttons[car2num].setIcon(img);
					clickCard.setAll();
					stopclick =0;//�ٽ� ��ư Ŭ�� Ȱ��ȭ
				}
			};
			
			if(stopclick ==0) {
				
				clickCard.click(pattern, cardNum);
				openCard(cardNum, pattern);
				
				//������ ������ ���� �۵�
				if(ChanceOnOff.chanceOnOff == 1) {
					int num2 = search.searchPattern(cardShuffle.card, car1num);
					
					//System.out.println("ī�� ��ư�� ����ִ� ��ȣ  = " + cardNum +", " );
					
					openCard(num2,cardShuffle.card[num2].getCardPattern());
					buttons[car1num].setEnabled(false);
					buttons[num2].setEnabled(false);
					cardShuffle.card[car1num].setCardState(1);
					cardShuffle.card[num2].setCardState(1);
					clickCard.setAll();
				}
			
			//���µ� ī����� ¦�� �´��� Ȯ���� ī�����, ���������� �ٽ� ������
				if(state == 1 && ChanceOnOff.chanceOnOff == 0) {
					ClickTime.addClicktime();
					buttons[car1num].setEnabled(false);
					buttons[car2num].setEnabled(false);
					
					cardShuffle.card[car1num].setCardState(1);//ī����¸� ���������� ǥ��
					cardShuffle.card[car2num].setCardState(1);
					clickCard.setAll();
					chance.increaseStack(); // ������������
				}
				
				else if (state == 0 && ChanceOnOff.chanceOnOff == 0) {
					ClickTime.addClicktime();
					stopclick = 1; //ī�� �����ִ� ���� ��ư Ŭ�� ��Ȱ��
					m_timer.schedule(m_task, 700);
					chance.decreaseStack(); // �������� ����
				}
			}
				//System.out.println("Ŭ���� ī���ȣ : "+cardNum+" Ŭ����ī�� ����: "+pattern + " ī�����: "+state+" fistnum : "+car1num + " secondnum : "+car2num+"\n\n");
				
				//ī�尡 ��� ���������� �ƴ��� Ȯ��
				check = gameend.checkgameend(cardShuffle.card, 4);
				if(check ==1) {
					JOptionPane.showMessageDialog(null, "�������� 1 Ŭ����!!!");
					for(int i=0; i<16; i++) {
						buttons[i].setEnabled(true);
						cardShuffle.card[i].setCardState(0);
						panelBoard.remove(buttons[i]);
					}
				}
			}	
		//ī�带 ������ �ִ� �޼���
		public void openCard(int cardNum, int pattern) {
			
			switch(pattern) {//ī���� �ո��� �����ش�
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
