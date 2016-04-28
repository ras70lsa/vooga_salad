package gameauthoring.creation.subforms;

import engine.definitions.concrete.AttributeDefinition;
import engine.definitions.concrete.SpriteDefinition;
import javafx.beans.property.BooleanProperty;

/**
 * Defines necessary information methods to update an Upgrade Module Definition Properly
 * 
 * @author Joe Lilien
 *
 */

public interface IUpgradeSFV  extends ISubFormView{

    BooleanProperty isUpgradableProperty ();

    BooleanProperty isGlobalProperty ();

    void setIsUpgradable (boolean isSelected);

    SpriteDefinition getNextUpgrade ();

    AttributeDefinition getDepletedAttribute ();

    String getMyCostKey ();

    void setDepletedAttribute (AttributeDefinition attributeDefinition);

    void setNextUpgrade (SpriteDefinition upgrade);
}
