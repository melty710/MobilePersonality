package com.example.mobilepersonality;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

public class MainActivity extends Activity {

	EditText debugTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		debugTextView = (EditText)findViewById(R.id.debug_output_field);
		
		PackageManager pm = getPackageManager();
		List<ApplicationInfo> apps = pm.getInstalledApplications(0);
		
		List<ApplicationInfo> installedApps = new ArrayList<ApplicationInfo>();

		for(ApplicationInfo app : apps) {
		    //checks for flags; if flagged, check if updated system app
		    if((app.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) == 1) {
		        installedApps.add(app);
		    //it's a system app, not interested
		    } else if ((app.flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
		        //Discard this one
		    //in this case, it should be a user-installed app
		    } else {
		        installedApps.add(app);
		    }
		}
		
		for(ApplicationInfo app : installedApps){
			debugTextView.setText(debugTextView.getText().append(app.packageName).append("\n"));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
