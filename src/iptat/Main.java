package iptat;

import javax.swing.SwingUtilities;

import iptat.gui.UserInterface;

public class Main {
	public static void main(String args[]) {
		SwingUtilities.invokeLater(new UserInterface());
	}
}
