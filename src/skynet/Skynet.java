package skynet;

import java.awt.image.BufferedImage;

import twitter4j.TwitterException;

public class Skynet {

	public static void main(String[] args) {
		
		BufferedImage image = Camera.capture();
		Algor.basicIterativeTo(image, "output");
		int avg = Algor.average(image);
		twitter tweeter = new twitter();
		try {
			tweeter.postImageWithMessage("output.png", "Hello astronomers, the average skyglow is currently "+avg);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		
	}

}
