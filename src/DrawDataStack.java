import java.util.ArrayList;


public class DrawDataStack {
    public ArrayList<XYCoordinates> xyCoordinatesStack;

    public DrawDataStack() {
        this.xyCoordinatesStack = new ArrayList<XYCoordinates>();
    }

    public ArrayList<XYCoordinates> getXyCoordinatesStack() {
        return xyCoordinatesStack;
    }

    public void pushData(XYCoordinates xyCoordinates) {
        xyCoordinatesStack.add(0, xyCoordinates);
    }

    public XYCoordinates popData() {
        return xyCoordinatesStack.remove(0);
    }

    public XYCoordinates topData() {
        return xyCoordinatesStack.get(0);
    }

    public void clearData() {
        xyCoordinatesStack.clear();
    }

    public void removeSpecificElement(int xCoordinate, int yCoordinate) {
        XYCoordinates xyCoordinates;
        int xValue = 0;
        int yValue = 0;
        for (int i = 0; i < xyCoordinatesStack.size(); i++) {
            xyCoordinates = xyCoordinatesStack.get(i);
            xValue = xyCoordinates.getxCoordinate();
            yValue = xyCoordinates.getyCoordinate();

            if (xValue ==xCoordinate && yValue == yCoordinate) {
                xyCoordinatesStack.remove(i);
            }
        }
    }

    public boolean isEmpty() {
        boolean isEmpty = false;

        if (xyCoordinatesStack.size() == 0) {
            isEmpty = true;
        }

        return isEmpty;
    }

    public int getSize() {
        return xyCoordinatesStack.size();
    }
}

