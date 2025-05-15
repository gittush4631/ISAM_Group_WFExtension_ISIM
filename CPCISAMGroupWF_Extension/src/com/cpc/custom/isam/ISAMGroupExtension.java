package com.cpc.custom.isam;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

/************************************************************************
* IBM Confidential
* OCO Source Materials
* *** IBM Security Identity Manager ***
*
* (C) Copyright IBM Corp. 2021 All Rights Reserved.
*
* The source code for this program is not published or otherwise  
* divested of its trade secrets, irrespective of what has been 
* deposited with the U.S. Copyright Office.
*************************************************************************/

import com.ibm.itim.workflow.application.WorkflowApplication;
import com.ibm.itim.workflow.application.WorkflowExecutionContext;
import com.ibm.itim.workflow.model.ActivityResult;

/**
 * Custom class for synchronous activity
 */
public class ISAMGroupExtension implements WorkflowApplication {

	protected WorkflowExecutionContext ctx;

	public ISAMGroupExtension() {
	}

	/**
	 * Passes the workflow execution context to the application.
	 * 
	 * @param context WorklowExecutionContext holding information about the
	 *                currently executing activity.
	 */
	public void setContext(WorkflowExecutionContext ctx) {
		this.ctx = ctx;
	}

	/**
	 * Perform add group extension synchronously
	 * 
	 * @return ActivityResult The result of the activity.
	 * 
	 */
	public ActivityResult modifyISAMGroup(String userid, List<String> groupsToAdd, List<String> groupsToRemove) {
		String output = "";
		String output1 = "";
		String command = "";
		String precommand = "";
		String data = "";
		 String POST_PARAMS = "";
        System.out.println("==========================Entering ISAM Group modification====================================");
		try {
			String activityId = String.valueOf(ctx.getActivityVO().getId());

			Properties prop = new Properties();
			InputStream inputprop = getClass().getClassLoader().getResourceAsStream("isam.properties");
			prop.load(inputprop);
			String isamadmin = prop.getProperty("isam_username");
			String isampassword = prop.getProperty("isam_password");
			String urlprop = prop.getProperty("url");
			String adminid = prop.getProperty("admin_id");
			String admin_pwd = prop.getProperty("admin_pwd");
			String appliance_hostname = prop.getProperty("hostname");
			String userpass=isamadmin+":"+isampassword;
            //System.out.println("user pass : "+userpass);
            POST_PARAMS="appliance_hostname="+appliance_hostname;
			String basicAuth="Basic "+new String(Base64.getEncoder().encode(userpass.getBytes()));
            
			//System.out.println("admin id " + adminid);
			//System.out.println("USER ID from ISIM :"+userid);
			// System.out.println("password "+password);

			URL url = new URL(urlprop);
			// System.out.println("final sms api url = "+url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", basicAuth);
			//System.out.println("Basic auth : "+basicAuth);
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
			//System.out.println("command = " + command);
			data = "{\"admin_id\":\"" + adminid + "\",\"admin_pwd\":" + "\"" + admin_pwd + "\",\"commands\":" + command
					+ "}";
			System.out.println("Data ===========" + data);

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
			System.out.println("Response : " + output);

			rd.close();
			conn.disconnect();
			 System.out.println("========================== End of ISAM Group Modification====================================");
			return new ActivityResult(ActivityResult.STATUS_COMPLETE, ActivityResult.SUCCESS, output, null);
		} catch (Exception e) {
			System.out.println("Error trace for ISAM Group : ");			
			e.printStackTrace();
			return new ActivityResult(ActivityResult.FAILED,
					e.getClass().getName() + "Failure : " + output + e.getMessage(), null);
			
		}
	}

}