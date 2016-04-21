package gameauthoring.listdisplay;

import engine.AttributeType;
import engine.IEventPackage;
import engine.IGame;
import engine.ILevel;
import engine.definitions.EventPackageDefinition;
import javafx.collections.ObservableList;


public abstract class AttributeEndView extends OnAttributeView {

    private IGame myGame;
    private EndOptions myEndOptions = new EndOptions();

    public AttributeEndView (IGame game, ILevel level, ObservableList<AttributeType> attributes) {
        super(game, level.getConditionsListProperty(),
              attributes);
        myGame = game;
        initializeDisplay();

    }

    protected void initBoxes () {
        super.initBoxes();
        addStringComboBox(myEndOptions.getBox());

    }

    protected IEventPackage getGlobal () {
        return myEndOptions.getGlobal();
    }

    protected IEventPackage createEmpty () {
        return new EventPackageDefinition().create();
    }

}
