package story;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Fight extends JFrame implements KeyListener {

	private static int width = 800;
	private static int height = 400;

	private Screen screen;
	private BufferedImage backimage;
	private Font font;
	private Seeker player;
	private Seeker enemy;
	private World world;
	private int insetLeft;
	private int insetTop;
	public boolean magic = false;
	

	Fight(World world, Seeker player,BufferedImage backimage) {

		super("Fight");
		this.world = world;
		this.player = player;
		this.backimage = backimage;
		Random r = new Random();
		double randomValue1 = 0.5 + (1.2 - 0.5) * r.nextDouble();
		double randomValue2 = 0.5 + (1.2 - 0.5) * r.nextDouble();
		double randomValue3 = 0.5 + (1.2 - 0.5) * r.nextDouble();
		enemy = new Seeker(player.getStr()*randomValue1, player.getInte()*randomValue2, player.getCon()*randomValue3);
		enemy.update();
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		addKeyListener(this);

		screen = new Screen();
		add(screen);

		pack();
		insetLeft = getInsets().left;
		insetTop = getInsets().top;
		setSize(width + insetLeft + getInsets().right, height + getInsets().bottom + insetTop);
		setLocationRelativeTo(null);
		setVisible(true);

		font = new Font("SansSerif", 0, 12);
		
	}
	
	public void checkDead(){
		
		if (enemy.getLive() <= 0.9) {
		
			player.setEsenco(player.getEsenco() + 5);
			world.deleteMonster(player.getX(), player.getY());
			world.setMonsterkills(world.getMonsterkills() + 1);
			world.placeMonster();
			world.getMainframe().repaint();
			this.dispatchEvent(new WindowEvent(this,
				WindowEvent.WINDOW_CLOSING));
	}

	else if(player.getLive() <= 0.9) {
		
			world.setDead(true);
			world.deletePlayer(player.getX(), player.getY());
			world.getMainframe().repaint();
			this.dispatchEvent(new WindowEvent(this,
				WindowEvent.WINDOW_CLOSING));
	}
		
	}

	public void attack() {
		
		enemy.setLive(enemy.getLive() - (player.getDmg() - player.getDmg() * enemy.getArmorpoints()));
		
		if (enemy.getLive() > 0.9) {
			
			player.setLive(player.getLive() - (enemy.getDmg() - enemy.getDmg() * player.getArmorpoints()));
		}
		
		
	}
	public void magAttack() {
		
		enemy.setLive(enemy.getLive() - (player.getMagdmg() - player.getMagdmg() * enemy.getMagarmorpoints()));
		
		if (enemy.getLive() > 0.9) {
			
			player.setLive(player.getLive() - (enemy.getDmg() - enemy.getDmg() * player.getArmorpoints()));
		}
		
	}
	
	public void heal() {
		
		double heal = player.getMagdmg()*1.5;
		if((player.getLive()+heal) > player.getMaxlive()){
			
			player.setLive(player.getMaxlive());
		}
		else
		{
			player.setLive(player.getLive() + heal);
		}
		
		
		if (enemy.getLive() > 0.9) {
			
			player.setLive(player.getLive() - (enemy.getDmg() - enemy.getDmg() * player.getArmorpoints()));
		}
		
	}
	

	public class Screen extends JPanel

	{
		@Override
		public void paintComponent(Graphics g) {
			
			g.setFont(font);
			g.drawImage(backimage, 0, 0, null);
			BufferedImage seeker = ImageLoader.scale(
					ImageLoader.loadImage("gfx/seekerimage.png"), 40,
					40);
			g.drawImage(seeker, 30, 150, null);
			BufferedImage monster = ImageLoader.scale(
					ImageLoader.loadImage("gfx/monsterimage.png"), 40,
					40);
			g.drawImage(monster, 750, 150, null);
			
			g.drawString(" You: " + " HP: "+ (Math.round(player.getLive() * 100) / 100) + " DMG: " + (Math.round(player.getDmg() * 100) / 100), 180, 280);
			g.drawString(" Enemy: " + " HP: "+ (Math.round(enemy.getLive() * 100) / 100) + " DMG: " + (Math.round(enemy.getDmg() * 100) / 100), 400, 280);
			
			if(!magic)
			{
				g.drawString(" 'P' for Attack ", 35, 280);
				g.drawString(" 'O' for Magic ", 35, 310);
				g.drawString(" 'L' for Run ", 35, 340);
			}
			else
			{
				g.drawString(" 'P' for Bolt ", 666, 280);
				g.drawString(" 'O' for Heal ", 666, 310);
				g.drawString(" 'L' for Back ", 666, 340);
			}
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
		
		if(e.getKeyCode() == KeyEvent.VK_P) {
			
			if(!magic)
			{
			attack();
			checkDead();
			repaint();
			}
			else
			{
				//Bolt
				magAttack();
				checkDead();
				magic = false;
				repaint();
			}
			
		}
		
		if (e.getKeyCode() == KeyEvent.VK_O) {
			
			if(!magic){
				//MagicMenu
				magic = true;
				repaint();
				
			}else{
				//Heal
				heal();
				checkDead();
				magic = false;
				repaint();
			}
			
		}

		if (e.getKeyCode() == KeyEvent.VK_L) {
			
			if(!magic)
			{
				//Run
				this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
				
			}else{
				
				//Back
				magic = false;
				repaint();
			}
			
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
