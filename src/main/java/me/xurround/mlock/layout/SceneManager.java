package me.xurround.mlock.layout;

import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import me.xurround.mlock.misc.LayoutLoader;
import me.xurround.mlock.misc.enums.TransitionType;

import java.io.IOException;
import java.util.HashMap;

public class SceneManager
{
    private final Scene mainScene;

    private final HashMap<String, Parent> layouts;

    public SceneManager(Stage stage, int width, int height, Parent defaultRoot)
    {
        layouts = new HashMap<>();
        mainScene = new Scene(defaultRoot, width, height);
        mainScene.setFill(Paint.valueOf("#313335"));
        stage.setScene(mainScene);
    }

    public Scene getScene()
    {
        return mainScene;
    }

    public void addLayout(String ...layoutNames)
    {
        try
        {
            for (String lName : layoutNames)
                layouts.put(lName, LayoutLoader.load(lName));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public boolean setLayout(String layoutName, TransitionType transitionType)
    {
        if (!layouts.containsKey(layoutName))
            return false;
        Parent layout = layouts.get(layoutName);
        Duration duration = Duration.millis(500);
        Transition transition;
        switch (transitionType)
        {
            case FADE:
                FadeTransition ft = new FadeTransition(duration, layout);
                ft.setFromValue(0.0);
                ft.setToValue(1.0);
                transition = ft;
                break;
            default:
                transition = null;
                break;
        }
        mainScene.setRoot(layout);
        if (transition != null)
            transition.play();
        return true;
    }

    public void setLayout(String layoutName)
    {
        setLayout(layoutName, TransitionType.NONE);
    }
}
