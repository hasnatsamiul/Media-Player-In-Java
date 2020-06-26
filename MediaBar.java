package application;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * Created by abrar on 12/01/15.
 */
public class MediaBar extends HBox{

    Pane pane=new Pane();

    Slider time=new Slider();
    Slider vol=new Slider();

    Button playbutton=new Button("||");

    Label volume=new Label("Volume: ");

    MediaPlayer player;


    public  MediaBar(MediaPlayer play) {
        player = play;
       // pane=pane1;

        setAlignment(Pos.CENTER);
        setPadding(new Insets(5, 10, 5, 10));

        vol.setPrefWidth(70);
        vol.setMinWidth(30);
        vol.setValue(70);

        HBox hbox=new HBox(2);
        hbox.setHgrow(time, Priority.ALWAYS);


        getChildren().add(playbutton);
        getChildren().add(time);
        getChildren().add(volume);
        getChildren().add(vol);


        pane.getChildren().add(hbox);

        playbutton.setOnAction(event -> {
            MediaPlayer.Status status = player.getStatus();

            if (status == MediaPlayer.Status.PLAYING) {
                if (player.getCurrentTime().greaterThanOrEqualTo(player.getTotalDuration())) {
                    player.seek(player.getStartTime());
                    player.play();
                } else {
                    player.pause();
                    playbutton.setText(">");
                }
            }
            if (status == MediaPlayer.Status.PAUSED || status == MediaPlayer.Status.HALTED || status == MediaPlayer.Status.STOPPED) {
                player.play();
                playbutton.setText("||");
            }
        });

        player.currentTimeProperty().addListener(observable -> {
            updatesValue();
        });

        time.valueProperty().addListener(observable -> {
            if(time.isPressed())
            {
                player.seek(player.getMedia().getDuration().multiply(time.getValue()/100));
            }
        });

        vol.valueProperty().addListener(observable -> {
            if(vol.isPressed())
            {
                player.setVolume(vol.getValue()/100);
            }
        });
    }

    protected void updatesValue()
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                time.setValue(player.getCurrentTime().toSeconds()/player.getTotalDuration().toSeconds()*100);
            }
        });
    }
}