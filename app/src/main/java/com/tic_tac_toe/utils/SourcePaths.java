package com.tic_tac_toe.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

public class SourcePaths {

    public static final Path RELATIVE_APP_PATH = Paths.get("app/src/main/resources");

    public static final Path ABSOLUTE_APP_PATH = RELATIVE_APP_PATH.toAbsolutePath();

    public static final String BASE_PATH = ABSOLUTE_APP_PATH.toString();

}
