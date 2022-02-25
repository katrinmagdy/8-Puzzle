import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Controller {

	@FXML
	private TextField input, indexOf0, indexOf1, indexOf2, indexOf3, indexOf4, indexOf5, indexOf6, indexOf7, indexOf8;

	@FXML
	private TextArea result;

	@FXML
	private ChoiceBox choice_box;

	@FXML
	private Button brn_next;

	private String str = "";
	private int algorithm = -1;
	private Result res = null;
	private int counter = 1;
	private ArrayList<Node> path = new ArrayList<Node>();

	@FXML
	public void onChoose(ActionEvent e) {
		algorithm = choice_box.getSelectionModel().getSelectedIndex();
		System.out.println(choice_box.getValue() + " is selected");
	}

	@FXML
	public void generateRandom(MouseEvent e) {
		//reset the data
		input.setText("");
//		algorithm = -1;
		res = null;
		counter = 1;
		path = new ArrayList<Node>();
		result.setText("");
		brn_next.setDisable(true);
		
		
		List<String> list = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8");
		Collections.shuffle(list);
		StringBuilder inputRandom = new StringBuilder();
		for (String s : list) {
			inputRandom.append(s);
		}
		str = inputRandom.toString();
		input.setText(str);
		updatePuzzle(str);
	}

	@FXML
	public void solve(MouseEvent e) {
		//reset the data
//		algorithm = -1;
		res = null;
		counter = 1;
		path = new ArrayList<Node>();
		result.setText("");
		brn_next.setDisable(true);
		
		String inputState = input.getText();
		if (validateInput(inputState)) {
			str = inputState;
			System.out.println(str);
			updatePuzzle(str);
		} else {
			Alert error = new Alert(Alert.AlertType.ERROR, "Invalid Input");
			error.show();
			return;
		}
		
		if (str != "" && algorithm != -1) { 
			if (algorithm == 0) {
				BFS bfs = new BFS();
				Node node = new Node(str, str.indexOf("0"), null, 0);
				res = bfs.bfsSolver(node);
			} else if (algorithm == 1) {
				DFS dfs = new DFS();
		        Node node = new Node(str, str.indexOf("0"),null, 0);
		        res=dfs.DFSsolver(node);
			} else if (algorithm == 2) {
				A_Star a_star = new A_Star(true);
				Node node = new Node(str, str.indexOf("0"), null, 0);
				res = a_star.A_star_solver(node);
			} else {
				A_Star a_star = new A_Star(false);
				Node node = new Node(str, str.indexOf("0"), null, 0);
				res = a_star.A_star_solver(node);
			}
			
			if(res.getDepth()!=-1) {
				brn_next.setDisable(false);
				path = res.getResultNode().getPath();
				result.setText(res.toString());
			}
			else {
				Alert error = new Alert(Alert.AlertType.ERROR, "GAME IS UNSOLVABLE" + "\nNodes Expanded = " + res.getNodesExpanded()
						+ "\nRunning Time = " +res.getRunningTime());
				error.show();
			}
			
			
		} else {
			Alert error = new Alert(Alert.AlertType.ERROR, "Enter a valid input & Choose an algorithm!");
			error.show();
		}
	}

	@FXML
	public void simulate(MouseEvent e) {
		if (res != null) { 
			if (counter < path.size()) {
				String state = path.get(counter++).getGameNode();
				updatePuzzle(state);
			} else {
				brn_next.setDisable(true);
			}
		} else {
			Alert error = new Alert(Alert.AlertType.ERROR, "Enter a valid input & Choose an algorithm!");
			error.show();
		}
	}

	private boolean validateInput(String input) {
		String regex = "[0-8]{9}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (!matcher.matches() || !isUnique(input)) {
			return false;
		}
		return true;
	}

	private static boolean isUnique(String input) {
		Set<Character> set = new HashSet<>();
		char[] characters = input.toCharArray();
		for (Character c : characters) {
			if (!set.add(c)) {
				return false;
			}
		}
		return true;
	}

	private void updatePuzzle(String str) {
		indexOf0.setText(String.valueOf(str.charAt(0)).equals("0")?"":String.valueOf(str.charAt(0)));
		indexOf1.setText(String.valueOf(str.charAt(1)).equals("0")?"":String.valueOf(str.charAt(1)));
		indexOf2.setText(String.valueOf(str.charAt(2)).equals("0")?"":String.valueOf(str.charAt(2)));
		indexOf3.setText(String.valueOf(str.charAt(3)).equals("0")?"":String.valueOf(str.charAt(3)));
		indexOf4.setText(String.valueOf(str.charAt(4)).equals("0")?"":String.valueOf(str.charAt(4)));
		indexOf5.setText(String.valueOf(str.charAt(5)).equals("0")?"":String.valueOf(str.charAt(5)));
		indexOf6.setText(String.valueOf(str.charAt(6)).equals("0")?"":String.valueOf(str.charAt(6)));
		indexOf7.setText(String.valueOf(str.charAt(7)).equals("0")?"":String.valueOf(str.charAt(7)));
		indexOf8.setText(String.valueOf(str.charAt(8)).equals("0")?"":String.valueOf(str.charAt(8)));
	}

}
