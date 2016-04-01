package maze.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;


public class GameLauncherMaze {

	private JFrame frmGraphicsDemo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameLauncherMaze window = new GameLauncherMaze();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameLauncherMaze() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGraphicsDemo = new JFrame();
		frmGraphicsDemo.setTitle("Maze");
		// TODO - REMOVER SE NAO USADO
		//frmGraphicsDemo.setBounds(100, 100, 600, 400);
		//frmGraphicsDemo.setPreferredSize(new Dimension(600, 400));
		frmGraphicsDemo.setResizable(false);
		frmGraphicsDemo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MazeGame panel = new MazeGame();
		panel.setBounds(0, 0, 510, 510);
		panel.setPreferredSize(new Dimension(510, 510));
		frmGraphicsDemo.getContentPane().add(panel, BorderLayout.CENTER);

		frmGraphicsDemo.pack();
		
		frmGraphicsDemo.setVisible(true);
		
		panel.requestFocus();
	}

}