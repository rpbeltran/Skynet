package skynet;

import java.awt.image.BufferedImage;

public class Algor {
	
	public static int[] scaleByBrightness(int[] rgb){
		int sum = (rgb[0]+rgb[1]+rgb[2])/3;
		return new int[] {sum,255-sum,0};
	}
	
	public static void basicIterativeTo(BufferedImage image, String filename){
		basicIterative(image).saveTo(filename);
	}
	
	public static NewImage basicIterative(BufferedImage image){
		ExistingImage test = new ExistingImage(image);
		NewImage newTest = new NewImage(test.getWidth(),test.getHeight());
		for(int row = 0; row < test.getHeight();row ++){
				for (int col = 0; col < test.getWidth(); col++){
					newTest.setAt(col,row,Algor.scaleByBrightness(test.getRGBat(col, row)) );
				
			}
		}
		return newTest;
	}

}
