package dev.just.explorethebuild.start;

import dev.just.explorethebuild.utils.Config;

public class IsStarted {
    public static boolean isStarted = Config.getBoolean("started", false);
}
