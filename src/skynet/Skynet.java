package skynet;

import java.awt.image.BufferedImage;

public class Skynet {

	public static void main(String[] args) {
		ExistingImage test = new ExistingImage("image.png");
		System.out.println("Width: "+test.getWidth()+" and Heigth: "+test.getHeight());

	}

}
