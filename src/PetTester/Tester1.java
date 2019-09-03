package PetTester;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class Tester1 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("digimonDesktopBaby");
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
        setTray(frame);
//        ���ͼƬJLabel
        JLabel digimonLabel = MainFrame.loadPicture(0,0);
        MainFrame.animateNormal(digimonLabel);
        frame.getContentPane().add(digimonLabel);
//        ����ƶ��¼�
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // ����갴�µ�ʱ���ô��ڵ�ǰ��λ��
                origin.x = e.getX();
                origin.y = e.getY();
            }
 
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                evolut(frame,digimonLabel);
//            }
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
                FileUtil.updateFile(configPath,configName,"pox",""+x);
                FileUtil.updateFile(configPath,configName,"poy",""+y);
            }
        });
 
    //�������̲˵�
    private static void setTray(JFrame frame) {
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
            ImageIcon icon = new ImageIcon(publicUrl + "trayIcon.png");
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
