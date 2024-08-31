package tiles.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tiles.model.Direction;
import tiles.model.Observer;
import tiles.model.TilesModel;

import java.util.List;

public class TilesGUI extends Application implements Observer<TilesModel, String> {
TilesModel Model=null;
    GridPane gp=null;
    FlowPane statusbox=null;
    VBox buttonbox=null;
    @Override
    public void init() {
        // TODO
        List<String> args = getParameters().getRaw();
        this.Model=new TilesModel(args.getFirst());
        Model.addObserver(this);
    }

    /**
     * Construct the layout for the game.
     *
     * @param primaryStage container (window) in which to render the GUI
     */
    @Override
    public void start(Stage primaryStage) {
        // TODO
        BorderPane bp=new BorderPane();
        Label moves=new Label("Moves: "+this.Model.getMovesMade());
        Label status=new Label("Status: "+this.Model.getGameStatus());
        Label score=new Label("Score: "+this.Model.getScore());
        Label best_score=new Label("Best score: "
        +this.Model.getBestScore());
        this.statusbox=new FlowPane(moves,status,score,best_score);
        bp.setTop(statusbox);
        this.statusbox.setHgap(70.5);
        this.gp=new GridPane();
        for(int row=0;row<this.Model.DIM;row++){
            for(int col=0;col<this.Model.DIM;col++){
                gp.add(
                new Button(String.valueOf(this.Model.getContent(row,col))),
                col,row);
            }
        }
        bp.setCenter(gp);
        Button newgame=new Button("New Game");
        Button up=new Button("^");
        Button down=new Button("V");
        Button left=new Button("<");
        Button right=new Button(">");
        this.buttonbox=new VBox(up,down,left,right,newgame);
        bp.setRight(buttonbox);
        newgame.setOnAction(event->Model.newGame());
        up.setOnAction(event -> Model.move(Direction.NORTH));
        down.setOnAction(event -> Model.move(Direction.SOUTH));
        left.setOnAction(event -> Model.move(Direction.WEST));
        right.setOnAction(event -> Model.move(Direction.EAST));
        primaryStage.setScene(new Scene(bp));
        primaryStage.show();
    }

    /**
     * Called by the model, model.TilesModel, whenever there is a state change
     * that needs to be updated by the GUI.
     *
     * @param model the TilesModel
     * @param message the status message sent by the model
     */
    @Override
    public void update(TilesModel model, String message) {
        // TODO
        /*
        First, update all the grid tiles by functions in TilesModel:
        + First, take gp.getChildren().get(row*length+col+1) - a node - and
        change the button to whatever the model tells the GUI at that time by:
      - Type cast the above node to button and set the text of the button.
      First, get the children from the flowpane statusbox, and cast all the elements
      to labels.
        Next, update all the statuses on top. In this step, go from
        index 0 to 3 and update each label accordingly.
        */
        for(int row=0;row<model.DIM;row++){
            for(int col=0;col<model.DIM;col++){
                Button content=(Button)gp.getChildren().get(row*model.DIM+col);
                content.setText(String.valueOf(model.getContent(row,col)));
            }
        }

        Label moves = (Label) statusbox.getChildren().get(0);
        Label status = (Label) statusbox.getChildren().get(1);
        Label score = (Label) statusbox.getChildren().get(2);
        Label bestscore = (Label) statusbox.getChildren().get(3);
        moves.setText("Moves: "+(model.getMovesMade()));
        status.setText("Status: "+message);
        score.setText("Score: "+model.getScore());
        bestscore.setText("Best score: "+model.getBestScore());
    }

    /**
     * This method is called before the application shuts down.
     * It provides a convenient place to save any information about the game
     * before closing the application.
     */
    @Override
    public void stop() {
        // TODO
        this.Model.shutdown();
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: TilesGUI <TEST|EASY|NORMAL>");
            System.exit(0);
        }
        Application.launch(args);
    }
}
