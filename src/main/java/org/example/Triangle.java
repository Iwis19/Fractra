/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author User
 */
public class Triangle implements Shape{
    
    private Point p1;
    private Point p2;
    private Point p3;
    
    private Color c;
    
    /**
     * Constructs a new triangle with specified vertices
     * @param p1
     * @param p2
     * @param p3 
     */
    public Triangle(Point p1, Point p2, Point p3){
        this.p1=p1;
        this.p2=p2;
        this.p3=p3;
    }
    
    /**
     * Constructs a new triangle with default vertices
     */
    public Triangle(){
        this(new Point(), new Point(), new Point());
    }
    
    public void setP(Point mouse){
        p2=mouse;
        p3.setY(p2.getY());
        p3.setX(2*p1.getX()-p2.getX());
        
    }

    //getters and setters
    @Override
    public void setColor(Color c) {
        this.c=c;
    }
    
    @Override
    public ArrayList<Point> getVertices(){
        return new ArrayList<>(Arrays.asList(p1, p2, p3));
    }
    
    @Override
    public ArrayList<Line> getEdges(){
        return new ArrayList<>(Arrays.asList(new Line(p1, p2), new Line (p2,p3), new Line(p3, p1)));
    }
    
    /**
     * Calculates x coordinate of the centroid of the shape
     * @return x coordinate of centroid
     */
    @Override
    public double getCenterX() {
        return GeometryUtil.calculateSimpleCentroid(getVertices()).getX();
    }

    /**
     * Calculates the y coordinate of the centroid of the shape
     * @return y coordinate of centroid
     */
    @Override
    public double getCenterY() {
        return GeometryUtil.calculateSimpleCentroid(getVertices()).getY();
    }
    
    @Override
    public Color getColor(){
        return c;
    }
    
    @Override
    public double getWidth() {
        double maxX = Math.max(p1.getX(), Math.max(p2.getX(), p3.getX()));
        double minX = Math.min(p1.getX(), Math.min(p2.getX(), p3.getX()));
        return maxX-minX;
    }

    @Override
    public double getHeight() {
        double minY = Math.min(p1.getY(), Math.min(p2.getY(), p3.getY()));
        double maxY = Math.max(p1.getY(), Math.max(p2.getY(), p3.getY()));
        return maxY-minY;
    }  

    /**
     * Renders the triangle by drawing three sides on the provided panel, the color of triangle is set before drawing the shape
     * @param g 
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(c);
        g.drawLine((int)Math.round(p1.getX()),(int)Math.round(p1.getY()),(int)Math.round(p2.getX()),(int)Math.round(p2.getY()));
        g.drawLine((int)Math.round(p2.getX()),(int)Math.round(p2.getY()),(int)Math.round(p3.getX()),(int)Math.round(p3.getY()));
        g.drawLine((int)Math.round(p3.getX()),(int)Math.round(p3.getY()),(int)Math.round(p1.getX()),(int)Math.round(p1.getY()));
    }
    
    /**
     * Checks for overlaps with other shapes on canvas
     * @param newest
     * @param canvas
     * @return true of overlaps, false otherwise
     */
    @Override
    public boolean hasOverlap(Point newest, Canvas canvas){
        ArrayList<Line> edges = getEdges();
        
        //Skip self check
        for(Shape shape : canvas.getShapes()){
            if (shape == this){
                continue;
            }
            
            //access lines from other shapes
            ArrayList<Line> otherEdges = shape.getEdges();
            for (Line edge : edges){
                for (Line otherEdge : otherEdges){
                    
                    //overlap found
                    if (GeometryUtil.intersection(edge.getP1(), edge.getP2(), otherEdge.getP1(),otherEdge.getP2())){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Creates a deep copy of current Triangle object
     * @return a new Triangle object that is an exact clone
     */
    @Override
    public Shape clone(){
        return new Triangle(new Point(p1.getX(),p1.getY()), new Point(p2.getX(),p2.getY()),new Point(p3.getX(),p3.getY()));
    }

    
}
