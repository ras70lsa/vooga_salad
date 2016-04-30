package gameauthoring.listdisplay;

import java.util.ResourceBundle;
import engine.Game;
import engine.IGame;
import engine.conditions.ICondition;
import gameauthoring.creation.cellviews.CardCell;
import gameauthoring.creation.cellviews.DeleteableProfileCellView;
import gameauthoring.tabs.ITabViewer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import util.BundleOperations;


public class GameConditionViewer extends ConditionViewer implements ITabViewer {

    
    private static final String PATH = "defaults/game_condition_view";
    private ResourceBundle myBundle = ResourceBundle.getBundle("defaults/game_cond_view");

    public GameConditionViewer (IGame game) {
        super(game.getConditionManager().getConditionListProperty());
        init();
        new GameConditionController(this, game);
    }

    public void init () {
        initListView();
        add(createLeft(), 0, 0, 1, 3);
        add(createTop(), 1, 1, 1, 1);

    }

    private void initListView () {
        getListView().setPrefWidth(Double.parseDouble(myBundle.getString("ListWidth")));
        getListView().setPrefHeight(Double.parseDouble(myBundle.getString("ListHeight")));
        add(getListView(), 0, 3, 2, 1);
        getListView().setCellFactory(cell -> new DeleteableProfileCellView<ICondition>());
        getOptions().getStyleClass().add(getStyle("CondListView"));
        getOptions().setCellFactory(cell -> new CardCell(Double
                .parseDouble(myBundle.getString("CardSize"))));
    }

    private Node createLeft () {
        return getOptions();
    }

    private Node createTop () {

        getEditor().setPrefWidth(getTopWidth());
        getEditor().setPrefHeight(getTopHeight());
        getEditor().getChildren().add(getPlus());
        return getEditor();
    }

    private double getTopWidth () {
        return Double.parseDouble(myBundle.getString("TopWidth"));
    }

    private double getTopHeight () {
        return Double.parseDouble(myBundle.getString("TopHeight"));
    }

    private Node getPlus () {
        Image image = new Image(myBundle.getString("ImageURL"));
        ImageView view = new ImageView(image);
        view.setTranslateX(half(getTopWidth()) -
                           half(view.getBoundsInLocal().getWidth()));
        view.setTranslateY(half(getTopHeight()) -
                           half(view.getBoundsInLocal().getHeight()));
        return view;
    }

    private double half (double input) {
        return input * .5;
    }

    protected ObservableList<String> getList () {
        return BundleOperations.getValuesAsObservable(getBundle());
    }

    private ResourceBundle getBundle () {
        return ResourceBundle.getBundle(PATH);
    }

    public void setCenter (Node specific) {
        add(specific, 1, 2, 1, 1);
    }
}