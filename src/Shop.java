package story;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Shop extends JFrame implements KeyListener {

	private static int width = 400;
	private static int height = 400;

	private Screen screen;
	private BufferedImage backimage;
	private World world;
	private Font font;
	private Seeker player;
	private int insetLeft;
	private int insetTop;

	Shop(World world,Seeker player,BufferedImage backimage) {

		super("Shop");

		this.player = player;
		this.world = world;
		this.setBackimage(backimage);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		addKeyListener(this);

		screen = new Screen();
		add(screen);

		pack();
		insetLeft = getInsets().left;
		insetTop = getInsets().top;
		setSize(width + insetLeft + getInsets().right, height
				+ getInsets().bottom + insetTop);
		setLocationRelativeTo(null);
		setVisible(true);

		font = new Font("SansSerif", 0, 12);

	}

	public class Screen extends JPanel

	{
		@Override
		public void paintComponent(Graphics g) {
			g.setFont(font);
			g.drawImage(backimage, 0, 0, null);
			g.drawString(" Your E: " + player.getEsenco(), 10, 285);
			g.drawString(" 'P' for STR (costs 15E) " + player.getStr(), 10, 310);
			g.drawString(" 'O' for INT (costs 15E) " + player.getInte(), 10, 335);
			g.drawString(" 'L' for CON (costs 15E) " + player.getCon(), 10, 360);
			g.drawString(" 'K' for Leave", 10, 385);
		}
	}

	public static int getScreenWidth() {
		return width;
	}

	public static int getScreenHeight() {
		return width;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_P && player.getEsenco() >= 15) {
			
			// Buy Str
			player.setStr(player.getStr() + 1);
			player.setEsenco(player.getEsenco() - 15);
			player.update();
			repaint();
			world.getMainframe().repaint();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_O && player.getEsenco() >= 15) {
			
			//Buy Int
			player.setInte(player.getInte() + 1);
			player.setEsenco(player.getEsenco() - 15);
			player.update();
			repaint();
			world.getMainframe().repaint();
		}

		if (e.getKeyCode() == KeyEvent.VK_L && player.getEsenco() >= 15) {
			
			//Buy Con
			player.setCon(player.getCon() + 1);
			player.setEsenco(player.getEsenco() - 15);
			player.update();
			repaint();
			world.getMainframe().repaint();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_K) {
			
			world.getMainframe().repaint();
			this.dispatchEvent(new WindowEvent(this,
				WindowEvent.WINDOW_CLOSING));
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public BufferedImage getBackimage() {
		return backimage;
	}

	public void setBackimage(BufferedImage backimage) {
		this.backimage = backimage;
	}

}

