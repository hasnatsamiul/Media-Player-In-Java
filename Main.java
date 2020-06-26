package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;

/**
 * Created by abrar on 12/01/15.
 */
public class Main extends Application {
    @Override
    public void start(final Stage stage) throws Exception {
        Player[] player = {null};
        FileChooser fileChooser;
        Pane pane=new Pane();
        pane.setStyle("-fx-background-color: black;");
        ImageView imageView=new ImageView("file:///home/abrar/IdeaProjects/newmovie1/pic/logo.png");
        imageView.setX(500);
        imageView.setY(300);

        MenuItem open=new MenuItem("open");
        Menu file=new Menu("file");
        MenuBar menu=new MenuBar();

        file.getItems().add(open);
        menu.getMenus().add(file);

        fileChooser=new FileChooser();

        open.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //player[0].player.pause();
                File file1=fileChooser.showOpenDialog(stage);

                if(file1!=null)
                {
                    try
                    {
                        player[0] =new Player(file1.toURI().toURL().toExternalForm());
                        player[0].setTop(menu);
                        Scene scene=new Scene(player[0],1080,720,Color.BLACK);
                        stage.setScene(scene);
                        stage.show();
                        //player[0].player.pause();
                    }catch (MalformedURLException e1)
                    {
                        e1.fillInStackTrace();
                    }
                }
            }
        });

        stage.setTitle("Basic mediaplayer");


        pane.getChildren().add(imageView);
        pane.getChildren().add(menu);
        Scene scene = new Scene(pane, 1080, 720, Color.VIOLET);
        //scene.setFill(Color.BURLYWOOD);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {launch(args);}

}