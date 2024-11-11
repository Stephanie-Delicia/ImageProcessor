<br/>CS3500
<br/>USEME
<br/>Stephanie Martinez
<br/>Lauren MacIver


### Using the Jar File:
For the jar file, there are three options for using the program:
1. Interactive text script (Input in command-line: java -jar Program.jar -text)
2. Upload a text file (java -jar Program.jar -file path-of-script-file )
   - For path-of-script-file, use either textfile1.txt or textfile2.txt.
3. Opening and using the graphical user interface (java -jar Program.jar)

Before choosing one of the ways to use the program:
1. Open all zipped files in the /res folder: ExamplePhotos.zip, hw6.jar.zip, and textfiles.zip.
2. Go to the /res folder on command line.
3. type in one of the available script commands above and press enter or return.
4. The transformed image should be saved in the /res folder and the program should finish running.

The text file, textfile1.txt, is a sample of user script that the image processor controller will
respond to. The sample script has the new commands such as sepia,
grayscale, blur, and sharpen that have been added to the updated program. The sample script also
contains previous commands from assignment 4 that continue to be supported in the new program.

NOTE: The jar file, hw6.jar, textfile1.txt/textfile2.txt, and examplePNG.png MUST be in the res folder for the
program to run successfully from the command line. 

Also, before using any command other than load and quit, an image MUST be loaded in order to
start performing transformations with the program.

### Important Note for text interactive mode:
Please type each command line by line. That is, copy one command at a time and then input it
in the command line and press enter. 

### Using the graphical user interface:
The GUI contains a dropdown box at the very top which allows one to choose a transformation.
Below that, there is a panel with scrollbars that displays the photo the user is working on.
Below this panel, there are two buttons: one to load, the other for saving a photo. Pressing these
buttons will open options of photos to choose from for loading or a folder to save an image in.
To save an image, the user need not manually write down the given path but can simply
write the name of the image in the bottom text box on the loading display box.
Finally, at the very bottom of the GUI, there is a line chart that displays the frequency of
the photo's RGB values and intensity values for all pixels.

### Commands available for interactive script mode:
- save
- load
- blur
- sharpen
- brighten
- sepia-transform
- grayscale-transform
- blue-component
- red-component
- green-component
- luma-component
- intensity-component
- value-component
- vertical-flip
- horizontal-flip  
- quit

### Examples of using script commands:
Most transformations require that a user input the name of the transformation along with the
reference name of the image already stored in the image processor model and finally a new 
name to reference the image by. If the original name and new name reference are equivalent,
the original image will be overwritten in the model. There are examples of using commands below.

e.g
<br />sharpen exampleImage newImage
<br />load exampleImage.png loadedImage
<br />brighten -20 loadedImage loadedImageDarkened
<br />save ImagePathName loadedImageDarkened

