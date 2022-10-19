package me.xurround.mlock.misc.skin;

import javafx.scene.control.TextField;
import javafx.scene.control.skin.TextFieldSkin;

public class PasswordTextField extends TextFieldSkin
{
    public static final char BULLET_CHAR = '\u2022';

    public PasswordTextField(TextField textField)
    {
        super(textField);
    }

    @Override
    protected String maskText(String txt)
    {
        if (getSkinnable() == null)
            return "";
        return String.valueOf(BULLET_CHAR).repeat(getSkinnable().getLength());
    }
}
