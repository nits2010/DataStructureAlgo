package Java.Design.TextEditor;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 11/04/19
 * Description:
 */
public class Driver {

    public static void main(String args[]) {

        ITextEditor textEditor = new SimpleTextEditor();

        System.out.println(textEditor.print());

        textEditor.insert('a');
        textEditor.insert('b');
        textEditor.insert('d');
        textEditor.insert('e');
        textEditor.insert('f');
        textEditor.insert('g');
        System.out.println(textEditor.print());
        System.out.println(" Cursor pos: " + textEditor.getCursor());

        System.out.println("backspace->");
        textEditor.backspace();
        System.out.println(textEditor.print());
        System.out.println(" Cursor pos: " + textEditor.getCursor());
        System.out.println("backspace->");
        textEditor.backspace();
        System.out.println(textEditor.print());
        System.out.println(" Cursor pos: " + textEditor.getCursor());
        System.out.println("backspace->");
        textEditor.backspace();
        System.out.println(textEditor.print());
        System.out.println(" Cursor pos: " + textEditor.getCursor());
        System.out.println("backspace->");
        textEditor.backspace();
        System.out.println(textEditor.print());
        System.out.println(" Cursor pos: " + textEditor.getCursor());
        System.out.println("backspace->");
        textEditor.backspace();
        System.out.println(textEditor.print());
        System.out.println(" Cursor pos: " + textEditor.getCursor());

        System.out.println("undo");
        textEditor.undo();
        System.out.println(textEditor.print());
        System.out.println(" Cursor pos: " + textEditor.getCursor());


        System.out.println("moveRight->");
        textEditor.moveRight();
        System.out.println(textEditor.print());
        System.out.println("  Cursor pos: " + textEditor.getCursor());

        System.out.println("moveLeft->");
        textEditor.moveLeft();
        System.out.println(textEditor.print());
        System.out.println(" Cursor pos: " + textEditor.getCursor());

        System.out.println("moveLeft->");
        textEditor.moveLeft();
        System.out.println(textEditor.print());
        System.out.println(" Cursor pos: " + textEditor.getCursor());

        System.out.println("moveRight->");
        textEditor.moveRight();
        System.out.println(textEditor.print());
        System.out.println("  Cursor pos: " + textEditor.getCursor());

        System.out.println("backspace->");
        textEditor.backspace();
        System.out.println(textEditor.print());
        System.out.println(" Cursor pos: " + textEditor.getCursor());

        System.out.println("insert->z");
        textEditor.insert('z');
        System.out.println(textEditor.print());
        System.out.println(" Cursor pos: " + textEditor.getCursor());

        System.out.println("backspace");
        textEditor.backspace();
        System.out.println(textEditor.print());
        System.out.println(" Cursor pos: " + textEditor.getCursor());

        System.out.println("undo");
        textEditor.undo();
        System.out.println(textEditor.print());
        System.out.println(" Cursor pos: " + textEditor.getCursor());

        System.out.println("backspace");
        textEditor.backspace();
        System.out.println(textEditor.print());
        System.out.println(" Cursor pos: " + textEditor.getCursor());

        System.out.println("backspace");
        textEditor.backspace();
        System.out.println(textEditor.print());
        System.out.println(" Cursor pos: " + textEditor.getCursor());

        System.out.println("backspace");
        textEditor.backspace();
        System.out.println(textEditor.print());
        System.out.println(" Cursor pos: " + textEditor.getCursor());

        System.out.println("moveRight");
        textEditor.moveRight();
        System.out.println(textEditor.print());
        System.out.println(" Cursor pos: " + textEditor.getCursor());

        System.out.println("backspace");
        textEditor.backspace();
        System.out.println(textEditor.print());
        System.out.println(" Cursor pos: " + textEditor.getCursor());


    }
}
