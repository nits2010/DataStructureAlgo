package Java.Design.TextEditor;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 11/04/19
 * Description:
 * Define the Doubly linked list for text Editor
 */

public class CharacterNode {

    private char value;
    private CharacterNode next;
    private CharacterNode prev;


    public CharacterNode(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public CharacterNode getNext() {
        return next;
    }

    public void setNext(CharacterNode next) {
        this.next = next;
    }

    public CharacterNode getPrev() {
        return prev;
    }

    public void setPrev(CharacterNode prev) {
        this.prev = prev;
    }

    @Override
    public String toString() {
        return  value + " ";

    }
}
