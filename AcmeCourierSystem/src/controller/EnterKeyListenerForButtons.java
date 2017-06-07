package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;;

public class EnterKeyListenerForButtons implements KeyListener {

	private JButton button;

	public EnterKeyListenerForButtons(JButton button) {
		this.button = button;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			button.doClick();
		}
	}

}
