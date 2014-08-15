package com.example.totohealth;
	import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import library.UserFunctions;

	import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

	import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

	public class SubmitDoc extends Activity
	{
		private static String KEY_SUCCESS = "success";
		protected void onCreate(Bundle savedInstanceState) 
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.submit);
		}

	//when button clicked
		public void btn_login(View v)
		{
			
			new ProgressDialog(v.getContext());
			final ProgressDialog dialog = ProgressDialog.show(v.getContext(),"Please Wait", "Accessing Server.....");
	      Thread thread = new Thread()
	      {
	          @Override
	          public void run() {
		   try {
			
			EditText email, password;
			
			//Reference your edit texts
			email= (EditText) findViewById(R.id.email);
			password= (EditText) findViewById(R.id.password);
			
			 //get text from edit texts
	    	String mail = email.getText().toString();
	    	String pass = password.getText().toString();

			UserFunctions userFunction = new UserFunctions();
           JSONObject json= userFunction.loginUser( mail, pass);
           String res = json.getString(KEY_SUCCESS); 
		  
	        runOnUiThread(new Runnable() {
	            public void run() {
	                //tv.setText("Response from PHP : " + response);
	                dialog.dismiss();
	            }
	        });
	             
	        
	       // Toast.makeText(SendData.this, "Thank You for registering"+result, Toast.LENGTH_LONG).show();
	        //check if response is 4
	        if(Integer.parseInt(res) == 1){
	        	Intent dashboard = new Intent(getApplicationContext(), MainActivity.class);
                
                // Close all views before launching Dashboard
                dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(dashboard);
	        	
	          }
	        
	        else{
	        	Toast.makeText(getApplicationContext(), "User registration failed", Toast.LENGTH_LONG).show();
	        	
	         }
	        
	       
	      

					 
	 }
	        catch (Exception e)
	        {
	            Toast.makeText(getApplicationContext(), "Error inside set:"+e.toString(), Toast.LENGTH_LONG).show();
	        }
	          }};thread.start();
	}
	}

