package com.neectron.system.ui.replacer;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;



public class Main_activity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity);
        try {
			Runtime.getRuntime().exec("su");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_activity, menu);
        return true;
    }
    
    public void copyStream(String assetFilename, String outFileName ) throws IOException
    { 
      InputStream myInput = getAssets().open(assetFilename); 
      OutputStream myOutput = new FileOutputStream(outFileName);    
      byte[] buffer = new byte[2048];
      int length;
      while ((length = myInput.read(buffer))>0)
      {
        myOutput.write(buffer, 0, length);
      }     
      myOutput.flush();
      myOutput.close();
      myInput.close();    
    }                
    
    public void MessageBox(String message)
    {
    	Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }   
          
    
    public void themer() throws IOException, InterruptedException
    {
    	    Process mSuProcess;
    	    mSuProcess = Runtime.getRuntime().exec("su");
    	    new DataOutputStream(mSuProcess.getOutputStream()).writeBytes("mount -o remount rw /system\n");
    	    DataOutputStream mSuDataOutputStream = new DataOutputStream(mSuProcess.getOutputStream());
    	    mSuDataOutputStream.writeBytes("cp /sdcard/SystemUI.apk /system/app/SystemUI.apk\n");
    	    MessageBox("Killing SystemUI!");
    	    mSuDataOutputStream.writeBytes("killall com.android.systemui\n"); 
    	    Thread.sleep(2000);
            mSuDataOutputStream.writeBytes("chmod 0644 /sdcard/SystemUI.apk /system/app/SystemUI.apk\n");
    	    mSuDataOutputStream.writeBytes("exit\n");
    	    MessageBox("Starting SystemUI!");
    	    Process proca = Runtime.getRuntime().exec(new String[]{"am","startservice","-n","com.android.systemui/.SystemUIService"});
    	    proca.waitFor();
    	    Process procb = Runtime.getRuntime().exec(new String[]{"am","startservice","-n","com.android.systemui/.ImageWallpaper"});
    	    procb.waitFor();
    	    	    
    	    MessageBox("Done!");
    	   
    }
    
    
         public void but1(View view) throws IOException, InterruptedException
    {
          copyStream("stock.apk","/sdcard/SystemUI.apk");
          themer();
   	 
    }
           
      public void but2(View view) throws IOException, InterruptedException
      {
    	    copyStream("1.apk","/sdcard/SystemUI.apk");
    	   themer();

      }
      public void but3(View view) throws IOException, InterruptedException
      {
    	    copyStream("2.apk","/sdcard/SystemUI.apk");
    	   themer();
      }
      public void but4(View view) throws IOException, InterruptedException
      {   
    	    copyStream("3.apk","/sdcard/SystemUI.apk");
    	   themer();
      }
      public void but5(View view) throws IOException, InterruptedException
      {
    	    copyStream("4.apk","/sdcard/SystemUI.apk");
    	   themer();
      }
 }

