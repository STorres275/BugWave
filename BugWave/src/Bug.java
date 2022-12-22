import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Bug {
    private Picture model;
    private boolean movement;

    public Bug(Picture model, boolean movement) {
        this.model = model;
        this.movement = movement;
    }

    public Picture getModel() {
        return model;
    }

    public boolean isMovement() {
        return movement;
    }

    public void setMovement(boolean movement) {
        this.movement = movement;
    }
}
