package PetTester;

import javax.swing.*;
import
public class Digimon {
	private String name; //����
//  public String level;   //�ȼ�
//  public String type; //����
//  public String attribute;//����
//  public int size; //����ͼ��

  public void setLabel(JLabel label) {
      this.label = label;
  }

  public void setFrame(JFrame frame) {
      this.frame = frame;
  }

  @Override
  public String toString() {
      return "Digimon{" +
              "name='" + name + '\'' +
//              ", level='" + level + '\'' +
//              ", type='" + type + '\'' +
//              ", attribute='" + attribute + '\'' +
//              ", size=" + size +
              ", pox=" + pox +
              ", poy=" + poy +
              '}';
  }

  public int getPoy() {
      return poy;
  }
  public void setPoy(int poy) {
      this.poy = poy;
  }
  public int getPox() {
      return pox;
  }
  public void setPox(int pox) {
      this.pox = pox;
  }
  private void init(){
      //        ��ʼ��Ĭ����ʾ��ͼƬ
      String normalPath = imgPath + this.getName()+"/normal.png";
      AnimateUtil.changeJLabelImg(frame,label,new ImageIcon(normalPath));
  }

  public Digimon(String name, String level, String type, String attribute) {
      this.name = name;
//      this.level = level;
//      this.type = type;
//      this.attribute = attribute;
  }
  public Digimon() {
      this.name = "unname";
//      this.level = "������";
//      this.type = "undefind";
//      this.attribute = "unknown";

  }

  public String getName() {
      return name;
  }

  public void setName(String name) {
      this.name = name;
  }

//  public int getSize() {
//      return size;
//  }
//
//  public void setSize(int size) {
//      this.size = size;
//  }
//  public String getLevel() {
//      return level;
//  }
//
//  public void setLevel(String level) {
//      this.level = level;
//  }
//
//  public String getType() {
//      return type;
//  }
//
//  public void setType(String type) {
//      this.type = type;
//  }

//  public String getAttribute() {
//      return attribute;
//  }
//
//  public void setAttribute(String attribute) {
//      this.attribute = attribute;
//  }

  //��Ϊ
  //����
  public Digimon evolut(){
      return this;
  }
  //����
  //����
  private void normal(){
      init();
//       Ĭ�϶���
      ImageIcon[] icon = new ImageIcon[5];
      int index =0;
      for(int i = 1; i < 6;i++){
          String path = imgPath + this.getName()+"/zhayan ("+ i +").png";
          icon[index++] = new ImageIcon(path);
      }
      normalThread = AnimateUtil.animate(frame,label,icon,500);
  }
  public void startNormal() {
      if(normalThread == null){
          normal();
      }
      normalThread.flag = true;
      AnimateUtil.playTimer(normalThread).start();
  }
  public void stopNormal(int n) {
      normalThread.flag = false;
  }
  //eat
  private void eat(){
//      ��ʼ��Ĭ����ʾ��ͼƬ
      String normalPath = imgPath + this.getName()+"/normal.png";
      AnimateUtil.changeJLabelImg(frame,label,new ImageIcon(normalPath));
//       Ĭ�϶���
      ImageIcon[] icon = new ImageIcon[7];
      int index =0;
      for(int i = 1; i < 8;i++){
          String path = imgPath + this.getName()+"/eat"+ i +".png";
          icon[index++] = new ImageIcon(path);
      }
      eatThread = AnimateUtil.animate(frame,label,icon,1000);
  }
  public void startEat(){
      stopNormal(2000);
      if(eatThread == null){
          System.out.println("����eat�����߳�");
          eat();
      }
      eatThread.flag = true;
      AnimateUtil.playNTimes(eatThread,2,this).start();
      eatThread.flag = false;
  }

  private static Behave normalThread = null;
  private static Behave eatThread = null;

  private int pox;
  private int poy;
  private JLabel label = null;
  private JFrame frame = null;
  private final static String imgPath = "res/img/";
  private final static String configPath = "config";
  private final static String configName = "config.txt";


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
