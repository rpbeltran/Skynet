package skynet;

import java.awt.image.BufferedImage;

public class Skynet {

	public static void main(String[] args) {
		
		ExistingImage test = new ExistingImage("image.png");
		System.out.println("Width: "+test.getWidth()+" and Heigth: "+test.getHeight());
		
		int width = test.getWidth(), height = test.getHeight();
		double total = 0;
		
		for(int x=0;x<width;x++) {
			
			int[test.getWidth()][3];
			for(int y=0;y<height;y++) {
				
				int[]oldrgb = test.getRGBat(x, y);
				
				total += 
				int average = (oldrgb[0]+oldrgb[1]+oldrgb[2])/3;
				int[]newrgb=new int[]{average,0,255-average};
				
			}
			
		}

	}

}
