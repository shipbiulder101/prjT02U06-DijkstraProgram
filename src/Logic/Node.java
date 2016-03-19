package Logic;

/**
 * Created by josh_000 on 13/02/2016.
 */
public class Node {
    private static int idCounter;
    private int x, y, gridX, gridY, difficulty, movementScore, id;

    public Node(int x, int y, int gridX, int gridY, int difficulty) {
        this.x = x;
        this.y = y;
        this.gridX = gridX;
        this.gridY = gridY;
        this.difficulty = difficulty;
        id = idCounter++;
    }

    public static boolean compare(Node nodel, Node node2){
        return nodel.id == node2.id ? true : false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getMovementScore() {
        return movementScore;
    }

    public void setMovementScore(int movementScore) {
        this.movementScore = movementScore;
    }

    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String toString() {
        return "{" + gridX + ", " + gridY + "} = (" + x + ", " + y + "), Diff" + difficulty;
    }

    @Override
    public boolean equals(Object obj){
        return obj instanceof Node ? (id == ((Node)obj ).id ? true : false) : false;
    }

    @Override
    public int hashCode(){
        return id;
    }
}
