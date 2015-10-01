package skynet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.github.sarxos.webcam.Webcam;

public class Camera {
	
	public static String captureTo(String fileNameNoExtension) {
		//Takes an image and saves it to a file, returning the file name
		
		//Aquire image from webcam
		BufferedImage image = capture();
		try {
			//Save image to file
			ImageIO.write(image, "PNG", new File(fileNameNoExtension+".png"));
			//Return filename
			return fileNameNoExtension+".png";
		} catch (IOException e) {
			//Print error
			e.printStackTrace();
			//return nothing
			return null;
		}
	}
	
	public static BufferedImage capture() {
		//Captures an image from webcam, returns it as a bufferd Image
		Webcam webcam = Webcam.getDefault();      //Acquire webcam
		webcam.open();                            //Initialize webcam
		BufferedImage image =  webcam.getImage(); //Record current frame
		webcam.close();                           //turn off the camera
		return image;                             //return the image
	}
	
}
