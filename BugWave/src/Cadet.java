import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import static org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent.*;
import static org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType.*;

public class Cadet implements KeyboardHandler {
    private Picture cadet;
    private Keyboard keyboard;

    private int health;

    public Cadet() {
        keyboard = new Keyboard(this);
        cadet = new Picture(10 * LevelGrid.CELLSIZE, 10 * LevelGrid.CELLSIZE,"Cadet.png");
        cadet.draw();
        health = 4;
        addMovement();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void addMovement() {
        KeyboardEvent moveLeft = new KeyboardEvent();
        moveLeft.setKey(KEY_LEFT);
        moveLeft.setKeyboardEventType(KEY_PRESSED);
        keyboard.addEventListener(moveLeft);

        KeyboardEvent moveRight = new KeyboardEvent();
        moveRight.setKey(KEY_RIGHT);
        moveRight.setKeyboardEventType(KEY_PRESSED);
        keyboard.addEventListener(moveRight);

        KeyboardEvent moveUp = new KeyboardEvent();
        moveUp.setKey(KEY_UP);
        moveUp.setKeyboardEventType(KEY_PRESSED);
        keyboard.addEventListener(moveUp);

        KeyboardEvent MoveDown = new KeyboardEvent();
        MoveDown.setKey(KEY_DOWN);
        MoveDown.setKeyboardEventType(KEY_PRESSED);
        keyboard.addEventListener(MoveDown);
    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {
            case KEY_LEFT:
                if (cadet.getX() > 10 * LevelGrid.CELLSIZE) {
                    cadet.translate(-LevelGrid.CELLSIZE, 0);
                }
                break;

            case KEY_RIGHT:
                if (cadet.getX() < (47 * LevelGrid.CELLSIZE) - LevelGrid.CELLSIZE) {
                    cadet.translate(+LevelGrid.CELLSIZE, 0);
                }
                break;

            case KEY_UP:
                if (cadet.getY() > 2 * LevelGrid.CELLSIZE) {
                    cadet.translate(0, -LevelGrid.CELLSIZE);
                }
                break;

            case KEY_DOWN:
                if (cadet.getY() < (19 * LevelGrid.CELLSIZE) - LevelGrid.CELLSIZE) {
                    cadet.translate(0, +LevelGrid.CELLSIZE);
                }
                break;
        }
    }


    @Override
    public void keyReleased(KeyboardEvent key) {}

    public Picture getCadet() {
        return cadet;
    }
}
