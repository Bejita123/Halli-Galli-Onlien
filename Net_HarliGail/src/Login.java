import javax.swing.*;
import java.awt.*;

public class Login extends JPanel{

	Image bg;			//로그인창 background
	JLabel label1, label2;	//라벨
	JTextField idF	;	//id 입력창
	JPasswordField pwdF; // 패스워드 입력창
	JButton btJoin, btLogin; // 회원가입, 로그인 버튼
	ImageIcon joinButton = new ImageIcon("img/JoinButton2.png");
	ImageIcon loginButton = new ImageIcon("img/LoginButton.png");
	
	public Login()
	{
		bg = Toolkit.getDefaultToolkit().getImage("img/monkey_back3.jpg");
		label1 = new JLabel("ID");
		label2 = new JLabel("PW");
		
		idF = new JTextField();
		pwdF = new JPasswordField();
		btJoin = new JButton(joinButton);
		btLogin = new JButton(loginButton);
		
		setLayout(null);
		
		JPanel p = new JPanel();
		p.setBounds(200, 250, 220, 150);
		p.setLayout(null);
		
		label1.setBounds(10,15,30,30);
		idF.setBounds(45, 15, 150, 30);
		label2.setBounds(10,50,30,30);
		pwdF.setBounds(45,50,150,30);
		btJoin.setBounds(15,95,90,40);
		btLogin.setBounds(120,95,90,40);
		
		btJoin.setBorderPainted(false);
		btJoin.setContentAreaFilled(false);
		btLogin.setBorderPainted(false);
		btLogin.setContentAreaFilled(false);
		
		
		p.setOpaque(false);//JPanel 묶은 회색을 투명하게...
		
		p.add(label1);
		p.add(idF);
		p.add(label2);
		p.add(pwdF);
		p.add(btJoin);
		p.add(btLogin);
		
		add(p);
	}
	
	
	protected void paintComponent(Graphics g)
	{
		
		//this->JPanel에 배경 이미지 뿌림.
		g.drawImage(bg,0,0,getWidth(),getHeight(),this);
	}
}
