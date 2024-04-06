import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.PriorityQueue;
import java.util.ArrayList;

/**
 * Write a description of class Person here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Person extends SuperSmoothMover
{
    /**
     * Act - do whatever the Person wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public void pathFind(int targetX, int targetY) {
        int exactStartX = getX();
        int exactStartY = getY();
        int totalRows = getWorld().getHeight()/10;
        int totalCols = getWorld().getWidth()/3*2/10;
        int targetRow = targetY/10;
        int targetCol = targetX/10;
        // r, c for current Row and current col respectivly
        int r = getY()/5*5;
        int c = getX()/5*5;
        PriorityQueue<int[]> openList = new PriorityQueue<int[]>();
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
            if (r == targetRow && c == targetY) {
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
                setLocation(newRow*10, newCol*10);
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
                
                
            }
        }
    }
    
    public void pathFind(Actor actor) {
        pathFind(actor.getX(), actor.getY());
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



