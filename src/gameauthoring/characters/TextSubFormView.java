package gameauthoring.characters;

import engine.ISprite;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

public class TextSubFormView implements SubFormView{
    private String myLabel;
    private SubFormController myController;
    private HBox myContainer = new HBox(20);
    private TextField myTextInput = new TextField();
    private boolean isNumberData;
    
    public TextSubFormView (String label, SubFormController controller, boolean isNumberData) {
        this.myController = controller;
        this.myLabel = label;
        this.isNumberData = isNumberData;
        myContainer.getChildren().add(new Label(myLabel));
        myContainer.getChildren().add(myTextInput);
    }

    @Override
    public void update () {
        // TODO Auto-generated method stub
        
    }

    @Override
    public FormData getData () {
        String key = myLabel;
        String value = myTextInput.getText();
        if(isNumberData){
            checkForAndThrowError(value);
            return null;
        }        
        return null;
    }

    private void checkForAndThrowError (String value) {
        try{
            Double.parseDouble(value);
        }
        catch(IllegalArgumentException e){
            ErrorMessage err = new ErrorMessage(myLabel + " Value Must Be a Double!");
            err.showError();
        }
    }

    @Override
    public void populateWithData (ISprite s) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public SubFormController getSubFormController () {
        return this.myController;
    }

    @Override
    public void setSubFormController (SubFormController controller) {
        this.myController = controller;
    }

    @Override
    public Node draw () {
        return myContainer;
    }

}
