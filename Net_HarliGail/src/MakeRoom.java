import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MakeRoom extends JFrame implements ActionListener{

	JLabel nameR,state,pwd,capacity;
	JTextField nameF;//방이름 입력 창
	JRadioButton rb1,rb2;//공개 or 비공개
	JPasswordField pwdF;
	JComboBox box;
	JButton b1,b2;//확인, 취소
	MyPanel panel;
	
	public MakeRoom()
	{
		setUndecorated(true);//타이틀바가 사라짐.
		setBounds(470,310,340,420);
		panel = new MyPanel("img/createRoom.png");
		setContentPane(panel);
		
		nameR = new JLabel("방이름");
		state = new JLabel("상태");
		pwd = new JLabel("비밀번호");
		capacity = new JLabel("인원");
		
		nameF = new JTextField();
		pwdF = new JPasswordField();
		rb1 = new JRadioButton("공개");
		rb2 = new JRadioButton("비공개");
		
		pwd.setVisible(false);
		pwdF.setVisible(false);
		
		ButtonGroup bt = new ButtonGroup();
		bt.add(rb1);bt.add(rb2);
		rb1.setSelected(true);
		
		box = new JComboBox();
		for(int i=2;i<=4;i++)
			box.addItem(i+"명");
		
		b1 = new JButton("확인");
		b2 = new JButton("취소");
		
		setLayout(null);
		nameR.setBounds(40,65,40,30);
		nameF.setBounds(85, 65, 150, 30);
		
		state.setBounds(40,100,40,30);
		rb1.setBounds(80,100,70,30);
		rb2.setBounds(160,100,70,30);
		
		rb1.setOpaque(false);
		rb2.setOpaque(false);
		
		pwd.setBounds(55,145,60,30);
		pwdF.setBounds(120,135,100,30);
		
		capacity.setBounds(40,190,40,30);
		box.setBounds(120,190,100,30);
		
		JPanel p = new JPanel();
		p.add(b1);p.add(b2);
		p.setBounds(40,230,195,35);
		p.setOpaque(false);
		
		add(nameR);add(nameF);
		add(state);add(rb1);add(rb2);
		add(pwd);add(pwdF);
		add(capacity);add(box);
		add(p);
		
		rb1.addActionListener(this);
		rb2.addActionListener(this);
		b2.addActionListener(this);
	}
	
	public static void main(String[] args)
	{
		new MakeRoom();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==rb2)
		{	//비공개
			pwdF.setVisible(true);
			pwd.setVisible(true);
			pwdF.setText("");
			pwdF.requestFocus();
		}
		else if(e.getSource()==rb1)
		{	//공개
			pwdF.setVisible(false);
			pwd.setVisible(false);
		}
		else if(e.getSource()==b2)
		{	//방만들기 취소
			dispose();
		}
	}	
	 
}


