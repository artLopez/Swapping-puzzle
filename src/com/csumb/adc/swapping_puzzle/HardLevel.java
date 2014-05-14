package com.csumb.adc.swapping_puzzle;

import java.io.FileNotFoundException;
import java.io.IOException;
import com.csumb.adc.swapping_puzzle_brian_arturo.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class HardLevel extends Activity {
	
	//Image button is created
	ImageButton frame1,
			    frame2,
			    frame3,
			    frame4,
			    frame5,
			    frame6,
			    frame7,
			    frame8,
			    frame9,
			    frame10,
			    frame11,
			    frame12,
			    frame13,
			    frame14,
			    frame15, 
			    frame16,
			    frame17,
			    frame18,
			    frame19,
			    frame20,
			    frame21,
			    frame22, 
			    frame23,
			    frame24,
			    frame25;//setting up reference to image view
	TextView numberMoves;

	//temp variables for swapping images
	int place_1 = -1;
	int place_2 = -1;
	int tempPlace;
	
	//number of tries
	int numberOfTries = 47;
	int countVictory = 0;
	
	//array of randomized tiles for the puzzle
	int[] indexOfImages = new int[]{7,24,13,16,9,20,3,12,8,23,14,6,18,1,22,15,4,17,11,21,2,10,19,5,0};
	//answer key for solved puzzled
	int[] answerKey = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24};
	
	//request for gallery and camera
	int GALLERY_REQUEST = 2;
	int CAMERA_REQUEST = 1;
	
	Bitmap bm;
	Bitmap[] bmp;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hard_level);
		//finds the r file location to use the imageButton
		frame1 = (ImageButton) findViewById(R.id.ImageButton1);//reference to my image view in xml
		frame2 = (ImageButton) findViewById(R.id.ImageButton2);
		frame3 = (ImageButton) findViewById(R.id.ImageButton3);
		frame4 = (ImageButton) findViewById(R.id.ImageButton4);
		frame5 = (ImageButton) findViewById(R.id.ImageButton5);
		frame6 = (ImageButton) findViewById(R.id.ImageButton6);
		frame7 = (ImageButton) findViewById(R.id.ImageButton7);
		frame8 = (ImageButton) findViewById(R.id.ImageButton8);
		frame9 = (ImageButton) findViewById(R.id.ImageButton9);
		frame10 = (ImageButton) findViewById(R.id.ImageButton10);
		frame11 = (ImageButton) findViewById(R.id.ImageButton11);
		frame12 = (ImageButton) findViewById(R.id.ImageButton12);
		frame13 = (ImageButton) findViewById(R.id.ImageButton13);
		frame14 = (ImageButton) findViewById(R.id.ImageButton14);
		frame15 = (ImageButton) findViewById(R.id.ImageButton15);
		frame16 = (ImageButton) findViewById(R.id.ImageButton16);
		frame17 = (ImageButton) findViewById(R.id.ImageButton17);
		frame18 = (ImageButton) findViewById(R.id.ImageButton18);
		frame19 = (ImageButton) findViewById(R.id.ImageButton19);
		frame20 = (ImageButton) findViewById(R.id.ImageButton20);
		frame21 = (ImageButton) findViewById(R.id.ImageButton21);
		frame22 = (ImageButton) findViewById(R.id.ImageButton22);
		frame23 = (ImageButton) findViewById(R.id.ImageButton23);
		frame24 = (ImageButton) findViewById(R.id.ImageButton24);
		frame25 = (ImageButton) findViewById(R.id.ImageButton25);
		
		//finds the r file location of the text view that shows moves left
		//from our xml
		numberMoves = (TextView) findViewById(R.id.movesMade);
		
		//button to take the id 
		Button b1 = (Button) findViewById(R.id.take);//reference to the take button in my xml file
		Button b2 = (Button) findViewById(R.id.gallery);
		
		b1.setOnClickListener(new OnClickListener() {//set up onClick listener to know that my take button has been clicked
			
		//camera pictures
		@Override
		public void onClick(View v) {//after button is clicked i need to launched my camera
			Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);//i have made an intent of the launching of my camera so that i can use it when ever i need it again
		
			if(intent.resolveActivity(getPackageManager())!= null){
				startActivityForResult(intent, CAMERA_REQUEST);//starting camera activity that will return a result after it starts
			}
		}	
		});	
		
		//gallery pictures
		//after button is clicked the gallery is launched
		b2.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//creates an intent with ACTION_PICK specification, this creates a nonspecific intent used to select
				//pictures from the picture gallery
				Intent inB = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(inB, GALLERY_REQUEST);
			}
		});

	}
	//swaps the tiles
	public void swap(View v)
	{
		//first touch on one frame
		if(place_1 == -1)
		{
			//creates the temp button when framed touched
			ImageButton temp = (ImageButton)v;
			//finds the tag for the button from the xml
			String tagname = temp.getTag().toString();
			//changes the tag from a string to an int
			place_1 = Integer.parseInt(tagname);
			//prints out the tag in the log cat
					
			} else {
				
				//creates the temp button when frame is touched for
				//the second button
				ImageButton temp2 = (ImageButton)v;
				//gets the tag from the second button which is a string 
				String tagname2 = temp2.getTag().toString();
				//changes the string to an int
				place_2 = Integer.parseInt(tagname2);
				//prints out the tag in the log cat
					
			}
			if(place_1 != place_2 && place_2 != -1)
			{
				//swap images using the arrays 
				tempPlace = indexOfImages[place_1];
				indexOfImages[place_1] = indexOfImages[place_2];
				indexOfImages[place_2] = tempPlace;
					
				numberOfTries--;
				
				//sets the new positions of the images on the screen again
				setImageBitMap();
					
				checkIfWon();
				countVictory = 0;
					
				//reset place_1, place_2
				place_1 = -1;
				place_2 = -1;
			}
			//if the user taps the tile twice
			else if (place_1 == place_2)
			{
				place_1 = -1;
				place_2 = -1;
			}
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		
		// TODO Auto-generated method stub
		//this will capture the image after it has been taken
		super.onActivityResult(requestCode, resultCode, data);
		//checks if all the requirements are met
		
		if(requestCode == CAMERA_REQUEST)
	    	bm = (Bitmap) data.getExtras().get("data");
	    else
	    {
	    	if(requestCode == GALLERY_REQUEST){
				if(resultCode != RESULT_CANCELED){
					if(resultCode == RESULT_OK){
						try{
							bm = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
						}catch(FileNotFoundException e){
							e.printStackTrace();
						}catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
	    }
		//sets up a array for the bitmaps images
		 bmp = new Bitmap[25];
		 
		
		 bmp[0] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),0,0,36,36);
		 bmp[1] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),36,0,36,36);
		 bmp[2] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),72,0,36,36);
		 bmp[3] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),108,0,36,36);
		 bmp[4] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),144,0,36,36);
		 bmp[5] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),0,36,36,36);
		 bmp[6] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),36,36,36,36);
		 bmp[7] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),72,36,36,36);
		 bmp[8] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),108,36,36,36);
		 bmp[9] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),144,36,36,36);
		 bmp[10] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),0,72,36,36);
		 bmp[11] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),36,72,36,36);
		 bmp[12] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),72,72,36,36);
		 bmp[13] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),108,72,36,36);
		 bmp[14] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),144,72,36,36);
		 bmp[15] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),0,108,36,36);
		 bmp[16] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),36,108,36,36);
		 bmp[17] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),72,108,36,36);
		 bmp[18] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),108,108,36,36);
		 bmp[19] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),144,108,36,36);
		 bmp[20] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),0,144,36,36);
		 bmp[21] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),36,144,36,36);
		 bmp[22] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),72,144,36,36);
		 bmp[23] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),108,144,36,36);
		 bmp[24] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),144,144,36,36);
		 	
		 setImageBitMap();
	}	
	//sets the images on the screens
	public void setImageBitMap()
	{
		//sets the three by three cropped images
		frame1.setImageBitmap(bmp[indexOfImages[0]]);
		frame2.setImageBitmap(bmp[indexOfImages[1]]);
		frame3.setImageBitmap(bmp[indexOfImages[2]]);
		frame4.setImageBitmap(bmp[indexOfImages[3]]);
		frame5.setImageBitmap(bmp[indexOfImages[4]]);
		frame6.setImageBitmap(bmp[indexOfImages[5]]);
		frame7.setImageBitmap(bmp[indexOfImages[6]]);
		frame8.setImageBitmap(bmp[indexOfImages[7]]);
		frame9.setImageBitmap(bmp[indexOfImages[8]]);
		frame10.setImageBitmap(bmp[indexOfImages[9]]);
		frame11.setImageBitmap(bmp[indexOfImages[10]]);
		frame12.setImageBitmap(bmp[indexOfImages[11]]);
		frame13.setImageBitmap(bmp[indexOfImages[12]]);
		frame14.setImageBitmap(bmp[indexOfImages[13]]);
		frame15.setImageBitmap(bmp[indexOfImages[14]]);
		frame16.setImageBitmap(bmp[indexOfImages[15]]);
		frame17.setImageBitmap(bmp[indexOfImages[16]]);
		frame18.setImageBitmap(bmp[indexOfImages[17]]);
		frame19.setImageBitmap(bmp[indexOfImages[18]]);
		frame20.setImageBitmap(bmp[indexOfImages[19]]);
		frame21.setImageBitmap(bmp[indexOfImages[20]]);
		frame22.setImageBitmap(bmp[indexOfImages[21]]);
		frame23.setImageBitmap(bmp[indexOfImages[22]]);
		frame24.setImageBitmap(bmp[indexOfImages[23]]);
		frame25.setImageBitmap(bmp[indexOfImages[24]]);
		
		//number of moves left are set on the screen
		numberMoves.setText("Number of moves left: " + Integer.toString(numberOfTries));
	}
	
	public void checkIfWon()
	{
		//checks if the user won
		for(int x = 0;x < 25;x++)
		{
			//increments the count victory if both numbers are the same
			if(indexOfImages[x] == answerKey[x])
				countVictory++;
			else
				break;
				
		}
		//if count victory is nine then user wins
		if(countVictory == 25)
			victoryActivity();
		//if no more tries left then the user lost
		else if(numberOfTries == 0)
			losingActivity();
		
	}
	//pops up the layout of the victory screen
	private void victoryActivity()
	{
		Intent victory = new Intent(HardLevel.this,VictoryScreen.class);
		startActivity(victory);
	}
	//pops up the layout of the losing screen
	private void losingActivity()
	{
		Intent losing = new Intent(HardLevel.this,LosingScreen.class);
		startActivity(losing);
	}
	
}
	