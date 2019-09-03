package PetTester;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame {
	private static void createAndShowGUI() {
//      ���������ô���
      frame = new JFrame("digimonDesktopPet");

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // ����͸��
      frame.setUndecorated(true); // ȡ�����ڱ�����
      frame.setBackground(new Color(0,0,0,0));// ����͸��
      //����λ�ò���ʾ����ǰ��
      frame.setBounds(first_x,first_y,0,0);
      frame.setAlwaysOnTop(true);
      //����ȡ������������ͼ��
      frame.setType(JFrame.Type.UTILITY);
      //��������ͼ��
      setTray();
      //�����ʾ�õ�JLabel
      label = new JLabel();
      frame.getContentPane().add(label);
//      ����ƶ��¼�
      frame.addMouseListener(new MouseAdapter() {
          @Override
          public void mousePressed(MouseEvent e) {
              // ����갴�µ�ʱ���ô��ڵ�ǰ��λ��
              origin.x = e.getX();
              origin.y = e.getY();
          }

//          @Override
//          public void mouseClicked(MouseEvent e) {
//              digimon.startEat();
//          }
      });
      frame.addMouseMotionListener(new MouseMotionAdapter() {
          // �϶���mouseDragged ָ�Ĳ�������ڴ������ƶ�������������϶���
          public void mouseDragged(MouseEvent e) {
              // ������϶�ʱ��ȡ���ڵ�ǰλ��
              Point p = frame.getLocation();
              // ���ô��ڵ�λ��
              // ���ڵ�ǰ��λ�� + ��굱ǰ�ڴ��ڵ�λ�� - ��갴�µ�ʱ���ڴ��ڵ�λ��
              int x = p.x + e.getX() - origin.x;
              int y = p.y + e.getY()- origin.y;
              frame.setLocation(x, y);
////              ���������ļ�
              FileUtil.updateFile(configPath,configName,"pox","" + x);
              FileUtil.updateFile(configPath,configName,"poy","" + y);
          }
      });
//      �϶��ļ�·��JFrameʱ
//      ����
      /**
       ******
       ** ��Ӽ����� DragSourceListener
       ** �������϶��ļ��������϶�û�ɿ����
       *****
       **/
//      �϶��ļ���JFrameʱ
//      ɾ���ļ� ��װ�Ǳ��Ե���������Ӧ����
      new DropTarget(frame, DnDConstants.ACTION_MOVE, new DropTargetAdapter()
      {
          public void drop(DropTargetDropEvent dtde){
              //��ͣ���涯�����Ų���ʼ����ɾ���ļ�����Ӧ����
              FileUtil.deleteFile(dtde);
              digimon.startEat();
          }
      });

//      ��ʾ
      frame.pack();
      frame.setVisible(true);
  }
  //�������̲˵�
  private static void setTray() {
      if (SystemTray.isSupported()) {// �ж�ϵͳ�Ƿ�֧��ϵͳ����
          SystemTray tray = SystemTray.getSystemTray(); // ��ȡ��ǰϵͳ������

          // Ϊ�������һ���Ҽ������˵�
          PopupMenu popMenu = new PopupMenu();

          MenuItem itemOpen = new MenuItem("��");
          itemOpen.addActionListener(e -> frame.setVisible(true));

          MenuItem itemHide = new MenuItem("����");
          itemHide.addActionListener(e -> frame.setVisible(false));

          MenuItem itemExit = new MenuItem("�˳�");
          itemExit.addActionListener(e -> System.exit(0));

          popMenu.add(itemOpen);
          popMenu.add(itemHide);
          popMenu.add(itemExit);

          // ��������ͼ��
          ImageIcon icon = new ImageIcon("res/icon/trayIcon.png");
          Image image = icon.getImage().getScaledInstance(icon.getIconWidth(), icon.getIconHeight(), Image.SCALE_DEFAULT);

          TrayIcon trayIcon = new TrayIcon(image, "�������", popMenu);
          trayIcon.setImageAutoSize(true); // ����Ӧ�ߴ磬�������������Ҫ

          try {
              tray.add(trayIcon);
          } catch (AWTException e1) {
              e1.printStackTrace();
          }
      }
  }

  public static void main(String[] args) {

      // ��ʾӦ�� GUI ����
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
          @Override
          public void run() {
              createAndShowGUI();
              digimon = new Digimon();
              digimon.setName("������");
              digimon.setLabel(label);
              digimon.setFrame(frame);
              digimon.startNormal();
          }
      });
  }

  //    ȫ�ֱ���,��ʾ���ڳ�ʼλ��
  private static int first_x = 1100;
  private static int first_y = 300;

  // ȫ�ֵ�λ�ñ��������ڱ�ʾ����ڴ����ϵ�λ��
  private static Point origin = new Point();
  //�����ļ�·��������
  private static String configPath = "config";
  private static String configName = "config.txt";

  private static Digimon digimon;
  private static JFrame frame;
  private static JLabel label;


}
