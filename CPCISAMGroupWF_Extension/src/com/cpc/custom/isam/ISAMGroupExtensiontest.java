package com.cpc.custom.isam;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

import com.ibm.itim.workflow.model.ActivityResult;

/**
 * Custom class for synchronous activity
 */
public class ISAMGroupExtensiontest {
/*
public static void main(String[] args) {
		//String[] groupsToAdd = { "test1" };
		List<String> groupsToAdd = new ArrayList<String>();
		List<String> groupsToRemove = new ArrayList<String>();
		groupsToAdd.add("test1");
		groupsToRemove.add("test2");
		groupsToRemove.add("test3");
		// String[] groupsToRemove= {"test4","test5"};
		// String[] groupsToAdd= {};
		//String[] groupsToRemove = {"test2"};
		modifyISAMGroups("isamsso3", groupsToAdd, groupsToRemove);
	}

	public static void modifyISAMGroups(String userid, List<String> groupsToAdd, List<String> groupsToRemove) {
		String output = "";
		String output1 = "";
		String precommand = "";
		String command = "";
		String data = "";
		String adminid = "sec_master";
		String admin_pwd = "Passw0rd";

		try {

			if (groupsToAdd != null || groupsToAdd.size() > 0) {
				for (String items : groupsToAdd) {
					if (!(items.isEmpty())) {
						precommand = precommand + "\"group modify " + items + " add " + userid + "\",";
					}

				}

			}
			if (groupsToRemove != null || groupsToRemove.size() > 0) {
				for (String items : groupsToRemove) {
					if (!(items.isEmpty())) {
						precommand = precommand + "\"group modify " + items + " remove " + userid + "\",";
					}

				}

			}
			command = "[" + precommand.substring(0, precommand.length() - 1) + ",\"user list "+userid+" 1\"]";
			System.out.println("command = " + command);
			data = "{\"admin_id\":\"" + adminid + "\",\"admin_pwd\":" + "\"" + admin_pwd + "\",\"commands\":" + command
					+ "}";
			System.out.println("data = "+data);
			String userpass="tushar"+":"+"test123";
            System.out.println("user pass : "+userpass);
            String POST_PARAMS="appliance_hostname="+"10.1.1.1";
			String basicAuth="Basic "+new String(Base64.getEncoder().encode(userpass.getBytes()));
			URL url = new URL("https://httpbin.org/anything");
			// System.out.println("final sms api url = "+url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", basicAuth);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);

		try (OutputStream os = conn.getOutputStream()) {
				byte[] input = data.getBytes("utf-8");
				os.write(input, 0, input.length);
				
			}
			conn.connect();

			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			StringBuffer buffer = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				buffer.append(line).append("\n");
			}
			output = buffer.toString();
			System.out.println("response : " + output);

			rd.close();
			conn.disconnect();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
*/
}