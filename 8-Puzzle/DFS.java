import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;



public class DFS {
	
	public Result DFSsolver(Node node) {
		if(node == null) {
			return null;
		}
		
		Stack<Node> frontier = new Stack<>();
		Set<String> frontier_names = new HashSet<String>();// stores strings of states those are in frontier stack to search in it in O(1)
		Set<String> visited = new HashSet<String>();
		
		double startTime = System.nanoTime();
		double endTime,runningTime;
		
		frontier.push(node);
		frontier_names.add(node.getGameNode());
		while(!frontier.isEmpty()) {
			Node currNode = frontier.pop();
			frontier_names.remove(currNode.getGameNode());
			visited.add(currNode.getGameNode());
			// test if goal is reached
			if(currNode.isReachedGoal()) {
				endTime = System.nanoTime();
                runningTime = endTime - startTime;
                Result result = new Result( visited.size() - 1,currNode.getPath().size(), runningTime, currNode);
                return result;
			}
			
			ArrayList<Node> children = currNode.getChildren();
			Collections.reverse(children);
			// put all children of current node in frontier if they are not visited and don't exist in frontier
			for (Node child : currNode.getChildren()) {
                if (!(visited.contains(child.getGameNode())) && !(frontier_names.contains(child.getGameNode()))) {
                    frontier.push(child);
                    frontier_names.add(child.getGameNode());
                }
            }
		}
		endTime = System.nanoTime();
		runningTime = endTime - startTime;
		return new Result(visited.size() - 1, -1 , runningTime, null);
	}

}
