import java.util.ArrayList;
import java.util.Collections;

public class Node{

    private String currentState;
    private int indexOfBlank;
    private Node parent;
    private int depth;
    private double heuristic;

    public Node(String currentState, int indexOfBlank, Node parent, int depth) {
        this.currentState = currentState;
        this.indexOfBlank = indexOfBlank;
        this.depth = depth;
        this.parent = parent;
    }

    public Node(String currentState, int indexOfBlank, Node parent, int depth, double heuristic) {
        this.currentState = currentState;
        this.indexOfBlank = indexOfBlank;
        this.parent = parent;
        this.depth = depth;
        this.heuristic = heuristic;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 9; i+=3) {
            builder.append("| " + currentState.charAt(i) + " " +
                    currentState.charAt(i + 1) + " " + currentState.charAt(i + 2) + " |\n");
        }
        return "Current State =\n" + "-".repeat(8) + "\n" + builder.toString().replace("0", " ")
                + "-".repeat(8) + "\n";
    }

    public String getGameNode() {
        return currentState;
    }

    public int getDepth() {
        return this.depth;
    }

    public boolean isReachedGoal() {
        return this.currentState.equals("012345678");
    }

    // getPath method to get the path from the parent to deepest node
    public ArrayList<Node> getPath() {
        ArrayList<Node> myPath = new ArrayList<>();
        var myNode = this;
        // save every instance of node to the last one to get all pathes that crated
        while (myNode != null) {
            myPath.add(myNode);
            myNode = myNode.parent;
        }
        // reverse the order of my path to get the path from first node to the end
        Collections.reverse(myPath);
        return myPath;
    }

    private int getRow() {
        return this.indexOfBlank/3;
    }

    private int getCol() {
        return this.indexOfBlank%3;
    }

    public double getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(double heuristic) {
        this.heuristic = heuristic;
    }

    private Node goLeft() {
        char[] newState = this.currentState.toCharArray();
        newState[indexOfBlank] = newState[indexOfBlank-1];
        newState[indexOfBlank-1] = '0';
        return new Node(String.valueOf(newState), this.indexOfBlank-1, this, this.depth+1);
    }

    private Node goRight() {
        char[] newState = this.currentState.toCharArray();
        newState[indexOfBlank] = newState[indexOfBlank+1];
        newState[indexOfBlank+1] = '0';
        return new Node(String.valueOf(newState), this.indexOfBlank+1, this, this.depth+1);
    }

    private Node goUp() {
        char[] newState = this.currentState.toCharArray();
        newState[indexOfBlank] = newState[indexOfBlank-3];
        newState[indexOfBlank-3] = '0';
        return new Node(String.valueOf(newState), this.indexOfBlank-3, this, this.depth+1);
    }

    private Node goDown() {
        char[] newState = this.currentState.toCharArray();
        newState[indexOfBlank] = newState[indexOfBlank+3];
        newState[indexOfBlank+3] = '0';
        return new Node(String.valueOf(newState), this.indexOfBlank+3, this, this.depth+1);
    }

    // Get ArrayList of the children of the current Node resulting of the 4 possible
    // movements.
    public ArrayList<Node> getChildren() {
        ArrayList<Node> children = new ArrayList<>();
        if (this.getCol() != 0)
            children.add(goLeft());
        if (this.getCol() != 2)
            children.add(goRight());
        if (this.getRow() != 0)
            children.add(goUp());
        if (this.getRow() != 2)
            children.add(goDown());
        return children;
    }
}
