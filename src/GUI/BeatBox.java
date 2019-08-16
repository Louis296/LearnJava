package GUI;
import java.awt.*;
import javax.sound.midi.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

public class BeatBox {
	JPanel mainPanel;
	ArrayList<JCheckBox>checkboxList;
	Sequencer sequencer;
	Sequence sequence;
	Track track;
	JFrame theFrame;
	
	String[] instrumentNames= {"Bass Drum","Closed Hi-Hat",
			"Open Hi-Hat","Acoustic Snare","Crash Cymbal","Hand Clap",
			"High Tom","Hi Bongo","Maracas","Whistle","Low Conga",
			"Cowbell","Vibraslap","Low-mid Tom","High Agogo",
			"Open Hi Conga"};
	int[] instruments= {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BeatBox().buildGUI();
	}
	
	public void buildGUI() {
		theFrame=new JFrame("Beat Box Ver1.20   by Louis296");
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout layout=new BorderLayout();
		JPanel background=new JPanel(layout);
		background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		checkboxList=new ArrayList<JCheckBox>();
		Box buttonBox=new Box(BoxLayout.Y_AXIS);
		
		JButton start=new JButton("Start");
		start.addActionListener(new MyStartListener());
		buttonBox.add(start);
		
		JButton stop=new JButton("Stop");
		stop.addActionListener(new MyStopListener());
		buttonBox.add(stop);
		
		JButton upTempo=new JButton("Tempo Up");
		upTempo.addActionListener(new MyUpTempoListener());
		buttonBox.add(upTempo);
		
		JButton downTempo=new JButton("Tempo Down");
		downTempo.addActionListener(new MyDownTempoListener());
		buttonBox.add(downTempo);
		
		Box nameBox=new Box(BoxLayout.Y_AXIS);
		for(int i=0;i<16;i++) {
			nameBox.add(new Label(instrumentNames[i]));
		}
		
		background.add(BorderLayout.WEST, nameBox);
		background.add(BorderLayout.EAST, buttonBox);
		
		JMenuBar menuBar=new JMenuBar();
		JMenu fileMenu=new JMenu("File");
		JMenuItem newMenuItem=new JMenuItem("New");
		JMenuItem openMenuItem=new JMenuItem("Open");
		JMenuItem saveMenuItem=new JMenuItem("Save");
		newMenuItem.addActionListener(new NewMenuListener());
		openMenuItem.addActionListener(new OpenMenuListener());
		saveMenuItem.addActionListener(new SaveMenuListener());
		fileMenu.add(newMenuItem);
		fileMenu.add(openMenuItem);
		fileMenu.add(saveMenuItem);
		menuBar.add(fileMenu);
		
		JMenu helpMenu=new JMenu("Help");
		JMenuItem aboutMenuItem=new JMenuItem("About");
		helpMenu.add(aboutMenuItem);
		menuBar.add(helpMenu);
		
		theFrame.getContentPane().add(background);
		theFrame.setJMenuBar(menuBar);
		
		GridLayout grid=new GridLayout(16,16);
		grid.setVgap(1);
		grid.setHgap(2);
		mainPanel=new JPanel(grid);
		background.add(BorderLayout.CENTER, mainPanel);
		
		for(int i=0;i<256;i++) {
			JCheckBox c=new JCheckBox();
			c.setSelected(false);
			checkboxList.add(c);
			mainPanel.add(c);
		}
		
		setUpMidi();
		
		theFrame.setBounds(50, 50, 300, 300);
		theFrame.pack();
		theFrame.setVisible(true);
		
		
	}
	
	public void setUpMidi() {
		try {
			sequencer=MidiSystem.getSequencer();
			sequencer.open();
			sequence=new Sequence(Sequence.PPQ,4);
			track=sequence.createTrack();
			sequencer.setTempoInBPM(120);
			
		}catch(Exception ex) {ex.printStackTrace();}
	}
	
	public void buildTrackAndStart() {
		int[] trackList=null;
		
		sequence.deleteTrack(track);
		track=sequence.createTrack();
		
		for(int i=0;i<16;i++) {
			trackList=new int[16];
			
			int key=instruments[i];
			
			for(int j=0;j<16;j++) {
				JCheckBox jc=(JCheckBox) checkboxList.get(j+(16*i));
				if(jc.isSelected()) {
					trackList[j]=key;
				}else {
					trackList[j]=0;
				}
			}
			
			makeTracks(trackList);
			track.add(makeEvent(176,1,127,0,16));
			
		}
		
		track.add(makeEvent(192,9,1,0,15));
		try {
			
			sequencer.setSequence(sequence);
			sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
			sequencer.start();
			sequencer.setTempoInBPM(120);
		}catch(Exception ex) {ex.printStackTrace();}
		
	}
	
	public class MyStartListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			buildTrackAndStart();
		}
	}
	
	public class MyStopListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			sequencer.stop();
		}
	}
	
	public class MyUpTempoListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			float tempoFactor=sequencer.getTempoFactor();
			sequencer.setTempoInBPM((float)(tempoFactor*1.03));
		}
	}
	
	public class MyDownTempoListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			float tempoFactor=sequencer.getTempoFactor();
			sequencer.setTempoInBPM((float)(tempoFactor*.97));
		}
	}
	
	public class NewMenuListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			
			for(int i=0;i<256;i++) {
				JCheckBox check=(JCheckBox) checkboxList.get(i);
				check.setSelected(false);
			}
		}
	}
	
	public class SaveMenuListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			JFileChooser fileSave=new JFileChooser();
			fileSave.showSaveDialog(theFrame);
			
			boolean[] checkboxState=new boolean[256];
			for(int i=0;i<256;i++) {
				JCheckBox check=(JCheckBox) checkboxList.get(i);
				if(check.isSelected()) {
					checkboxState[i]=true;
				}
			}
			
			try {
				FileOutputStream filestream=new FileOutputStream(fileSave.getSelectedFile()); 
				ObjectOutputStream os=new ObjectOutputStream(filestream);
				os.writeObject(checkboxState);
				os.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public class OpenMenuListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			JFileChooser fileOpen=new JFileChooser();
			fileOpen.showOpenDialog(theFrame);
			
			boolean[] checkboxState=null;
			
			try {
				FileInputStream filestream=new FileInputStream(fileOpen.getSelectedFile());
				ObjectInputStream os=new ObjectInputStream(filestream);
				checkboxState=(boolean[]) os.readObject();
				os.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			
			for(int i=0;i<256;i++) {
				JCheckBox check=(JCheckBox) checkboxList.get(i);
				if(checkboxState[i]) {
					check.setSelected(true);
				}else {
					check.setSelected(false);
				}
			}
		}
	}
	
	public void makeTracks(int[] list) {
		
		for(int i=0;i<16;i++) {
			int key=list[i];
			
			if(key!=0) {
				track.add(makeEvent(144,9,key,100,i));
				track.add(makeEvent(144,9,key,100,i+1));
			}
		}
	}
	
	public MidiEvent makeEvent(int comd,int chan,int one,int two,int tick) {
		MidiEvent event=null;
		try {
			ShortMessage a=new ShortMessage();
			a.setMessage(comd, chan, one, two);
			event=new MidiEvent(a,tick);
		}catch(Exception ex) {}
		return event;
	}
}
