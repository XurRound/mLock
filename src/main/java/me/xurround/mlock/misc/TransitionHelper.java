package me.xurround.mlock.misc;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class TransitionHelper
{
    public static FadeTransition getFade(Node applyTo, Duration duration, float fromValue, float toValue)
    {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(duration);
        fadeTransition.setFromValue(fromValue);
        fadeTransition.setToValue(toValue);
        fadeTransition.setNode(applyTo);
        return fadeTransition;
    }

    public static TranslateTransition getTranslate(Node applyTo, Duration duration, float fromValue, float toValue)
    {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(duration);
        translateTransition.setFromX(fromValue);
        translateTransition.setToX(toValue);
        translateTransition.setNode(applyTo);
        return translateTransition;
    }
}
