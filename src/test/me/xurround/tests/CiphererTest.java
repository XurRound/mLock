package me.xurround.tests;

import me.xurround.mlock.logic.crypto.Cipherer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CiphererTest
{
    @Test
    public void passwordToKeyTest()
    {
        byte[] test = Cipherer.passwordToKey("123");
        byte[] ref = {3, 1, 51, 49, 50, 51, 49, 50, 51, 49, 50, 51, 49, 50, 51, 49};
        Assertions.assertArrayEquals(test, ref);
    }
}
