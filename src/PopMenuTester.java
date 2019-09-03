

import javax.swing.* ;
import java.awt.event.* ;
class MyMenu extends JFrame {
	//JFrame frame = new JFrame("右键菜单") ;
	//JMenuItem item1 = new JMenuItem("子菜单1") ;
	//JMenuItem item2 = new JMenuItem("子菜单2") ;
	
	public MyMenu(){
		this.setTitle("右键菜单");
		JMenuItem item3 = new JMenuItem("子菜单3") ;
		JPopupMenu menu = new JPopupMenu() ;
		//JMenu m = new JMenu() ;
		JPanel panel = new JPanel();
         menu.add(new JMenuItem("选择")) ;
		 menu.add(new JMenuItem("退出")) ;
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
