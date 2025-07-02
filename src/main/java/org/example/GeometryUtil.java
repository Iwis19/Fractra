/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example;

import java.util.ArrayList;

/**
 *
 * @author ronni
 */
public class GeometryUtil {
    
    private static final double ERROR = 0.0000001;
    
    /**
     * Calculates centroid for regular simple shapes, such as rectangle and triangle
     * @param points
     * @return centroid point, x and y values
     */
    public static Point calculateSimpleCentroid(ArrayList<Point> points){
        double xSum = 0;
        double ySum = 0;
        int n = points.size();
        
        for (Point p : points){
            xSum += p.getX();
            ySum += p.getY();
        }
        
        return new Point(xSum/n, ySum/n);
    }
    
    /**
     * Calculates centroid for irregular shapes, with inconsistent length and angles
     * @param points
     * @return centroid point, x and y values
     */
    public static Point calculateComplexCentroid(ArrayList<Point> points){
        //if a shape has only one point, it is the centroid
        if (points.size() < 2){
            return points.get(0);
        }
        double xSum = 0;
        double ySum = 0;
        double total = 0;
        
        //iterate through all points
        for (int i = 0; i < points.size() - 1; i++){
            Point p1 = points.get(i);
            Point p2 = points.get(i+1);
            
            double xMid = (p1.getX() + p2.getX())/2;
            double yMid = (p1.getY() + p2.getY())/2;
            
            double length = Math.hypot(p2.getX() - p1.getX(), p2.getY() - p1.getY());
            
            xSum += xMid * length;
            ySum += yMid * length;
            
            total += length;
        }
        //find centroid coordinates
        double centroidX = xSum/total;
        double centroidY = ySum/total;
        
        return new Point(centroidX, centroidY);
    }
    
    
    /**
     * Checks for intersection between two lines
     * @param p1
     * @param q1
     * @param p2
     * @param q2
     * @return true if intersects, false otherwise
     */
    //checks for intersection between 2 lines
    public static boolean intersection(Point p1, Point q1, Point p2, Point q2) {
        
        //block out shared vertex, as it doesnt count as intersection
        if (isSamePoint(p1, p2) || isSamePoint(p1, q2) || isSamePoint(q1, p2) || isSamePoint(q1, q2)) {
            return false; 
        }

        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        //general case checks from geeksforgeeks
        if (o1 != o2 && o3 != o4) {
            return true; 
        }

        //collinear scenarios
        if (o1 == 0 && onLine(p1, p2, q1)){
            return true;
        }
        if (o2 == 0 && onLine(p1, q2, q1)){
            return true;
        }
        if (o3 == 0 && onLine(p2, p1, q2)){
            return true;
        }
        if (o4 == 0 && onLine(p2, q1, q2)){
            return true;
        }

        return false;
    }
    
    /**
     * Checks if a point is on a line segment
     * @param a first point
     * @param b second point
     * @param c third point
     * @return true if point is on the line segment, false otherwise
     */
    public static boolean onLine(Point a, Point b, Point c){
        
        return b.getX() <= Math.max(a.getX(), c.getX()) && b.getX() >= Math.min(a.getX(), c.getX()) &&
                b.getY() <= Math.max(a.getY(), c.getY()) && b.getY() >= Math.min(a.getY(), c.getY()); 
    }
    
    /**
     * Help check if two line segments share the same vertex
     * @param a
     * @param b
     * @return true if points are the same, false otherwise
     */
    public static boolean isSamePoint(Point a, Point b) {
        return Math.abs(a.getX() - b.getX()) < ERROR && Math.abs(a.getY() - b.getY()) < ERROR;
    }


    /**
     * Calculates for relative turn on the three points
     * @param a first point
     * @param b second point
     * @param c third point
     * @return returns an integer that represents the orientation of the line, collinear, clockwise, counterclockwise
     */
    public static int orientation(Point a,Point b, Point c){
        
        //Using vectors cross product formula to find orientation (colinear, clockwise, counterclock)
        double crossProduct = (b.getY() - a.getY()) * (c.getX() - b.getX()) - (b.getX() - a.getX()) * (c.getY() - b.getY());
        
        //collinear
        if (crossProduct == 0){
            return 0;
        }
        
        //clockwise
        else if (crossProduct > 0){
            return 1;
        }
        
        //counterclockwise
        else{
            return 2;
        }
    }
    
}
