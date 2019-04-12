package Java.Design.TextEditor;

import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 11/04/19
 * Description:
 */
public interface ITextEditor {

    void moveLeft();

    void moveRight();

    void backspace();

    void insert(char data);

    void undo();

    String print();

    CharacterNode getCursor();

}
