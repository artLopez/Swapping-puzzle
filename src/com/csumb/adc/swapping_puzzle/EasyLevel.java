package com.csumb.adc.swapping_puzzle;

import java.io.FileNotFoundException;
import java.io.IOException;
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
import com.csumb.adc.swapping_puzzle_brian_arturo.R;

public class EasyLevel extends Activity {
	
	//Image button is created for each imageButton
	ImageButton frame1,
			    frame2,
			    frame3,
			    frame4,
			    frame5,
			    frame6,
			    frame7,
			    frame8,
			    frame9;
	
	//text view for the number of moves left
	TextView numberMoves;
	
	//temporary button for swapping images
	int tempPlace;
	
	//temporary variables for default 
	int place_1 = -1;
	int place_2 = -1;
	
	//Number of tries
	int numberOfTries = 15;
	//checks if the user completed the puzzle if count
	//victory gets to nine
	int countVictory = 0;
	
	//Gallery and Camera Request variables
	int GALLERY_REQUEST = 2;
	int CAMERA_REQUEST = 1;
	
	//array of randomized tiles for the puzzle
	int[] indexOfImages = new int[]{7,3,8,6,1,4,2,5,0};
	//answer key for solved puzzled
	int[] answerKey = new int[]{0,1,2,3,4,5,6,7,8};
	
	//creates an object for bitmap
	Bitmap bm;
	//Creates an array of bitmaps 
	Bitmap[] bmp;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_easy_level);
		
		//finds the r file location of the imageButtons
		frame1 = (ImageButton) findViewById(R.id.ImageButton1);//reference to my image view in xml
		frame2 = (ImageButton) findViewById(R.id.ImageButton2);
		frame3 = (ImageButton) findViewById(R.id.ImageButton3);
		frame4 = (ImageButton) findViewById(R.id.ImageButton4);
		frame5 = (ImageButton) findViewById(R.id.ImageButton5);
		frame6 = (ImageButton) findViewById(R.id.ImageButton6);
		frame7 = (ImageButton) findViewById(R.id.ImageButton7);
		frame8 = (ImageButton) findViewById(R.id.ImageButton8);
		frame9 = (ImageButton) findViewById(R.id.ImageButton9);
		
		//finds the r file location of the text view that shows moves left
		//from our xml
		numberMoves = (TextView) findViewById(R.id.movesMade);
		
		//buttons for taking a picture or a picture from the gallery
		Button b1 = (Button) findViewById(R.id.take);
		Button b2 = (Button) findViewById(R.id.gallery);
		
		//set up onClick listener to know that my take button has been clicked
		b1.setOnClickListener(new OnClickListener() {
			
		//camera pictures
		//after button is clicked the camera is launched
		//An intent of the launching of my camera 
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		
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
	//swaps the tiles after two are pressed 
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
				//checks if the same button is clicked twice and if it is not
				//then run the if statement 
				if(place_1 != place_2 && place_2 != -1)
				{
					//swap images using the arrays 
					tempPlace = indexOfImages[place_1];
					indexOfImages[place_1] = indexOfImages[place_2];
					indexOfImages[place_2] = tempPlace;
					
					//number of tries decreases
					numberOfTries--;
				
					//sets the new positions of the images on the screen again
					setImageBitMap();
					
					//checks if the user swapped the puzzle
					checkIfWon();
					//resets the count victory to zero
					countVictory = 0;
					
					//reset place_1, place_2
					place_1 = -1;
					place_2 = -1;
				}
				//if the same tile is tapped twice reset the
				//the temporary values
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
		//gathers picture from camera
		if(requestCode == CAMERA_REQUEST)
	    	bm = (Bitmap) data.getExtras().get("data");
		//recevies the selected picture from the gallery
	    else
	    {
	    	if(requestCode == GALLERY_REQUEST){
				if(resultCode != RESULT_CANCELED){
					if(resultCode == RESULT_OK){
						try{
							//receives picture
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
		//sets up an array of 9 for the bitmaps images
		 bmp = new Bitmap[9];
		 
		 //cropped the images for the grid view of the puzzle
		 bmp[0] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),0,0,60,60);
		 bmp[1] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),60,0,60,60);
		 bmp[2] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),120,0,60,60);
		 bmp[3] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),0,60,60,60);
		 bmp[4] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),60,60,60,60);
		 bmp[5] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),120,60,60,60);
		 bmp[6] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),0,120,60,60);
		 bmp[7] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),60,120,60,60);
		 bmp[8] = Bitmap.createBitmap(Bitmap.createScaledBitmap(bm,180, 180, true),120,120,60,60);
		 //sets the new images on screen		 
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

		//number of moves left are set on the screen
		numberMoves.setText("Number of moves left: " + Integer.toString(numberOfTries));
			
	}
	public void checkIfWon()
	{
		//checks if the user won
		for(int x = 0;x < 9;x++)
		{
			//increments the count victory if both numbers are the same
			if(indexOfImages[x] == answerKey[x])
				countVictory++;
			else
				break;	
		}
		//if count victory is nine then user wins
		if(countVictory == 9)
			victoryActivity();
		//if no more tries left then the user lost
		else if(numberOfTries == 0)
			losingActivity();
	
	}
	//pops up the layout of the victory screen
	private void victoryActivity()
	{
		Intent victory = new Intent(EasyLevel.this,VictoryScreen.class);
		startActivity(victory);
	}
	//pops up the layout of the losing screen
	private void losingActivity()
	{
		Intent losing = new Intent(EasyLevel.this,LosingScreen.class);
		startActivity(losing);
	}

}
	
