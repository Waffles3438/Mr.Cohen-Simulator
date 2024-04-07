import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Write a description of class Person here.
 * 
 * @author Felix Zhao
 * @version April 6th 2024
 */
public class Person extends SuperSmoothMover
{
    public final static int GRID_CHECK = 10; // the space in between when path finding
    // In order of x, y
    protected Queue<int[]> currentPath;
    protected double speed;
    public Person() {
        currentPath = new LinkedList<int[]>();
        speed = 5;
        enableStaticRotation();
    }
    /**
     * Act - do whatever the Person wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        if (currentPath.size() > 0) {
            double distanceRequired = speed;
            int[] position = currentPath.peek();
            turnTowards(position[0], position[1]);
            double distance = getDistance(new int[]{getX(), getY()}, position);
            if (distance <= speed) {

                while (distanceRequired >= distance && currentPath.size() > 0) {
                    setLocation(position[0], position[1]);
                    currentPath.poll();
                    distanceRequired -= distance;
                    if (currentPath.size() == 0) {
                        break;
                    }
                    position = currentPath.peek();
                    distance = getDistance(new int[]{getX(), getY()}, position);
                    turnTowards(position[0], position[1]);
                }
                if (currentPath.size() > 0) {
                    move(distanceRequired);
                }
            } else {
                move(speed);
            }
            
        }
    }
    
    /**
     * Finds the shortest path from the current location to the given location
     * Uses A* algorithm to compute the shortest distance
     * The algo checks every (GRID_CHECK) pixels
     *
     * @param targetX The X position to find
     * @param targetY The y position to find
     */
    public void pathFind(int targetX, int targetY) {
        int exactStartX = getX();
        int exactStartY = getY();
        int totalRows = getWorld().getHeight()/GRID_CHECK;
        int totalCols = getWorld().getWidth()/3*2/GRID_CHECK;
        int targetRow = targetY/GRID_CHECK;
        int targetCol = targetX/GRID_CHECK;
        // r, c for current Row and current col respectivly
        int r = getY()/GRID_CHECK;
        int c = getX()/GRID_CHECK;
        PriorityQueue<int[]> openList = new PriorityQueue<int[]>((a, b) -> Integer.compare(a[0], b[0]));
        boolean[][] closedList = new boolean[totalRows][totalCols];
        Cell[][] cellData = new Cell[totalRows][totalCols];
        
        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                cellData[i][j] = new Cell();
            }
        }
        
        cellData[r][c].setF(0);
        cellData[r][c].setG(0);
        cellData[r][c].setH(0);
        cellData[r][c].setParent(r, c);
        openList.add(new int[]{0, r, c});
        
        while (openList.size() != 0) {
            
            int[] item = openList.poll();
            r = item[1];
            c = item[2];
            if (closedList[r][c]) {
                continue;
            }
            closedList[r][c] = true;
            if (r == targetRow && c == targetCol) {
                System.out.println("found path");
                currentPath = new LinkedList<int[]>(tracePath(cellData, new int[]{r, c}));
                break;
            }
            ArrayList<int[]> children = new ArrayList<int[]>();
            int[][] directions = new int[][] {
                {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}
            };
            
            
            for (int[] position : directions) {
                int newRow = r + position[0];
                int newCol = c + position[1];
                // checks if it's out of bounds
                if (newRow < 0 || newRow >= totalRows || newCol < 0 || newCol >= totalCols) {
                    continue;
                }
                // check if the person can walk through the spot
                setLocation(newCol*GRID_CHECK, newRow*GRID_CHECK);
                if (isTouching(Actor.class)) {
                    setLocation(exactStartX, exactStartY);
                    continue;
                }
                setLocation(exactStartX, exactStartY);
                
                if (closedList[newRow][newCol]) {
                    continue;
                }
                
                children.add(new int[]{newRow, newCol});
            }
            
            for (int[] child : children) {
                int newG = cellData[r][c].getG()+1;
                int newH = Math.abs(targetRow-child[0]) + Math.abs(targetCol-child[1]);
                int newF = newG + newH;
                
                if (cellData[child[0]][child[1]].getF() == -1 || cellData[child[0]][child[1]].getF() > newF) {
                    openList.add(new int[]{newF, child[0], child[1]});
                    cellData[child[0]][child[1]].setF(newF);
                    cellData[child[0]][child[1]].setG(newG);
                    cellData[child[0]][child[1]].setH(newH);
                    cellData[child[0]][child[1]].setParent(r, c);
                }
                    
                
            }
        }
        System.out.println("done");
    }
    
    public void pathFind(Actor actor) {
        pathFind(actor.getX(), actor.getY());
    }
    
    public ArrayList<int[]> tracePath(Cell[][] cellData, int[] target) {
        ArrayList<int[]> path = new ArrayList<int[]>();
        int row = target[0];
        int col = target[1];
        
        while (!(cellData[row][col].getParent()[0] == row && cellData[row][col].getParent()[1] == col)) {
            // reveresed as cols is the x-axis and row is the y-axis
            path.add(new int[]{col*GRID_CHECK, row*GRID_CHECK});
            int tempRow = cellData[row][col].getParent()[0];
            int tempCol = cellData[row][col].getParent()[1];
            row = tempRow;
            col = tempCol;
            
        }
        
        Collections.reverse(path);
        return path;
    }
    
    /**
     * Gets the distance from one (x, y) pair to another (x, y) pair
     *
     * @param one The first position
     * @param two The second position
     * @return distance The distance from point one to point 2
     */
    public static double getDistance(int[] one, int[] two) {
        double xLength = one[0]-two[0];
        double yLength = one[1]-two[1];
        double distance = Math.sqrt(Math.pow(xLength, 2) + Math.pow(yLength, 2));
        return distance;
    }
}

class Cell {
    /**
     * f - total estimated cost (g+h)
     * g - distance traveled from starting node
     * h - estimated cost to get to end node
     */
    private int parent_i, parent_j, f, g, h;
    
    public Cell() {
        this.parent_i = -1;
        this.parent_j = -1;
        this.f = -1;
        this.g = -1;
        this.h = -1;
    }
    /**
     * Returns parent
     *
     * @return Returns the coords of its parent
     */
    public int[] getParent() {
        return new int[]{parent_i, parent_j};
    }
    
    /**
     * Returns f
     *
     * @return Returns the value of f
     */
    public int getF() {
        return f;
    }
    
    /**
     * Returns g
     *
     * @return Returns the value of g
     */
    public int getG() {
        return g;
    }
    
    /**
     * Returns h
     *
     * @return Returns the value h
     */
    public int getH() {
        return h;
    }
    
    /**
     * Changes value of f
     *
     * @param val Sets f to val
     */
    public void setF(int val) {
        f = val;
    }
    
    /**
     * Changes value of g
     *
     * @param val Sets g to val
     */
    public void setG(int val) {
        g = val;
    }
    
    /**
     * Changes value of h
     *
     * @param val Sets h to val
     */
    public void setH(int val) {
        h = val;
    }
    
    /**
     * Sets the parent
     *
     * @param i The i-position (row) of the parent
     * @param j The j-position (col) of the parent
     */
    public void setParent(int i, int j) {
        parent_i = i;
        parent_j = j;
    }
}



