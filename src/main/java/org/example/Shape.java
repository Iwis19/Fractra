/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.example;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author ronni
 */
public interface Shape extends Serializable {
    void setColor(Color c);
    void paint(Graphics g);
    ArrayList<Point> getVertices();
    ArrayList<Line> getEdges();
    boolean hasOverlap(Point newest, Canvas canvas);
    double getCenterX();
    double getCenterY();
    Shape clone();
    double getWidth();
    double getHeight();
    Color getColor();
    
}
