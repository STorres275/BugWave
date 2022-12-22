import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Lvl1 {
    private Cadet cadet;
    private int numberOfBugs = 28;
    private int bugRefX = 14 * LevelGrid.CELLSIZE;
    private LevelGrid levelGrid;
    private boolean isGameRunning;
    private boolean hasWon;
    private boolean isEnd;



    public Lvl1(Cadet cadet, LevelGrid level) {
        this.cadet = cadet;
        levelGrid = level;
        isGameRunning = true;
        hasWon=false;
        isEnd=false;
    }

    public boolean isGameRunning() {
        return isGameRunning;
    }

    public boolean isHasWon() {
        return hasWon;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    private Bug[] bugsArray = new Bug[numberOfBugs];

    public void createBug() {
        for (int i = 0; i < numberOfBugs; i++) {
            if (i % 2 == 0) {
                bugsArray[i] = new Bug(new Picture(bugRefX, 2 * LevelGrid.CELLSIZE, "bug1.png"), true);
                bugRefX += 1 * LevelGrid.CELLSIZE;//2

            } else {
                bugsArray[i] = new Bug(new Picture(bugRefX, 18 * LevelGrid.CELLSIZE, "bug2.png"), false);
                bugRefX += 1 * LevelGrid.CELLSIZE;//3
            }
        }
    }

    public void drawBugs() {
        for (Bug a : bugsArray) {
            a.getModel().draw();
        }
    }

    public void attack() throws InterruptedException {
        for (Bug bug : bugsArray) {
            if (bug.isMovement()) {
                bug.getModel().translate(0, 2);
                if (bug.getModel().getY() == 18 * LevelGrid.CELLSIZE) {
                    bug.setMovement(false);
                }
            } else {
                bug.getModel().translate(0, -2);
                if (bug.getModel().getY() == 2 * LevelGrid.CELLSIZE) {
                    bug.setMovement(true);
                }
            }
        }
        Thread.sleep(2);
        collision();
    }

    public void collision() {
        for (Bug bug : bugsArray) {
            if ((cadet.getCadet().getMaxX() > bug.getModel().getX() && cadet.getCadet().getX() < bug.getModel().getMaxX()) && (cadet.getCadet().getMaxY() > bug.getModel().getY() && cadet.getCadet().getY() < bug.getModel().getMaxY())){
                cadet.getCadet().translate(10 * LevelGrid.CELLSIZE - cadet.getCadet().getX(), 10 * LevelGrid.CELLSIZE - cadet.getCadet().getY());
                cadet.setHealth(cadet.getHealth()-1);
                switch (cadet.getHealth()){
                    case 3:
                        levelGrid.getHealth().load("lifeBar3.png");
                        break;
                    case 2:
                        levelGrid.getHealth().load("lifeBar2.png");
                        break;
                    case 1:
                        levelGrid.getHealth().load("lifeBar1.png");
                        break;
                    case 0:
                        isGameRunning = false;
                        isEnd=true;
                        break;
                }
            }
        }
    }
    public void win(){
        if((cadet.getCadet().getX() == (47 * LevelGrid.CELLSIZE) - LevelGrid.CELLSIZE) && (cadet.getHealth() > 0)) {
            isGameRunning = false;
            hasWon = true;
        }
    }
}
