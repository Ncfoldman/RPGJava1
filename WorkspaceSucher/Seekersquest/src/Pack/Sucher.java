package Pack;

public class Sucher {
	
	String name,titel;
	double aktleben,maxleben,ruestwert,physchaden,waffenschaden,geld;
	int lebenszeit;
	Haus homesweethome;
	Ware[] inventar;
	MyRandom random2 = new MyRandom();
	
	public Sucher(String name){
		
		//doubles
		maxleben = 15;
		aktleben = 15;
		ruestwert = 0.01;
		physchaden = 1;
		waffenschaden = 0;
		geld = 0;
		//integers
		lebenszeit = 1 ;
		this.name = name;
		homesweethome = null;
		//
		inventar = new Ware[9];
	}
	
	public int freierInventarplatz(){
		
		//Durchläuft das array und sucht einen freien platz wird nichts gefunden wird -1 zurückgegeben 
		int y = 0;
		int voll = 0;
		
		for (int i = 0; i < inventar.length; i++) {
			if(inventar[i] == null){
				y = i;
			}
			else{ voll ++;}
		}
		
		if(voll == inventar.length){
			return -1;
		}
		else{return y;}
	}
	
	public boolean inventarleer(){
		
		if(this.inventar[0] == null && this.inventar[1] == null && this.inventar[2] == null && 
				this.inventar[3] == null && this.inventar[4] == null && this.inventar[5] == null &&
						this.inventar[6] == null && this.inventar[7] == null && this.inventar[8] == null )
			{return true;}
		
		else{return false;}
		
	}
	
	public void aktionWareaufnehmen(Ware item){
		
		int freiertemp = freierInventarplatz();
		if(freiertemp == -1){System.out.println("Item passt net ins Inventar");}
		else{inventar[freiertemp] = item;}
	}
	
	public void aktionWarenTypentfernen(String typ){
		
		boolean temp = false;
		for (int i = 0; i < inventar.length; i++) {
			if(temp == false){
				if(inventar[i] != null){if(inventar[i].typ == typ){inventar[i] = null;temp = true;}}
					}
		}
		
	}
	
	public boolean aktionErmittelnDrinnen(Haus haendler){
		
		// beruf (ja / nein) produziere ware verkaufe alles was du hast 
		
		if(inventarleer() != true){
			
			if(this.homesweethome == null){aktionVerkaufAlles(haendler);}
			else{
				
				/*
				 * Übergabe: 
				 * "Handel"
				 * "Alch"
				 * "Rune"
				 * "Rüst"
				 * "Bauer"
				 * "Forster"
				 * 
				 */ 
				
				switch(this.homesweethome.beruf.typ){
				
				case "Handel" : 
					
					break;
				
				case "Alch" : 
					int ton,kraut;
					ton = 0;
					kraut = 0;
					for (int i = 0; i < inventar.length; i++) {
						if(inventar[i].typ == "Kraut"){ kraut++; }
						if(inventar[i].typ == "Ton"){ton ++;}
					}
					
					for (int i = 0; i < ton; i++) {
							if(kraut > 3){
								this.aktionWarenTypentfernen("Ton");
								this.aktionWarenTypentfernen("Kraut");
								this.aktionWarenTypentfernen("Kraut");
								this.aktionWarenTypentfernen("Kraut");
								kraut = kraut - 3;
								this.homesweethome.aktionWareINSlager(new Ware("Trank"));
							}
							}
					
					this.aktionVerkaufAlles(haendler);	
					break;
				
				case "Rune" : 
					int temp2 = random2.myRandom(1, 2);
					if(temp2 == 1){this.aktionSuchebestimmt("Edel");}
					this.aktionSuchebestimmt("Zahn");
					break;
				
				case "Ruest" :
					int temp3 = random2.myRandom(1, 2);
					if(temp3 == 1){this.aktionSuchebestimmt("Meta");}
					else{this.aktionSuchebestimmt("Haut");}
					break;
				
				case "Bauer" : 
					this.aktionSuchebestimmt("Samen");
					break;
				
				case "Forster" : 
					this.aktionSuchebestimmt("Holz");
					break;
				
				}
			}
			
			return true;
			
		}
		else{ return false;}
		}
	
