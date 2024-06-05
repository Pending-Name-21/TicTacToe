package com.tic_tac_toe;
import com.bridge.inputsuscription.EventType;
import com.bridge.inputsuscription.KeyboardSuscriber;
import com.bridge.piece.Coord;
import com.bridge.piece.Size;

// import bridge.piece.Sprite;

public class App {


    public static void main(String[] args) {
        KeyboardSuscriber keyboardSuscriber = new KeyboardSuscriber();
        BoardPosition boardPosition = new BoardPosition();
        BoardValidator validator = new BoardValidator();
        Board board = new Board(new Coord(0,0),new Size(3,3),"app/src/main/java/com/tic_tac_toe/Images/board/EmptyBoard.png");
        GameController gameController = new GameController(board,validator,boardPosition);
        keyboardSuscriber.suscribe(new EventType("Up"),boardPosition);
        keyboardSuscriber.suscribe(new EventType("Down"),boardPosition);
        keyboardSuscriber.suscribe(new EventType("Left"),boardPosition);
        keyboardSuscriber.suscribe(new EventType("Right"),boardPosition);
        keyboardSuscriber.suscribe(new EventType("Enter"), gameController);
    }

   }
