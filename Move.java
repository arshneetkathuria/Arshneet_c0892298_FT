import java.util.ArrayList;
import java.util.List;

class SingleObject {
    private String objectName;

    public SingleObject(String objectName) {  //creates single object
        this.objectName = objectName;
    }

    public String getName() {    //gets the name of object
        return objectName;
    }
}

class Box {
    private List<Object> contents;
    private int boxNumber;

    public Box(int capacity, int boxNumber) {  //creates a box with capacity and box number
        contents = new ArrayList<>(capacity);
        this.boxNumber = boxNumber;
    }

    public void addItem(Object item) {  //adds item to the content array
        contents.add(item);
    }

    public void displayContents() {  //Displays the content of the box
        for (Object item : contents) {
            if (item instanceof SingleObject) {
                System.out.println(((SingleObject) item).getName());
            } else if (item instanceof Box) {
                ((Box) item).displayContents();
            }
        }
    }

    public int find(String objectName) { //returns the outermost box number
        for (Object item : contents) {
            if (item instanceof SingleObject && ((SingleObject) item).getName().equals(objectName)) {
                return boxNumber;
            } else if (item instanceof Box) {
                int result = ((Box) item).find(objectName);
                if (result > 0) {
                    return boxNumber;
                }
            }
        }
        return -boxNumber;  // Return negative number if the object is not found in the current box
    }}

public class Move {
    private List<Box> boxes;

    public Move(int capacity) { //creates an array with the capacity
        boxes = new ArrayList<>(capacity);
    }

    public void addBox(Box box) { //adds the box to the moving boxes array
        boxes.add(box);
    }

    public void displayContents() { // display the content of the moving boxes array
        System.out.println("The objects of my move are:");
        for (Box box : boxes) {
            box.displayContents();
        }
    }

    public int find(String objectName) { // finds the object in the outermost box
        for (Box box : boxes) {
            int result = box.find(objectName);
            if (result > 0) {
                return result;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Move move = new Move(2);

        Box box1 = new Box(1, 1);
        box1.addItem(new SingleObject("scissors"));

        Box box2 = new Box(1, 2);
        box2.addItem(new SingleObject("book"));

        Box box3 = new Box(2, 3);
        box3.addItem(new SingleObject("compass"));
        Box box4 = new Box(1, 4);
        box4.addItem(new SingleObject("scarf"));
        box3.addItem(box4);

        Box box5 = new Box(3, 5);
        box5.addItem(box1);
        box5.addItem(box2);
        box5.addItem(box3);

        Box box6 = new Box(3, 6);
        box6.addItem(new SingleObject("pencils"));
        box6.addItem(new SingleObject("pens"));
        box6.addItem(new SingleObject("rubber"));

        move.addBox(box5);
        move.addBox(box6);

        move.displayContents();
        System.out.println("The scarf is in the cardboard number " + move.find("scarf"));
    }
}
