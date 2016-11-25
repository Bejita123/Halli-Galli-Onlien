import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class WaitRoom extends JPanel{
	
	Image bg;
	JTable t1, t2, t3;
	DefaultTableModel m1,m2,m3;
	
	JTextArea chat;	//채팅 창
	JTextField chatF; //채팅 입력창
	JComboBox box;	//채팅 엔터창
	JButton b1,b2,b3;//전송, 도움말, 방만들기
	JTextArea profile;//사용자정보
	
	public WaitRoom()
	{
		bg= Toolkit.getDefaultToolkit().getImage("img/monkey_back.jpg");
		
		String[] room = {"방이름","인원","게임상태"};	//방
		String[][] row1 = new String[10][3]; // room열의 수에 맞게 5줄로 생성
		m1 = new DefaultTableModel(row1,room);
		t1 = new JTable(m1);
		JScrollPane sc1 = new JScrollPane(t1);
		
		String[] rank = {"순위","ID","승률"}; //랭킹
		String[][] row2 = new String[0][3];
		m2 = new DefaultTableModel(row2,rank);
		t2 = new JTable(m2);
		JScrollPane sc2 = new JScrollPane(t2);
		
		String[] user= {"ID","승률"}; //접속자
		String[][] row3 = new String[0][3];
		m3 = new DefaultTableModel(row3,user);
		t3 = new JTable(m3);
		JScrollPane sc3 = new JScrollPane(t3);
		
		chat = new JTextArea();	//채팅창
		JScrollPane sc4 = new JScrollPane(chat);
		chatF = new JTextField();//채팅입력창
		
		b1 = new JButton("전송");//전송버튼
		b2 = new JButton("도움말");
		b3 = new JButton("방만들기");
		
		profile = new JTextArea();
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1,2,5,5));
		p.add(b2);
		p.add(b3);
		p.setOpaque(false);
		
		setLayout(null);
		sc1.setBounds(10, 15, 500, 320);//방목록
		sc2.setBounds(515, 10, 270, 160);//순위표
		sc3.setBounds(515, 175, 270, 160);//접속자
		sc4.setBounds(10, 340, 500, 190);//채팅
		chat.setBounds(10, 535, 410, 30);//채팅입력
		b1.setBounds(425, 535, 85, 30);//채팅전송
		profile.setBounds(515, 340, 270, 100);//사용자 정보
		
		p.setBounds(515, 525, 270, 40);//버튼 묶음.
		
		add(sc1);
		add(sc2);
		add(sc3);
		add(sc4);
		add(chat);
		add(b1);
		add(profile);
		
		add(p);
	}
	
	protected void paintComponent(Graphics g)
	{
		g.drawImage(bg, 0, 0, getWidth(),getHeight(),this);
	}

}
