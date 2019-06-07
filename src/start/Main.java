package start;


import presentation.MainGUI;
import java.util.logging.Logger;


public class Main {
	protected static final Logger LOGGER = Logger.getLogger(Main.class.getName());
	public static void main(String[] args) throws Exception {
		MainGUI a = new MainGUI();
		a.setVisible(true);
		//BookBLL.findProductById(3333);
	}
}
