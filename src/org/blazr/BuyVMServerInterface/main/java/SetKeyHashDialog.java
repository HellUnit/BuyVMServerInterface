package org.blazr.BuyVMServerInterface.main.java;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SetKeyHashDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1422857684693139380L;
	private final JPanel contentPanel = new JPanel();
	private static JTextField textField;
	private static JTextField textField_1;

	/**
	 * Create the dialog.
	 */
	public SetKeyHashDialog() {
		setLocationByPlatform(true);
		setModal(true);
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Set Hash & Key");
		setBounds(100, 100, 291, 134);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JLabel lblHash = new JLabel("Hash:");
		lblHash.setBounds(10, 11, 37, 14);
		contentPanel.add(lblHash);

		JLabel lblKey = new JLabel("Key:");
		lblKey.setBounds(10, 36, 37, 14);
		contentPanel.add(lblKey);

		textField = new JTextField(MainWindow.hash);
		textField.setBounds(57, 8, 205, 20);
		contentPanel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField(MainWindow.key);
		textField_1.setBounds(57, 33, 205, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (!getTextField().getText().contentEquals("")
								&& !getTextField_1().getText()
										.contentEquals("")) {
							MainWindow.ops.writeKeyHash(
									getTextField_1().getText(), getTextField()
											.getText());
						}
						closeDialog();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						closeDialog();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	protected static JTextField getTextField() {
		return textField;
	}

	protected static JTextField getTextField_1() {
		return textField_1;
	}
	
	private void closeDialog(){
		dispose();
		if (MainWindow.hasDefaults()) {
			MainWindow.defaultText(true);
			MainWindow.disableAll(false);
		} else {
			MainWindow.defaultText(false);
			MainWindow.enableAll(false);
		}
	}
}
