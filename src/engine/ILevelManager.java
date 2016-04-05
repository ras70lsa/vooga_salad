package engine;

import java.util.List;
import interactionevents.KeyIOEvent;
import interactionevents.MouseIOEvent;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;


/**
 *
 * @author Joe Timko
 * @author Dhrumil Patel
 * @author David Maydew
 * @author Ryan St.Pierre
 * @author Jonathan Im
 *
 */
public interface ILevelManager extends IAdder {

    /**
     * @return the current level
     */
    ObjectProperty<ILevel> getCurrentLevel ();

    /**
     * @return an ObservableList of all the levels associated with this manager
     */
    ObservableList<ObjectProperty<ILevel>> getLevels ();

    /**
     * returns the Drawables of the current level
     * @return
     */
    ObservableList<? extends ObjectProperty<? extends Drawable>> getDrawables ();

    /**
     * @param list of key events to internalize
     */
    void internalizeKeyEvents (List<KeyIOEvent> list);

    
    /**
     * @param list of mouse events to internalize
     */
    void internalizeMouseEvents (List<MouseIOEvent> list);
    
    /**
     * @param sprite to be removed from the current level
     */
    void remove(ObjectProperty<ISprite> sprite);
    
}
