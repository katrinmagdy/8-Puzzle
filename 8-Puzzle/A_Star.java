import java.util.*;

public class A_Star {
    //Boolean indicating the heuristic function used
    boolean manhattan;

    //Constructor
    public A_Star(boolean manhattanHeuristic) {
        this.manhattan = manhattanHeuristic;
    }

    //Main driver function for the algorithm
    public Result A_star_solver(Node initialState){
        if (initialState == null)
            return null;
        double startTime = System.nanoTime();
        double endTime,runningTime;
        HashSet<String> visited = new HashSet<>();
        PriorityQueue<Node> frontier = new PriorityQueue<Node>(new NodeComparator());
        frontier.add(initialState);
        while (!frontier.isEmpty()) {
            Node currentNode = frontier.poll();
            visited.add(currentNode.getGameNode());
            if (currentNode.isReachedGoal()) {
                endTime = System.nanoTime();
                runningTime = endTime - startTime;
                Result result = new Result( visited.size() - 1,
                        currentNode.getPath().size(), runningTime, currentNode);
                return result;
            }
            for (Node child : currentNode.getChildren()) {
                if (!visited.contains(child.getGameNode())) {
                    child.setHeuristic(heuristicFunction(child.getGameNode()) + child.getDepth());
                    frontier.add(child);
                }
            }
        }
		endTime = System.nanoTime();
		runningTime = endTime - startTime;
		return new Result(visited.size() - 1, -1 , runningTime, null);
    }

    // Mapping the heuristic function
    private double heuristicFunction(String currentState){
        double distance = 0;
        for(int i = 0 ; i < 9 ; i++) {
            int currentX = i/3;
            int currentY = i%3;
            int goalX = (currentState.charAt(i)-48)/3;
            int goalY = (currentState.charAt(i)-48)%3;
            if(manhattan)
                distance += manhattanHeuristic(currentX,currentY,goalX,goalY);
            else
                distance += euclideanHeuristic(currentX,currentY,goalX,goalY);
        }
        return distance;
    }
    //Calculating manhattan heuristic given state string
    private double manhattanHeuristic(int currentX, int currentY , int goalX, int goalY){
        return Math.abs((currentX-goalX)) + Math.abs((currentY-goalY));
    }
    //Calculating euclidean heuristic given state string
    private double euclideanHeuristic(int currentX, int currentY , int goalX, int goalY){
        return Math.sqrt( (Math.pow((currentX-goalX),2) + (Math.pow((currentY-goalY),2)) ));
    }
}

class NodeComparator implements Comparator<Node> {
    @Override
    public int compare(Node x, Node y) {
        if (x.getHeuristic() < y.getHeuristic()) {
            return -1;
        }
        if (x.getHeuristic() > y.getHeuristic()) {
            return 1;
        }
        return 0;
    }
}
