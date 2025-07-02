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
 * @author User
 */
public class Rectangle implements Shape{
    
    //Coordinates 
    
    //Need all four points to apply translations
    private Point topLeft;
    private Point topRight;
    private Point bottomRight;
    private Point bottomLeft;
    
    private double width;
    private double height;
    private Color c;
    
    /**
     * Constructs a rectangle with the specified vertices
     * @param topLeft
     * @param width
     * @param height 
     */
    public Rectangle(Point topLeft,int width, int height){
        this.topLeft=topLeft;
        this.topRight=new Point(topLeft.getX()+width,topLeft.getY());
        this.bottomLeft=new Point(topLeft.getX(),topLeft.getY()+height);
        this.bottomRight=new Point(topLeft.getX()+width,topRight.getY()+height);
        this.width=width;
        this.height=height;
    }
    
    /**
     * Constructs a rectangle with default vertices
     */
    public Rectangle(){
        this(new Point(), 1, 1);
    }
    
    /**
     * Updates the second vertex of the rectangle based on mouse coordinates
     * @param mouseCoords 
     */
    public void setP2(Point mouseCoords){
        bottomRight=mouseCoords;
        topRight.setX(bottomRight.getX());
        topRight.setY(topLeft.getY());
        bottomLeft.setX(topLeft.getX());
        bottomLeft.setY(bottomRight.getY());
        this.width = Math.abs(bottomRight.getX() - topLeft.getX());
        this.height = Math.abs(bottomRight.getY() - topLeft.getY());
    }
    
    public double getWidth(){
        return width;
    }
    
    public double getHeight(){
        return height;
    }
    
    public double getTopLeftX(){
        return topLeft.getX();
    }
    
    public double getTopLeftY(){
        return topLeft.getY();
    }
    
    public double getBottomRightX(){
        return bottomRight.getX();
    }
    
    public double getBottomRightY(){
        return bottomRight.getY();
    }

    @Override
    public void setColor(Color c) {
        this.c=c;
    }

    /**
     * Renders the rectangle by drawing its four edges on the panel, using the vertex positions and colors
     * @param g 
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(c);
        g.drawLine((int)Math.round(topLeft.getX()),(int)Math.round(topLeft.getY()),(int)Math.round(topRight.getX()),(int)Math.round(topRight.getY()));
        g.drawLine((int)Math.round(topLeft.getX()),(int)Math.round(topLeft.getY()),(int)Math.round(bottomLeft.getX()),(int)Math.round(bottomLeft.getY()));
        g.drawLine((int)Math.round(bottomRight.getX()),(int)Math.round(bottomRight.getY()),(int)Math.round(topRight.getX()),(int)Math.round(topRight.getY()));
        g.drawLine((int)Math.round(bottomRight.getX()),(int)Math.round(bottomRight.getY()),(int)Math.round(bottomLeft.getX()),(int)Math.round(bottomLeft.getY()));
    }
    
    @Override
    public ArrayList<Point> getVertices(){
        return new ArrayList<>(Arrays.asList(topLeft, topRight, bottomRight, bottomLeft));
    }
    
    @Override
    public ArrayList<Line> getEdges(){
        return new ArrayList<>(Arrays.asList(new Line(topLeft, topRight), new Line(topRight, bottomRight),new Line(bottomRight, bottomLeft), new Line (bottomLeft, topLeft)));
    }
    
    /**
     * Checks for overlap with other shapes on canvas
     * @param newest
     * @param canvas
     * @return true if overlaps, false otherwise
     */
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
     * Calculates x coordinate of centroid of the rectangle
     * @return x coordinate of centroid
     */
    @Override
    public double getCenterX() {
        return GeometryUtil.calculateSimpleCentroid(getVertices()).getX();
    }

    /**
     * Calculates y coordinate of centroid of the rectangle
     * @return y coordinate of centroid
     */
    @Override
    public double getCenterY() {
        return GeometryUtil.calculateSimpleCentroid(getVertices()).getY();
    }
    
    /**
     * Deep copies the current Rectangle object
     * @return a new Rectangle object that is a clone
     */
    public Shape clone(){
        return new Rectangle(new Point(topLeft.getX(),topLeft.getY()),(int)width,(int)height);
    }
    
    @Override
    public Color getColor(){
        return c;
    }

}
