package com.sh.tr.toyrob;

import java.util.Scanner;

import com.sh.tr.toyrob.exception.TRException;
import com.sh.tr.toyrob.model.Board;
import com.sh.tr.toyrob.model.Command;
import com.sh.tr.toyrob.model.Direction;
import com.sh.tr.toyrob.model.impl.SquareBoard;
import com.sh.tr.toyrob.util.Position;
import com.sh.tr.toyrob.util.ShRobot;

public class Game 
{


    private Board squareBoard;
    ShRobot robot;

    public Game(Board squareBoard, ShRobot robot) {
        this.squareBoard = squareBoard;
        this.robot = robot;
    }

    /**
     * Places the robot on the squareBoard  in position X,Y and facing NORTH, SOUTH, EAST or WEST
     *
     * @param position Robot position
     * @return true if placed successfully
     * @throws ToyRobotException
     */
    public boolean placeToyRobot(Position position) throws TRException {

        if (squareBoard == null)
            throw new TRException("Invalid squareBoard object");

        if (position == null)
            throw new TRException("Invalid position object");

        if (position.getDirection() == null)
            throw new TRException("Invalid direction value");

        // validate the position
        if (!squareBoard.isValidPosition(position))
            return false;

        // if position is valid then assign values to fields
        robot.setPosition(position);
        return true;
    }

    /**
     * Evaluates and executes a string command.
     *
     * @param inputString command string
     * @return string value of the executed command
     * @throws com.gvnn.trb.exception.ToyRobotException
     *
     */
    public String eval(String inputString) throws TRException {
        String[] args = inputString.split(" ");

        // validate command
        Command command;
        try {
            command = Command.valueOf(args[0]);
        } catch (IllegalArgumentException e) {
            throw new TRException("Invalid command");
        }
        if (command == Command.PLACE && args.length < 2) {
            throw new TRException("Invalid command");
        }

        // validate PLACE params
        String[] params;
        int x = 0;
        int y = 0;
        Direction commandDirection = null;
        if (command == Command.PLACE) {
            params = args[1].split(",");
            try {
                x = Integer.parseInt(params[0]);
                y = Integer.parseInt(params[1]);
                commandDirection = Direction.valueOf(params[2]);
            } catch (Exception e) {
                throw new TRException("Invalid command");
            }
        }

        String output; //currently to capture the output, can be used for logging for prod 

        switch (command) {
            case PLACE:
                output = String.valueOf(placeToyRobot(new Position(x, y, commandDirection)));
                break;
            case MOVE:
                Position newPosition = robot.getPosition().getNextPosition();
                if (!squareBoard.isValidPosition(newPosition))
                    output = String.valueOf(false);
                else
                    output = String.valueOf(robot.move(newPosition));
                break;
            case LEFT:
                output = String.valueOf(robot.rotateLeft());
                break;
            case RIGHT:
                output = String.valueOf(robot.rotateRight());
                break;
            case REPORT:
                output = report();
                System.out.println(output);
                break;
            default:
                throw new TRException("Invalid command");
        }
        
        return output;
    }

    /**
     * Returns the X,Y and Direction of the robot
     */
    public String report() {
        if (robot.getPosition() == null)
            return null;

        return robot.getPosition().getX() + "," + robot.getPosition().getY() + "," + robot.getPosition().getDirection().toString();
    }

    /**
     * Main method
     */
    public static void main(String[] args) {

    	Scanner scan = new Scanner(System.in);
    	
        SquareBoard squareBoard = new SquareBoard(5, 5);
        ShRobot robot = new ShRobot();
        Game game = new Game(squareBoard, robot);

        System.out.println("Toy Robot Simulator");
        System.out.println("Enter a command, Valid commands are:");
        System.out.println("\'PLACE X,Y,NORTH|SOUTH|EAST|WEST\', MOVE, LEFT, RIGHT, REPORT or EXIT");

        boolean keepRunning = true;
        while (keepRunning) {
            String inputString = scan.nextLine();
            if ("EXIT".equals(inputString)) {
                keepRunning = false;
            } else {
                try {
                    game.eval(inputString);
                } catch (TRException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        scan.close();
    }
}
