package Pack;

public class Knoten {

	Knoten naechster;
	Sucher sucher;

	public Knoten(Sucher sucher) {
		this.sucher = sucher;
		naechster = null;
	}

	public Knoten getNaechster() {
		return naechster;
	}

	public void setNaechster(Knoten naechster) {
		this.naechster = naechster;
	}

}
