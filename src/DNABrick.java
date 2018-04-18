/* Author: Arnav Goyal and Akshita Sahai
 * Project: DNA Pen
 * Mentor: Prof. Manish K Gupta
 */

/* This class creates dnaBricks DNA Brick as described in the referred Paper.
 * It takes the Height and Width as inputted by the user and creates dnaBricks DNA Brick
 * A couple of mathematical assumptions have been taken.
 * These assumptions have been drawn from the Brick Structure given in the paper
 * for the default 3nm * 7nm Brick and have been used to create bricks of any other dimension
 *
 * Note: 3nm * 7nm is the smallest possible dimension.
 */

public class DNABrick {
    public double brickWidth;
    public double brickHeight;
    public int minWidth;
    public int minHeight;
    public DNADomains dnaDomains;

    public Object[][] middleU;
    public StickyEnd leftStickyEnd;
    public StickyEnd rightStickyEnd;

    public DNABrick(double brickHeight,double brickWidth) {
        this.brickHeight = brickHeight;
        this.brickWidth = brickWidth;

        minWidth = (int)(brickWidth / 1.75);
        minHeight = (int)(brickHeight / .6);


        middleU = new Object[minHeight][minWidth + 1];

        GenerateDNAFile gen = new GenerateDNAFile();

    }


    public double getBrickWidth() {
        return brickWidth;
    }

    public double getBrickHeight() {
        return brickHeight;
    }

}
