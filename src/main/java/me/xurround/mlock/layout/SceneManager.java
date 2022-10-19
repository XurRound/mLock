package me.xurround.mlock.layout;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import me.xurround.mlock.misc.LayoutLoader;
import me.xurround.mlock.misc.TransitionHelper;
import me.xurround.mlock.misc.enums.AppScene;
import me.xurround.mlock.misc.enums.TransitionType;

import java.io.IOException;
import java.util.Arrays;

public class SceneManager
{
    private final Scene mainScene;

    private final Pane basePane;

    private Duration duration;

    public SceneManager(Stage stage, int width, int height)
    {
        basePane = new StackPane();
        mainScene = new Scene(basePane, width, height);
        mainScene.setFill(Paint.valueOf("#1F1F1F"));
        stage.setScene(mainScene);
        duration = Duration.millis(400);
    }

    public Scene getScene()
    {
        return mainScene;
    }

    public void setDuration(Duration duration)
    {
        this.duration = duration;
    }

    public void setLayout(AppScene scene, TransitionType transitionType)
    {
        if (!Arrays.asList(AppScene.class.getEnumConstants()).contains(scene))
            return;
        try
        {
            Parent layout = LayoutLoader.load(scene.name().toLowerCase());
            Node oldLayout = basePane.getChildren().size() > 0 ? basePane.getChildren().get(0) : basePane;
            Transition in, out;
            switch (transitionType)
            {
                case FADE:
                    in = TransitionHelper.getFade(layout, duration, 0, 1);
                    out = TransitionHelper.getFade(oldLayout, duration, 1, 0);
                    break;
                case SLIDE_LEFT:
                    in = TransitionHelper.getTranslate(layout, duration, -800, 0);
                    out = TransitionHelper.getTranslate(oldLayout, duration, 0, 800);
                    break;
                case SLIDE_RIGHT:
                    in = TransitionHelper.getTranslate(layout, duration, 800, 0);
                    out = TransitionHelper.getTranslate(oldLayout, duration, 0, -800);
                    break;
                default:
                    in = null;
                    out = null;
                    break;
            }
            if (in != null)
            {
                in.setInterpolator(Interpolator.EASE_BOTH);
                out.setInterpolator(Interpolator.EASE_BOTH);
                basePane.getChildren().add(layout);
                out.setOnFinished(e -> basePane.getChildren().remove(oldLayout));
                in.play();
                out.play();
            }
            else
            {
                basePane.getChildren().clear();
                basePane.getChildren().add(layout);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void setLayout(AppScene scene)
    {
        setLayout(scene, TransitionType.NONE);
    }
}
