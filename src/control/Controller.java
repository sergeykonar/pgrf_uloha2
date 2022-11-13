package control;

import view.Panel;

public abstract class Controller {

    protected boolean isDrawing = true;
    protected boolean isFilling = false;
    void initListeners(Panel panel){}

    protected void changeState(){
        if (isDrawing) {
            isFilling = true;
            isDrawing = false;
        }else {
            isFilling = false;
            isDrawing = true;
        }
    }

}
