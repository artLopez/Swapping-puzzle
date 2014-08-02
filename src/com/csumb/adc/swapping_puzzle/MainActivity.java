package com.csumb.adc.swapping_puzzle;

/* 
 
 Created by Brian De Anda, and Arturo Lopez
 
Summary:
	This program allows the user to select a picture from
	the gallery or take a picture through the camera and 
	stores the picture as a Bitmap image. Afterwards, our 
	bitmap image is cut into 9 images for easy mode, 16 for
	medium mode and 25 for hard mode. The program also checks
	after each swap if the user solved the puzzle and then it 
	takes them to the winning screen and if they run out of 
	moves then it takes them to the losing screen.
 
 */

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
			
			//when clicked the activity opens
			@Override
			public void onClick(View v) {				
				Intent easy = new Intent(MainActivity.this,EasyLevel.class);
				easy.putExtra("NumTiles", 9);
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
			
			//when clicked it starts the hard activity
			@Override
			public void onClick(View v) {
				Intent hard = new Intent(MainActivity.this,HardLevel.class);
				startActivity(hard);			
			}
		});
		
	}

}
	
