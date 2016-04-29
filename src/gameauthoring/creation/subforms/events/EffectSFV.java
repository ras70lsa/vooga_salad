package gameauthoring.creation.subforms.events;

import java.util.ResourceBundle;
import splash.LocaleManager;
import engine.AuthorshipData;
import engine.definitions.concrete.AttributeDefinition;
import engine.profile.ProfileDisplay;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.creation.subforms.fire.RemoveOption;
import gameauthoring.tabs.AuthoringView;
import gameauthoring.util.ProfileDisplayIterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;


/**
 * Implementation of IEffectSFV using HBox arrangement, allows users to define an effect package
 * 
 * @author Tommy
 * @author Joe Lilien
 *
 */
public class EffectSFV extends SubFormView implements IEffectSFV {

    private SingleChoiceEntryView<AttributeDefinition> myAttribute;
    private SingleChoiceEntryView<ProfileDisplay> myEffectType;
    private NumberEntryView myLength;
    private NumberEntryView myValue;
    private ResourceBundle myLabel;
    private String myLengthKey;
    private String myValueKey;
    private String myAttributeKey;
    private String myTypeKey;
    private HBox myContainer;
    private RemoveOption myRemove;


    public EffectSFV (AuthorshipData data,
                      ObservableList<ProfileDisplay> effectTypes, RemoveOption remove) {
        setResourceBundleAndKey();
        myRemove = remove;
        myEffectType = new SingleChoiceEntryView<ProfileDisplay>(myTypeKey,
                                                                 effectTypes,
                                                                 AuthoringView.DEFAULT_ENTRYVIEW);

        ObservableList<AttributeDefinition> definitions = createAttributeList(data);
        myAttribute =
                new SingleChoiceEntryView<AttributeDefinition>(myAttributeKey,
                                                               definitions,
                                                               AuthoringView.DEFAULT_ENTRYVIEW);
        myValue =
                new NumberEntryView(myValueKey, 100, 30,
                                    AuthoringView.DEFAULT_ENTRYVIEW);
        myLength =
                new NumberEntryView(myLengthKey, 100, 30,
                                    AuthoringView.DEFAULT_ENTRYVIEW);
        initView();
    }

    private void setResourceBundleAndKey () {
        myLabel = ResourceBundle.getBundle("languages/labels", LocaleManager
                .getInstance().getCurrentLocaleProperty().get());
        myLengthKey = myLabel.getString("LengthKey");
        myValueKey = myLabel.getString("ValueKey");
        myAttributeKey = myLabel.getString("AttributeKey");
        myTypeKey = myLabel.getString("TypeKey");
    }

    private ObservableList<AttributeDefinition> createAttributeList (AuthorshipData data) {
        ObservableList<AttributeDefinition> defs = FXCollections.observableArrayList();
        defs.addAll(data.getMyCreatedAttributes().getItems());
        defs.addAll(data.getMyCreatedGlobals().getItems());
        return defs;
    }

    @Override
    protected void initView () {
        myContainer =
                getMyUIFactory().makeHBox(10, Pos.CENTER, myEffectType.draw(), myAttribute.draw(),
                                          myValue.draw(), myLength.draw(), myRemove.draw());
    }

    @Override
    public AttributeDefinition getAttribute () {
        return myAttribute.getSelected();
    }

    @Override
    public String getEffectType () {
        return myEffectType.getSelected().getProfile().getName().get();
    }

    @Override
    public double getValue () {
        return myValue.getData();
    }

    @Override
    public double getLength () {
        return myLength.getData();
    }

    @Override
    public void populateWithData (String type,
                                  AttributeDefinition attrbute,
                                  double value,
                                  double length) {
        myEffectType.setSelected(new ProfileDisplayIterator()
                .matchStringtoProfile(myEffectType.getItems(), type));
        myAttribute.setSelected(attrbute);
        myValue.setData(value);
        myLength.setData(length);
    }

    @Override
    public Node draw () {
        return myContainer;
    }

}
