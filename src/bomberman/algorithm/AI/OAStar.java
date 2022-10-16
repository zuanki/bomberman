package bomberman.algorithm.AI;

import java.util.*;
import java.util.function.BiFunction;

public class OAStar {
    private Node[][] searchArea;
    private PriorityQueue<Node> openList;
    private Set<Node> closedSet;
    private Node initialNode;
    private Node finalNode;

    private int[][] board;

    private BiFunction<Vector2<Integer>, Vector2<Integer>, Integer> heuristic = Heuristic::euclidean;


    public OAStar(int[][] board, Node initialNode, Node finalNode) {
        setInitialNode(initialNode);
        setFinalNode(finalNode);
        this.board = board;
        int rows = board.length;
        int cols = board[0].length;
        this.searchArea = new Node[rows][cols];
        this.openList = new PriorityQueue<>(Comparator.comparingInt(Node::getF));
        setNodes();
        this.closedSet = new HashSet<>();
    }

/*    public static void main(String[] args) {
        Node initialNode = new Node(2, 1);
        Node finalNode = new Node(2, 5);
        int[][] board = new int[][]{
                {1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1, 1, 1, 0, 1, 1},
                {1, 1, 1, 0, 1, 1, 0, 1, 0, 1},
                {0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
                {1, 1, 1, 0, 1, 1, 1, 0, 1, 0},
                {1, 0, 1, 1, 1, 1, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
                {1, 1, 1, 0, 0, 0, 1, 0, 0, 1}};
        OAStar aStar = new OAStar(board, initialNode, finalNode);
        aStar.setBlocks(board);
        List<Node> path = aStar.findPath();
        for (Node node : path) {
            System.out.println(node);
        }
    }*/

    public Vector2<Integer> nextMoveByAStar() {
        List<Node> r = this.findPath();
        if (r.size() == 0) {
            Vector2<Integer> re = this.initialNode.getPos();
            return new Vector2<>(re.y, re.x);
        } else {
            Vector2<Integer> tmp = r.get(1).getPos();
            return new Vector2<>(tmp.y, tmp.x);
        }
    }

    public void setHeuristic(BiFunction<Vector2<Integer>, Vector2<Integer>, Integer> heuristic) {
        this.heuristic = heuristic;
    }

    private void setNodes() {
        for (int i = 0; i < searchArea.length; i++) {
            for (int j = 0; j < searchArea[0].length; j++) {
                Node node = new Node(i, j);
                node.calculateHeuristic(getFinalNode(), this.heuristic);
                this.searchArea[i][j] = node;
            }
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBlocks(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 0) {
                    setBlock(i, j);
                }
            }
        }
    }

    public List<Node> findPath() {
        openList.add(initialNode);
        while (!isEmpty(openList)) {
            Node currentNode = openList.poll();
            closedSet.add(currentNode);
            assert currentNode != null;
            if (isFinalNode(currentNode)) {
                return getPath(currentNode);
            } else {
                addAdjacentNodes(currentNode);
            }
        }
        return new ArrayList<Node>();
    }

    private List<Node> getPath(Node currentNode) {
        List<Node> path = new ArrayList<Node>();
        path.add(currentNode);
        Node parent;
        while ((parent = currentNode.getParent()) != null) {
            path.add(0, parent);
            currentNode = parent;
        }
        return path;
    }

    private void addAdjacentNodes(Node currentNode) {
        addAdjacentUpperRow(currentNode);
        addAdjacentMiddleRow(currentNode);
        addAdjacentLowerRow(currentNode);
    }

    private void addAdjacentLowerRow(Node currentNode) {
        int row = currentNode.getPos().x;
        int col = currentNode.getPos().y;
        int lowerRow = row + 1;
        if (lowerRow < getSearchArea().length) {
            if (col - 1 >= 0) {
                checkNode(currentNode, col - 1, lowerRow); // Comment this line if diagonal movements are not allowed
            }
            if (col + 1 < getSearchArea()[0].length) {
                checkNode(currentNode, col + 1, lowerRow); // Comment this line if diagonal movements are not allowed
            }
            checkNode(currentNode, col, lowerRow);
        }
    }

    private void addAdjacentMiddleRow(Node currentNode) {
        int row = currentNode.getPos().x;
        int col = currentNode.getPos().y;
        if (col - 1 >= 0) {
            checkNode(currentNode, col - 1, row);
        }
        if (col + 1 < getSearchArea()[0].length) {
            checkNode(currentNode, col + 1, row);
        }
    }

    private void addAdjacentUpperRow(Node currentNode) {
        int row = currentNode.getPos().x;
        int col = currentNode.getPos().y;
        int upperRow = row - 1;
        if (upperRow >= 0) {
            if (col - 1 >= 0) {
                checkNode(currentNode, col - 1, upperRow); // Comment this if diagonal movements are not allowed
            }
            if (col + 1 < getSearchArea()[0].length) {
                checkNode(currentNode, col + 1, upperRow); // Comment this if diagonal movements are not allowed
            }
            checkNode(currentNode, col, upperRow);
        }
    }

    private void checkNode(Node currentNode, int col, int row) {
        Node adjacentNode = getSearchArea()[row][col];
        if (!adjacentNode.isBlock() && !getClosedSet().contains(adjacentNode)) {
            if (!getOpenList().contains(adjacentNode)) {
                adjacentNode.setNodeData(currentNode);
                getOpenList().add(adjacentNode);
            } else {
                boolean changed = adjacentNode.checkBetterPath(currentNode);
                if (changed) {
                    // Remove and Add the changed node, so that the PriorityQueue can sort again its
                    // contents with the modified "finalCost" value of the modified node
                    getOpenList().remove(adjacentNode);
                    getOpenList().add(adjacentNode);
                }
            }
        }
    }

    private boolean isFinalNode(Node currentNode) {
        return currentNode.equals(finalNode);
    }

    private boolean isEmpty(PriorityQueue<Node> openList) {
        return openList.size() == 0;
    }

    private void setBlock(int row, int col) {
        this.searchArea[row][col].setBlock(true);
    }

    public Node getInitialNode() {
        return initialNode;
    }

    public void setInitialNode(Node initialNode) {
        this.initialNode = initialNode;
    }

    public Node getFinalNode() {
        return finalNode;
    }

    public void setFinalNode(Node finalNode) {
        this.finalNode = finalNode;
    }

    public Node[][] getSearchArea() {
        return searchArea;
    }

    public void setSearchArea(Node[][] searchArea) {
        this.searchArea = searchArea;
    }

    public PriorityQueue<Node> getOpenList() {
        return openList;
    }

    public void setOpenList(PriorityQueue<Node> openList) {
        this.openList = openList;
    }

    public Set<Node> getClosedSet() {
        return closedSet;
    }

    public void setClosedSet(Set<Node> closedSet) {
        this.closedSet = closedSet;
    }
}
