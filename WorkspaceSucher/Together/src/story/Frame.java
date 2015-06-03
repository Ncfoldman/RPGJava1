package story;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Frame extends JFrame implements MouseListener, KeyListener
{
	private static int width = 800;
	private static int height = 800;
	
	private Screen screen;
	private World world;
	private Font font;
	
	private int insetLeft;
	private int insetTop;
	
	public Frame()
	{
		super("Seekers Quest");
		
		world = new World(this);
		
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addMouseListener(this);
		addKeyListener(this);
		
		screen = new Screen();
		add(screen);
		
		pack();
		insetLeft = getInsets().left;
		insetTop = getInsets().top;
		setSize(width + insetLeft + getInsets().right, height + getInsets().bottom + insetTop);
		setLocationRelativeTo(null);
		setVisible(true);
		
		font = new Font("Arial", Font.BOLD, 15);
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		/*
		if(e.getButton() == 1) world.clickedLeft(e.getX() - insetLeft, e.getY() - insetTop);
		if(e.getButton() == 3) world.clickedRight(e.getX() - insetLeft, e.getY() - insetTop);
		screen.repaint();
		*/
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_R)
		{
			world.reset();
			screen.repaint();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			world.clickedEnter();
			screen.repaint();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			world.clickedRightArrow();
			screen.repaint();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			world.clickedLeftArrow();
			screen.repaint();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			world.clickedUpArrow();
			screen.repaint();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			world.clickedDownArrow();
			screen.repaint();
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		
	}
	
	public class Screen extends JPanel
	{
		@Override
		public void paintComponent(Graphics g)
		{
			g.setFont(font);
			world.draw(g);
		}
	}
	
	public static int getScreenWidth()
	{
		return width;
	}
	
	public static int getScreenHeight()
	{
		return width;
	}
}
