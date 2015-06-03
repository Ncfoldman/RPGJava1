package Pack;

public class World {
	Knoten head;
	int sucheranzahl;
	Dorf dorf1;
	MyRandom random1;
	
	public World(){
		
		random1 = new MyRandom();
		Sucher haendler = new Sucher("Händler");
		Sucher alchemist = new Sucher("Alchemist");
		Sucher runenschmied = new Sucher("Runenschmied");
		Sucher ruestschmied = new Sucher("Rüstschmied");
		Sucher bauer = new Sucher("Bauer");
		Sucher forster = new Sucher("Forster");
		dorf1= new Dorf(haendler,alchemist,runenschmied,ruestschmied,bauer,forster);
		head = new Knoten(haendler);sucheranzahl = 1;
		addSucher(alchemist);
		addSucher(runenschmied);
		addSucher(ruestschmied);
		addSucher(bauer);
		addSucher(forster);
	}
	
	
	public void startup(){
		
		for(int i = 0; i < 5000;i++){
		Knoten temp = new Knoten(null);
		temp = this.head;
		
		while (temp.getNaechster() != null){
			
			//Drraußen 
			if(dorf1.istSucherimDorf(temp.sucher) == false){
				if(temp.sucher.aktionErmittelnDraußen() == true){} //
				else{dorf1.addSucher(temp.sucher);}
				}
			//Drinnen
			else{ 
				if(temp.sucher.aktionErmittelnDrinnen(dorf1.hausarray[0]) == true){}
				else{dorf1.removeSucher(temp.sucher);}
				}
			
			
			temp = temp.getNaechster();
			
		}
		
		if(temp.getNaechster() == null){
			
			//Draußen
			if(dorf1.istSucherimDorf(temp.sucher) == false){
				if(temp.sucher.aktionErmittelnDraußen() == true){}
				else{dorf1.addSucher(temp.sucher);}
			}
		
			//Drinnen
			else{ 
				if(temp.sucher.aktionErmittelnDrinnen(dorf1.hausarray[0]) == true){}
				else{dorf1.removeSucher(temp.sucher);}	
			}
			}
		
		
		
		
		
		
		if(i % 73 == 0){
			int randomsuchername = random1.myRandom(0,999);
			addSucher(new Sucher("S" + randomsuchername));
			}
		
		
		
		
		
		
		
		ausgabeAllerSucher();
		dorf1.GesamtvermoegenHaeuser();
		}
		
	}
	
	public void addSucher(Sucher sucher){
		
		//zählt SUCHERANZAHL hoch also uffpasse
		Knoten temp = new Knoten(null);
		temp = this.head;
		
		while(temp.getNaechster() != null){
			temp = temp.getNaechster();
		}
		
		temp.setNaechster(new Knoten(sucher));
		this.sucheranzahl++;
	}
	
	public void deleteSucher(Sucher sucher){
		
	}
	
	public void ausgabeAllerSucher(){
		
		Knoten temp = new Knoten(null);
		temp = this.head;
		int i = 1;
		do{
			System.out.print("(" + i + "/" + sucheranzahl +")" + ". Sucher: " + temp.sucher.name + " --- " + temp.sucher.geld +" € ");
			System.out.print("Inventar: ");
			for (int j = 0; j < 9; j++) {
				
				if(temp.sucher.inventar[j] == null){System.out.print("NULL");}
				else { System.out.print(temp.sucher.inventar[j].typ); }
			}
			System.out.print(" ");
			temp = temp.getNaechster();
			System.out.println();
			i++;
		}while(temp.getNaechster() != null);
		
		if(temp.getNaechster() == null){
			System.out.print("(" + i + "/" + sucheranzahl +")" + ". Sucher: " + temp.sucher.name + " --- " + temp.sucher.geld +" € ");
			System.out.print("Inventar: ");
			for (int j = 0; j < temp.sucher.inventar.length; j++) {
				
				if(temp.sucher.inventar[j] == null){System.out.print("NULL");}
				else { System.out.print(temp.sucher.inventar[j].typ); }
			}
			}
		
		System.out.println();System.out.println();
	}
	
	

	
}
