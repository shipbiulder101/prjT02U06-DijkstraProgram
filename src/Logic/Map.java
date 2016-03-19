package Logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by josh_000 on 13/02/2016.
 */
public class Map {
    public final int MAP_WIDTH;
    public final int MAP_HEIGHT;
    public final int NODE_WIDTH;
    public final int NODE_HEIGHT;
    public final int MOVEMENT_COST_STRAIGHT = 10;
    public final int MOVEMENT_COST_DIAGONAL = 14;

    public Node[][] grid;
    public Node startNode;
    public Node endNode;

    public Map(int numWide, int numHeight, int displayWidth, int displayHeight) {
        grid = new Node[numWide][numHeight];
        MAP_WIDTH = numWide;
        MAP_HEIGHT = numHeight;
        NODE_WIDTH = displayWidth / numWide;
        NODE_HEIGHT = displayHeight / numHeight;
        init();
    }

    private void init(){
        Random rand = new Random();
        for (int x = 0; x < MAP_WIDTH; x++) {
            for (int y = 0; y < MAP_HEIGHT; y++) {
                grid[x][y] = new Node(x * NODE_WIDTH, y * NODE_HEIGHT, x, y, rand.nextInt(100) < 80 ? 1 : 0);
            }
        }

        while ((startNode = grid[rand.nextInt(MAP_WIDTH)][rand.nextInt(MAP_HEIGHT)]).getDifficulty() == 0)
            continue;
        while ((endNode = grid[rand.nextInt(MAP_WIDTH)][rand.nextInt(MAP_HEIGHT)]).getDifficulty() == 0)
            continue;
    }



    public Node getNode(int x, int y) {
        return grid[x][y];
    }

    public Node getStartNode(){
        return startNode;
    }

    public Node getEndNode(){
        return endNode;
    }

    @Override
    public String toString() {
        String mapString = "";
        for (int x = 0; x < MAP_WIDTH; x++) {
            for (int y = 0; y < MAP_HEIGHT; y++) {
                mapString += grid[x][y] + "\t";
            }
            mapString += "\n";
        }
        return mapString;
    }

    public List<Node> findNeighbours(int gridX, int gridY){
        List<Node> neighbours = new ArrayList<Node>();
        for(int x = gridX -1; x <= gridX + 1; x++){
            for(int y = gridY - 1; y <= gridY + 1; y++) {
                if (x < 0 || x >= MAP_WIDTH || y < 0 || y >= MAP_HEIGHT){
                    continue;
                }
                neighbours.add(grid[x][y]);
            }

        }
        return neighbours;
    }

}
