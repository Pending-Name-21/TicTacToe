package com.tic_tac_toe;

import com.tic_tac_toe.Library.inputsuscription.EventType;
import com.tic_tac_toe.Library.inputsuscription.IProcessInputSubscriber;

public class BoardPosition implements IProcessInputSubscriber {

    private int x;
    private int y;

    public BoardPosition() {
        x = 0;
        y = 0;
    }

    public int getXPosition (){
        return x;
    }
    public int getYPosition (){
        return y;
    }

    @Override
    public void notify(EventType eventType) {
        switch (eventType.getName()){
            case "Up" : { y += 1; break;}
            case "Down" : { y -= 1; break;}
            case "Left" : {  x += 1; break;}
            case "Right" : {  x -= 1; break;}
            default: break;
        }
    }
}
