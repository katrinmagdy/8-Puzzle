import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	public static void main(String[] args) throws Exception {
		System.out.println("8-Puzzle Game Starts!");
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("8puzzle-2.fxml"));
		arg0.setTitle("8-Puzzle Game");
		arg0.setScene(new Scene(root, 450, 650));
		arg0.show();
	}
}
