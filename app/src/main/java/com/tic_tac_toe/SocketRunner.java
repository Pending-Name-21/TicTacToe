package com.tic_tac_toe;

import com.bridge.ipc.Receiver;
import com.bridge.ipc.SocketServer;
import com.bridge.processinputhandler.InputVerifier;
import com.bridge.processinputhandler.KeyboardEventManager;
import com.bridge.processinputhandler.MouseEventManager;
import com.tic_tac_toe.Listener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class SocketRunner {

    public static final Path NAMESPACE =
            Path.of(System.getProperty("java.io.tmpdir"), "test-events-socket.sock");

    public static void main(String[] args) {
        KeyboardEventManager keyboardEventManager = new KeyboardEventManager();
        Listener listener = new Listener();
        keyboardEventManager.subscribe(listener);
        MouseEventManager mouseEventManager = new MouseEventManager();
        Receiver receiver = new Receiver();
        receiver.addBuffer(keyboardEventManager);
        receiver.addBuffer(mouseEventManager);

        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        Thread thread = startServer(receiver, atomicBoolean);

        listener.startConnection();

        InputVerifier verifier = new InputVerifier(List.of(keyboardEventManager));

        while (true) {
            verifier.check();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static Thread startServer(Receiver receiver, AtomicBoolean atomicBoolean) {
        try {
            Files.deleteIfExists(NAMESPACE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SocketServer socketServer = new SocketServer(receiver, NAMESPACE, atomicBoolean);
        Thread thread = new Thread(socketServer);
        thread.start();
        while (!Files.exists(NAMESPACE)) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                thread.interrupt();
            }
        }
        return thread;
    }
}
