package bomberman.algorithm.AI;

import java.util.function.BiFunction;

public class Node {
    private int f;
    private int g; // cost from start node to current node
    private int h; // cost from current node to target node
    // We need optimize f(Node) = g(Node) + h(Node)
    private Vector2<Integer> pos; // position of node (board coordinate) (row and col)
    private boolean isBlock;
    private Node parent;

    public Node(int row, int col) {
        super();
        this.pos = new Vector2<>(row, col);
    }

    public Vector2<Integer> getPos() {
        return pos;
    }

    public void setPos(Vector2<Integer> pos) {
        this.pos = pos;
    }

    public void calculateHeuristic(Node finalNode, BiFunction<Vector2<Integer>, Vector2<Integer>, Integer> heuristic) {
        this.h = heuristic.apply(this.pos, finalNode.pos);
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public boolean isBlock() {
        return isBlock;
    }

    public void setBlock(boolean block) {
        isBlock = block;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setNodeData(Node currentNode) {
        int gCost = currentNode.g;
        this.setParent(currentNode);
        this.setG(gCost);
        this.calculateFinalCost();
    }

    public boolean checkBetterPath(Node currentNode) {
        int gCost = currentNode.g;
        if (gCost < g) {
            this.setNodeData(currentNode);
            return true;
        }
        return false;
    }

    private void calculateFinalCost() {
        int finalCost = g + h;
        this.setF(finalCost);
    }

    @Override
    public boolean equals(Object o) {
        Node other = (Node) o;
        return this.pos.equals(other.pos);
    }

    @Override
    public String toString() {
        return "Node [row=" + this.pos.x + ", col=" + this.pos.y + "]";
    }
}
