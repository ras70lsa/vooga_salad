package gameauthoring.creation.subforms.costs;

import engine.AuthorshipData;
import engine.definitions.concrete.AttributeDefinition;
import gameauthoring.creation.entryviews.NumberEntryView;
import gameauthoring.creation.entryviews.SingleChoiceEntryView;
import gameauthoring.creation.subforms.SubFormView;
import gameauthoring.tabs.AuthoringView;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class CostSubFormView extends SubFormView {
    private static final int SPACING = 5;
    
    private String myAttributeChoicesKey = "Resource Required: ";
    private String myCostKey = "Amount to buy sprite: ";
    private SingleChoiceEntryView<AttributeDefinition> myAttributes;
    private NumberEntryView myCost;
    private HBox myContainer;
    
    public CostSubFormView (AuthorshipData data) {
        myAttributes =
                new SingleChoiceEntryView<AttributeDefinition>(myAttributeChoicesKey,
                                                               data.getMyCreatedGlobals()
                                                                       .getItems(),
                                                               AuthoringView.DEFAULT_ENTRYVIEW);
        myCost =
                new NumberEntryView(myCostKey, super.getData(), 60, 20,
                                    AuthoringView.DEFAULT_ENTRYVIEW);
        initView();
    }

    @Override
    public Node draw () {
        return myContainer;
    }

    @Override
    protected void initView () {
        myContainer = new HBox(SPACING);
        myContainer.getChildren().add(myAttributes.draw());
        myContainer.getChildren().add(myCost.draw());
    }
    
    public AttributeDefinition getSelectedAttribute () {
        return myAttributes.getSelected();
    }
    
    public String getCostKey () {
        return myCostKey;
    }

}