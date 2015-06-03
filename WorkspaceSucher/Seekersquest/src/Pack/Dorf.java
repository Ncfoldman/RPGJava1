package Pack;

public class Dorf {
	
	Sucher[] sucherarray;
	Haus[] hausarray;
	
	public Dorf(Sucher haendler,Sucher alch,Sucher rune,Sucher ruest,Sucher bauer,Sucher forst){
		
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
		
		this.sucherarray = new Sucher[30];
		this.hausarray = new Haus[8];
		//							 beruf     besitzer
		this.hausarray[0] = new Haus("Handel",haendler); 	haendler.setHomesweethome(this.hausarray[0]);
		this.hausarray[1] = new Haus("Alch",alch);	alch.setHomesweethome(this.hausarray[1]);
		this.hausarray[2] = new Haus("Rune",rune);	rune.setHomesweethome(this.hausarray[2]);
		this.hausarray[3] = new Haus("Ruest",ruest);	ruest.setHomesweethome(this.hausarray[3]);
		this.hausarray[4] = new Haus("Bauer",bauer);	bauer.setHomesweethome(this.hausarray[4]);
		this.hausarray[5] = new Haus("Forster",forst);	forst.setHomesweethome(this.hausarray[5]);
		this.hausarray[6] = null;
		this.hausarray[7] = null;
	}
	
	
	public void addSucher(Sucher sucher){
		
		int freiertemp = freierPlatz();
		if(freiertemp == -1){System.out.println("Sucher passt net ins dorf");}
		else{sucherarray[freiertemp] = sucher;}
		
	}
	
	public void removeSucher(Sucher sucher){
		
		for (int i = 0; i < sucherarray.length; i++) {
			
			if(sucherarray[i] == sucher){
				sucherarray[i] = null;
			}
			
		}
		
	}


	public void ausgabeDorf() {
		
		for (int i = 0; i < hausarray.length; i++) {
			if(hausarray[i] != null){System.out.println("Gebäude im Dorf: " + hausarray[i].geldlager);}
			
		}
		
	}
	
	public int freierPlatz(){
		
		//Durchläuft das array und sucht einen freien platz wird nichts gefunden wird -1 zurückgegeben 
		int y = 0;
		int voll = 0;
		
		for (int i = 0; i < sucherarray.length; i++) {
			if(sucherarray[i] == null){
				y = i;
			}
			else{ voll ++;}
		}
		
		if(voll == sucherarray.length){
			return -1;
		}
		else{return y;}
	}
	
	public boolean istSucherimDorf(Sucher sucher){
		
		//ist der sucher im dorf true - sonst false 
		
		boolean temp = false;
		
		for (int i = 0; i < sucherarray.length; i++) {
			
			if(sucherarray[i] == sucher){temp = true;}
			
		}
		return temp;
		}
	
	public void GesamtvermoegenHaeuser(){
		
		double temp = 0;
		
		for (int i = 0; i < hausarray.length; i++) {
			
			if(hausarray[i] != null){ temp = temp + hausarray[i].geldlager; }
			
		}
		
		
		System.out.println("Gesamtvermögen(Dorf): " + temp);System.out.println();
	}




}













