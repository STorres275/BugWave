import javax.sound.sampled.*;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.io.IOException;

import static org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent.*;
import static org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType.KEY_PRESSED;

public class Game implements KeyboardHandler {
    private LevelGrid levelGrid;
    private Cadet cadet;
    private Lvl1 lvl1;
    private Keyboard menuKeyboard;
    private Picture menuPicture;
    private Picture gameOver;
    private Picture winner;
    private Sound sound;

    private boolean menuStart;


    public Game() {
        this.menuStart = true;
    }

    public void keyboardMoves(){
        menuKeyboard = new Keyboard(this);

        KeyboardEvent space = new KeyboardEvent();
        space.setKey(KEY_SPACE);
        space.setKeyboardEventType(KEY_PRESSED);
        menuKeyboard.addEventListener(space);

        KeyboardEvent end = new KeyboardEvent();
        end.setKey(KEY_R);
        end.setKeyboardEventType(KEY_PRESSED);
        menuKeyboard.addEventListener(end);

        KeyboardEvent win = new KeyboardEvent();
        win.setKey(KEY_Y);
        win.setKeyboardEventType(KEY_PRESSED);
        menuKeyboard.addEventListener(win);

        KeyboardEvent close = new KeyboardEvent();
        close.setKey(KEY_Q);
        close.setKeyboardEventType(KEY_PRESSED);
        menuKeyboard.addEventListener(close);
    }
    public void play() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        keyboardMoves();
        this.sound = new Sound("musictrack.wav");
        this.sound.play();

        menuPicture = new Picture(0, 0, "gameStart.png");
        gameOver = new Picture(0, 0, "gameOver.png");
        winner = new Picture(0, 0, "youWon.png");

        while (true) {
            while (menuStart) {
                menuPicture.draw();
            }
            menuPicture.delete();
            gameInit();
            while (lvl1.isGameRunning()) {
                gameStart();
            }
            while (lvl1.isEnd()) {
                gameOver.draw();
            }
            gameOver.delete();
            while (lvl1.isHasWon()) {
                winner.draw();
            }
            winner.delete();
            System.gc();
        }
    }

    public void gameInit() {
        levelGrid = new LevelGrid();
        cadet = new Cadet();
        lvl1 = new Lvl1(cadet, levelGrid);
        lvl1.createBug();
        lvl1.drawBugs();
    }

    public void gameStart() {
        try {
            lvl1.attack();
            lvl1.win();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.getKey() == KEY_SPACE) {
            menuStart = false;
        }
        if (keyboardEvent.getKey() == KEY_R) {
            lvl1.setEnd(false);
        }
        if (keyboardEvent.getKey() == KEY_Y) {
            lvl1.setHasWon(false);
        }
        if (keyboardEvent.getKey() == KEY_Q) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
    }
}
