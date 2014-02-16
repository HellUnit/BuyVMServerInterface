package org.blazr.BuyVMServerInterface.main.java;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

import java.awt.Font;

import javax.swing.JScrollPane;

import java.awt.Cursor;

import org.blazr.Updater.main.java.AboutDialog;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow {
	protected static void defaultText(final boolean defaultsNotify) {
		if (!defaultsNotify) {
			MainWindow.txtpnServerStatusGui.setText("BuyVM Server Interface v"
					+ MainWindow.version + "\r\nDeveloped"
					+ " by Antony Prince\r\nJanuary"
					+ " 2014\r\nhttp://blazr.no-ip.org");
		} else {
			MainWindow.txtpnServerStatusGui.setText("BuyVM Server Interface v"
					+ MainWindow.version + "\r\nDeveloped"
					+ " by Antony Prince\r\nJanuary"
					+ " 2014\r\nhttp://blazr.no-ip.org\n" + "WARNING!!!\n"
					+ "Default values detected. Please set hash & key to"
					+ " your info.");
		}
	}

	protected static void disableAll(final boolean setKeyDisabled) {
		MainWindow.getBtnNewButton().setEnabled(false);
		// getBtnNewButton_1().setEnabled(false);
		MainWindow.getBtnNewButton_2().setEnabled(false);
		MainWindow.getBtnNewButton_3().setEnabled(false);
		if (setKeyDisabled) {
			MainWindow.getBtnNewButton_4().setEnabled(false);
		}
	}

	protected static void enableAll(final boolean setKeyOnly) {
		if (setKeyOnly) {
			MainWindow.getBtnNewButton_4().setEnabled(true);
		} else {
			MainWindow.getBtnNewButton().setEnabled(true);
			// getBtnNewButton_1().setEnabled(true);
			MainWindow.getBtnNewButton_2().setEnabled(true);
			MainWindow.getBtnNewButton_3().setEnabled(true);
			MainWindow.getBtnNewButton_4().setEnabled(true);
		}
	}

	protected static JButton getBtnNewButton() {
		return MainWindow.btnNewButton;
	}

	protected static JButton getBtnNewButton_1() {
		return MainWindow.btnNewButton_1;
	}

	protected static JButton getBtnNewButton_2() {
		return MainWindow.btnNewButton_2;
	}

	protected static JButton getBtnNewButton_3() {
		return MainWindow.btnNewButton_3;
	}

	protected static JButton getBtnNewButton_4() {
		return MainWindow.btnNewButton_4;
	}

	protected static JTextArea getTxtpnServerStatusGui() {
		return MainWindow.txtpnServerStatusGui;
	}

	protected static boolean hasDefaults() {
		boolean hasDefaults = false;
		if (MainWindow.key.contentEquals("EXAMP-LEKEY-XXXXX")
				|| MainWindow.hash.contentEquals("XXXXXXXXXXXXXXXXXXXX")) {
			hasDefaults = true;
		}
		return hasDefaults;
	}

	private JFrame frmServerStatus;
	protected final static String version = "1.0.1-SNAPSHOT";
	protected static String key = "";
	protected static String hash = "";
	private final static String infoFileLoc = "." + File.separator
			+ "infoFile.txt";
	private static JButton btnNewButton;
	private static JButton btnNewButton_1;
	private static JButton btnNewButton_4;

	private static JButton btnNewButton_2;

	private static JButton btnNewButton_3;

	protected static Operations ops = new Operations();

	private JCheckBox chckbxNewCheckBox_1;

	private JCheckBox chckbxNewCheckBox_2;

	private JCheckBox chckbxNewCheckBox;

	private JCheckBox chckbxStatus;

	private static JTextArea txtpnServerStatusGui;

	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					final MainWindow window = new MainWindow();
					window.frmServerStatus.setVisible(true);
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JCheckBox chckbxAllIps;

	private JScrollPane scrollPane;

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	protected JCheckBox getChckbxAllIps() {
		return this.chckbxAllIps;
	}

	protected JCheckBox getChckbxNewCheckBox() {
		return this.chckbxNewCheckBox;
	}

	protected JCheckBox getChckbxNewCheckBox_1() {
		return this.chckbxNewCheckBox_1;
	}

	protected JCheckBox getChckbxNewCheckBox_2() {
		return this.chckbxNewCheckBox_2;
	}

	protected JCheckBox getChckbxStatus() {
		return this.chckbxStatus;
	}

	private String getExtraOptions() {
		String extraOptions = "";
		if (getChckbxNewCheckBox().isSelected()) {
			extraOptions += "&mem=true";
		}
		if (getChckbxNewCheckBox_1().isSelected()) {
			extraOptions += "&hdd=true";
		}
		if (getChckbxNewCheckBox_2().isSelected()) {
			extraOptions += "&bw=true";
		}
		if (getChckbxStatus().isSelected()) {
			extraOptions += "&status=true";
		}
		if (getChckbxAllIps().isSelected()) {
			extraOptions += "&ipaddr=true";
		}
		return extraOptions;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.frmServerStatus = new JFrame();
		this.frmServerStatus.setResizable(false);
		this.frmServerStatus.setTitle("BuyVM Server Interface");
		this.frmServerStatus.setBounds(100, 100, 600, 420);
		this.frmServerStatus.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frmServerStatus.getContentPane().setLayout(null);
		MainWindow.btnNewButton = new JButton("Start Server");
		MainWindow.btnNewButton.setCursor(Cursor
				.getPredefinedCursor(Cursor.HAND_CURSOR));
		MainWindow.btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				startThread(1);
			}
		});
		MainWindow.btnNewButton.setBounds(12, 39, 131, 37);
		this.frmServerStatus.getContentPane().add(MainWindow.btnNewButton);

		MainWindow.btnNewButton_1 = new JButton("Reboot Server");
		MainWindow.btnNewButton_1.setCursor(Cursor
				.getPredefinedCursor(Cursor.HAND_CURSOR));
		MainWindow.btnNewButton_1.setEnabled(false);
		MainWindow.btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				startThread(2);
			}
		});
		MainWindow.btnNewButton_1.setBounds(12, 89, 131, 37);
		this.frmServerStatus.getContentPane().add(MainWindow.btnNewButton_1);

		MainWindow.btnNewButton_2 = new JButton("Stop Server");
		MainWindow.btnNewButton_2.setCursor(Cursor
				.getPredefinedCursor(Cursor.HAND_CURSOR));
		MainWindow.btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				startThread(3);
			}
		});
		MainWindow.btnNewButton_2.setBounds(155, 39, 131, 37);
		this.frmServerStatus.getContentPane().add(MainWindow.btnNewButton_2);

		MainWindow.btnNewButton_3 = new JButton("Server Info");
		MainWindow.btnNewButton_3.setCursor(Cursor
				.getPredefinedCursor(Cursor.HAND_CURSOR));
		MainWindow.btnNewButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				startThread(4);
			}
		});
		MainWindow.btnNewButton_3.setBounds(155, 89, 131, 37);
		this.frmServerStatus.getContentPane().add(MainWindow.btnNewButton_3);

		MainWindow.btnNewButton_4 = new JButton("Set Key/Hash");
		MainWindow.btnNewButton_4.setCursor(Cursor
				.getPredefinedCursor(Cursor.HAND_CURSOR));
		MainWindow.btnNewButton_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				final SetKeyHashDialog dialog = new SetKeyHashDialog();
				dialog.setVisible(true);
			}
		});
		MainWindow.btnNewButton_4.setBounds(12, 139, 131, 37);
		this.frmServerStatus.getContentPane().add(MainWindow.btnNewButton_4);

		final JPanel panel = new JPanel();
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
				null));
		panel.setBounds(401, 39, 183, 137);
		this.frmServerStatus.getContentPane().add(panel);
		panel.setLayout(null);

		this.chckbxNewCheckBox_1 = new JCheckBox("HDD");
		this.chckbxNewCheckBox_1.setSelected(true);
		this.chckbxNewCheckBox_1.setBounds(8, 9, 114, 25);
		panel.add(this.chckbxNewCheckBox_1);

		this.chckbxNewCheckBox_2 = new JCheckBox("Bandwidth");
		this.chckbxNewCheckBox_2.setSelected(true);
		this.chckbxNewCheckBox_2.setBounds(8, 39, 113, 25);
		panel.add(this.chckbxNewCheckBox_2);

		this.chckbxNewCheckBox = new JCheckBox("Memory");
		this.chckbxNewCheckBox.setSelected(true);
		this.chckbxNewCheckBox.setBounds(8, 73, 113, 25);
		panel.add(this.chckbxNewCheckBox);

		this.chckbxStatus = new JCheckBox("Status");
		this.chckbxStatus.setSelected(true);
		this.chckbxStatus.setBounds(8, 103, 83, 25);
		panel.add(this.chckbxStatus);

		this.chckbxAllIps = new JCheckBox("All IP's");
		this.chckbxAllIps.setBounds(95, 103, 80, 25);
		panel.add(this.chckbxAllIps);

		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(14, 189, 570, 185);
		this.frmServerStatus.getContentPane().add(this.scrollPane);

		MainWindow.txtpnServerStatusGui = new JTextArea();
		this.scrollPane.setViewportView(MainWindow.txtpnServerStatusGui);
		MainWindow.txtpnServerStatusGui.setFont(new Font("Tahoma", Font.PLAIN,
				15));
		MainWindow.txtpnServerStatusGui.setWrapStyleWord(true);
		MainWindow.txtpnServerStatusGui.setLineWrap(true);
		MainWindow.txtpnServerStatusGui.setEditable(false);

		final JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 594, 26);
		this.frmServerStatus.getContentPane().add(menuBar);

		final JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		final JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);

		final JMenu mnAbout = new JMenu("About");
		mnAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent arg0) {
				final AboutDialog dialog = new AboutDialog();
				dialog.setProgramName("BuyVMServerInterface");
				dialog.setText("BuyVM Server Interface", MainWindow.version);
				dialog.setProgramVersion(MainWindow.version);
				dialog.setExeDownloadEnabled(false);
				dialog.setLocationRelativeTo(menuBar);
				dialog.setVisible(true);
			}
		});
		mnAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				final AboutDialog dialog = new AboutDialog();
				dialog.setProgramName("BuyVMServerInterface");
				dialog.setText("BuyVM Server Interface", MainWindow.version);
				dialog.setProgramVersion(MainWindow.version);
				dialog.setExeDownloadEnabled(false);
				dialog.setLocationRelativeTo(menuBar);
				dialog.setVisible(true);
			}
		});
		menuBar.add(mnAbout);
		this.frmServerStatus.getContentPane().setFocusTraversalPolicy(
				new FocusTraversalOnArray(new Component[] {
						MainWindow.btnNewButton, MainWindow.btnNewButton_2,
						MainWindow.btnNewButton_3, MainWindow.btnNewButton_4,
						this.chckbxNewCheckBox_1, this.chckbxNewCheckBox_2,
						this.chckbxNewCheckBox, this.chckbxStatus,
						this.chckbxAllIps }));
		this.frmServerStatus.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { MainWindow.btnNewButton,
						MainWindow.btnNewButton_2, MainWindow.btnNewButton_3,
						MainWindow.btnNewButton_4, this.chckbxNewCheckBox_1,
						this.chckbxNewCheckBox_2, this.chckbxNewCheckBox,
						this.chckbxStatus, this.chckbxAllIps }));
		try {
			final File infoFile = new File(MainWindow.infoFileLoc);
			boolean fileCreated;

			fileCreated = infoFile.createNewFile();

			if (fileCreated) {
				MainWindow.ops.writeKeyHash("EXAMP-LEKEY-XXXXX",
						"XXXXXXXXXXXXXXXXXXXX");
			} else {
				String sCurrentLine;
				final BufferedReader br1 = new BufferedReader(new FileReader(
						"." + File.separator + "infoFile.txt"));
				while ((sCurrentLine = br1.readLine()) != null) {
					if (sCurrentLine.startsWith("Key:")) {
						MainWindow.key = sCurrentLine.substring(
								sCurrentLine.indexOf(":") + 1).trim();
					} else if (sCurrentLine.startsWith("Hash:")) {
						MainWindow.hash = sCurrentLine.substring(
								sCurrentLine.indexOf(":") + 1).trim();
					} else {
						MainWindow.getTxtpnServerStatusGui().setText(
								"Invalid Data in infoFile.txt\n" + "\""
										+ sCurrentLine + "\" is invalid.");
					}
				}
				br1.close();
			}
			if (MainWindow.hasDefaults()) {
				MainWindow.defaultText(true);
				MainWindow.disableAll(false);
			} else {
				MainWindow.defaultText(false);
				MainWindow.enableAll(false);
			}
		} catch (final IOException e) {
			e.printStackTrace();
			MainWindow.getTxtpnServerStatusGui().setText(
					e.getStackTrace()[0].toString() + "\n"
							+ e.getStackTrace()[1].toString() + "\n"
							+ e.getStackTrace()[2].toString());
		}
	}

	private void startThread(final int type) {
		MainWindow.ops.startWaitThread();
		final DataThread thread = new DataThread();
		switch (type) {
		case 1:
			thread.args = "?key=" + MainWindow.key + "&hash=" + MainWindow.hash
					+ "&action=boot" + getExtraOptions();
			break;
		case 2:
			thread.args = "?key=" + MainWindow.key + "&hash=" + MainWindow.hash
					+ "&action=reboot" + getExtraOptions();
			break;
		case 3:
			thread.args = "?key=" + MainWindow.key + "&hash=" + MainWindow.hash
					+ "&action=shutdown" + getExtraOptions();
			break;
		case 4:
			thread.args = "?key=" + MainWindow.key + "&hash=" + MainWindow.hash
					+ "&action=info" + getExtraOptions();
			break;
		default:
			thread.args = "?key=" + MainWindow.key + "&hash=" + MainWindow.hash
					+ "&action=info" + getExtraOptions();
			break;
		}
		thread.start();
	}
}
