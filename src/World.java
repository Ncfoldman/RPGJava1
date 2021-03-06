package story;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class World {
	private static int width = 40;
	private static int height = 40;

	private Frame mainframe;

	private boolean dead;

	private Seeker player;

	private Fight fight;

	private Shop shop;

	private Random random;
	
	private int monsterkills;

	private Tile[][] tiles;

	private BufferedImage bomb = ImageLoader.scale(
			ImageLoader.loadImage("gfx/bomb.png"), Tile.getWidth(),
			Tile.getHeight());
	private BufferedImage flag = ImageLoader.scale(
			ImageLoader.loadImage("gfx/flag.png"), Tile.getWidth(),
			Tile.getHeight());
	private BufferedImage pressed = ImageLoader.scale(
			ImageLoader.loadImage("gfx/pressed.png"), Tile.getWidth(),
			Tile.getHeight());
	private BufferedImage normal = ImageLoader.scale(
			ImageLoader.loadImage("gfx/normal.png"), Tile.getWidth(),
			Tile.getHeight());
	private BufferedImage seeker = ImageLoader.scale(
			ImageLoader.loadImage("gfx/seekerimage.png"), Tile.getWidth(),
			Tile.getHeight());
	private BufferedImage house = ImageLoader.scale(
			ImageLoader.loadImage("gfx/house.png"), Tile.getWidth(),
			Tile.getHeight());
	private BufferedImage monster = ImageLoader.scale(
			ImageLoader.loadImage("gfx/monsterimage.png"), Tile.getWidth(),
			Tile.getHeight());
	private BufferedImage fightimage = ImageLoader.scale(
			ImageLoader.loadImage("gfx/fight.png"), 800,
			400);
	private BufferedImage shopimage = ImageLoader.scale(
			ImageLoader.loadImage("gfx/shop.png"), 400,
			400);

	public World(Frame frame) {
		this.mainframe = frame;
		this.dead = false;

		random = new Random();

		tiles = new Tile[width][height];

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				tiles[x][y] = new Tile(x, y, normal, bomb, pressed, flag,
						seeker, house, monster);
			}
		}

		reset();
	}

	/*
	 * private void placeBombs() { for(int i = 0;i < AMOUNT_OF_BOMBS;i++) {
	 * placeBomb(); } }
	 * 
	 * private void placeBomb() { int x = random.nextInt(width); int y =
	 * random.nextInt(height);
	 * 
	 * if(!tiles[x] [y].isBomb()) tiles[x] [y].setBomb(true); else placeBomb();
	 * }
	 */

	/*
	 * private void setNumbers() { for(int x = 0;x < width;x++) { for(int y =
	 * 0;y < height;y++) { int mx = x - 1; int gx = x + 1; int my = y - 1; int
	 * gy = y + 1;
	 * 
	 * int amountOfBombs = 0; if(mx >= 0&&my >= 0&&tiles[mx] [my].isBomb())
	 * amountOfBombs++; if(mx >= 0&&tiles[mx] [y].isBomb()) amountOfBombs++;
	 * if(mx >= 0&&gy < height&&tiles[mx] [gy].isBomb()) amountOfBombs++;
	 * 
	 * if(my >= 0&&tiles[x] [my].isBomb()) amountOfBombs++; if(gy <
	 * height&&tiles[x] [gy].isBomb()) amountOfBombs++;
	 * 
	 * if(gx < width&&my >= 0&&tiles[gx] [my].isBomb()) amountOfBombs++; if(gx <
	 * width&&tiles[gx] [y].isBomb()) amountOfBombs++; if(gx < width&&gy <
	 * height&&tiles[gx] [gy].isBomb()) amountOfBombs++;
	 * 
	 * tiles[x] [y].setAmountOfNearBombs(amountOfBombs); } } }
	 */
	/*
	 * public void clickedLeft(int x, int y) { if(!dead&&!finish) { int tileX =
	 * x/Tile.getWidth(); int tileY = y/Tile.getHeight();
	 * 
	 * if(!tiles[tileX] [tileY].isFlag()) { tiles[tileX]
	 * [tileY].setOpened(true);
	 * 
	 * if(tiles[tileX] [tileY].isBomb()) dead = true; else { if(tiles[tileX]
	 * [tileY].getAmountOfNearBombs() == 0) { open(tileX, tileY); } }
	 * 
	 * checkFinish(); } } }
	 */
	/*
	 * public void clickedRight(int x, int y) { if(!dead&&!finish) { int tileX =
	 * x/Tile.getWidth(); int tileY = y/Tile.getHeight(); tiles[tileX]
	 * [tileY].placeFlag();
	 * 
	 * checkFinish(); } }
	 */

	private void placeMonsters() {
		for (int i = 0; i < 40; i++) {
			placeMonster();
		}
	}

	public void placeMonster() {
		int x = random.nextInt(width);
		int y = random.nextInt(height);

		if (!tiles[x][y].isMonster() && !tiles[x][y].isHouse())
			tiles[x][y].setMonster(true);
		else
			placeMonster();
	}

	private void placeHouses() {
		for (int i = 0; i < 20; i++) {
			placeHouse();
		}
	}

	private void placeHouse() {
		int x = random.nextInt(width);
		int y = random.nextInt(height);

		if (!tiles[x][y].isMonster() && !tiles[x][y].isHouse())
			tiles[x][y].setHouse(true);
		else
			placeHouse();
	}

	public void clickedEnter() {

		if (!dead) {

			if (tiles[player.getX()][player.getY()].isHouse()) {
				if (this.shop != null) {
					this.shop.dispatchEvent(new WindowEvent(shop,
							WindowEvent.WINDOW_CLOSING));
					this.shop = null;
				}
				this.shop = new Shop(this,player,shopimage);
			} else if (tiles[player.getX()][player.getY()].isMonster()) {
				if (this.fight != null) {
					this.fight.dispatchEvent(new WindowEvent(fight,WindowEvent.WINDOW_CLOSING));
					this.fight = null;
				}
				this.fight = new Fight(this, player,fightimage);
			}
		}
	}

	public void clickedRightArrow() {
		if (player.getX() + 1 < width && !dead) {

			if (!tiles[player.getX() + 1][player.getY()].isOpened()) {
				tiles[player.getX() + 1][player.getY()].setOpened(true);
			} else {
				tiles[player.getX()][player.getY()].setSeeker(false);
				tiles[player.getX() + 1][player.getY()].setSeeker(true);
				player.setX(player.getX() + 1);
				stepcheck();
			}
		}
	}

	public void clickedLeftArrow() {
		if (player.getX() - 1 >= 0 && !dead) {

			if (!tiles[player.getX() - 1][player.getY()].isOpened()) {
				tiles[player.getX() - 1][player.getY()].setOpened(true);
			} else {
				tiles[player.getX()][player.getY()].setSeeker(false);
				tiles[player.getX() - 1][player.getY()].setSeeker(true);
				player.setX(player.getX() - 1);
				stepcheck();
			}
		}
	}

	public void clickedUpArrow() {
		if (player.getY() - 1 >= 0 && !dead) {

			if (!tiles[player.getX()][player.getY() - 1].isOpened()) {
				tiles[player.getX()][player.getY() - 1].setOpened(true);
			} else {
				tiles[player.getX()][player.getY()].setSeeker(false);
				tiles[player.getX()][player.getY() - 1].setSeeker(true);
				player.setY(player.getY() - 1);
				stepcheck();
			}
		}
	}

	public void clickedDownArrow() {
		if (player.getY() + 1 < height && !dead) {

			if (!tiles[player.getX()][player.getY() + 1].isOpened()) {
				tiles[player.getX()][player.getY() + 1].setOpened(true);
			} else {
				tiles[player.getX()][player.getY()].setSeeker(false);
				tiles[player.getX()][player.getY() + 1].setSeeker(true);
				player.setY(player.getY() + 1);
				stepcheck();
			}
		}
	}

	public void stepcheck() {

		// Everything that is done after every step
		
		double lifereg = player.getMaxlive()*0.05;
		if((player.getLive()+lifereg) > player.getMaxlive()){
				
			player.setLive(player.getMaxlive());
		}
		else
		{
			player.setLive(player.getLive() + lifereg);
		}
		
		
		if (this.fight != null) {
			this.fight.dispatchEvent(new WindowEvent(fight,
					WindowEvent.WINDOW_CLOSING));
			this.fight = null;
		}
		if (this.shop != null) {
			this.shop.dispatchEvent(new WindowEvent(shop,
					WindowEvent.WINDOW_CLOSING));
			this.shop = null;
		}
	}

	public void deleteMonster(int x, int y) {

		this.tiles[x][y].setMonster(false);
	}

	public void deletePlayer(int x, int y) {

		this.tiles[x][y].setSeeker(false);
	}

	/*
	 * private void open(int x, int y) { tiles[x] [y].setOpened(true);
	 * if(tiles[x] [y].getAmountOfNearBombs() == 0) { int mx = x - 1; int gx = x
	 * + 1; int my = y - 1; int gy = y + 1;
	 * 
	 * 
	 * if(mx >= 0&&my >= 0&&tiles[mx] [my].canOpen()) open(mx, my); if(mx >=
	 * 0&&tiles[mx] [y].canOpen()) open(mx, y); if(mx >= 0&&gy <
	 * height&&tiles[mx] [gy].canOpen()) open(mx, gy);
	 * 
	 * if(my >= 0&&tiles[x] [my].canOpen()) open(x, my); if(gy <
	 * height&&tiles[x] [gy].canOpen()) open(x, gy);
	 * 
	 * if(gx < width&&my >= 0&&tiles[gx] [my].canOpen()) open(gx, my); if(gx <
	 * width&&tiles[gx] [y].canOpen()) open(gx, y); if(gx < width&&gy <
	 * height&&tiles[gx] [gy].canOpen()) open(gx, gy);
	 * 
	 * // if(mx >= 0&&tiles[mx] [y].canOpen()) open(mx, y); // if(gx <
	 * width&&tiles[gx] [y].canOpen()) open(gx, y); // if(my >= 0&&tiles[x]
	 * [my].canOpen()) open(x, my); // if(gy < height&&tiles[x] [gy].canOpen())
	 * open(x, gy); } }
	 */

	public void reset() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				tiles[x][y].reset();
			}
		}

		dead = false;
		monsterkills = 0;
		player = new Seeker(0, 0);
		tiles[0][0].setSeeker(true);
		tiles[0][0].setOpened(true);
		placeHouses();
		placeMonsters();
	}

	public void draw(Graphics g) {

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				tiles[x][y].draw(g);
			}
		}

		if (dead) {
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.drawString("You're dead! Press R to reload!", 10, 30);
		}

		// Stats
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 18));
		g.drawString("STR:    " + Math.round(player.getStr()), 650, 20);
		g.drawString("INT:    " + Math.round(player.getInte()), 650, 40);
		g.drawString("CON:    " + Math.round(player.getCon()), 650, 60);
		g.drawString(
				"Life:   " + Math.round(player.getMaxlive()) + "/"
						+ Math.round(player.getLive()), 650, 80);
		g.drawString("DMG:    " + Math.round(player.getDmg()), 650, 100);
		g.drawString("DEF:    " + (Math.round(player.getArmorpoints() * 1000) / 1000.0), 650, 120);
		g.drawString("Esenco: " + Math.round(player.getEsenco()), 650, 140);
		g.drawString("Kills: " + monsterkills, 650, 160);

		// INFOS
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString(
				"Use Arrow Keys to Move and Enter to interact. S=Shop M=Monster",
				5, 795);

	}

	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}

	public boolean getDead() {
		return dead;
	}

	public Frame getMainframe() {
		return mainframe;
	}

	public int getMonsterkills() {
		return monsterkills;
	}

	public void setMonsterkills(int monsterkills) {
		this.monsterkills = monsterkills;
	}

	public void setMainframe(Frame mainframe) {
		this.mainframe = mainframe;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

}
