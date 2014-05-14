package com.csumb.adc.swapping_puzzle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.csumb.adc.swapping_puzzle_brian_arturo.R;

public class MainActivity extends Activity {
	
	Button easyB, mediumB, hardB;//reference for our difficulty levels
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//finds the r file location of the Buttons
		easyB = (Button) findViewById(R.id.easyButton);
		mediumB = (Button) findViewById(R.id.mediumButton);
		hardB = (Button) findViewById(R.id.hardButton);
		
		//setting up the on click listener to open the easy level activity
		easyB.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {				
				Intent easy = new Intent(MainActivity.this,EasyLevel.class);
				startActivity(easy);
			}
		});
		
		//setting up the on click listener to open the medium level activity
		mediumB.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent medium = new Intent(MainActivity.this,MediumLevel.class);
				startActivity(medium);				
			}
		});
		
		//setting up the on click listener to open the hard level activity
		hardB.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent hard = new Intent(MainActivity.this,HardLevel.class);
				startActivity(hard);			
			}
		});
		
	}
	
	//if back button is pressed then the main activity will keep showing
	@Override
	public void onBackPressed(){
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		
	}
	

}
	
