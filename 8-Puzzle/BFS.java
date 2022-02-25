import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;

public class BFS {

	public Result bfsSolver(Node node) {
		if (node == null) {
			return null;
		}

		HashSet<String> visited = new HashSet<>();
		double startTime = System.nanoTime();
		double endTime, runningTime;
		Queue<Node> frontier = new ArrayDeque<>();
		HashSet<String> frontierNames = new HashSet<>(); // stores strings of states those are in frontier queue to search in it in O(1)

		frontier.add(node);
		frontierNames.add(node.getGameNode());

		while (!frontier.isEmpty()) {
			Node currentNode = frontier.poll();
			frontierNames.remove(currentNode.getGameNode());
			visited.add(currentNode.getGameNode());
			// test if goal is reached
			if (currentNode.isReachedGoal()) { 
				endTime = System.nanoTime();
				runningTime = endTime - startTime;
				Result result = new Result(visited.size() - 1, currentNode.getPath().size(), runningTime, currentNode);
				return result;
			}
			// put all children of current node in frontier if they are not visited and don't exist in frontier
			for (Node child : currentNode.getChildren()) {
				if (!visited.contains(child.getGameNode()) && !frontierNames.contains(child.getGameNode())) {
					frontier.add(child);
					frontierNames.add(child.getGameNode());
				}
			}
		}
		endTime = System.nanoTime();
		runningTime = endTime - startTime;
		return new Result(visited.size() - 1, -1, runningTime, null);
	}
}

