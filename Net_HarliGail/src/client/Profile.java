package client;

import java.awt.*;

import java.awt.event.*;
import java.net.*;

import javax.swing.JFrame;

import java.io.*;
import client.ClientStart;

public class Profile extends JFrame implements ActionListener {

	FileDialog fd;
	Button b1, b2;
	TextField tf;
	String directory = "";
	static String file = "";
	String id,pwd;


	public Profile(String id,String pwd) {
		this.id=id;
		this.pwd=pwd;
		
		b1 = new Button("프로필 사진 선택");
		b1.addActionListener(this);
		tf = new TextField(25);
		b2 = new Button("프로필로 결정");
		b2.addActionListener(this);
		add(b1, "North");
		add(tf, "Center");
		add(b2, "South");
		setBounds(200, 200, 500, 200);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent click) {
		try {
			if (click.getActionCommand().equals("프로필 사진 선택")) {
				fd = new FileDialog(this, "", FileDialog.LOAD);
				fd.setVisible(true);
				tf.setText("");

				directory = fd.getDirectory();
				file = fd.getFile();
				tf.setText(directory + file);
			} else {
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ClientStart.s.getOutputStream()));				
				
				
				System.out.println("파일명 : " + file);
				writer.write(Protocol.SENDINFORMATION + "|" +id+"|"+pwd+"|" +file + "\n");
				writer.flush();

				DataInputStream InputData = new DataInputStream(new FileInputStream(new File(tf.getText())));
				DataOutputStream OutputData = new DataOutputStream(ClientStart.s.getOutputStream());

				int b = 0;
				while ((b = InputData.read()) != -1) {
					OutputData.writeByte(b);
					OutputData.flush();
				}

				InputData.close();
				OutputData.close();

				System.exit(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}


}
