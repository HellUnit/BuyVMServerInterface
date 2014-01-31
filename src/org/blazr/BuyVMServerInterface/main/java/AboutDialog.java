package org.blazr.BuyVMServerInterface.main.java;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.blazr.Updater.main.java.DownloadOptionDialog;
import org.blazr.Updater.main.java.Updater;

public class AboutDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5056490909773447815L;
	private final JTextField txtHttpblazrnoiporg;
	private final JTextField txtHttpblazrnoiporgjavaappslicensehtml;
	private final JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					AboutDialog dialog = new AboutDialog();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public AboutDialog() {
		setModal(true);
		setTitle("About");
		setResizable(false);
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 335);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 444, 302);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblBuyVmServer = new JLabel("BuyVM Server Interface v"
				+ MainWindow.version);
		lblBuyVmServer.setBounds(12, 13, 374, 27);
		panel.add(lblBuyVmServer);

		JLabel lblDevelopedByAntony = new JLabel("Developed By Antony Prince");
		lblDevelopedByAntony.setBounds(12, 53, 374, 27);
		panel.add(lblDevelopedByAntony);

		JLabel lblCopyrightc = new JLabel("Copyright (c) 2014");
		lblCopyrightc.setBounds(12, 93, 374, 27);
		panel.add(lblCopyrightc);

		txtHttpblazrnoiporg = new JTextField();
		txtHttpblazrnoiporg.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtHttpblazrnoiporg.setBorder(null);
		txtHttpblazrnoiporg.setEditable(false);
		txtHttpblazrnoiporg.setText("http://blazr.no-ip.org");
		txtHttpblazrnoiporg.setBounds(12, 133, 374, 27);
		panel.add(txtHttpblazrnoiporg);
		txtHttpblazrnoiporg.setColumns(10);

		JLabel lblDistributedUnderThe = new JLabel(
				"Distributed under the license found here:");
		lblDistributedUnderThe.setBounds(12, 173, 374, 27);
		panel.add(lblDistributedUnderThe);

		txtHttpblazrnoiporgjavaappslicensehtml = new JTextField();
		txtHttpblazrnoiporgjavaappslicensehtml.setFont(new Font("Tahoma",
				Font.BOLD, 13));
		txtHttpblazrnoiporgjavaappslicensehtml.setBorder(null);
		txtHttpblazrnoiporgjavaappslicensehtml.setEditable(false);
		txtHttpblazrnoiporgjavaappslicensehtml
				.setText("http://blazr.no-ip.org/Java_Apps/license.html");
		txtHttpblazrnoiporgjavaappslicensehtml.setBounds(12, 213, 374, 27);
		panel.add(txtHttpblazrnoiporgjavaappslicensehtml);
		txtHttpblazrnoiporgjavaappslicensehtml.setColumns(10);

		btnNewButton = new JButton("Check for update");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					@SuppressWarnings("unused")
					Updater updater = new Updater(
							MainWindow.version,
							"BuyVMServerInterface",
							"http://blazr.no-ip.org/Java_Apps/BuyVMServerInterface/",
							"http://blazr.no-ip.org/versions/", false);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				switch (Updater.status) {
				case CONNECTION_ERROR:
					JOptionPane
							.showMessageDialog(
									btnNewButton,
									"The site may be down or you\n"
											+ "may be experiencing connection problems",
									"Connection Error",
									JOptionPane.WARNING_MESSAGE);
					break;
				case NEW_VERSION:
					DownloadOptionDialog confirmDL = new DownloadOptionDialog();
					confirmDL.setVisible(true);
					break;
				case OTHER_ERROR:
					JOptionPane.showMessageDialog(btnNewButton,
							"There was a problem during the update check.",
							"Error", JOptionPane.ERROR_MESSAGE);
					break;
				case VERSION_CURRENT:
					JOptionPane.showMessageDialog(btnNewButton,
							"This is the current version", "No Update",
							JOptionPane.INFORMATION_MESSAGE);
					break;
				default:
					JOptionPane.showMessageDialog(btnNewButton,
							"This is the current version", "No Update",
							JOptionPane.INFORMATION_MESSAGE);
					break;

				}
			}
		});
		btnNewButton.setBounds(256, 253, 176, 36);
		panel.add(btnNewButton);

	}

	protected JButton getBtnNewButton() {
		return btnNewButton;
	}
}
