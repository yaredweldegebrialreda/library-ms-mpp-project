package librarysystem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import business.ControllerInterface;
import business.SystemController;

public class LibrarySystem extends JFrame implements LibWindow {
	ControllerInterface ci = new SystemController();
	public final static LibrarySystem INSTANCE = new LibrarySystem();
	JPanel mainPanel;
	JMenuBar menuBar;
	JMenu options;
	JMenuItem login, allBookIds, allMemberIds;
	String pathToImage;
	private boolean isInitialized = false;

	private static LibWindow[] allWindows = {
			LoginWindow.INSTANCE,
			AddMemberWindow.INSTANCE,
			CheckoutBookWindow.INSTANCE,
			BothRolesWindow.INSTANCE
	};

	public static void hideAllWindows() {
		for (LibWindow frame : allWindows) {
			frame.setVisible(false);
		}
	}

	private LibrarySystem() {}

	public void init() {
		LoginWindow.INSTANCE.init();
		Util.centerFrameOnDesktop(LoginWindow.INSTANCE);
		LoginWindow.INSTANCE.setVisible(true);
		isInitialized = true;
	}

	@Override
	public boolean isInitialized() {
		return isInitialized;
	}

	@Override
	public void isInitialized(boolean val) {
		isInitialized = val;
	}

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(() -> {
			LibrarySystem.INSTANCE.init();
		});
	}
}
