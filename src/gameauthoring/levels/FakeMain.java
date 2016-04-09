package gameauthoring.levels;

import engine.ConditionManager;
import engine.Game;
import engine.ILevel;
import engine.sprite.ISprite;
import engine.Level;
import engine.LevelManager;
import engine.modules.GraphicModule;
import engine.modules.IGraphicModule;
import engine.sprite.Sprite;
import engine.sprite.SpriteType;
import graphics.Block;
import graphics.ImageGraphic;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import util.RGBColor;


public class FakeMain extends Application {

    private TabPane myLevelTabs;
    private LevelManager myLevelManager;
    private ConditionManager myConditionManager;

    /**
     * Set things up at the beginning.
     * Create model, create view, assign them to each other.
     */
    @Override
    public void start (Stage stage) {
        ObjectProperty<ILevel> startingLevel = new SimpleObjectProperty<>(new Level());
        myLevelManager = new LevelManager(startingLevel);
        myConditionManager = new ConditionManager();
        Game game = new Game(myLevelManager, null, myConditionManager);
        makeSomeSprites(game);
        LevelEditorView view = new LevelEditorView(game, startingLevel.get());

        myLevelTabs = new TabPane();
        Tab createLevelTab = createButtonTab();
        Tab firstLevelTab = new Tab("Level 1");
        myLevelTabs.getSelectionModel().select(firstLevelTab);
        firstLevelTab.setContent(view.draw());
        myLevelTabs.getTabs().addAll(createLevelTab, firstLevelTab);
        stage.setScene(new Scene(myLevelTabs));
        stage.show();
    }

    private Tab createButtonTab () {
        Tab createLevelTab = new Tab();
        Button addNewLevelButton = new Button("+");
        addNewLevelButton.setOnAction(e -> addNewLevel());
        createLevelTab.setGraphic(addNewLevelButton);
        createLevelTab.setClosable(false);
        return createLevelTab;
    }

    private void addNewLevel () {
        Tab newLevelTab = new Tab("Level" + (myLevelTabs.getTabs().size()));
        myLevelTabs.getTabs().add(newLevelTab);
        myLevelTabs.getSelectionModel().select(newLevelTab);
        ObjectProperty<ILevel> newLevel = new SimpleObjectProperty<>(new Level());
        myLevelManager.createNewLevel(newLevel);

        Game game = new Game(myLevelManager, null, myConditionManager);
        makeSomeSprites(game);
        LevelEditorView view = new LevelEditorView(game, newLevel.get());
        Group nextLevel = new Group(view.draw());
        newLevelTab.setContent(nextLevel);
    }

    private void makeSomeSprites (Game game) {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                game.getAuthorshipData().getCreatedSprites()
                        .add(new SimpleObjectProperty<>(createFirstSprite()));
            }
            else {
                game.getAuthorshipData().getCreatedSprites()
                        .add(new SimpleObjectProperty<>(createSecondSprite()));
            }
        }
    }

    private ISprite createFirstSprite () {
        ISprite sprite = new Sprite();
        sprite.getType().set(new SpriteType("Person"));

        ObjectProperty<IGraphicModule> g =
                new SimpleObjectProperty<>(new GraphicModule(new ImageGraphic(30, 30,
                                                                              "images/photo.png")));
        sprite.getDrawer().set(g.get());
        return sprite;
    }

    private ISprite createSecondSprite () {
        ISprite sprite = new Sprite();
        sprite.getType().set(new SpriteType("Block"));
        ObjectProperty<IGraphicModule> g =
                new SimpleObjectProperty<>(new GraphicModule(new Block(40, 40, RGBColor.BLACK)));
        sprite.getDrawer().set(g.get());
        return sprite;
    }

    /**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
