# DnaPen


## Implemented Classes

1. NewFreeImportImageListener

This class is responsible for importing, processing and rendering of image on the canvas. 

*Implemented Methods*

* **actionPerformed()** : This methods allow the user to select the image from the file-system.
* **processImage()** : This method does a bunch of jobs. First it reads the image and converts it into binary form. Second, it calls the find_pen_stroke() method to get the order of pen strokes used while drawing the image. Third, it render the image on the canvas. Fourth, it checks the image for cennectedness by calling the find_connectedness() method and raises a warning if the image is not connected.
* **find_pen_strokes()** : This method takes in a binary image in the form of 2D array and returns pairs in the order in which the image was drawn. It uses a modified DFS alogrithm to find the pen strokes
* **find_connectedness()** : This method takes in a binary image in the form form of a 2D array and returns true if the image is connected and false otherwise. It uses a modified BFS algorithm to find connectedness. 

2. NewImportImagelistener

This class is responsible for importing, processing and rendering of image on the grid.

*Implemented Methods*

* **actionPerformed()** : This methods allow the user to select the image from the file-system.
* **processImage()** : This method reads the image and converts it into binary form. It then uses already implemented methods to fill in the bricks on the grid.

3. ThinningService

This class applies ZhangSuenThinning algorithm to a binary image and does image thinning. Currently this feature is disabled. 

For the original source code: http://www.guptalab.org/dnapen/downloads.htm
For demonstration of the add-on features refer to this video link: https://www.youtube.com/watch?v=vih5xYzo01A
