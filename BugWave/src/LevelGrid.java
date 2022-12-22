import org.academiadecodigo.simplegraphics.pictures.Picture;

public class LevelGrid {
    private Picture ideBackground;

    private Picture health;

    public static final int CELLSIZE = 40;

    public LevelGrid(){
        ideBackground = new Picture(0, 0, "ideBackground.png");
        ideBackground.draw();

        health = new Picture(2 * CELLSIZE, 7 * CELLSIZE, "lifeBar4.png");
        health.draw();
    }

    public Picture getIdeBackground() {
        return ideBackground;
    }

    public void setIdeBackground(Picture ideBackground) {
        this.ideBackground = ideBackground;
    }

    public Picture getHealth() {
        return health;
    }
}
