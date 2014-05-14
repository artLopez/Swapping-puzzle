package com.csumb.adc.swapping_puzzle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.csumb.adc.swapping_puzzle_brian_arturo.R;

public class LosingScreen extends Activity {

	//sets up the losing screen
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_losing_screen);
		
		//button for the reset button
		Button reset = (Button) findViewById(R.id.reset_button);
		reset.setOnClickListener(new OnClickListener() {//set up onClick listener to know that my take button has been clicked
			
			//goes back into main_activity
			@Override
			public void onClick(View v) {//after button is clicked i need to launched my camera
				Intent intent = new Intent(LosingScreen.this, MainActivity.class);
				startActivity(intent);
				
			}
		});
	}
	
	//if the button is pressed then it goes into the main activity
	@Override
	public void onBackPressed(){
		Intent intent = new Intent(LosingScreen.this, MainActivity.class);
		startActivity(intent);
		
	}
}
