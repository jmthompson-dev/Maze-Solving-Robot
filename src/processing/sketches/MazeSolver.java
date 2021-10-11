package processing.sketches;

import processing.core.PApplet;
import processing.core.PVector;

import static processing.core.PApplet.*;
import static processing.core.PConstants.PI;


public class MazeSolver {
    private static final PApplet p = Main.sketch;

    private PVector position;
    private PVector distanceSensors;

    private float width = 40;
    private float height = 30;
    private float angle;
    private float targetAngle;

    //Define the Distance enum here
    public enum Direction {
        RIGHT, DOWN, LEFT, UP
    }

    public MazeSolver(PVector position, Direction direction) {
        this.position = position;

        angle = direction.ordinal() * PI/2;
        targetAngle = angle;
        distanceSensors = new PVector(100,100);
        getSensorDistance();
    }

    public void drawSelf() {
        p.pushMatrix();

            p.translate(position.x, position.y);
            p.rotate((float)angle);

            p.fill(255);
            p.stroke(0);
            //body
            p.rect(0, 0, width, height);

            //wheels
            p.rect(-width/2+6, -height/2-2, 12, 4);
            p.rect(-width/2+6, height/2+2, 12, 4);
            p.rect(width/2-6, -height/2-2, 12, 4);
            p.rect(width/2-6, height/2+2, 12, 4);

            //sensors
            p.fill(0);
            p.rect(20, 0, 5, 15);
            p.rect(10, -10, 15, 5);

            getSensorDistance();
            p.stroke(255,0,0);
            p.line(20, 0, 20 + distanceSensors.x, 0);
            p.line(10, -10, 10, -10-distanceSensors.y);

        p.popMatrix();

        p.fill(255);
        p.text(distanceSensors.x, 300, 60);
        p.text(distanceSensors.y, 300, 25);
    }

    public void move() {
        if (crashCheck()) return;
        if (smoothTurning()) return;

        // Put your maze-solving logic below this line:
        if (distanceSensors.x > 10) {
            moveForward();
        } else if (distanceSensors.y > 50) {
            turnRight();
        } else {
            turnLeft();
        }
    }



    private void turnLeft() {
        turn(Direction.LEFT);
    }

    private void turnRight() {
        turn(Direction.RIGHT);
    }


    public void moveForward() {
        position.add(PVector.fromAngle(angle));
    }

    public void turn(Direction dir) {
        targetAngle = (angle + (dir.ordinal() - 1) * PI / 2) % (2 * PI);
    }

    boolean smoothTurning() {
        if (angle != targetAngle) {
            float a = (targetAngle - angle) % (2 * PI);
            float da = (2 * a % (2 * PI) - a) > 0 ? 1 : -1;
            if (abs(a) > da * PI / 60.0f) {
                angle += da * PI / 60.0f;
            } else {
                angle = targetAngle;
            }
        }
        return angle != targetAngle;
    }

    public PVector getPosition() {
        return position;
    }

    private void getSensorDistance() {
        boolean hit = false;

        float theta = angle;
        float phi = PI/2.0f - theta;

        for (int i = 0; i <= 1; i++) {
            hit = false;
            for (int dist = 0; dist <= 600; dist++) {

                PVector origin;
                PVector ray;

                if (i == 0) {
                    origin = new PVector(20 * cos(theta), 20 * sin(theta));
                    ray = new PVector(dist * cos(theta), dist * sin(theta));
                } else {
                    origin = new PVector(10 * (cos(theta) + cos(phi)), 10 * (sin(theta) - sin(phi)));
                    ray = new PVector(dist * cos(phi), -dist * sin(phi));
                }

                PVector point = ray.add(origin).add(position);
                for (Wall wall : Wall.objects) {
                    if (wall.containsPoint(point)) {
                        hit = true;
                        break;
                    }
                }
                if (hit) {
                    if (i == 0) distanceSensors.x = (float) dist;
                    if (i == 1) distanceSensors.y = (float) dist;
                    break;
                }
            }
        }
    }

    public boolean crashCheck() {
        for (Wall wall : Wall.objects) {
            PVector extents = PVector.div(wall.getSize(),2);
            PVector cartExtents = PVector.div(new PVector(width, height), 2).rotate(angle);
            float theta = (float)Math.atan2(height, width);

            for (int i = 0; i <= 1; i++) {
                for (int j = 0; j <= 2; j+=2) {
                    PVector newCartExtents = new PVector(cartExtents.x, cartExtents.y);
                    newCartExtents.rotate(i*PI - j*theta);

                    PVector point = PVector.add(new PVector(newCartExtents.x, newCartExtents.y), position);

                    if (wall.containsPoint(point)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void setPosition(PVector position) {
        this.position = position;
    }

    public PVector getDistanceSensors() {
        return distanceSensors;
    }

    public void setDistanceSensors(PVector distanceSensors) {
        this.distanceSensors = distanceSensors;
    }
}
