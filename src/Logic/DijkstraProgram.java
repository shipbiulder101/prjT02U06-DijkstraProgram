package Logic;

/**
 * Created by josh_000 on 13/02/2016.
 */

import graphics.DijkstraProgramGUI;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class DijkstraProgram {
    private DijkstraProgramGUI guiWrapper;
    private Map map;
    private List<Node> open, closed;

    public DijkstraProgram(int width, int height, int numWide, int numHeight) {
        guiWrapper = new DijkstraProgramGUI(width, height);
        map = new Map(numWide, numHeight, width, height);
        System.out.println(map);
        open = new ArrayList<Node>();
        closed = new ArrayList<Node>();
        calculate();
    }

    private void render() {
        Graphics brush = guiWrapper.createBrush();

        renderNode(brush);
        renderKeyNodes(brush);
        renderOpenList(brush);
        renderClosedList(brush);
    }

    private void renderNode(Graphics brush) {
        for (int x = 0; x < map.MAP_WIDTH; x++) {
            for (int y = 0; y < map.MAP_HEIGHT; y++) {
                brush.setColor(map.getNode(x, y).getDifficulty() == 0 ? Color.GRAY : Color.WHITE);
                brush.fillOval(map.getNode(x, y).getX(), map.getNode(x, y).getY(), map.NODE_WIDTH, map.NODE_HEIGHT);
                brush.setColor(Color.black);
                brush.drawOval(map.getNode(x, y).getX(), map.getNode(x, y).getY(), map.NODE_WIDTH, map.NODE_HEIGHT);
            }
        }
    }

    private void renderKeyNodes(Graphics brush) {
        brush.setColor(Color.cyan);
        brush.fillOval(map.getStartNode().getX(), map.getStartNode().getY(), map.NODE_WIDTH, map.NODE_HEIGHT);
        brush.setColor(Color.magenta);
        brush.fillOval(map.getEndNode().getX(), map.getEndNode().getY(), map.NODE_WIDTH, map.NODE_HEIGHT);
        brush.setColor(Color.black);
        brush.drawOval(map.getStartNode().getX(), map.getStartNode().getY(), map.NODE_WIDTH, map.NODE_HEIGHT);
        brush.drawOval(map.getEndNode().getX(), map.getEndNode().getY(), map.NODE_WIDTH, map.NODE_HEIGHT);
    }

    private void renderOpenList(Graphics brush){
        for(Node node : open){
            brush.setColor(Color.orange);
            brush.fillOval(node.getX(), node.getY(), map.NODE_WIDTH, map.NODE_HEIGHT);
            brush.setColor(Color.BLACK);
            brush.drawOval(node.getX(), node.getY(), map.NODE_WIDTH, map.NODE_HEIGHT);
        }
    }

    private void renderClosedList(Graphics brush){
        for(Node node : closed){
            brush.setColor(Color.yellow);
            brush.fillOval(node.getX(),node.getY(),map.NODE_WIDTH, map.NODE_HEIGHT);
            brush.setColor(Color.black);
            brush.drawOval(node.getX(), node.getY(), map.NODE_WIDTH, map.NODE_HEIGHT);
        }
    }

    private void calculate() {
        map.getStartNode().setMovementScore(0);
        open.add(map.getStartNode());
        boolean pathFound = false;
        while (!pathFound && open.size() > 0) {
            open = sortList(open);
            Node currentNode = open.get(0);
            closed.add(currentNode);
            if(Node.compare(currentNode, map.getEndNode())){
                break;
            }
            open.remove(0);
            List<Node> neighbours = map.findNeighbours(currentNode.getGridX(),currentNode.getGridY());
            for(Node neighbour : neighbours){
                boolean inClosed = closed.contains(neighbour) ? true:false;
                if(inClosed || neighbour.getDifficulty() == 0){
                    continue;
                }
                int openIndex = open.indexOf(neighbour);
                int moveCost = (currentNode.getGridX() == neighbour.getGridX() || currentNode.getGridY() == neighbour.getGridY() ? map.MOVEMENT_COST_STRAIGHT : map.MOVEMENT_COST_DIAGONAL);
                if(openIndex == -1){
                    int totalMovementCost = currentNode.getMovementScore() + moveCost;
                    neighbour.setMovementScore(totalMovementCost);
                    open.add(neighbour);
                }
                else{
                    int totalMovementCost = currentNode.getMovementScore() + moveCost;
                    if(totalMovementCost < neighbour.getMovementScore()){
                        neighbour.setMovementScore(totalMovementCost);
                        open.set(openIndex, neighbour);
                    }
                }
            }
            render();
        }
    }

    private List<Node> sortList(List<Node> list) {
        boolean sorting = true;
        while (sorting) {
            sorting = false;
            for (int i = 0; i < list.size() - 1; i++)
                if (list.get(i).getMovementScore() > list.get(i + 1).getMovementScore()) {
                    Node temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    sorting = true;
                }
        }

        return list;
    }
}


