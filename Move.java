import java.util.ArrayList;
import java.util.List;

class SingleObject {
    private String objectName;

    public SingleObject(String objectName) {
        this.objectName = objectName;
    }

    public String getName() {
        return objectName;
    }
}

class Box {
    private List<Object> contents;
    private int boxNumber;

    public Box(int capacity, int boxNumber) {
        contents = new ArrayList<>(capacity);
        this.boxNumber = boxNumber;
    }

    public void addItem(Object item) {
        contents.add(item);
    }

    public void displayContents() {
        for (Object item : contents) {
            if (item instanceof SingleObject) {
                System.out.println(((SingleObject) item).getName());
            } else if (item instanceof Box) {
                ((Box) item).displayContents();
            }
        }
    }

    public int find(String objectName) {
        for (Object item : contents) {
            if (item instanceof SingleObject && ((SingleObject) item).getName().equals(objectName)) {
                return boxNumber;  // Return the current box number if the object is found
            } else if (item instanceof Box) {
                int result = ((Box) item).find(objectName);
                if (result > 0) {
                    return boxNumber;  // Return the current box number, not the inner box number
                }
            }
        }
        return -boxNumber;  // Return negative number only if the object is not found in the current box
    }}

public class Move {
    private List<Box> boxes;

    public Move(int capacity) {
        boxes = new ArrayList<>(capacity);
    }

    public void addBox(Box box) {
        boxes.add(box);
    }

    public void displayContents() {
        System.out.println("The objects of my move are:");
        for (Box box : boxes) {
            box.displayContents();
        }
    }

    public int find(String objectName) {
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
