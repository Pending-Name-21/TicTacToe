package com.tic_tac_toe;
import com.tic_tac_toe.Library.game.Game;
import com.tic_tac_toe.Library.inputsuscription.EventType;
import com.tic_tac_toe.Library.inputsuscription.KeyboardSuscriber;
import com.tic_tac_toe.Library.piece.Coord;
import com.tic_tac_toe.Library.piece.Size;

// import bridge.piece.Sprite;

public class App {


    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        KeyboardSuscriber keyboardSuscriber = new KeyboardSuscriber();
        BoardPosition boardPosition = new BoardPosition();
        BoardValidator validator = new BoardValidator();
        Board board = new Board(new Coord(0,0),new Size(3,3),"app/src/main/java/com/tic_tac_toe/Images/Board.png");
        GameController gameController = new GameController(board,validator,boardPosition);
        keyboardSuscriber.suscribe(new EventType("Up"),boardPosition);
        keyboardSuscriber.suscribe(new EventType("Down"),boardPosition);
        keyboardSuscriber.suscribe(new EventType("Left"),boardPosition);
        keyboardSuscriber.suscribe(new EventType("Right"),boardPosition);
        keyboardSuscriber.suscribe(new EventType("Enter"), gameController);
    }

   }
