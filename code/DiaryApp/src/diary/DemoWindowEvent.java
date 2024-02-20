package diary;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class DemoWindowEvent extends JFrame implements WindowListener, WindowFocusListener, WindowStateListener {

	static DemoWindowEvent frame = new DemoWindowEvent("DemoWindowEvent");
	JTextArea display;

	private void addComponentsToPane() {
		display = new JTextArea();
		display.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(display);
		scrollPane.setPreferredSize(new Dimension(500, 450));
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		addWindowListener(this);
		addWindowFocusListener(this);
		addWindowStateListener(this);

		checkWM();
	}

	public DemoWindowEvent(String name) {
		super(name);
	}

//Some window managers don't support all window states.

	public void checkWM() {
		Toolkit tk = frame.getToolkit();
		if (!(tk.isFrameStateSupported(Frame.ICONIFIED))) {
			displayMessage("Your window manager doesn't support ICONIFIED.");
		} else
			displayMessage("Your window manager supports ICONIFIED.");
		if (!(tk.isFrameStateSupported(Frame.MAXIMIZED_VERT))) {
			displayMessage("Your window manager doesn't support MAXIMIZED_VERT.");
		} else
			displayMessage("Your window manager supports MAXIMIZED_VERT.");
		if (!(tk.isFrameStateSupported(Frame.MAXIMIZED_HORIZ))) {
			displayMessage("Your window manager doesn't support MAXIMIZED_HORIZ.");
		} else
			displayMessage("Your window manager supports MAXIMIZED_HORIZ.");
		if (!(tk.isFrameStateSupported(Frame.MAXIMIZED_BOTH))) {
			displayMessage("Your window manager doesn't support MAXIMIZED_BOTH.");
		} else {
			displayMessage("Your window manager supports MAXIMIZED_BOTH.");
		}
	}

	public void windowClosing(WindowEvent e) {
		displayMessage("WindowListener method called: windowClosing.");
//A pause so user can see the message before
//the window actually closes.
		ActionListener task = new ActionListener() {
			boolean alreadyDisposed = false;

			public void actionPerformed(ActionEvent e) {
				if (frame.isDisplayable()) {
					alreadyDisposed = true;
					frame.dispose();
				}
			}
		};
		Timer timer = new Timer(500, task); // fire every half second
		timer.setInitialDelay(2000); // first delay 2 seconds
		timer.setRepeats(false);
		timer.start();
	}

	public void windowClosed(WindowEvent e) {
//This will only be seen on standard output.
		displayMessage("WindowListener method called: windowClosed.");
	}

	public void windowOpened(WindowEvent e) {
		displayMessage("WindowListener method called: windowOpened.");
	}

	public void windowIconified(WindowEvent e) {
		displayMessage("WindowListener method called: windowIconified.");
	}

	public void windowDeiconified(WindowEvent e) {
		displayMessage("WindowListener method called: windowDeiconified.");
	}

	public void windowActivated(WindowEvent e) {
		displayMessage("WindowListener method called: windowActivated.");
	}

	public void windowDeactivated(WindowEvent e) {
		displayMessage("WindowListener method called: windowDeactivated.");
	}

	public void windowGainedFocus(WindowEvent e) {
		displayMessage("WindowFocusListener method called: windowGainedFocus.");
	}

	public void windowLostFocus(WindowEvent e) {
		displayMessage("WindowFocusListener method called: windowLostFocus.");
	}

	public void windowStateChanged(WindowEvent e) {
		displayStateMessage("WindowStateListener method called: windowStateChanged.", e);
	}

	String newline = "\n";
	String space = " ";

	void displayMessage(String msg) {
		display.append(msg + newline);
		System.out.println(msg);
	}

	void displayStateMessage(String prefix, WindowEvent e) {
		int state = e.getNewState();
		int oldState = e.getOldState();
		String msg = prefix + newline + space + "New state: " + convertStateToString(state) + newline + space
				+ "Old state: " + convertStateToString(oldState);
		displayMessage(msg);
	}

	String convertStateToString(int state) {
		if (state == Frame.NORMAL) {
			return "NORMAL";
		}
		String strState = " ";
		if ((state & Frame.ICONIFIED) != 0) {
			strState += "ICONIFIED";
		}
//MAXIMIZED_BOTH is a concatenation of two bits, so
//we need to test for an exact match.
		if ((state & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH) {
			strState += "MAXIMIZED_BOTH";
		} else {
			if ((state & Frame.MAXIMIZED_VERT) != 0) {
				strState += "MAXIMIZED_VERT";
			}
			if ((state & Frame.MAXIMIZED_HORIZ) != 0) {
				strState += "MAXIMIZED_HORIZ";
			}
			if (" ".equals(strState)) {
				strState = "UNKNOWN";
			}
		}
		return strState.trim();
	}
}
