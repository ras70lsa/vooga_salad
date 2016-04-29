package gameauthoring.creation.forms;

import java.util.List;
import java.util.function.Consumer;
import engine.profile.IProfilable;
import gameauthoring.creation.subforms.ISubFormView;
import gameauthoring.shareddata.DefinitionCollection;
import gameauthoring.shareddata.IDefinitionCollection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


/**
 * Object that holds and displays both view items that make up object creation interface
 * 
 * @author Joe Lilien, Jeremy Schreck
 *
 */
public class ObjectCreationView<E extends IProfilable> implements IObjectCreationView<E> {

    private IObjectListView<E> myObjectListView;
    private IFormView myFormView;

    private Button myNewButton = new Button("New");
    private GridPane myCreationPane = new GridPane();

    /**
     * Constructor
     * 
     * Note: the subformviews in an ObjectCreationView are customizable
     * - this way, we can add classes that implement IObjectCreationView and
     * lay out their subforms in different ways
     * - the only requirement is that they have a list of ISubFormViews, so that
     * we can generate those reflectively
     * 
     * Note: part of constructor moved to init method to fix weird dependency
     * issue with some creation controllers needing to share authorshipData with
     * other creation controller's subforms
     * 
     * @param subFormViews The subformviews to create the FormView with
     */
    public ObjectCreationView () {
        ObservableList<E> items = FXCollections.observableArrayList();
        this.myObjectListView = new ObjectListView<E>(items);

    }

    /**
     * Initialize view
     */
    public void init (List<ISubFormView> subFormViews) {
        this.myFormView = new FormView(subFormViews);

        myCreationPane.setAlignment(Pos.TOP_LEFT);
        GridPane.setValignment(myObjectListView.draw(), VPos.TOP);
        
        myCreationPane.add(myObjectListView.draw(), 0, 0);
        myCreationPane.add(myFormView.draw(), 1, 0);
        
        //myCreationPane.getStyleClass().add("myCreationView");

    }
    
    @Override
    public Node draw () {
        return myCreationPane;
    }

    // Getters and Setters

    @Override
    public IObjectListView<E> getObjectListView () {
        // TODO Auto-generated method stub
        return myObjectListView;
    }

    @Override
    public IFormView getFormView () {
        // TODO Auto-generated method stub
        return myFormView;
    }

    @Override
    public void setNewAction (Consumer<?> action) {
        myNewButton.setOnAction(e -> action.accept(null));
    }

    @Override
    public void setEditAction (Consumer<E> action) {
        // set listcell's edit button's setOnAction to call action
        getObjectListView().setEditAction(action);

    }

    @Override
    public ObservableList<E> getItems () {
        return getObjectListView().getMyItems();

    }

    @Override
    public E getCurrentItem () {
        return getObjectListView().getSelectedItem();
    }

}