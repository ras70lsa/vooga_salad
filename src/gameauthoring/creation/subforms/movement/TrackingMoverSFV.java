package gameauthoring.creation.subforms.movement;

import java.util.ResourceBundle;
import splash.LocaleManager;
import engine.SpriteGroup;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.shareddata.DefinitionCollection;
import gameauthoring.tabs.AuthoringView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;


public class TrackingMoverSFV extends SubFormView implements ITrackingMoverSFV {

    private HBox myPane;
    private ResourceBundle myLabel;
    private String mySpeedKey;
    private String myTargetsKey;
    private NumberEntryView mySpeed;
    private SingleChoiceEntryView<SpriteGroup> myTargets;

    public TrackingMoverSFV (DefinitionCollection<SpriteGroup> groupsList) {
        setResourceBundleAndKey();
        myTargets =
                new SingleChoiceEntryView<SpriteGroup>(myTargetsKey, groupsList.getItems(),
                                                       AuthoringView.DEFAULT_ENTRYVIEW);
        mySpeed = new NumberEntryView(mySpeedKey, 150, 30, AuthoringView.DEFAULT_ENTRYVIEW);
        initView();

    }

    @Override
    public SpriteGroup getTargetsCoice () {
        return myTargets.getSelected();
    }

    private void setResourceBundleAndKey () {
        myLabel = ResourceBundle.getBundle("languages/labels", LocaleManager
                .getInstance().getCurrentLocaleProperty().get());
        mySpeedKey = myLabel.getString("SpeedKey");
        myTargetsKey = myLabel.getString("TargetsKey");
    }

    @Override
    public Node draw () {
        return myPane;
    }

    @Override
    protected void initView () {
        myPane = getMyUIFactory().makeHBox(20, Pos.CENTER, mySpeed.draw(), myTargets.draw());
    }

    @Override
    public void populateWithData (double speed, SpriteGroup targets) {
        mySpeed.setData(speed);
        myTargets.setSelected(targets);
    }

    @Override
    public double getSpeed () {
        return mySpeed.getData();
    }

}
