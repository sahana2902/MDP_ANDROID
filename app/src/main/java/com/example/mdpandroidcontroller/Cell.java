package com.example.mdpandroidcontroller;

import android.graphics.Color;
import android.graphics.Paint;

public class Cell {

    float startX, startY, endX, endY;
    Paint paint;
    boolean highlighted = false;
    String type;
    int id = -1;

    //repeated - create a color thing
    private Paint black = new Paint();
    private Paint obstacleColor = new Paint();
    private Paint robotColor = new Paint();
    private Paint unexploredCellColor = new Paint();
    private Paint testColor = new Paint();
    private Paint exploredCellColor = new Paint();



    public Cell(float startX, float startY, float endX, float endY, Paint paint, String type) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.paint = paint;
        this.type = type;

        black.setStyle(Paint.Style.FILL_AND_STROKE);
        obstacleColor.setColor(Color.YELLOW);
        robotColor.setColor(Color.CYAN);
        testColor.setColor(Color.RED);
        unexploredCellColor.setColor(Color.parseColor("#eec9d2"));
        exploredCellColor.setColor(Color.WHITE);
    }
    public void setType(String type) {
        this.type = type;
        switch (type) {
            case "obstacle":
                this.paint = obstacleColor;
                break;
            case "robot":
                this.paint = robotColor;
                break;
            case "unexplored":
                this.paint = unexploredCellColor;
                break;
            case "explored":
                this.paint = exploredCellColor;
                break;
            case "test":
                this.paint = testColor;
                break;
            default:
                break;
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

    public boolean getHighlighted() {
        return this.highlighted;
    }


}
