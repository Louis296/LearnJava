package PetTester;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame {
	private static void createAndShowGUI() {
//      创建并设置窗口
      frame = new JFrame("digimonDesktopPet");

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // 框体透明
      frame.setUndecorated(true); // 取消窗口标题栏
      frame.setBackground(new Color(0,0,0,0));// 背景透明
      //设置位置并显示在最前端
      frame.setBounds(first_x,first_y,0,0);
      frame.setAlwaysOnTop(true);
      //设置取消窗体任务栏图标
      frame.setType(JFrame.Type.UTILITY);
      //设置托盘图标
      setTray();
      //添加显示用的JLabel
      label = new JLabel();
      frame.getContentPane().add(label);
//      添加移动事件
      frame.addMouseListener(new MouseAdapter() {
          @Override
          public void mousePressed(MouseEvent e) {
              // 当鼠标按下的时候获得窗口当前的位置
              origin.x = e.getX();
              origin.y = e.getY();
          }

//          @Override
//          public void mouseClicked(MouseEvent e) {
//              digimon.startEat();
//          }
      });
      frame.addMouseMotionListener(new MouseMotionAdapter() {
          // 拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动）
          public void mouseDragged(MouseEvent e) {
              // 当鼠标拖动时获取窗口当前位置
              Point p = frame.getLocation();
              // 设置窗口的位置
              // 窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
              int x = p.x + e.getX() - origin.x;
              int y = p.y + e.getY()- origin.y;
              frame.setLocation(x, y);
////              更新配置文件
              FileUtil.updateFile(configPath,configName,"pox","" + x);
              FileUtil.updateFile(configPath,configName,"poy","" + y);
          }
      });
//      拖动文件路过JFrame时
//      张嘴
      /**
       ******
       ** 添加监听器 DragSourceListener
       ** 作用是拖动文件到窗体上而没松开左键
       *****
       **/
//      拖动文件到JFrame时
//      删除文件 假装是被吃掉并播放相应动画
      new DropTarget(frame, DnDConstants.ACTION_MOVE, new DropTargetAdapter()
      {
          public void drop(DropTargetDropEvent dtde){
              //暂停常规动画播放并开始播放删除文件的相应动画
              FileUtil.deleteFile(dtde);
              digimon.startEat();
          }
      });

//      显示
      frame.pack();
      frame.setVisible(true);
  }
  //设置托盘菜单
  private static void setTray() {
      if (SystemTray.isSupported()) {// 判断系统是否支持系统托盘
          SystemTray tray = SystemTray.getSystemTray(); // 获取当前系统的托盘

          // 为托盘添加一个右键弹出菜单
          PopupMenu popMenu = new PopupMenu();

          MenuItem itemOpen = new MenuItem("打开");
          itemOpen.addActionListener(e -> frame.setVisible(true));

          MenuItem itemHide = new MenuItem("隐藏");
          itemHide.addActionListener(e -> frame.setVisible(false));

          MenuItem itemExit = new MenuItem("退出");
          itemExit.addActionListener(e -> System.exit(0));

          popMenu.add(itemOpen);
          popMenu.add(itemHide);
          popMenu.add(itemExit);

          // 设置托盘图标
          ImageIcon icon = new ImageIcon("res/icon/trayIcon.png");
          Image image = icon.getImage().getScaledInstance(icon.getIconWidth(), icon.getIconHeight(), Image.SCALE_DEFAULT);

          TrayIcon trayIcon = new TrayIcon(image, "桌面宠物", popMenu);
          trayIcon.setImageAutoSize(true); // 自适应尺寸，这个属性至关重要

          try {
              tray.add(trayIcon);
          } catch (AWTException e1) {
              e1.printStackTrace();
          }
      }
  }

  public static void main(String[] args) {

      // 显示应用 GUI 窗口
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
          @Override
          public void run() {
              createAndShowGUI();
              digimon = new Digimon();
              digimon.setName("滚球兽");
              digimon.setLabel(label);
              digimon.setFrame(frame);
              digimon.startNormal();
          }
      });
  }

  //    全局变量,表示窗口初始位置
  private static int first_x = 1100;
  private static int first_y = 300;

  // 全局的位置变量，用于表示鼠标在窗口上的位置
  private static Point origin = new Point();
  //配置文件路径及名称
  private static String configPath = "config";
  private static String configName = "config.txt";

  private static Digimon digimon;
  private static JFrame frame;
  private static JLabel label;


}
