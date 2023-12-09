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



public class Move {


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
