package engine;

import java.util.function.DoublePredicate;
import engine.interactionevents.KeyIOEvent;
import engine.interactionevents.MouseIOEvent;
import javafx.beans.property.ObjectProperty;
import util.TimeDuration;


public class OnSpriteAttributeCondition implements ICondition {

    private IGame myGame;
    private AttributeType myAttributeType;
    private DoublePredicate myValueCheck;

    private IEventPackage mySpritePackage;
    private IEventPackage myOtherPackage;
    private IEventPackage myGlobalPackage;

    public OnSpriteAttributeCondition (IGame game,
                                       AttributeType attributeType,
                                       DoublePredicate valueCheck,
                                       IEventPackage spritePackage,
                                       IEventPackage otherPackage,
                                       IEventPackage globalPackage) {
        myGame = game;
        myAttributeType = attributeType;
        myValueCheck = valueCheck;
        mySpritePackage = spritePackage;
        myOtherPackage = otherPackage;
        myGlobalPackage = globalPackage;

    }

    @Override
    public void update (TimeDuration duration) {

        myGame.getLevelManager().getCurrentLevel().get().getSprites().stream()
                .filter(sprite -> mySpritePackage.getTargetedSpriteGroup()
                        .contains(sprite.getType().get()))
                .forEach(sprite -> sprite.getAttributes().stream()
                        .filter(atty -> atty.get().getType().equals(myAttributeType))
                        .forEach(atty -> checkAttribute(atty)));
    }

    private void checkAttribute (ObjectProperty<IAttribute> atty) {
        if (myValueCheck.test(atty.get().getValueProperty().get())) {
            mySpritePackage.getMyEffects().forEach(effect -> atty.get().applyEffect(effect));
            myOtherPackage.getMyEffects().forEach(
                                                  effect -> myGame.getLevelManager()
                                                          .getCurrentLevel().get()
                                                          .getSprites().stream()
                                                          .filter(sprite -> myOtherPackage
                                                                  .getTargetedSpriteGroup()
                                                                  .contains(sprite.getType().get()))
                                                          .forEach(sprite -> sprite
                                                                  .applyEffect(effect)));
            myGlobalPackage.getMyEffects()
                    .forEach(effect -> myGame.getAttributeManager().applyEffect(effect));
            myGlobalPackage.getMyEffects()
                    .forEach(effect -> myGame.getLevelManager().getCurrentLevel().get()
                            .getAttributeManager().get().applyEffect(effect));
        }
    }

    @Override
    public void registerKeyEvent (KeyIOEvent keyEvent) {
        // do nothing
    }

    @Override
    public void registerMouseEvent (MouseIOEvent mouseEvent) {
        // do nothing
    }

}
