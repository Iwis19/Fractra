/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author ronni
 */
public class Point implements Shape{
    
    
    private double x;
    private double y;
    
    /**
     * Constructs a Point with specified x and y coordinates
     * @param x
     * @param y
     */
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    /**
     * Constructs a Point at origin 0,0
     */
    public Point(){
        this(0, 0);
    }
    
    //getters and setters
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
   
    public void setX(double x){
        this.x = x;
    }
    
    public void setY(double y){
        this.y = y;
    }

    //implementation not needed
    @Override
    public void setColor(Color c){}
    
     @Override
    public ArrayList<Point> getVertices(){
        return new ArrayList<>(Arrays.asList(this));
    }
    
    @Override
    public ArrayList<Line> getEdges(){
        return new ArrayList<>();
    }
    
    /**
     * Returns the x coordinate of the point as its the only point, making it the centroid
     * @return x coordinate
     */
    @Override
    public double getCenterX() {
        return x;
    }

    /**
     * Finds centroid of the point
     * @return y coordinate
     */
    @Override
    public double getCenterY() {
        return y;
    }
    
    //implementation not needed
    @Override
    public double getWidth() {
        return 0;
    }

    //implementation not needed
    @Override
    public double getHeight() {
        return 0;
    }
    
    @Override
    public Color getColor(){
        return null;
    }

    /**
     * Paints the point on the given panel, drawn as a small circle
     * @param g 
     */
    @Override
    public void paint(Graphics g) {
        g.fillOval((int)Math.round(x),(int)Math.round(y),2,2);
    }
    
    /**
     * Checks if newest point overlaps with any other shapes
     * @param newest
     * @param canvas
     * @return false
     */
    @Override
    public boolean hasOverlap(Point newest, Canvas canvas) {
        
        //singular point cannot overlap
        return false;
    }

    /**
     * Creates a deep copy of the point
     * @return a new Point object with same x and y coordinates
     */
    @Override
    public Shape clone(){
        return new Point(x,y);
    }

    
    
}
