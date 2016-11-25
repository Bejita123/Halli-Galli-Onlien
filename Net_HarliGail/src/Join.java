import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.image.*;

public class Join extends JFrame implements ActionListener{
	JLabel name,id,pwd,rePwd;
	JTextField nameF,idF;
	JPasswordField pwdF,rePwdF;
	JButton b1,b2,b3;
	MyPanel panel;
	
	ImageIcon joinButton = new ImageIcon("img/JoinButton.png");
	ImageIcon backButton = new ImageIcon("img/BackButton.png");
	
	public Join()
	{
		setUndecorated(true);//타이틀 바가 사라짐.
		
		setBounds(470,310,340,420);
		setBackground(new Color(0,0,0,0));
		panel = new MyPanel("img/Join.png");//회원가입 창 배경
		setContentPane(panel);
		
		name = new JLabel("이름");
		id = new JLabel("ID");
		pwd = new JLabel("PW");
		rePwd = new JLabel("PW확인");
		
		nameF = new JTextField();
		idF = new JTextField();
		pwdF = new JPasswordField();
		rePwdF = new JPasswordField();
		
		b1 = new JButton(joinButton);
		b2 = new JButton(backButton);
		b3 = new JButton("중복확인");
		
		b1.setBorderPainted(false);
		b1.setContentAreaFilled(false);
		b2.setBorderPainted(false);
		b2.setContentAreaFilled(false);
		
		setLayout(null);
		
		name.setBounds(50,65,40,30);
		nameF.setBounds(95, 65, 140, 30);
		
		id.setBounds(50,100,40,30);
		idF.setBounds(95, 100, 140, 30);
		b3.setBounds(245,100,55,30);//중복체크버튼
		
		pwd.setBounds(50,145,40,30);
		pwdF.setBounds(95, 145, 140, 30);
		
		pwd.setBounds(50,180,40,30);
		pwdF.setBounds(95, 180, 140, 30);
		
		JPanel p = new JPanel(new GridLayout(1,2)); //join, back버튼..?
		p.add(b1);//join
		p.add(b2);//back
		p.setOpaque(false);
		p.setBounds(60,355,200,40);
		
		add(name);add(nameF);
		add(id);add(idF);
		add(pwd);add(pwdF);
		add(rePwd);add(rePwdF);
		add(p);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		
	}

	public static void main(String[] args)
	{
		new Join();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(b1==e.getSource())
		{
			JOptionPane.showMessageDialog(this,"회원가입완료");
			dispose();//메모리 그대로 둔 채 창닫기
		}
		else if(b2==e.getSource())
		{
			System.out.println("회원가입이 취소되었습니다.");
			dispose();
		}
		else if(b3==e.getSource())
		{
			System.out.println("ID중복체크");
		}
	}
}

class MyPanel extends JPanel	//회원가입 JPanel창 
{
    Image image;

    MyPanel(String img)
    {
        image = Toolkit.getDefaultToolkit().createImage(img);
        setOpaque(false);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (image != null)
        {
            g.drawImage(image, 0, 0, getWidth(),getHeight(),this);
        }
        
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.0f));
        
        g2d.setColor(getBackground());
        g2d.fill(getBounds());
        g2d.dispose();
    }
}
