package gameauthoring.creation.cellviews;

import engine.profile.IProfilable;
import engine.profile.IProfile;
import engine.rendering.GraphicFactory;
import engine.rendering.ScaleFactory;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


/**
 * Class to help visualize a profilable definition in a list view.
 * Anything that implements IProfilable can use this to create its
 * cells in a user friendly way.
 * 
 * @author Tommy
 *
 * @param <E>
 */
public class ProfileCellView<E extends IProfilable> extends ListCell<E> {

    private static final double PIC_SIZE = 30;

    private E myProfile;
    

    @Override
    protected void updateItem (E item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            myProfile = null;
            setGraphic(null);
        }
        else {
            myProfile = item;
            setGraphic(createSpriteCell(item));
        }
    }

    protected Node createSpriteCell (E profile) {
        HBox container = getHBox(profile);
        return container;
    }

    /**
     * For subclasses to alter the HBox not the node
     * @param profile
     * @return
     */
    protected HBox getHBox (E profile) {
        HBox container = new HBox(10);
        container.setAlignment(Pos.CENTER_LEFT);
        container.getChildren().add(createImageProfile(profile.getProfile()));
        container.getChildren().add(createTextProfile(profile.getProfile()));
        return container;
    }

    private Node createTextProfile (IProfile profile) {
        VBox container = new VBox();

        Text name = bindText(profile.getName());
        Text description = bindText(profile.getDescription());
        container.getChildren().addAll(name, description);
        return container;
    }

    private Text bindText (StringProperty name) {
        Text text = new Text();
        text.textProperty().bind(name);
        return text;
    }

    private Node createImageProfile (IProfile profile) {
        GraphicFactory graphics = new ScaleFactory(PIC_SIZE, PIC_SIZE);

        Node node = profile.getImage().getVisualRepresentation(graphics);
        return node;
    }

    protected E getProfilable () {
        return myProfile;
    }

}
