package gameauthoring.creation.subforms.fire;

import engine.IGame;
import engine.definitions.concrete.SpriteDefinition;
import engine.definitions.moduledef.FirerDefinition;
import engine.definitions.moduledef.UserFirerDefinition;
import gameauthoring.creation.subforms.ISubFormView;

public class UserFireSFC extends RemovableFireSFC {

    public UserFireSFC (FiringSFC sfc) {
        super(sfc);
        // TODO Auto-generated constructor stub
    }

    public UserFireSFC (IGame myGame,
                        FiringSFC myFiringSFC,
                        UserFirerDefinition userFirerDefinition) {
        super(myFiringSFC);
    }

    @Override
    public void updateItem (SpriteDefinition item) {
        // TODO Auto-generated method stub

    }

    @Override
    public void initializeFields (SpriteDefinition item) {
        // TODO Auto-generated method stub

    }

    @Override
    public ISubFormView getSubFormView () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void populateViewsWithData (SpriteDefinition item) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public FirerDefinition getModuleDefinition () {
        // TODO Auto-generated method stub
        return null;
    }

}
