/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author ronni
 */
public class Polyline implements Shape {
    
    //Parts of a line variables
    private ArrayList<Point> points;
    private Color c;
    private boolean completed = false;
    
    /**
     * Constructs a poly line starting with the given point
     * @param start 
     */
    public Polyline(Point start){
        points = new ArrayList<>();
        points.add(start);
    }
    
    @Override
    public void setColor(Color c){
        this.c = c;
    }
    
    @Override
    public ArrayList<Point> getVertices(){
        return points;
    } 
    
    @Override
    public ArrayList<Line> getEdges(){
        ArrayList<Line> edges = new ArrayList<>();
        int size = points.size();
        if (size < 2){
            return edges;
        }
        for (int i = 0; i <size-1;i++){
            edges.add(new Line(points.get(i),points.get(i+1)));
        }
        return edges;
    }
    
    //getters & setters
    public Point getFirst(){
        if (points.isEmpty()){
            return null;
        }
        else{
            return points.get(0);
        }
    }
    
    //Last point
    public void addLast(Point p){
        points.add(p);
    }
    
    public void setLast(Point point){
        if (!points.isEmpty()){
            points.set(points.size()-1,point);
        }
    }
    
    public Point getLast(){
        return points.get(points.size()-1);
    }  
    
    /**
     * Calculates x coordinate of centroid of poly line
     * @return x coordinate of centroid
     */
    @Override
    public double getCenterX() {
         return GeometryUtil.calculateComplexCentroid(getVertices()).getX();
    }

    /**
     * Calculates y coordinate of centroid of poly line
     * @return y coordinate of centroid
     */
    @Override
    public double getCenterY() {
        return GeometryUtil.calculateComplexCentroid(getVertices()).getY();
    }
    
    @Override
    public double getWidth() {
        double minX = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        for(Point p : points){
            minX = Math.min(minX, p.getX());
            maxX = Math.max(maxX, p.getX());
        }
        return maxX-minX;
    }

    @Override
    public double getHeight() {
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;
        for (Point p : points) {
            minY = Math.min(minY, p.getY());
            maxY = Math.max(maxY, p.getY());
        }
        return maxY-minY;
    }
    
    @Override
    public Color getColor(){
        return c;
    }
    
    /**
     * Pains the poly line by drawing lines between consecutive points
     * @param g 
     */
    @Override
    public void paint(Graphics g) {
        
        g.setColor(c);

        //Loop through points and draw lines between consecutive points
        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            
            //Draw a line from p1 to p2
            g.drawLine((int)p1.getX(),(int)p1.getY(),(int)p2.getX(),(int)p2.getY());
        }
    }
    
    public void setCompleted(boolean completed){
        this.completed = completed;
    }
    
    /**
     * Checks if the new line connecting to the last point overlaps with any other lines
     * @param newest
     * @param canvas
     * @return true if overlaps, false otherwise
     */
    @Override
    public boolean hasOverlap(Point newest, Canvas canvas){
        
        if (points.size() < 2){
            //no points to check
            return false;
        }
        Point last = points.get(points.size()-1);
        
        for (Shape shape : canvas.getShapes()){
            ArrayList<Line> edges = shape.getEdges();
            
            for (Line edge : edges){
                if (shape == this && (GeometryUtil.isSamePoint(edge.getP1(),last) || GeometryUtil.isSamePoint(edge.getP2(),last))){
                    continue;
                }
            
            
                if(GeometryUtil.intersection(edge.getP1(),edge.getP2(),last,newest)){
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Checks if the poly line is marked as completed
     * @return true if completed, false otherwise
     */
    public boolean isCompleted(){
        return completed;
    }

    /**
     * Creates a deep clone of the poly line
     * @return new object that is a clone of this poly line
     */
    @Override
    public Shape clone() {
        Polyline newPolyline = new Polyline(new Point(points.getFirst().getX(), points.getFirst().getY()));
        for(int i = 1; i < points.size(); i++){
            newPolyline.addLast(new Point(points.get(i).getX(), points.get(i).getY()));
        }
        return newPolyline;
    }

    
    
    

    
}
