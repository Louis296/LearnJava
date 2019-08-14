package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TextAreaA implements ActionListener{
	
	JTextArea text;
	JFrame frame;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TextAreaA gui=new TextAreaA();
		gui.go();
	}
	
	public void go() {
		text=new JTextArea(10,20);
		frame=new JFrame();
		JPanel panel=new JPanel();
		JButton button=new JButton("Change The Color");
		button.addActionListener(this);
		
		JScrollPane scroller=new JScrollPane(text);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		panel.add(scroller);
		
		MyDrawPanel a=new MyDrawPanel();
		
		frame.getContentPane().add(BorderLayout.CENTER, a);
		frame.getContentPane().add(BorderLayout.WEST, panel);
		frame.getContentPane().add(BorderLayout.SOUTH, button);
		frame.setSize(500, 300);
		frame.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent event) {
		text.append("Change!\n");
		frame.repaint();
	}
	
}
