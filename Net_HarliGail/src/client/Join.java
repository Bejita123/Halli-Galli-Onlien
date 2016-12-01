package client;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.image.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import client.ClientStart;
import client.ClientStart;
public class Join extends JFrame implements ActionListener {

	String id, pwd;
	JTextField nameF, idF;
	JPasswordField pwdF, rePwdF;
	JButton b1, b2, b3, b4;
	MyPanel panel;


	
	
	ImageIcon joinButton = new ImageIcon("img/JoinButton.png");
	ImageIcon backButton = new ImageIcon("img/BackButton.png");

	
	Profile profile;


	
	public Join() {


		setUndecorated(true);// 타이틀 바가 사라짐.

		setBounds(470, 310, 340, 280);
		setBackground(new Color(0, 0, 0, 0));
		panel = new MyPanel("img/test3.png");// 회원가입 창 배경
		setContentPane(panel);

		// name = new JLabel("이름");
		// id = new JLabel("ID");
		// pwd = new JLabel("PW");
		// rePwd = new JLabel("PW확인");

		// nameF = new JTextField();
		idF = new JTextField();
		pwdF = new JPasswordField();
		// rePwdF = new JPasswordField();

		b1 = new JButton(joinButton);
		b2 = new JButton(backButton);
		b3 = new JButton("중복확인");
		b4 = new JButton("프로필사진 설정");

		b1.setBorderPainted(false);
		b1.setContentAreaFilled(false);
		b2.setBorderPainted(false);
		b2.setContentAreaFilled(false);
		b4.setBorderPainted(false);
		b4.setContentAreaFilled(false);
		setLayout(null);

		// name.setBounds(50,85,40,30);
		// nameF.setBounds(95, 85, 140, 30);

		// id.setBounds(50,120,40,30);
		idF.setBounds(115, 100, 140, 30);
		b3.setBounds(245,100,55,30);//중복체크버튼

		// pwd.setBounds(50,165,40,30);
		pwdF.setBounds(115, 135, 140, 30);

		// pwd.setBounds(50,200,40,30);
		// pwdF.setBounds(95, 200, 140, 30);

		JPanel p = new JPanel(new GridLayout(1, 2)); // join, back버튼..?
		p.add(b1);// join
		p.add(b2);// back
		p.add(b3);
		p.add(b4);
		p.setOpaque(false);
		p.setBounds(60, 200, 200, 40);

		// add(name);
		// add(nameF);
		// add(id);
		add(idF);
		// add(pwd);
		add(pwdF);
		// add(rePwd);
		// add(rePwdF);
		add(p);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);

	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (b1 == e.getSource()) {
			JOptionPane.showMessageDialog(this, "회원가입완료");
			dispose();// 메모리 그대로 둔 채 창닫기
		} else if (b2 == e.getSource()) {
			System.out.println("회원가입이 취소되었습니다.");
			dispose();
		} else if (b3 == e.getSource()) {
			System.out.println("ID중복체크");
			id = idF.getText();			
			BufferedWriter writer;
			try {
				writer = new BufferedWriter(new OutputStreamWriter(ClientStart.s.getOutputStream()));
				writer.write(Protocol.IDCHECK+"|"+id);	
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}				
			
		}

		else if (b4 == e.getSource()) {
			System.out.println("프로필 사진 설정");
			id = idF.getText();
			pwd = pwdF.getText();
			if (id == null)
				;//팝업창 띄우기(id를 입력하세요)
			if(pwd==null)
				;//팝업창 띄우기(패스워드를 입력하세요)		

			profile = new Profile(id,pwd);
		}
	}
}

class MyPanel extends JPanel // 회원가입 JPanel창
{
	Image image;

	MyPanel(String img) {
		image = Toolkit.getDefaultToolkit().createImage(img);
		setOpaque(false);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null) {
			g.drawImage(image, 0, 0, 340, 280, this);
		}

		Graphics2D g2d = (Graphics2D) g.create();

		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.0f));

		g2d.setColor(getBackground());
		g2d.fill(getBounds());
		g2d.dispose();
	}
}
