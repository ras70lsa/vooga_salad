package gameauthoring.creation.subforms.fire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import engine.AuthorshipData;
import engine.SpriteGroup;
import engine.definitions.SpriteDefinition;
import gameauthoring.creation.entryviews.IEntryView;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.shareddata.DefinitionCollection;
import gameauthoring.tabs.AuthoringView;
import gameauthoring.util.UIFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


/**
 * View representing a subform that creates the information required to build a tracking mover
 * module
 * 
 * @author Dhrumil
 * @author Joe Lilien
 *
 */
public class TrackingFirerSFV extends SubFormView implements ITrackingFireSFV {

    private GridPane myPane;
    private String myWaitTimeKey = "Wait Time: ";
    private String myTargetsKey = "Targets: ";
    private IEntryView myWaitTime;
    private SingleChoiceEntryView<SpriteGroup> myTargets;
    private SingleChoiceEntryView<SpriteDefinition> myMissileSelectionView;
    private Button myRemoveButton;
    private UIFactory myUIFactory= new UIFactory();

    public TrackingFirerSFV (AuthorshipData data, EventHandler<ActionEvent> e) {
        myRemoveButton = myUIFactory.createButton("remove",e);
        myWaitTime =
                new NumberEntryView(myWaitTimeKey, this.getData(), 150, 30,
                                    AuthoringView.DEFAULT_ENTRYVIEW);
        myTargets =
                new SingleChoiceEntryView<SpriteGroup>(myTargetsKey, data.getMyCreatedGroups().getItems(),
                                                       AuthoringView.DEFAULT_ENTRYVIEW);
        myMissileSelectionView =
                new SingleChoiceEntryView<>("Missile", data.getMyCreatedMissiles().getItems(), AuthoringView.DEFAULT_ENTRYVIEW);
        initView();

    }
    
    @Override
    protected void initView () {
        myPane = new GridPane();
        myPane.setGridLinesVisible(true);
        myPane.add(myWaitTime.draw(), 0, 0);
        myPane.add(myTargets.draw(), 1, 0);
        myPane.add(myMissileSelectionView.draw(), 0, 2);
        myPane.add(myRemoveButton, 1, 2);  
    }

    public SingleChoiceEntryView<SpriteGroup> getTargetsCoice () {
        return myTargets;
    }

    @Override
    public Node draw () {
        return myPane;
    }


    @Override
    public String getWaitTimeKey () {
        return myWaitTimeKey;
    }

    @Override
    public String getTargetsKey () {
        return myTargetsKey;
    }

    @Override
    public SpriteDefinition getSelectedMissile () {
        return myMissileSelectionView.getSelected();
    }

}
