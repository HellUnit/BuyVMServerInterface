package org.blazr.BuyVMServerInterface.main.java;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class DataThread extends Thread {
	protected String args = null;

	@Override
	public void run() {
		String displayInfo[] = new String[32];
		try {
			MainWindow.getTxtpnServerStatusGui().setText(
					"Connecting to server...");
			URL url = new URL("https://manage.buyvm.net/api/client/command.php"
					+ args);
			HttpURLConnection connection = (HttpsURLConnection) url
					.openConnection();
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			connection.setRequestProperty("Content-Type",
					"text/plain; charset=\"utf8\"");
			connection.setRequestMethod("GET");
			// DEBUG: int statusCode = connection.getResponseCode();
			// DEBUG: System.out.println("Connection Status Code: " +
			// statusCode);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			int n = 0;
			while ((displayInfo[n] = rd.readLine()) != null) {
				if (!displayInfo[n].trim().contentEquals("")) {
					n++;
				}
			}
			connection.disconnect();
			rd.close();
			Document doc = MainWindow.getTxtpnServerStatusGui().getDocument();
			SimpleAttributeSet keyWord = new SimpleAttributeSet();
			StyleConstants.setForeground(keyWord, Color.BLACK);
			StyleConstants.setBackground(keyWord, Color.WHITE);
			StyleConstants.setBold(keyWord, false);
			MainWindow.getTxtpnServerStatusGui().setText("");
			n = 0;
			while (displayInfo[n] != null) {
				try {
					String displayString = MainWindow.ops
							.parseData(displayInfo[n]);
					if (n == 0) {
						doc.insertString(0, displayString, keyWord);
						n++;
					} else {
						doc.insertString(doc.getLength(), "\n" + displayString,
								keyWord);
						n++;
					}
				} catch (BadLocationException e) {
					e.printStackTrace();
					n++;
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			MainWindow.getTxtpnServerStatusGui().setText("Bad URL!");
		} catch (Exception e) {
			e.printStackTrace();
			MainWindow.getTxtpnServerStatusGui().setText(
					"Error occured while connecting to remote server.\n"
							+ "Error: " + e.getStackTrace()[0].toString());
		}
	}
}
