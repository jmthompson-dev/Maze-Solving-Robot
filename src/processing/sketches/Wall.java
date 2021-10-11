package processing.sketches;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class Wall {
    public static final List<Wall> objects = new ArrayList<>();
    public static final PApplet p = Main.sketch;

    private PVector position;
    private PVector size;

    public Wall(PVector position, PVector size) {
        this.position = position;
        this.size = size;
        objects.add(this);
    }

    public boolean containsPoint(PVector point) {
        return (point.x >= position.x - size.x/2 &&
                point.x <= position.x + size.x/2 &&
                point.y >= position.y - size.y/2 &&
                point.y <= position.y + size.y/2);
    }

    public void drawSelf() {
        p.fill(0);
        p.noStroke();
        p.rect(position.x, position.y, size.x, size.y);
    }

    public PVector getSize() {
        return size;
    }

    public PVector getPosition() {
        return position;
    }
}
