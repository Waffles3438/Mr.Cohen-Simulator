import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.LinkedList;

/**
 * <div>
 * All people in the simulation are a subclass of this class
 * This class gives the basic methods to all people
 * </div>
 * Uses A* for path finding. <br>
 * Some information on the algorithm: <a href="https://en.wikipedia.org/wiki/A*_search_algorithm"> A* Star</a><br>
 * 
 * @author Felix Zhao
 * @version April 8th 2024
 */
public class Person extends SuperSmoothMover
{
    public final static int GRID_CHECK = 10; // the space in between when path finding
    // In order of x, y
    protected Queue<int[]> currentPath;
    protected double speed;
    protected ArrayList<Class<?>> avoidList;
    public Person() {
        currentPath = new LinkedList<int[]>();
        speed = 5;
        enableStaticRotation();
        avoidList = new ArrayList<Class<?>>();
        avoidList.add(Image.class);
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
     * @param radius How far/close can the person be from the given points for it to count as a path found
     * @return boolean Returns if a path is found
     */
    public boolean pathFind(int targetX, int targetY, double radius) {
        boolean pathFound = false;
        int exactStartX = getX();
        int exactStartY = getY();
        int totalRows = getWorld().getHeight()/GRID_CHECK;
        int totalCols = getWorld().getWidth()/3*2/GRID_CHECK;
        int targetRow = targetY/GRID_CHECK;
        int targetCol = targetX/GRID_CHECK;
        // r, c for current Row and current col respectivly
        int r = getY()/GRID_CHECK;
        int c = getX()/GRID_CHECK;
        // In order of (f, r, c)
        // has to double[] as the first value may have to be a decimal due to diagonal movements - The points (r, c) are casted to int when used
        PriorityQueue<double[]> openList = new PriorityQueue<double[]>((a, b) -> Double.compare(a[0], b[0]));
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
        openList.add(new double[]{0, r, c});
        
        while (openList.size() != 0) {
            
            double[] item = openList.poll();
            r = (int)item[1];
            c = (int)item[2];
            if (closedList[r][c]) {
                continue;
            }
            closedList[r][c] = true;
            if (r == targetRow && c == targetCol || (getDistance(new int[]{c, r}, new int[]{targetCol, targetRow})*GRID_CHECK <= radius)) {
                System.out.println("found path");
                pathFound = true;
                currentPath = new LinkedList<int[]>(tracePath(cellData, new int[]{r, c}));
                break;
            }
            
            int[][] directions = new int[][] {
                {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}
            };
            // int[][] directions = new int[][] {
                // {-1, 0},{0, -1}, {0, 1}, {1, 0}
            // };
            
            
            for (int[] position : directions) {
                int newRow = r + position[0];
                int newCol = c + position[1];
                // checks if it's out of bounds
                if (newRow < 0 || newRow >= totalRows || newCol < 0 || newCol >= totalCols) {
                    continue;
                }
                // check if the person can walk through the spot
                setLocation(newCol*GRID_CHECK, newRow*GRID_CHECK);
                boolean valid = true;
                for (int i = 0; i < avoidList.size(); i++) {
                    if (isTouching(avoidList.get(i))) {
                        valid = false;
                        break; 
                    }
                }
                setLocation(exactStartX, exactStartY);
                if (!valid) {
                    continue;
                }
                
                
                if (closedList[newRow][newCol]) {
                    continue;
                }
                
                
                double newG = cellData[r][c].getG()+1;
                if (position[0] != 0 && position[1] != 0) {
                    // adds another 0.4 because diagonal movements are longer
                    // 1.4 is near sqrt2
                    newG += 0.4;
                }
                int newH = Math.abs(targetRow-newRow) + Math.abs(targetCol-newCol);
                double newF = newG + newH;
                if (cellData[newRow][newCol].getF() == -1 || cellData[newRow][newCol].getF() > newF) {
                    openList.add(new double[]{newF, newRow, newCol});
                    cellData[newRow][newCol].setF(newF);
                    cellData[newRow][newCol].setG(newG);
                    cellData[newRow][newCol].setH(newH);
                    cellData[newRow][newCol].setParent(r, c);
                }
            }
            
            
        }
        System.out.println("done");
        return pathFound;
    }
    
    public boolean pathFind(Actor actor, double radius) {
        return pathFind(actor.getX(), actor.getY(), radius);
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
    private int parent_i, parent_j;
    private double f, g, h;
    
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
    public double getF() {
        return f;
    }
    
    /**
     * Returns g
     *
     * @return Returns the value of g
     */
    public double getG() {
        return g;
    }
    
    /**
     * Returns h
     *
     * @return Returns the value h
     */
    public double getH() {
        return h;
    }
    
    /**
     * Changes value of f
     *
     * @param val Sets f to val
     */
    public void setF(double val) {
        f = val;
    }
    
    /**
     * Changes value of g
     *
     * @param val Sets g to val
     */
    public void setG(double val) {
        g = val;
    }
    
    /**
     * Changes value of h
     *
     * @param val Sets h to val
     */
    public void setH(double val) {
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



