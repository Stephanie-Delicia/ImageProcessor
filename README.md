<br/>CS3500
<br/>README
<br/>Stephanie Martinez
<br/>Lauren MacIver

### Assignment 8:
In order to use the new features, please navigate to the res folder and unzip everything! Also,
read the USEME for further details.

Level 1: We exposed the downsize feature through both script and the GUI. The following are the changes we made to implement this new feature:
<br/>1. Added a new downsize signature to the Image interface.
<br/>2. Implemented the downsize method in the ImageImpl class.
<br/>3. Added a new string transformation option in the view. Also, added a new private method to
            ask the user to enter values for the new width and height of the image.
<br/>4. For both the script and GUI controllers, we added a new command case for the 
            downsize method and called it "downscale". This takes an image, width, height, and
            new image name.
            
            For script, the new command for downscaling is:
            <br/>downscale image-name width height image-new-name
            
In order to successfully use down-scaling, the dimensions of the new photo MUST be 
smaller than or equal to the original dimensions of the given photo. 

Level 2: We exposed the mask method through the script image processor program. The changes
made to implement this new feature is similar to the downsize feature:
<br/>1. Added a new mask signature to the Image interface.
<br/>2. Implemented the mask method in the ImageImpl class.
<br/>3. For both the script controller, we added a new command case for the 
            mask method and called it "mask". This takes an image, the mask image, the new image
            name, and the transformation name.
            
            For script, the new command for masking is:
            <br/>mask image-name image-mask-name image-new-name command
            where image-mask-name is the name of the mask image and
            command is the desired transformation for the masking
            
In order to successfully mask a photo, both the original image AND the mask image must be loaded first. Then, the command must be one of the original transformations provided by the program:

- intensity-component
- luma-component
- value-component
- red-component
- green-component
- blue-component
- blur
- sharpen
- sepia-transform
- grayscale-transform

Therefore, the possible scripts for masking are:
- mask image mask new-name intensity-component
- mask image mask new-name luma-component
- mask image mask new-name value-component
- mask image mask new-name red-component
- mask image mask new-name green-component
- mask image mask new-name blue-component
- mask image mask new-name blur
- mask image mask new-name sharpen
- mask image mask new-name sepia-transform
- mask image mask new-name grayscale-transform

### Design Approach:
We used the MVC design pattern to create our image processor
for this assignment.

Our image processing program includes a model that contains a 
map of images with associated string names. 
The model contains the data of Images saved and created by the controller. 
The model also contains methods to remove or add to the map of stored images.
Our model package contains interfaces for IImage (represents and image with pixel data), 
IPixel (represents a pixel with 3-component integer values), and ImageProcessorModel (represents
the image processing model itself and contains the map of images).
An IPixel represents a pixel and contains methods to produce new IPixels or derived values
based off an IPixel such as deriving the luma of a pixel.
RGBPixel is an implementation of the IPixel interface and contains 3 RGB values for red, green, 
and blue. 
The IImage interface represents an image and contains methods that manipluate all 
the pixels that an IImage contains. The implementation, ImageImpl, represents its pixels with 
a 2-d array of IPixels.

The image controller takes user input and interacts with the model and view when needed.
For the controller, we created an ImageController interface and its implementation,
ImageControllerImpl which takes in a processing model, processing view, and a readable.
The controller communicates with the view by transmitting messages to the
view based off the user's input. The controller communicates with the model by storing
images transformed by the user into the model's map and obtaining a specific
image from the map when the user wants to save the image as a file.
Finally, the controller also communicates with the model when an image is needed
to be loaded from its map to transform it. All file operations are called on
by the controller only.
A MainController class was created to test run our controller using user input.
The controller processes the script of commands listed in the
USEME file.

The view interface, ImageProcessorView, represents a view for the image processor. 
The view is responsible for printing messages to the user during 
use of the controller. 

Finally, we created two new implementations: a GUI view and a
controller that works with this new GUI view. Multiple commands were created for the controller
to execute with specific components/buttons of the GUI. Additionally, a separate class for 
the histogram to be displayed to the user was created.

### How to use the program:
There are three options for using the program: By interactive text script, inputting a text file path,
or by opening the user GUI. Ensure that the jar file, textfiles, and example images are all unzipped 
in the res folder. The three commands that can be inputted on the command line are: 

- java -jar Program.jar -file path-of-script-file
- java -jar Program.jar -text
- java -jar Program.jar

The GUI contains a dropdown box at the very top which allows one to choose a transformation.
Below that, there is a panel with scrollbars that displays the photo the user is working on.
Below this panel, there are two buttons: one to load, the other for saving a photo. Pressing these
buttons will open options of photos to choose from for loading or a folder to save an image in.
Finally, at the very bottom of the GUI, there is a line chart that displays the frequency of 
the photo's RGB values and intensity values for all pixels.

### Changes to our design:
For this submission, we created a new IView interface for the image processor's GUI view.
IView contains methods that allow it to draw a histogram or update the current image the user
is working on. Also, we created a new controller implementation called ProcessorControllerGUI
which works with the new GUI view and the original model design. 

### Goals for assignment 8:
We were able to complete parts 1 and 2.

#### Image citation:
The example image used was created and owned by I, Stephanie Martinez.
I authorize the use of this photo for this assignment.
