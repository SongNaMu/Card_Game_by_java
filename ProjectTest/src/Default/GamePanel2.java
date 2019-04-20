package Default;

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


public class GamePanel2 {

	JPanel panelBoard = new JPanel();
	JButton[] buttons;	
	
	Listen listen = new Listen();
	CardShuffle model = new CardShuffle(6);
	ClickCard clickCard = new ClickCard();
	CheckGameEnd gameend = new CheckGameEnd();
	ChanceStack chance = new ChanceStack();
	Search search = new Search();
	
	int stopclick = 0;
	int check = 0;
	
	ImageIcon img = new ImageIcon(".\\img\\card3.gif");//ī��޸� �̹���
	ImageIcon img1 = new ImageIcon(".\\img\\card01.png");
	ImageIcon img2 = new ImageIcon(".\\img\\card02.jpg");
	ImageIcon img3 = new ImageIcon(".\\img\\card03.jpg");
	ImageIcon img4 = new ImageIcon(".\\img\\card04.jpg");
	ImageIcon img5 = new ImageIcon(".\\img\\card05.jpg");
	ImageIcon img6 = new ImageIcon(".\\img\\card06.jpg");
	ImageIcon img7 = new ImageIcon(".\\img\\card07.jpg");
	ImageIcon img8 = new ImageIcon(".\\img\\card08.jpg");
	ImageIcon img9 = new ImageIcon(".\\img\\card09.jpg");
	ImageIcon img10 = new ImageIcon(".\\img\\card10.jpg");
	ImageIcon img11 = new ImageIcon(".\\img\\card11.jpg");
	ImageIcon img12 = new ImageIcon(".\\img\\card12.jpg");
	ImageIcon img13 = new ImageIcon(".\\img\\card13.jpg");
	ImageIcon img14 = new ImageIcon(".\\img\\card14.jpg");
	ImageIcon img15 = new ImageIcon(".\\img\\card15.jpg");
	ImageIcon img16 = new ImageIcon(".\\img\\card16.jpg");
	ImageIcon img17 = new ImageIcon(".\\img\\card17.jpg");
	ImageIcon img18 = new ImageIcon(".\\img\\card18.jpg");
	
	
	public GamePanel2() {
		buttons = new JButton[36];
		
		panelBoard.setLayout(new GridLayout(6,6,3,3));
		panelBoard.setMaximumSize(new Dimension(800, 700));
		panelBoard.setPreferredSize(new Dimension(800, 700));
		
		for(int i=0; i<36; i++) {
			buttons[i] = new JButton(""+i);
			buttons[i].setIcon(img);
			buttons[i].addActionListener(listen);
			panelBoard.add(buttons[i]);
		}
	}
	
	
	
	
	private class Listen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int cardNum = Integer.parseInt(((JButton)e.getSource()).getText());
			int pattern = model.card[cardNum].getCardPattern();
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
				
				/*switch(pattern) {//ī���� �ո��� �����ش�
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
				case 8 : buttons[cardNum].setIcon(img9);
						break;
				case 9 : buttons[cardNum].setIcon(img10);
						break;
				case 10 : buttons[cardNum].setIcon(img11);
						break;
				case 11 : buttons[cardNum].setIcon(img12);
						break;
				case 12 : buttons[cardNum].setIcon(img13);
						break;
				case 13 : buttons[cardNum].setIcon(img14);
						break;
				case 14 : buttons[cardNum].setIcon(img15);
						break;
				case 15 : buttons[cardNum].setIcon(img16);
						break;
				case 16 : buttons[cardNum].setIcon(img17);
						break;
				case 17 : buttons[cardNum].setIcon(img18);
						break;
				default :
						break;
				}*/
				
				//������ ������ ���� �۵�
				if(ChanceOnOff.chanceOnOff == 1) {
					int num2 = search.searchPattern(model.card, car1num);
					
					//System.out.println("ī�� ��ư�� ����ִ� ��ȣ  = " + cardNum +", " );
					
					openCard(num2,model.card[num2].getCardPattern());
					buttons[car1num].setEnabled(false);
					buttons[num2].setEnabled(false);
					model.card[car1num].setCardState(1);
					model.card[num2].setCardState(1);
					clickCard.setAll();
				}
			
			//���µ� ī����� ¦�� �´��� Ȯ���� ī�����, ���������� �ٽ� ������
				if(state == 1 && ChanceOnOff.chanceOnOff == 0) {
					ClickTime.addClicktime();
					buttons[car1num].setEnabled(false);
					buttons[car2num].setEnabled(false);
					
					model.card[car1num].setCardState(1);//ī����¸� ���������� ǥ��
					model.card[car2num].setCardState(1);
					clickCard.setAll();
					chance.increaseStack();
				}
				
				else if (state == 0 && ChanceOnOff.chanceOnOff == 0) {
					ClickTime.addClicktime();
					stopclick = 1; //ī�� �����ִ� ���� ��ư Ŭ�� ��Ȱ��
					m_timer.schedule(m_task, 700);
					chance.decreaseStack();
				}
			}
				//System.out.println("Ŭ���� ī���ȣ : "+cardNum+" Ŭ����ī�� ����: "+pattern + " ī�����: "+state+" fistnum : "+car1num + " secondnum : "+car2num+"\n\n");
				
				//ī�尡 ��� ���������� �ƴ��� Ȯ��
				check = gameend.checkgameend(model.card, 6);
				if(check ==1) {
					JOptionPane.showMessageDialog(null, "���ӳ�");
					for(int i=0; i<36; i++) {
						buttons[i].setEnabled(true);
						model.card[i].setCardState(0);
						panelBoard.remove(buttons[i]);
					}
				}
			}
		//ī�带 �������ִ� �޼���
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
			case 8 : buttons[cardNum].setIcon(img9);
					break;
			case 9 : buttons[cardNum].setIcon(img10);
					break;
			case 10 : buttons[cardNum].setIcon(img11);
					break;
			case 11 : buttons[cardNum].setIcon(img12);
					break;
			case 12 : buttons[cardNum].setIcon(img13);
					break;
			case 13 : buttons[cardNum].setIcon(img14);
					break;
			case 14 : buttons[cardNum].setIcon(img15);
					break;
			case 15 : buttons[cardNum].setIcon(img16);
					break;
			case 16 : buttons[cardNum].setIcon(img17);
					break;
			case 17 : buttons[cardNum].setIcon(img18);
					break;
			default :
					break;
			}
		}
	}
}
