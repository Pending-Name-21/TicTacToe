package com.tic_tac_toe;

public enum WinnerLine {

    ROW (new String[]{
            Utils.BASE_PATH.concat("/assets/specificLines/HTopLine.png"),
            Utils.BASE_PATH.concat("/assets/specificLines/HMidLine.png"),
            Utils.BASE_PATH.concat("/assets/specificLines/HBottomLine.png")
    }),
    COLUMN (new String[] {
            Utils.BASE_PATH.concat("/assets/specificLines/VLeftLine.png"),
            Utils.BASE_PATH.concat("/assets/specificLines/VMidLine.png"),
            Utils.BASE_PATH.concat("/assets/specificLines/VRightLine.png")
    }),
    DIAGONAL (new String[] {
            Utils.BASE_PATH.concat("/assets/general/Diagonal_Line1.png"),
            Utils.BASE_PATH.concat("/assets/general/Diagonal_Line2.png")
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
