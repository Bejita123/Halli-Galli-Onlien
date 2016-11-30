package client;
import javax.swing.*;
import java.awt.*;

public class Loading extends JPanel{
	Image bg;			//로그인 창 배경이미지(background)
	
	public Loading()
	{
		//배경 이미지
		bg = Toolkit.getDefaultToolkit().getImage("img/loading.jpg");  
	}
	
	protected void paintComponent(Graphics g)
	{
		g.drawImage(bg, 0, 0, getWidth(),getHeight(),this);
	}
}
