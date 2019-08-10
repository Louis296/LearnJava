package GUI;
import javax.swing.*;
import java.awt.*;

class MyDrawPanel extends JPanel{
	public void paintComponent(Graphics g) {
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		int red=(int) (Math.random()*255);
		int green=(int)(Math.random()*255);
		int blue=(int)(Math.random()*255);
		Color randomColor=new Color(red,green,blue);
		g.setColor(randomColor);
		g.fillOval(70, 70, 100, 100);
	}
}

class MyDrawPanelB extends JPanel{
	public void paintComponent(Graphics g) {
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		Graphics2D g2d=(Graphics2D) g;
		GradientPaint gradient=new GradientPaint(70,70,Color.blue,150,150,Color.orange);
		g2d.setPaint(gradient);
		g2d.fillOval(70, 70, 100, 100);
	}
}
public class SimpleGui1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame=new JFrame();
		MyDrawPanel a=new MyDrawPanel();
		MyDrawPanelB b=new MyDrawPanelB();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(b);
		frame.setSize(300,300);
		frame.setVisible(true);
	}

}