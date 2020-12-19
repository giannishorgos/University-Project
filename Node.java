import java.util.ArrayList;

public class Node {
    private Node parent;
    public ArrayList<Node> children;
    private int nodeDepth;
    private int[] nodeMove;
    private Board nodeBoard;
    private double nodeEvaluation;

    public Node() {
        parent = null;
        nodeEvaluation = 0;
        nodeDepth = 0;
        nodeMove = new int[3];
    }

    public Node(Node parent, Board nodeBoard, int x, int y, int dice) {
        this.parent = parent;
        this.nodeBoard = nodeBoard;
        nodeMove = new int[] {x, y, dice};

        nodeEvaluation = 0;
        nodeDepth = 0;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getNodeDepth() {
        return nodeDepth;
    }

    public void setNodeDepth(int nodeDepth) {
        this.nodeDepth = nodeDepth;
    }

    public int[] getNodeMove() {
        return nodeMove;
    }

    public void setNodeMove(int[] nodeMove) {
        this.nodeMove = nodeMove;
    }

    public Board getNodeBoard() {
        return nodeBoard;
    }

    public void setNodeBoard(Board nodeBoard) {
        this.nodeBoard = nodeBoard;
    }

    public double getNodeEvaluation() {
        return nodeEvaluation;
    }

    public void setNodeEvaluation(double nodeEvaluation) {
        this.nodeEvaluation = nodeEvaluation;
    }
}
