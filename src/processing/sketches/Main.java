package processing.sketches;

import processing.core.PApplet;
import processing.core.PVector;

public class Main extends PApplet {
    public static PApplet sketch;
    private MazeSolver mazeSolver;

    public void settings() {
        sketch = this;
        size(576, 576);
    }

    public void setup() {
        mazeSolver = new MazeSolver(new PVector(96, 32), MazeSolver.Direction.DOWN);
        createMaze();
        rectMode(CENTER);
    }

    public void draw() {
        background(255);
        Wall.objects.forEach(wall -> wall.drawSelf());
        mazeSolver.move();
        mazeSolver.drawSelf();
    }

    public void createMaze() {
        new Wall(new PVector(32, 160),  new PVector(64, 320));
        new Wall(new PVector(352, 32),  new PVector(448, 64));
        new Wall(new PVector(256, 160), new PVector(384, 64));
        new Wall(new PVector(160, 320), new PVector(64, 128));
        new Wall(new PVector(288, 288), new PVector(64, 192));
        new Wall(new PVector(448, 288), new PVector(128, 64));
        new Wall(new PVector(544, 320), new PVector(64, 512));
        new Wall(new PVector(96, 480),  new PVector(192, 192));
        new Wall(new PVector(352, 416), new PVector(192, 64));
        new Wall(new PVector(352, 544), new PVector(320, 64));
    }

    public static void main(String... args) {
        PApplet.main("processing.sketches.Main");
    }
}
