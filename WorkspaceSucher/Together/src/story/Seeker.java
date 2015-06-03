package story;

public class Seeker {
	
	int x;
	int y;
	private double maxlive,live,str,con,inte,esenco,dmg,wapdmg,magdmg,armorpoints,magarmorpoints;
	
	
	public Seeker (int x, int y) {
		
		//for player
		this.x = x;
		this.y = y;
		this.setStr(10);
		this.setInte(10);
		this.setCon(15);
		this.setEsenco(0);
		this.setWapdmg(0);
		
		this.update();
		this.setLive(this.getMaxlive()); 
	
	}

	public Seeker(double str, double inte, double con) {
		
		//for monster
		this.setStr(str);
		this.setInte(inte);
		this.setCon(con);
		this.setEsenco(0);
		this.setWapdmg(0);
		
		this.update();
		this.setLive(this.getMaxlive()); 
	
	}
	
	public void update()
	{
		//update player stats ( not aktLive ! ) 
		
		this.setMaxlive(this.getCon()*10);
		this.setDmg(this.getStr()*2);
		this.setMagdmg(this.getInte()*2);
		this.setArmorpoints(1-(1/((this.getStr()/10 + this.getCon()/5)/1000 + 1)));
		this.setMagarmorpoints(1-(1/((this.getInte()/10 + this.getCon()/5)/1000+1)));

		}
	
	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getMaxlive() {
		return maxlive;
	}

	public void setMaxlive(double maxlive) {
		this.maxlive = maxlive;
	}

	public double getLive() {
		return live;
	}

	public void setLive(double live) {
		this.live = live;
	}

	public double getStr() {
		return str;
	}

	public void setStr(double str) {
		this.str = str;
	}

	public double getCon() {
		return con;
	}

	public void setCon(double con) {
		this.con = con;
	}

	public double getInte() {
		return inte;
	}

	public void setInte(double inte) {
		this.inte = inte;
	}

	public double getEsenco() {
		return esenco;
	}

	public void setEsenco(double esenco) {
		this.esenco = esenco;
	}

	public double getDmg() {
		return dmg;
	}

	public void setDmg(double dmg) {
		this.dmg = dmg;
	}

	public double getMagdmg() {
		return magdmg;
	}

	public void setMagdmg(double magdmg) {
		this.magdmg = magdmg;
	}

	public double getArmorpoints() {
		return armorpoints;
	}

	public void setArmorpoints(double armorpoints) {
		this.armorpoints = armorpoints;
	}

	public double getMagarmorpoints() {
		return magarmorpoints;
	}

	public void setMagarmorpoints(double magarmorpoints) {
		this.magarmorpoints = magarmorpoints;
	}

	public double getWapdmg() {
		return wapdmg;
	}

	public void setWapdmg(double wapdmg) {
		this.wapdmg = wapdmg;
	}
}
