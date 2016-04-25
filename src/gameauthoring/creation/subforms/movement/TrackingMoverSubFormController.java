package gameauthoring.creation.subforms.movement;

import java.util.List;
import engine.IGame;
import java.util.ArrayList;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.moduledef.TrackingMoverDefinition;
import engine.sprite.SpriteType;
import gameauthoring.creation.entryviews.IFormDataManager;
import gameauthoring.creation.subforms.ISubFormControllerSprite;
import gameauthoring.creation.subforms.ISubFormView;


public class TrackingMoverSubFormController implements ISubFormControllerSprite {

    private TrackingMoverSubFormView myView;
    private IFormDataManager myFormData;
    private IGame myGame;
    private double myDefaultSpeed = 0;

    public TrackingMoverSubFormController (IGame game) {
        this.myGame = game;
        this.myView = new TrackingMoverSubFormView(game.getAuthorshipData().getMyCreatedGroups());
        this.myFormData = myView.getData();
    }

    @Override
    public void initializeFields () {
        myFormData.set(myView.getSpeedKey(), Double.toString(myDefaultSpeed));
    }

    @Override
    public void updateItem (SpriteDefinition item) {
        TrackingMoverDefinition newTrackingMoverDef = new TrackingMoverDefinition();
        newTrackingMoverDef.setGame(myGame);
        Double mySpeedDouble =
                Double.valueOf(myFormData.getValueProperty(myView.getSpeedKey()).get());
        newTrackingMoverDef.setSpeed(mySpeedDouble);
        newTrackingMoverDef.setTargets(myView.getTargetsCoice().getSelected().getSpriteTypes());
    

        item.setMovementDefinition(newTrackingMoverDef);
    }

    @Override
    public ISubFormView getSubFormView () {
        return myView;
    }

}