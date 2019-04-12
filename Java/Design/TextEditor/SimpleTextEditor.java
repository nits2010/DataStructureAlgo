package Java.Design.TextEditor;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 11/04/19
 * Description: https://www.careercup.com/question?id=5726975948226560
 */
public class SimpleTextEditor implements ITextEditor {

    private Stack<EditAction> undoStack; //holds the last action
    private CharacterNode cursor; //holds the current cursor position
    private CharacterNode start; //to print
    int totalSize = 0;

    public SimpleTextEditor() {
        this.undoStack = new Stack<>();
        this.cursor = new CharacterNode('\0');
        start = null;
    }

    @Override
    public void moveLeft() {
        if (cursor.getPrev() == null) return;
        cursor = cursor.getPrev();
        undoStack.push(new EditAction(Action.RIGHT, null));
    }

    @Override
    public void moveRight() {
        if (cursor.getNext() == null) return;
        cursor = cursor.getNext();
        undoStack.push(new EditAction(Action.LEFT, null));
    }

    @Override
    public void backspace() {
        if (cursor.getPrev() == null) return; //No data to delete
        totalSize--;
        CharacterNode deleted = delete(cursor.getPrev());
        undoStack.push(new EditAction(Action.INSERT, deleted));
        if (totalSize == 0)
            start = null;


    }

    private CharacterNode delete(CharacterNode toDelete) {

        if (toDelete != null) {

            CharacterNode prev = toDelete.getPrev();
            CharacterNode next = toDelete.getNext();

            if (prev != null) {
                prev.setNext(next);
            }

            if (next != null)
                next.setPrev(prev);

            if (toDelete.getPrev() == null && totalSize == 0)
                start = null;

            if (toDelete == start)
                start = next;
        }

        if (cursor.getPrev() == null && totalSize == 0)
            start = null;


        return toDelete;

    }

    @Override
    public void insert(char data) {


        CharacterNode node = new CharacterNode(data);

        CharacterNode prev = cursor.getPrev();

        node.setNext(cursor);
        cursor.setPrev(node);

        if (prev != null) {
            prev.setNext(node);
            node.setPrev(prev);

        } else
            start = node;

        if (totalSize == 0)
            start = node;


        undoStack.push(new EditAction(Action.DELETE, node));
        totalSize++;

    }

    @Override
    public void undo() {

        if (undoStack.isEmpty()) return;

        EditAction action = undoStack.pop();

        switch (action.getAction()) {
            case LEFT:
                if (cursor != null)
                    cursor = cursor.getNext();
                break;
            case RIGHT:
                if (cursor != null)
                    cursor = cursor.getPrev();
                break;
            case DELETE:
                if (cursor != null) {
                    if (cursor.getPrev() == null)
                        start = null;

                    delete(cursor.getPrev());
                }
                break;
            case INSERT:

                insert(action.getData().getValue());
                break;
        }

    }

    @Override
    public String print() {
        List<CharacterNode> currentText = new LinkedList<>();
        CharacterNode temp = start;
        while (temp != null) {

            if (temp == cursor)
                currentText.add(new CharacterNode('|'));
            if (temp.getValue() != '\0')
                currentText.add(temp);
            temp = temp.getNext();
        }
        return currentText.toString();
    }


    @Override
    public CharacterNode getCursor() {
        return cursor;
    }
}
