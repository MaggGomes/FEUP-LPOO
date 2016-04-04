package maze.gui;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import maze.logic.Maze;
import maze.logic.Maze.Mode;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Options extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JSlider sliderMazeSize;
	JSlider sliderNumDragons;
	JComboBox<String> modeSelect;
	
	private int mazeSize;
	private int numberDragons;
	private Maze.Mode mode;

	/**
	 * Create the dialog.
	 */
	public Options() {
		setResizable(false);
		this.setTitle("Options");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(Options.class.getResource("/maze/gui/res/dragon.png")));
		this.setModal(true);
		setBounds(100, 100, 300, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel labelDimension = new JLabel("Maze Dimension");
		labelDimension.setBounds(10, 21, 109, 14);
		contentPanel.add(labelDimension);
		
		JLabel labelNumDragons = new JLabel("Number of Dragons");
		labelNumDragons.setBounds(10, 95, 109, 14);
		contentPanel.add(labelNumDragons);
		
		JLabel labelTypeDragons = new JLabel("Type of Dragons");
		labelTypeDragons.setBounds(10, 175, 109, 14);
		contentPanel.add(labelTypeDragons);
	
		sliderMazeSize = new JSlider(11, 41, 21);
		sliderMazeSize.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				sliderNumDragons.setMaximum(Maze.calculateMaxNumberDragons(sliderMazeSize.getValue()));
			}
		});
		mazeSize = sliderMazeSize.getValue();
		sliderMazeSize.setPaintTicks(true);
		sliderMazeSize.setPaintLabels(true);
		sliderMazeSize.setSnapToTicks(true);
		sliderMazeSize.setBounds(133, 11, 140, 51);
		sliderMazeSize.setMajorTickSpacing(10);
		sliderMazeSize.setMinorTickSpacing(2);
		contentPanel.add(sliderMazeSize);
		
		sliderNumDragons = new JSlider(1, Maze.calculateMaxNumberDragons(sliderMazeSize.getValue()), 1);
		sliderNumDragons.setMajorTickSpacing(2);
		sliderNumDragons.setValue(1);
		sliderNumDragons.setSnapToTicks(true);
		sliderNumDragons.setPaintTicks(true);
		sliderNumDragons.setPaintLabels(true);
		sliderNumDragons.setMinorTickSpacing(1);
		sliderNumDragons.setBounds(133, 73, 140, 51);
		contentPanel.add(sliderNumDragons);
		
		modeSelect = new JComboBox<String>();
		modeSelect.setModel(new DefaultComboBoxModel<String>(new String[] {"Static", "Moving", "Moving and Sleeping"}));
		modeSelect.setSelectedIndex(0);		
		modeSelect.setBounds(129, 172, 144, 20);
		contentPanel.add(modeSelect);
				
		String strMode = modeSelect.getSelectedItem().toString();
		if(strMode == "Static")
			mode = Mode.STATIC;
		else if(strMode == "Moving")
			mode = Mode.MOVING;
		else if(strMode == "Moving and Sleepy")
			mode = Mode.MOVINGSLEEPING;
		
		JButton btnApply = new JButton("Apply");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				mazeSize = sliderMazeSize.getValue();
				numberDragons= sliderNumDragons.getValue();
				String strMode = modeSelect.getSelectedItem().toString();
				if(strMode == "Static")
					mode = Mode.STATIC;
				else if(strMode == "Moving")
					mode = Mode.MOVING;
				else if(strMode == "Moving and Sleepy")
					mode = Mode.MOVINGSLEEPING;
				
				closeDialog();
					closeDialog();							
			}
		});
		btnApply.setBounds(10, 237, 89, 23);
		contentPanel.add(btnApply);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				sliderMazeSize.setValue(mazeSize);
				sliderNumDragons.setValue(numberDragons);
				
				switch(mode){
				case STATIC:
					modeSelect.setSelectedIndex(0);
					break;
				case MOVING:
					modeSelect.setSelectedIndex(1);
					break;
				case MOVINGSLEEPING:
					modeSelect.setSelectedIndex(2);
					break;
					}
				
				closeDialog();
			}
		});
		btnBack.setBounds(184, 237, 89, 23);
		contentPanel.add(btnBack);		
	}

	public int getMazeSize() {
		return mazeSize;
	}

	public int getNumberDragons() {
		return numberDragons;
	}

	public Maze.Mode getMode() {
		return mode;
	}
	
	public void closeDialog(){
		this.dispose();
	}
}
