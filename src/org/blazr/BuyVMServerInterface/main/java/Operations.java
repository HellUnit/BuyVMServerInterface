package org.blazr.BuyVMServerInterface.main.java;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Operations {

	protected boolean writeKeyHash(String key, String hash) {
		try {
			PrintWriter out;
			out = new PrintWriter(new BufferedWriter(new FileWriter("."
					+ File.separator + "infoFile.txt", false)));
			out.println("Key: " + key);
			out.println("Hash: " + hash);
			out.close();
			MainWindow.hash = hash;
			MainWindow.key = key;
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	protected String parseData(String data) {
		// DEBUG: System.out.println(data);
		if (data.trim().length() == 0) {
			return "";
		}
		String dataType = data.substring(data.indexOf("<") + 1,
				data.indexOf(">"));
		data = data.substring(data.indexOf(">") + 1);
		String returnString = "";
		if (dataType.contains("mem")) {
			long totalMem = (Long
					.parseLong(data.substring(0, data.indexOf(","))) / 1048576);
			data = data.substring(data.indexOf(",") + 1);
			long usedMem = (Long
					.parseLong(data.substring(0, data.indexOf(","))) / 1048576);
			data = data.substring(data.indexOf(",") + 1);
			long freeMem = (Long
					.parseLong(data.substring(0, data.indexOf(","))) / 1048576);
			data = data.substring(data.indexOf(",") + 1, data.indexOf("<"));
			int memPercentage = Integer.parseInt(data);
			returnString = "Memory: Total: " + totalMem + " MB, Used: "
					+ usedMem + " MB, Free: " + freeMem + " MB, Used: "
					+ memPercentage + "%";
		} else if (dataType.contains("hdd")) {
			long totalSpace = (Long.parseLong(data.substring(0,
					data.indexOf(","))) / 1048576);
			data = data.substring(data.indexOf(",") + 1);
			long usedSpace = (Long.parseLong(data.substring(0,
					data.indexOf(","))) / 1048576);
			data = data.substring(data.indexOf(",") + 1);
			long freeSpace = (Long.parseLong(data.substring(0,
					data.indexOf(","))) / 1048576);
			data = data.substring(data.indexOf(",") + 1, data.indexOf("<"));
			int spacePercentage = Integer.parseInt(data);
			returnString = "Hard Disk: Total: " + totalSpace + " MB, Used: "
					+ usedSpace + " MB, Free: " + freeSpace + " MB, Used: "
					+ spacePercentage + "%";
		} else if (dataType.contains("bw")) {
			String doubleFormat = "%.2f";
			double totalBandwidth = (Double.parseDouble(data.substring(0,
					data.indexOf(","))) / 1073741824);
			data = data.substring(data.indexOf(",") + 1);
			double usedBandwidth = (Double.parseDouble(data.substring(0,
					data.indexOf(","))) / 1073741824);
			data = data.substring(data.indexOf(",") + 1);
			double freeBandwidth = (Double.parseDouble(data.substring(0,
					data.indexOf(","))) / 1073741824);
			data = data.substring(data.indexOf(",") + 1, data.indexOf("<"));
			int bandwidthPercentage = Integer.parseInt(data);
			returnString = "Bandwidth: Total: "
					+ String.format(doubleFormat, totalBandwidth)
					+ " GB, Used: "
					+ String.format(doubleFormat, usedBandwidth)
					+ " GB, Free: "
					+ String.format(doubleFormat, freeBandwidth)
					+ " GB, Used: " + bandwidthPercentage + "%";
		} else if (dataType.contains("type")) {
			returnString = "VPS Type: " + data.substring(0, data.indexOf("<"));
		} else if (dataType.contains("hostname")) {
			returnString = "Hostname: " + data.substring(0, data.indexOf("<"));
		} else if (dataType.contains("ipaddress")) {
			returnString = "IP Address: "
					+ data.substring(0, data.indexOf("<"));
		} else if (dataType.contains("vmstat")) {
			returnString = "Server Status: "
					+ data.substring(0, data.indexOf("<"));
		} else if (dataType.contentEquals("status")) {
			returnString = "Status: " + data.substring(0, data.indexOf("<"));
		} else if (dataType.contains("statusmsg")) {
			returnString = "Status Message: "
					+ data.substring(0, data.indexOf("<"));
		} else if (dataType.contentEquals("ipaddr")) {
			returnString = "All IP's: " + data.substring(0, data.indexOf("<"));
			returnString = returnString.replaceAll(",", "\n");
		} else {
			dataType = dataType.replaceFirst(
					String.valueOf(dataType.charAt(0)),
					String.valueOf(dataType.charAt(0)).toUpperCase());
			returnString = dataType + ": "
					+ data.substring(0, data.indexOf("<"));
		}
		return returnString;
	}

	protected void startWaitThread() {
		new Thread() {
			@Override
			public void run() {
				MainWindow.disableAll(true);
				try {
					Thread.sleep(30000);
				} catch (InterruptedException e) {
					;
				}
				MainWindow.enableAll(false);
			}
		}.start();
	}
}
