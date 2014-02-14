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
import javax.swing.WindowConstants;

import org.blazr.Updater.main.java.DownloadOptionDialog;
import org.blazr.Updater.main.java.Updater;

public class AboutDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5056490909773447815L;
	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					final AboutDialog dialog = new AboutDialog();
					dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private final JTextField txtHttpblazrnoiporg;
	private final JTextField txtHttpblazrnoiporgjavaappslicensehtml;

	private final JButton btnNewButton;

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

		final JPanel panel = new JPanel();
		panel.setBounds(0, 0, 444, 302);
		getContentPane().add(panel);
		panel.setLayout(null);

		final JLabel lblBuyVmServer = new JLabel("BuyVM Server Interface v"
				+ MainWindow.version);
		lblBuyVmServer.setBounds(12, 13, 374, 27);
		panel.add(lblBuyVmServer);

		final JLabel lblDevelopedByAntony = new JLabel("Developed By Antony Prince");
		lblDevelopedByAntony.setBounds(12, 53, 374, 27);
		panel.add(lblDevelopedByAntony);

		final JLabel lblCopyrightc = new JLabel("Copyright (c) 2014");
		lblCopyrightc.setBounds(12, 93, 374, 27);
		panel.add(lblCopyrightc);

		this.txtHttpblazrnoiporg = new JTextField();
		this.txtHttpblazrnoiporg.setFont(new Font("Tahoma", Font.BOLD, 13));
		this.txtHttpblazrnoiporg.setBorder(null);
		this.txtHttpblazrnoiporg.setEditable(false);
		this.txtHttpblazrnoiporg.setText("http://blazr.no-ip.org");
		this.txtHttpblazrnoiporg.setBounds(12, 133, 374, 27);
		panel.add(this.txtHttpblazrnoiporg);
		this.txtHttpblazrnoiporg.setColumns(10);

		final JLabel lblDistributedUnderThe = new JLabel(
				"Distributed under the license found here:");
		lblDistributedUnderThe.setBounds(12, 173, 374, 27);
		panel.add(lblDistributedUnderThe);

		this.txtHttpblazrnoiporgjavaappslicensehtml = new JTextField();
		this.txtHttpblazrnoiporgjavaappslicensehtml.setFont(new Font("Tahoma",
				Font.BOLD, 13));
		this.txtHttpblazrnoiporgjavaappslicensehtml.setBorder(null);
		this.txtHttpblazrnoiporgjavaappslicensehtml.setEditable(false);
		this.txtHttpblazrnoiporgjavaappslicensehtml
				.setText("http://blazr.no-ip.org/Java_Apps/license.html");
		this.txtHttpblazrnoiporgjavaappslicensehtml.setBounds(12, 213, 374, 27);
		panel.add(this.txtHttpblazrnoiporgjavaappslicensehtml);
		this.txtHttpblazrnoiporgjavaappslicensehtml.setColumns(10);

		this.btnNewButton = new JButton("Check for update");
		this.btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				try {
					@SuppressWarnings("unused")
					final
					Updater updater = new Updater(
							MainWindow.version,
							"BuyVMServerInterface",
							"http://blazr.no-ip.org/Java_Apps/BuyVMServerInterface/",
							"http://blazr.no-ip.org/versions/", false);
				} catch (final MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				switch (Updater.status) {
				case CONNECTION_ERROR:
					JOptionPane
							.showMessageDialog(
									AboutDialog.this.btnNewButton,
									"The site may be down or you\n"
											+ "may be experiencing connection problems",
									"Connection Error",
									JOptionPane.WARNING_MESSAGE);
					break;
				case NEW_VERSION:
					final DownloadOptionDialog confirmDL = new DownloadOptionDialog();
					confirmDL.setVisible(true);
					break;
				case OTHER_ERROR:
					JOptionPane.showMessageDialog(AboutDialog.this.btnNewButton,
							"There was a problem during the update check.",
							"Error", JOptionPane.ERROR_MESSAGE);
					break;
				case VERSION_CURRENT:
					JOptionPane.showMessageDialog(AboutDialog.this.btnNewButton,
							"This is the current version", "No Update",
							JOptionPane.INFORMATION_MESSAGE);
					break;
				case VERSION_HIGHER_THAN_RELEASE:
					JOptionPane.showMessageDialog(
							AboutDialog.this.btnNewButton,
							"This version is higher than the current release!\n"
									+ "No update needed.", "No Update",
							JOptionPane.INFORMATION_MESSAGE);
					break;
				case SNAPSHOT_VERSION:
					JOptionPane
							.showMessageDialog(
									AboutDialog.this.btnNewButton,
									"This is a SNAPSHOT (development) version.",
									"Snapshot Version",
									JOptionPane.INFORMATION_MESSAGE);
					break;
				default:
					JOptionPane.showMessageDialog(AboutDialog.this.btnNewButton,
							"This is the current version", "No Update",
							JOptionPane.INFORMATION_MESSAGE);
					break;

				}
			}
		});
		this.btnNewButton.setBounds(256, 253, 176, 36);
		panel.add(this.btnNewButton);

	}

	protected JButton getBtnNewButton() {
		return this.btnNewButton;
	}
}
