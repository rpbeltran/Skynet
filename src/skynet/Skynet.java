package skynet;

//For images from the camera
import java.awt.image.BufferedImage;
//For exception handling
import twitter4j.TwitterException;

public class Skynet {

	public static void main(String[] args) {
		
		//Gather settings
		Settings s = new Settings();
		//Captures image from webcam
		BufferedImage image = Camera.capture();
		//Analyzes image, saves result to output.png
		Algor.basicIterativeTo(image, "output");
		//Computes average brightness
		int avg = Algor.average(image);
		//Initialize our twitter object
		twitter tweeter = new twitter(s);
		try {
			//Tweet the image with a relevant caption
			tweeter.postImageWithMessage("output.png", "Hello astronomers, the average skyglow is currently "+avg);
		} catch (TwitterException e) {
			//If something with twitter fails, a TwitterException is thrown
			e.printStackTrace();
		}
		
		
		
		
	}

}
