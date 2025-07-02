/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.example;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class Canvas extends JPanel {

    //Constants to represent each shape
    public final static int LINE = 0;
    public final static int RECT = 1;
    public final static int TRI = 2;
    
    //Next selected shape
    private int next;
    
    private Color c;
    
    //ArrayList that stores all shapes
    private ArrayList<Shape> shapes;
    
    /**
     * Creates new form Canvas
     */
    public Canvas() {
        next = -1;
        c = Color.BLACK;
        shapes = new ArrayList<>();
        initComponents();
    }
    
    //getters and setters
    public ArrayList<Shape> getShapes(){
        return shapes;
    }
    
    public void setShapes(ArrayList<Shape> shapes){
        this.shapes = shapes;
        repaint();
    }
    
    public void setColor(Color c){
        this.c=c;
    }
    
    public void setNextShape(int next){
        this.next=next;
    }
    
    public Shape getLastShape(){
        return shapes.get(shapes.size()-1);
    }
    
    public int getNextShape(){
        return next;
    }
    
    /**
     * Paint method
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        //smooths weird thin diagonal polylines with anti aliasing
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        
        for (Shape shape : shapes){
            shape.paint(g2d);
        }
    }
    
    /**
     * Adds new shape to shapes array list that will be displayed on canvas
     * @param x
     * @param y 
     */
    public void addNewShape(double x,double y){
        switch(next){
            //Draw lines
            case LINE:
                Polyline l=new Polyline(new Point(x,y));
                l.setColor(c);
                shapes.add(l);
                break;
            //Draw rectangles
            case RECT:
                Rectangle r=new Rectangle(new Point(x,y), 1, 1);
                r.setColor(c);
                shapes.add(r);
                break;
            //Draw triangles
            case TRI:
                Triangle t = new Triangle(new Point(x,y),new Point(x,y),new Point(x,y));
                t.setColor(c);
                shapes.add(t);
                break;
            
        }
        repaint();
    }
    
    /**
     * Checks if a poly line is actively being drawn
     * @return true if is being drawn, false otherwise
     */
    public boolean isDrawingPolyline(){
        if(shapes.isEmpty()){
            return false;
        }
        Shape last = shapes.isEmpty()?null:getLastShape();
        return last instanceof Polyline && !((Polyline) last).isCompleted();
    }
    
    /**
     * Helps user draw better connected shapes through snapping when under the threshold
     * @param mouse
     * @param threshold
     * @return Point with x and y coordinate values depending on if it was snapped or not
     */
    public Point snapToVertex(Point mouse, double threshold){
        
        for (Shape shape : shapes) {
            ArrayList<Point> vertices = null;
            
            if (shape instanceof Polyline polyline) {
                vertices = polyline.getVertices();
            } 
            else if (shape instanceof Rectangle rectangle) {
                vertices = rectangle.getVertices();
            } 
            else if (shape instanceof Triangle triangle) {
                vertices = triangle.getVertices();
            }

            //if any vertices exist
            if (vertices != null) {
                for (Point vertex : vertices) {
                    if (vertex == null) continue; // Skip null vertices
                    double distance = Math.hypot(vertex.getX() - mouse.getX(), vertex.getY() - mouse.getY());
                    if (distance < threshold) {
                        //sets the vertex to the location of the closest vertex
                        return vertex;
                    }
                }
            }
        }
        //no snap if mouse is over threshold distance alway from any vertex 
        return mouse;
    }    
    
    
    /**
     * Retrieve all vertices
     * @return vertices of the shape
     */
    public ArrayList<Point> getAllVertices(){
        ArrayList<Point> vertices = new ArrayList<>();
        for (Shape shape : shapes){
            if (shape instanceof Polyline l){
                vertices.addAll(l.getVertices());
            }
            else if (shape instanceof Rectangle r){
                vertices.addAll(r.getVertices());
            }
            else if (shape instanceof Triangle t){
                vertices.addAll(t.getVertices());
            }
        }
        return vertices;
    }
    
    /**
     * Removes last shape of array list
     */
    public void removeLast(){
        if (!shapes.isEmpty()){
            shapes.remove(shapes.size()-1);
            repaint();
        }       
    }
    
    /**
     * Removes all shapes from array list
     */
    public void removeAllShapes(){
        shapes.clear();
        repaint();
    }
    
    //general overlap checking method
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
