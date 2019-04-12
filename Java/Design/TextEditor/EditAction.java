package Java.Design.TextEditor;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 11/04/19
 * Description:
 */
public class EditAction {

    private Action action;
    private CharacterNode data;


    public EditAction(Action action, CharacterNode data) {
        this.action = action;
        this.data = data;
    }

    public CharacterNode getData() {

        return data;
    }

    public void setData(CharacterNode data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "EditAction{" +
                "action=" + action +
                ", data=" + data +
                '}';
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
