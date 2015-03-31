#~~~~~~~~~~~~~~~~~Program Information~~~~~~~~~~~~~~~~~~~~~~~~
# Program: Intelligent Sky Glow Calculator
#      AKA: Skynet
#
# Intended Goals and Versions to implement them:
#       Pull relative brightness value from image  | v1.0
#       Recolor image with values                  | v1.0
#   On RPi Only:
#       Utilize onboard camera                     |
#       Calibrate to accepted skyglow              |
#       Plot daily readings                        |
#       Post daily readings to twitter             |
#       Twitter other relavent info                |
#       Find readings on request                   |
#       
# Written By: Ryan Beltran, for the Westwood NASA Club
# Began On: September 19th 2014
# Revisions:
    # 9/19/14: r1.0
    #
#~~~~~~~~~~~~~~~~/Program Information~~~~~~~~~~~~~~~~~~~~~~~~

#~~~~~~~~~~~~~~~~~~Settings~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
#Image Source
IMAGESOURCE = "image.png"
#~~~~~~~~~~~~~~~~~/Settings~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

#~~~~~~~~~~~~~~~~~~~Calibration~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
CALIBRATION_CONSTANT = 0.0
CALIBRATION_MULTIPLIER = 1.0
def adjustToCalibrate(value):
    return ((CALIBRATION_MULTIPLIER * float(value)) + CALIBRATION_CONSTANT)
#~~~~~~~~~~~~~~~~~~/Calibration~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

#~~~~~~~~~~~~~~~~~~~Imports~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
from PIL import Image, ImageFilter
#~~~~~~~~~~~~~~~~~~/Imports~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

#~~~~~~~~~~~~~~~~~~~Main Program~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
#Open raw image
try:
    colored = Image.open(IMAGESOURCE)
except:
    print "error loading image, most likely an invalid address"
#Returns color scale from brightness
def colorScale(level):
    return (level,255-level,0)
#Create grayscale clone
gray = colored.convert('L')
#Get Dimentions
width, height = gray.size
total=0#For averaging
#Cycle through pixels
for x in range(width):
   for y in range(height):
       #Get data
       value = gray.getpixel( (x,y) )
       total += value
       #Color in scale values
       colored.putpixel( (x,y), colorScale(value))
#Save images
gray.save("gray.png")
colored.save("final.png")
print "Image Saved"
#Calculate Average light intensity, and calibrate it
average = adjustToCalibrate(float(total)/float(width*height))
print "Average = "+str(average)
#Pause the program
try:
    input()
except:
    pass
#~~~~~~~~~~~~~~~~~~/Main Program~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
