# Maze Solving Robot

This project is a simulation of a beginner robotics project where
students program a robotic car two find its way through a maze.

The robot has two driving wheels and can `moveForward` or `turn` 90
degrees to the `Left` or to the `Right`.

The robot also has two infrared `distanceSensors` one which points
forwards and one which points to the left.

The maze itself is actually not technically a maze. It is a labyrinth.
The difference being that a labyrinth does not contain any dead-ends.
Additionally, this labyrinth only contains 90 degree angles.

This means that the robot must either turn 90 degrees to the left or
to the right whenever it encounters a wall, and move forward all
other times.

## Starter Code

Much of the starter code is some nasty trigonometry, which you can
absolutely check out if you are curious, but is not necessary.

What you do need to know is that the `turn` method takes a `Direction`
enum- which does not yet exist. Know that when you eventually do 
create this enum, the order of the enumerables matters. **Right, Down,
Left, Up. Clockwise from RIGHT.**

You will be writing the logic for the `MazeSolver` in the `move()` 
method. This method also has the two following guard clauses, which must come before
any other movement logic:

```java
if (crashCheck()) return
```

This line cancels the `move` method if the robot collides with a wall.
(This is considered a failure condition).

```java
if (smoothTurning()) return;
```

This is a method to make the simulation look nicer. The purpose is to
allow the robot to slowly rotate when turning. It must not move during
this time and so the `move` method is temporarily halted.

# Your Task

First create an enum inside of the `MazeSolver` class named `Direction`.
This enum must have the members RIGHT, DOWN, LEFT, UP in that order. The
turning logic uses the ordinal of each enum to decide which direction to
turn.

Next create the logic for the MazeSolver. Think about what values the
distanceSensors would see over time. Using only this information,
when would the robot know to turn? When would it know it was safe to
move forward? How would it know which direction to turn?
    
**Tools:**

- Use if-else statements to control the logic of the maze solver.
- `distanceSensors` this is a Vector with publicly accessible 
x and y properties.
    - `distanceSensors.x` refers to the **forward sensor**.
    - `distanceSensors.y` refers to the **left sensor**.
    - You can see the values of the sensors displayed during runtime.
- `moveForward` moves the mazeSolver forward according to the Direction
it is facing.
    
## A Completed Project Will

Display the cart traveling through the maze, without colliding with
any walls.
