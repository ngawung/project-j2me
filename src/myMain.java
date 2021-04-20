import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Form;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;


public class myMain extends MIDlet {

	public myMain() {
		// TODO Auto-generated constructor stub
	}

	protected void destroyApp(boolean unconditional)
			throws MIDletStateChangeException {
		// TODO Auto-generated method stub

	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	protected void startApp() throws MIDletStateChangeException {
		// TODO Auto-generated method stub
		Form form = new Form("test");
		Display.getDisplay(this).setCurrent(form);
		
		System.out.println("test");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		

	}

}
