package melody.enums;

public class BMFAlign extends Enums {
	
	public final static BMFAlign LEFT = new BMFAlign("LEFT", 0);
	public final static BMFAlign RIGHT = new BMFAlign("RIGHT", 1);
	public final static BMFAlign CENTER = new BMFAlign("CENTER", 2);

	public BMFAlign(String Name, int Value) {
		super(Name, Value);
	}

}
