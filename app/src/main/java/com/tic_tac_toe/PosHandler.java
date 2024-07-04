package com.tic_tac_toe;

import CoffeeTime.InputEvents.Keyboard;
import com.bridge.core.exceptions.renderHandlerExceptions.NonExistentFilePathException;
import com.bridge.core.exceptions.renderHandlerExceptions.RenderException;
import com.bridge.ipc.Transmitter;
import com.bridge.processinputhandler.IEventSubscriber;
import com.bridge.renderHandler.builders.SpriteBuilder;
import com.bridge.renderHandler.render.Frame;
import com.bridge.renderHandler.repository.SpriteRepository;
import com.bridge.renderHandler.sprite.Sprite;

import java.util.ArrayList;
import java.util.List;

public class PosHandler implements IEventSubscriber<Keyboard> {

    private Transmitter transmitter;

    public PosHandler(Transmitter transmitter) {
        this.transmitter = transmitter;
    }

    @Override
    public void doNotify(Keyboard keyboard) {
        if (transmitter != null) {
            if (keyboard.type().equals("KeyPressed") && keyboard.key().equals("S")) {
                System.out.println("___________________________RECEIVED___________________________");
                SpriteRepository squareRepository = new SpriteRepository();
                SpriteBuilder builder = new SpriteBuilder(squareRepository);
                List<Sprite> sprites = new ArrayList<>();
                builder.buildSize(600, 800);
                builder.buildCoord(0, 0);
                try {
                    builder.buildPath(Utils.BASE_PATH + "/board/selectedCells/Board-21.png");
                } catch (NonExistentFilePathException e) {
                    throw new RuntimeException(e);
                }
                builder.assemble();
                try {
                    transmitter.send(new Frame(squareRepository.retrieve(), List.of()));
                } catch (RenderException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

}
