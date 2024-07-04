package com.tic_tac_toe.model.winner;

import com.tic_tac_toe.utils.SourcePaths;

public enum WinnerLine {

    ROW(new String[]{
            SourcePaths.BASE_PATH.concat("/assets/specificLines/HTopLine.png"),
            SourcePaths.BASE_PATH.concat("/assets/specificLines/HMidLine.png"),
            SourcePaths.BASE_PATH.concat("/assets/specificLines/HBottomLine.png")
    }),
    COLUMN(new String[]{
            SourcePaths.BASE_PATH.concat("/assets/specificLines/VLeftLine.png"),
            SourcePaths.BASE_PATH.concat("/assets/specificLines/VMidLine.png"),
            SourcePaths.BASE_PATH.concat("/assets/specificLines/VRightLine.png")
    }),
    DIAGONAL(new String[]{
            SourcePaths.BASE_PATH.concat("/assets/general/Diagonal_Line1.png"),
            SourcePaths.BASE_PATH.concat("/assets/general/Diagonal_Line2.png")
    });

    private final String[] paths;

    WinnerLine(String[] paths) {
        this.paths = paths;
    }

    public String[] getPaths() {
        return paths;
    }

    public String getPath(int index) {
        return paths[index];
    }
}
