package me.xurround.mlock.misc;

import java.nio.file.Path;
import java.nio.file.Paths;

public class IOHelper
{
    public static Path getWorkingDirectoryPath()
    {
        return (Paths.get("").toAbsolutePath());
    }
}
