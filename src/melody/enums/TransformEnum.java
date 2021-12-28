package melody.enums;

public class TransformEnum extends Enums {
	
	public final static TransformEnum FLIP_X = new TransformEnum("FLIP_X", 0);
	public final static TransformEnum FLIP_Y = new TransformEnum("FLIP_Y", 1);
	
	public final static TransformEnum ROTATE_90 = new TransformEnum("ROTATE_90", 2);
	public final static TransformEnum ROTATE_180 = new TransformEnum("ROTATE_180", 3);
	public final static TransformEnum ROTATE_270 = new TransformEnum("ROTATE_270", 4);
	
	public final static TransformEnum FLIP_ROTATE_90 = new TransformEnum("FLIP_ROTATE_90", 5);
	public final static TransformEnum FLIP_ROTATE_180 = new TransformEnum("FLIP_ROTATE_180", 6);
	public final static TransformEnum FLIP_ROTATE_270 = new TransformEnum("FLIP_ROTATE_270", 7);
	
	public final static TransformEnum NONE = new TransformEnum("NONE", 8);

	private TransformEnum(String Name, int Value) {
		super(Name, Value);
		
	}

}

