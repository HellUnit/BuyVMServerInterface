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
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow {
	private JFrame frmServerStatus;
	protected final static String version = "1.0.0";
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
	private JCheckBox chckbxAllIps;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmServerStatus.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmServerStatus = new JFrame();
		frmServerStatus.setResizable(false);
		frmServerStatus.setTitle("BuyVM Server Interface");
		frmServerStatus.setBounds(100, 100, 600, 420);
		frmServerStatus.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServerStatus.getContentPane().setLayout(null);
		btnNewButton = new JButton("Start Server");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				startThread(1);
			}
		});
		btnNewButton.setBounds(12, 39, 131, 37);
		frmServerStatus.getContentPane().add(btnNewButton);

		btnNewButton_1 = new JButton("Reboot Server");
		btnNewButton_1
				.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startThread(2);
			}
		});
		btnNewButton_1.setBounds(12, 89, 131, 37);
		frmServerStatus.getContentPane().add(btnNewButton_1);

		btnNewButton_2 = new JButton("Stop Server");
		btnNewButton_2
				.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startThread(3);
			}
		});
		btnNewButton_2.setBounds(155, 39, 131, 37);
		frmServerStatus.getContentPane().add(btnNewButton_2);

		btnNewButton_3 = new JButton("Server Info");
		btnNewButton_3
				.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				startThread(4);
			}
		});
		btnNewButton_3.setBounds(155, 89, 131, 37);
		frmServerStatus.getContentPane().add(btnNewButton_3);

		btnNewButton_4 = new JButton("Set Key/Hash");
		btnNewButton_4
				.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SetKeyHashDialog dialog = new SetKeyHashDialog();
				dialog.setVisible(true);
			}
		});
		btnNewButton_4.setBounds(12, 139, 131, 37);
		frmServerStatus.getContentPane().add(btnNewButton_4);

		JPanel panel = new JPanel();
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null,
				null));
		panel.setBounds(401, 39, 183, 137);
		frmServerStatus.getContentPane().add(panel);
		panel.setLayout(null);

		chckbxNewCheckBox_1 = new JCheckBox("HDD");
		chckbxNewCheckBox_1.setSelected(true);
		chckbxNewCheckBox_1.setBounds(8, 9, 114, 25);
		panel.add(chckbxNewCheckBox_1);

		chckbxNewCheckBox_2 = new JCheckBox("Bandwidth");
		chckbxNewCheckBox_2.setSelected(true);
		chckbxNewCheckBox_2.setBounds(8, 39, 113, 25);
		panel.add(chckbxNewCheckBox_2);

		chckbxNewCheckBox = new JCheckBox("Memory");
		chckbxNewCheckBox.setSelected(true);
		chckbxNewCheckBox.setBounds(8, 73, 113, 25);
		panel.add(chckbxNewCheckBox);

		chckbxStatus = new JCheckBox("Status");
		chckbxStatus.setSelected(true);
		chckbxStatus.setBounds(8, 103, 83, 25);
		panel.add(chckbxStatus);

		chckbxAllIps = new JCheckBox("All IP's");
		chckbxAllIps.setBounds(95, 103, 80, 25);
		panel.add(chckbxAllIps);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 189, 570, 185);
		frmServerStatus.getContentPane().add(scrollPane);

		txtpnServerStatusGui = new JTextArea();
		scrollPane.setViewportView(txtpnServerStatusGui);
		txtpnServerStatusGui.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnServerStatusGui.setWrapStyleWord(true);
		txtpnServerStatusGui.setLineWrap(true);
		txtpnServerStatusGui.setEditable(false);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 594, 26);
		frmServerStatus.getContentPane().add(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);

		JMenu mnAbout = new JMenu("About");
		mnAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				AboutDialog dialog = new AboutDialog();
				dialog.setLocationRelativeTo(txtpnServerStatusGui);
				dialog.setVisible(true);
			}
		});
		mnAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AboutDialog dialog = new AboutDialog();
				dialog.setVisible(true);
			}
		});
		menuBar.add(mnAbout);
		frmServerStatus.getContentPane().setFocusTraversalPolicy(
				new FocusTraversalOnArray(new Component[] { btnNewButton,
						btnNewButton_2, btnNewButton_3, btnNewButton_4,
						chckbxNewCheckBox_1, chckbxNewCheckBox_2,
						chckbxNewCheckBox, chckbxStatus, chckbxAllIps }));
		frmServerStatus.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { btnNewButton, btnNewButton_2, btnNewButton_3,
						btnNewButton_4, chckbxNewCheckBox_1,
						chckbxNewCheckBox_2, chckbxNewCheckBox, chckbxStatus,
						chckbxAllIps }));
		try {
			File infoFile = new File(infoFileLoc);
			boolean fileCreated;

			fileCreated = infoFile.createNewFile();

			if (fileCreated) {
				ops.writeKeyHash("EXAMP-LEKEY-XXXXX", "XXXXXXXXXXXXXXXXXXXX");
			} else {
				String sCurrentLine;
				BufferedReader br1 = new BufferedReader(new FileReader("."
						+ File.separator + "infoFile.txt"));
				while ((sCurrentLine = br1.readLine()) != null) {
					if (sCurrentLine.startsWith("Key:")) {
						key = sCurrentLine.substring(
								sCurrentLine.indexOf(":") + 1).trim();
					} else if (sCurrentLine.startsWith("Hash:")) {
						hash = sCurrentLine.substring(
								sCurrentLine.indexOf(":") + 1).trim();
					} else {
						getTxtpnServerStatusGui().setText(
								"Invalid Data in infoFile.txt\n" + "\""
										+ sCurrentLine + "\" is invalid.");
					}
				}
				br1.close();
			}
			if (hasDefaults()) {
				defaultText(true);
				disableAll(false);
			} else {
				defaultText(false);
				enableAll(false);
			}
		} catch (IOException e) {
			e.printStackTrace();
			MainWindow.getTxtpnServerStatusGui().setText(
					e.getStackTrace()[0].toString() + "\n"
							+ e.getStackTrace()[1].toString() + "\n"
							+ e.getStackTrace()[2].toString());
		}
	}

	protected static void defaultText(boolean defaultsNotify) {
		if (!defaultsNotify) {
			txtpnServerStatusGui.setText("BuyVM Server Interface v" + version
					+ "\r\nDeveloped" + " by Antony Prince\r\nJanuary"
					+ " 2014\r\nhttp://blazr.no-ip.org");
		} else {
			txtpnServerStatusGui.setText("BuyVM Server Interface v" + version
					+ "\r\nDeveloped" + " by Antony Prince\r\nJanuary"
					+ " 2014\r\nhttp://blazr.no-ip.org\n" + "WARNING!!!\n"
					+ "Default values detected. Please set hash & key to"
					+ " your info.");
		}
	}

	protected static void disableAll(boolean setKeyDisabled) {
		getBtnNewButton().setEnabled(false);
		// getBtnNewButton_1().setEnabled(false);
		getBtnNewButton_2().setEnabled(false);
		getBtnNewButton_3().setEnabled(false);
		if (setKeyDisabled) {
			getBtnNewButton_4().setEnabled(false);
		}
	}

	protected static void enableAll(boolean setKeyOnly) {
		if (setKeyOnly) {
			getBtnNewButton_4().setEnabled(true);
		} else {
			getBtnNewButton().setEnabled(true);
			// getBtnNewButton_1().setEnabled(true);
			getBtnNewButton_2().setEnabled(true);
			getBtnNewButton_3().setEnabled(true);
			getBtnNewButton_4().setEnabled(true);
		}
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

	private void startThread(int type) {
		ops.startWaitThread();
		DataThread thread = new DataThread();
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

	protected static boolean hasDefaults() {
		boolean hasDefaults = false;
		if (key.contentEquals("EXAMP-LEKEY-XXXXX")
				|| hash.contentEquals("XXXXXXXXXXXXXXXXXXXX")) {
			hasDefaults = true;
		}
		return hasDefaults;
	}

	protected static JTextArea getTxtpnServerStatusGui() {
		return txtpnServerStatusGui;
	}

	protected static JButton getBtnNewButton() {
		return btnNewButton;
	}

	protected static JButton getBtnNewButton_1() {
		return btnNewButton_1;
	}

	protected static JButton getBtnNewButton_4() {
		return btnNewButton_4;
	}

	protected static JButton getBtnNewButton_2() {
		return btnNewButton_2;
	}

	protected static JButton getBtnNewButton_3() {
		return btnNewButton_3;
	}

	protected JCheckBox getChckbxNewCheckBox_1() {
		return chckbxNewCheckBox_1;
	}

	protected JCheckBox getChckbxNewCheckBox_2() {
		return chckbxNewCheckBox_2;
	}

	protected JCheckBox getChckbxNewCheckBox() {
		return chckbxNewCheckBox;
	}

	protected JCheckBox getChckbxStatus() {
		return chckbxStatus;
	}

	protected JCheckBox getChckbxAllIps() {
		return chckbxAllIps;
	}
}
