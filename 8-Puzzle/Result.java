public class Result {

	private int nodesExpanded;
	private int depth;
	private double runningTime;
	private Node resultNode;

	public Result(int nodesExpanded, int depth, double runningTime, Node node) {
		this.nodesExpanded = nodesExpanded;
		this.depth = depth;
		this.runningTime = runningTime;
		this.resultNode = node;
	}

	@Override
	public String toString() {
		return "---------- GAME SOLVED ----------" + "\nNodes Expanded = " + nodesExpanded + "\nSearch Depth = " + depth
				+ "\nRunning Time = " + String.format("%.5f", (this.runningTime) / 1_000_000_000) + " Sec";
	}

	public int getNodesExpanded() {
		return this.nodesExpanded;
	}

	public int getDepth() {
		return depth;
	}

	public String getRunningTime() {
		return String.format("%.5f", (this.runningTime) / 1_000_000_000) + " Sec";
	}

	public Node getResultNode() {
		return resultNode;
	}
}