	public boolean aktionErmittelnDraußen(){
		
		//befüllt das inventar und gibt false zurück wenn das inventar voll ist 
		
		if(this.freierInventarplatz() != -1){
			
			if(this.homesweethome == null){this.aktionSucheUNbestimmt();}
			else{
				
				/*
				 * Übergabe: 
				 * "Handel"
				 * "Alch"
				 * "Rune"
				 * "Rüst"
				 * "Bauer"
				 * "Forster"
				 * 
				 */ 
				
				switch(this.homesweethome.beruf.typ){
				
				case "Handel" : 
					this.aktionSucheUNbestimmt();
					break;
				
				case "Alch" : 
					int temp = random2.myRandom(1, 2);
					if(temp == 1){this.aktionSuchebestimmt("Ton");}
					else{ this.aktionSuchebestimmt("Kraut"); }
					break;
				
				case "Rune" : 
					int temp2 = random2.myRandom(1, 2);
					if(temp2 == 1){this.aktionSuchebestimmt("Edel");}
					this.aktionSuchebestimmt("Zahn");
					break;
				
				case "Ruest" :
					int temp3 = random2.myRandom(1, 2);
					if(temp3 == 1){this.aktionSuchebestimmt("Meta");}
					else{this.aktionSuchebestimmt("Haut");}
					break;
				
				case "Bauer" : 
					this.aktionSuchebestimmt("Samen");
					break;
				
				case "Forster" : 
					this.aktionSuchebestimmt("Holz");
					break;
				
				}
			}
			
			return true;
			
		}
		else{ return false;}
	}
	
	public Haus getHomesweethome() {
		return homesweethome;
	}

	public void setHomesweethome(Haus homesweethome) {
		this.homesweethome = homesweethome;
	}

	public void aktionVerkaufAlles(Haus haus){
		// 0-8
		
		for (int j = 0; j < inventar.length; j++) {
			
			if(this.inventar[j] != null){
				haus.aktionWareINSlager(this.inventar[j]);
				this.inventar[j] = null;
				this.geld++;
				haus.geldlager--;
				haus.owner.geld = haus.owner.geld + haus.geldlager;
				haus.geldlager = 0;
				
			}
			
		}
		
		
	}
	
	public void aktionSuchebestimmt(String itemsuche){
		
		Ware item = new Ware(itemsuche);
		aktionWareaufnehmen(item);
	}
	
	
	public void aktionSucheUNbestimmt(){
		
		int temp = random2.myRandom(1, 9);
		Ware item;
		
		switch(temp){
			
		case 1 : 
			item = new Ware("Ton");
			aktionWareaufnehmen(item);
			break;
				
		case 2 : 
			item = new Ware("Kraut");
			aktionWareaufnehmen(item);
			break;
			
		case 3 : 
			item = new Ware("Edel");
			aktionWareaufnehmen(item);
			break;
			
		case 4 : 
			item = new Ware("Zahn");
			aktionWareaufnehmen(item);
			break;
			
		case 5 : 
			item = new Ware("Meta");
			aktionWareaufnehmen(item);
			break;
			
		case 6 : 
			item = new Ware("Haut");
			aktionWareaufnehmen(item);
			break;
			
		case 7 : 
			item = new Ware("Samen");
			aktionWareaufnehmen(item);
			break;
			
		case 8 : 
			item = new Ware("Holz");
			aktionWareaufnehmen(item);
			break;
			
		case 9 : 
			item = new Ware("Meat");
			aktionWareaufnehmen(item);
			break;
		
		}
		
	}
	
	
	
	
	
	
}
