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
public class Line implements Shape{
    
    //Attributes of the line
    private Point p1;
    private Point p2;
    private Color c;
    
    /**
     * Constructs a Line with the specified end points
     * @param p1
     * @param p2 
     */
    public Line(Point p1, Point p2){
        this.p1 = p1;
        this.p2 = p2;
    }
    
    /**
     * Constructs a line with default end points
     */
    public Line(){
        this(new Point(), new Point());
    }
    
    public Point getP1(){
        return p1;
    }
    
    public Point getP2(){
        return p2;
    }

    @Override
    public void setColor(Color c) {
        this.c = c;
    }
    
    @Override
    public ArrayList<Point> getVertices() {
        return new ArrayList<>(Arrays.asList(p1, p2));
    }
    
    @Override
    public ArrayList<Line> getEdges(){
        return new ArrayList<>(Arrays.asList(new Line(p1,p2)));
    }
    
    /**
     * Calculates the x coordinate of the center of line segment
     * @return x coordinate of centroid
     */
    @Override
    public double getCenterX() {
        return GeometryUtil.calculateSimpleCentroid(getVertices()).getX();
    }

    /**
     * Calculates the y coordinate of the center of line segment
     * @return y coordinate of centroid
     */
    @Override
    public double getCenterY() {
        return GeometryUtil.calculateSimpleCentroid(getVertices()).getY();
    }
    
    //implementation not needed
    @Override
    public double getWidth(){
        return 0;
    }

    //implementation not needed
    @Override
    public double getHeight(){
        return 0;
    }
    
    public double getLength(){
        return Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
    }
    
    @Override
    public Color getColor(){
        return c;
    }

    /**
     * Paints the line on the given panel, drawn from starting point to ending point
     * @param g 
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(c);
        g.drawLine((int)Math.round(p1.getX()),(int)Math.round(p1.getY()),(int)Math.round(p2.getX()),(int)Math.round(p2.getY()));
    }
    
    //implementation not needed
    @Override
    public boolean hasOverlap(Point newest, Canvas canvas) {
        return false;
   }
    
    /**
     * Creates a deep copy of line
     * @return Line object that is a clone of this instance
     */
    @Override
    public Shape clone(){
        return new Line(new Point(p1.getX(),p1.getY()),new Point(p2.getX(),p2.getY()));
    }

    
    
}
