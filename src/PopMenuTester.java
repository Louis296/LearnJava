

import javax.swing.* ;
import java.awt.event.* ;
class MyMenu extends JFrame {
	//JFrame frame = new JFrame("�Ҽ��˵�") ;
	//JMenuItem item1 = new JMenuItem("�Ӳ˵�1") ;
	//JMenuItem item2 = new JMenuItem("�Ӳ˵�2") ;
	
	public MyMenu(){
		this.setTitle("�Ҽ��˵�");
		JMenuItem item3 = new JMenuItem("�Ӳ˵�3") ;
		JPopupMenu menu = new JPopupMenu() ;
		//JMenu m = new JMenu() ;
		JPanel panel = new JPanel();
         menu.add(new JMenuItem("ѡ��")) ;
		 menu.add(new JMenuItem("�˳�")) ;
		 //m.add(item1) ;
		 //m.add(item2) ;
		 //menu.add(m) ;
		 menu.add(item3) ;
	panel.addMouseListener(new MouseAdapter(){
		public void mouseReleased(MouseEvent e){
			if( e.isPopupTrigger() ){
				menu.show( panel, e.getX(), e.getY() ) ;
			}
		}
	}) ;

	panel.add(menu) ;
	this.add(panel) ;
	this.setSize(300,300) ;
	this.setVisible(true) ;
	}

}


public class PopMenuTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MyMenu();
	}

}
