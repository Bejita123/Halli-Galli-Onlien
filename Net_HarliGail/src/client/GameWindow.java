package client;
import java.awt.*;
import javax.swing.*;

public class GameWindow extends JPanel{
	Image bg;
	JTextArea chat;
	JTextField chatF;
	JButton b1,b2,b3,b4;//전송, 카드뒤집기, 벨, 대기실로 나가기
	JTextArea p1,p2,p3,p4;//사용자 프로필
	
	public GameWindow()
	{
		bg=Toolkit.getDefaultToolkit().getImage("img/test.png");
		
		chat = new JTextArea();
		JScrollPane js4 = new JScrollPane(chat);
		chatF = new JTextField();
		
		b1 = new JButton("전송");
		b2 = new JButton("카드 뒤집기");
		b3 = new JButton("벨");
		b4 = new JButton("나가기");
		
		p1 = new JTextArea();
		p2 = new JTextArea();
		p3 = new JTextArea();
		p4 = new JTextArea();
		
		setLayout(null);
		JPanel p = new JPanel();
		p.setBounds(10,10,775,400);
		p.setLayout(null);
		p.setOpaque(false);
		
		p1.setBounds(0,0,120,80);
		p2.setBounds(655,0,120,80);
		p3.setBounds(0,320,120,80);
		p4.setBounds(655,320,120,80);
		b3.setBounds(370,200,50,50);//벨버튼
		b4.setBounds(370,280,80,30);
		
		p.add(p1);p.add(p2);
		p.add(p3);p.add(p4);
		p.add(b3);p.add(b4);
		add(p);
		
		JPanel pan1 = new JPanel();
		pan1.setBounds(10, 410, 500, 170);
		pan1.setLayout(null);
		pan1.setOpaque(false);
		
		js4.setBounds(0, 0, 500, 120);//채팅창 스크롤
		chatF.setBounds(0, 125, 425, 30);//채팅 입력창
		b1.setBounds(430, 125, 70, 30);//전송버튼
		pan1.add(js4);pan1.add(chatF);pan1.add(b1);
		pan1.setOpaque(true);
		add(pan1);
	}
	
	protected void paintConponent(Graphics g)
	{
		g.drawImage(bg, 0, 0, getWidth(),getHeight(),this);
	}
}
