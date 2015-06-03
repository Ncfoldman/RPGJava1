package Pack;

public class Haus {
	
	Beruf beruf;
	Sucher owner;
	double geldlager;
	Ware [] lager;
	
	public Haus(String beruftyp, Sucher owner){
		
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
		this.lager = new Ware[900];
		this.beruf = new Beruf(beruftyp);
		this.owner = owner;
		this.geldlager = 0;
		
	}

	public Beruf getBeruf() {
		return beruf;
	}

	public void setBeruf(Beruf beruf) {
		this.beruf = beruf;
	}
	
	public void aktionWareINSlager(Ware item){
		
		int freiertemp = freierLagerplatz();
		if(freiertemp == -1){System.out.println("Item passt net ins Lager");}
		else{lager[freiertemp] = item;}
	}
	
	public int freierLagerplatz(){
		
		//Durchläuft das array und sucht einen freien platz wird nichts gefunden wird -1 zurückgegeben 
		int y = 0;
		int voll = 0;
		
		for (int i = 0; i < lager.length; i++) {
			if(lager[i] == null){
				y = i;
			}
			else{ voll ++;}
		}
		
		if(voll == lager.length){
			return -1;
		}
		else{return y;}
	}
	
	
	
}
