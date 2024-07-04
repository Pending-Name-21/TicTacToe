package com.tic_tac_toe;

import com.bridge.Game;
import com.bridge.core.exceptions.GameException;
import com.bridge.core.exceptions.renderHandlerExceptions.NonExistentFilePathException;
import com.bridge.initializerhandler.GameInitializer;
import com.bridge.gamesettings.AGameSettings;
import com.bridge.processinputhandler.InputVerifier;
import com.bridge.processinputhandler.KeyboardEventManager;
import com.bridge.renderHandler.render.RenderManager;
import com.bridge.renderHandler.repository.SoundRepository;
import com.bridge.renderHandler.repository.SpriteRepository;
import com.bridge.updatehandler.UpdatePublisher;

import java.util.List;


public class App {

    public static void main(String[] args) throws NonExistentFilePathException {
        /*Path socketPath = Path.of("/tmp/socket_console");
        SocketClient socketClient = new SocketClient(socketPath);
        Transmitter transmitter = new Transmitter(socketClient);

        try {
            Size size = new Size(50, 50);
            Sound sound = new Sound(Paths.get(""));
            sound.setPlaying(false);

            Coord position1 = new Coord(0, 0);
            Sprite sprite = new Sprite(position1, 0,size, Paths.get("/home/fundacion/University/Fifth/SoftwareDevelopment/images/x.jpg"));
            Frame frame = new Frame(List.of(sprite), List.of(sound));
            transmitter.send(frame);

            Coord position2 = new Coord(50, 50);
            sprite = new Sprite(position2, 0, size, Paths.get("/home/fundacion/University/Fifth/SoftwareDevelopment/images/o.png"));
            frame = new Frame(List.of(sprite), List.of(sound));
            transmitter.send(frame);

        } catch (RenderException e) {
            throw new RuntimeException(e);
        }*/

        runGame(makeGame(new SpriteRepository(), new SoundRepository()));


        /*SpriteRepository spriteRepository = new SpriteRepository();
        KeyboardEventManager keyboardSuscriber = new KeyboardEventManager();

        Board board = new Board(spriteRepository);
        BoardPosition boardPosition = new BoardPosition(board);
        BoardValidator validator = new BoardValidator(board);
        GameController gameController = new GameController(board, validator, boardPosition);*/
    }

    public static Game makeGame(SpriteRepository spriteRepository, SoundRepository soundRepository){
        KeyboardEventManager manager = new KeyboardEventManager();
//        AbstractListener listener = new AbstractListener();
//        manager.subscribe(listener);
        InputVerifier inputVerifier = new InputVerifier(List.of(manager));
        AGameSettings gameSettings = new AGameSettings() {
            @Override
            public boolean isGameOver() {
                return false;
            }
        };
        return new Game(inputVerifier, gameSettings, new UpdatePublisher(), new RenderManager(spriteRepository, soundRepository), new GameInitializer());
    }

    public static void runGame(Game game) {
        try {
            game.run();
        } catch (GameException e) {
            e.printStackTrace();
        }
    }
}