package maze.gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import maze.logic.Position;
import maze.logic.Dragon;
import maze.logic.Maze;
import maze.logic.Maze.Token;

public class MazeGame extends JPanel {

	private Maze maze;
	private BufferedImage hero;
	private BufferedImage heroSword;
	private BufferedImage dragon;
	private BufferedImage dragonSleeping;
	private BufferedImage sword;
	private BufferedImage wall;
	private BufferedImage path;
	private BufferedImage exit;
	private int x=10, y=10, width=30, height=30;

	public MazeGame() {		

		try{
			maze = new Maze(Maze.Mode.STATIC, 1, 11);
		} catch(Exception e){
			System.out.println(e.getMessage());
		}

		loadImages();

		addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				//x = e.getX();
				//y = e.getY();
				//repaint();				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}	
		});

		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				//System.out.println("x=" + x);
				switch(e.getKeyCode()){
				case KeyEvent.VK_LEFT: 
					x--; 
					break;

				case KeyEvent.VK_RIGHT: 
					x++; 
					//System.out.println("x=" + x);
					break;

				case KeyEvent.VK_UP: 
					y--; 
					break;

				case KeyEvent.VK_DOWN: 
					y++; 
					break;
				}

				repaint();				
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}			
		});
	}

	public void loadImages(){
		try {
			hero =  ImageIO.read(new File("res/hero.png"));
			dragon = ImageIO.read(new File("res/dragon.png"));
			dragonSleeping = ImageIO.read(new File("res/dragonsleeping.png"));
			sword = ImageIO.read(new File("res/sword.png"));
			path = ImageIO.read(new File("res/wall.png"));
			path = ImageIO.read(new File("res/path.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int w = width;
		int h = height;
		
		Position pos = new Position();


		/*for (int i = 0; i < 30; i++){
			for (int j = 0; j < 30; j++){
				g.drawImage(hero, x, y, x + w, y + h, 0, 0, hero.getWidth(), hero.getHeight(), null);
				x+=width;
			}

			x = 10;
			y+=height;
		}

		x = 10;
		y = 10;*/

		/*
		 * 
		 * pos.setX(j);
				pos.setY(i);

				mazeString+=" "; // Spacing between elements

				if (hero.getPos().equals(pos))
					mazeString+= hero.getSymbol();

				else if(checkDragon(pos) instanceof Dragon && checkDragon(pos).isAlive()){
					if (pos.equals(sword.getPos()) && !sword.isPicked())
						mazeString += Token.DRAGSWORD.getSymbol();
					else
						mazeString+= checkDragon(pos).getSymbol();
				}				

				else if (sword.getPos().equals(pos) && !sword.isPicked())
					mazeString+= sword.getSymbol();
				else
					mazeString += gameBoard.getBoard()[i][j];
		 * 
		 * 
		 * 
		 * 
		 */

		for (int i = 0; i < maze.getGameBoard().getBoard().length; i++){
			for (int j = 0; j < maze.getGameBoard().getBoard()[i].length; j++){
				pos.setX(j);
				pos.setY(i);
				
				if (maze.getHero().getPos().equals(pos))
					g.drawImage(hero, x, y, x + w, y + h, 0, 0, hero.getWidth(), hero.getHeight(), null);
				
				else if(maze.checkDragon(pos) instanceof Dragon && maze.checkDragon(pos).isAlive()){
					if (pos.equals(maze.getSword().getPos()) && !maze.getSword().isPicked()) // TODO Corrigir dragsword
						g.drawImage(sword, x, y, x + w, y + h, 0, 0, hero.getWidth(), hero.getHeight(), null);
					else if (maze.checkDragon(pos).isSleeping())
						g.drawImage(dragonSleeping, x, y, x + w, y + h, 0, 0, hero.getWidth(), hero.getHeight(), null);
					else
						g.drawImage(dragon, x, y, x + w, y + h, 0, 0, hero.getWidth(), hero.getHeight(), null);
				}				

				else if (maze.getSword().getPos().equals(pos) && !maze.getSword().isPicked())
					g.drawImage(sword, x, y, x + w, y + h, 0, 0, hero.getWidth(), hero.getHeight(), null);
				else if (maze.getGameBoard().getBoard()[j][i] == 'X')
					g.drawImage(wall, x, y, x + w, y + h, 0, 0, hero.getWidth(), hero.getHeight(), null);
				else
					g.drawImage(path, x, y, x + w, y + h, 0, 0, hero.getWidth(), hero.getHeight(), null);
					
				x+=width;
			}

			x = 10;
			y+=height;
		}
		
		x = 10;
		y = 10;
	}

}