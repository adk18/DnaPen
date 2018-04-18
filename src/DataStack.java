import java.util.ArrayList;


public class DataStack {
    public ArrayList<Domains> DomainStack;

    public DataStack() {
        this.DomainStack = new ArrayList<Domains>();
    }

    public ArrayList<Domains> DomainStack() {
        return DomainStack;
    }

    public void pushData(Domains domData) {
        DomainStack().add(0, domData);
    }

    public Domains popData() {
        return DomainStack.remove(0);
    }

    public Domains topData() {
        return DomainStack().get(0);
    }

    public void clearData() {
        DomainStack.clear();
    }

    /*
    public void removeSpecificElement(int xCoordinate, int yCoordinate) {
        XYCoordinates xyCoordinates;
        int xValue = 0;
        int yValue = 0;
        for (int i = 0; i < xyCoordinatesStack.size(); i++) {
            xyCoordinates = xyCoordinatesStack.get(i);
            xValue = xyCoordinates.getxCoordinate();
            yValue = xyCoordinates.getyCoordinate();

            if (xValue == (xCoordinate / 90) && yValue == (yCoordinate / 30)) {
                xyCoordinatesStack.remove(i);
            }
        }
    }
    */
    public boolean isEmpty() {
        boolean isEmpty = false;

        if (DomainStack.size() == 0) {
            isEmpty = true;
        }

        return isEmpty;
    }

    public int getSize() {
        return DomainStack.size();
    }
}

