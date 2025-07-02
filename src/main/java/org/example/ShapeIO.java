/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class ShapeIO {
    
    /**
     * Writes a list of Shape objects into a file, serializes shapes into text format
     * @param shapes
     * @param filename 
     * @throws IOException if input/output error occurs
     * 
     */
    public static void shapesToFile(ArrayList<Shape> shapes, String filename){
        try(FileWriter fw = new FileWriter(filename)){
            for (Shape s : shapes){
                if(s instanceof Rectangle r){
                    fw.write(String.format("Rectangle %.2f %.2f %.2f %.2f %s\n",
                            r.getTopLeftX(), r.getTopLeftY(),r.getWidth(),
                            r.getHeight(),hex(r.getColor())));
                }
                else if (s instanceof Triangle t){
                    fw.write(String.format("Triangle %.2f %.2f %.2f %.2f %.2f %.2f %s\n",
                            t.getVertices().get(0).getX(), t.getVertices().get(0).getY(),
                            t.getVertices().get(1).getX(), t.getVertices().get(1).getY(),
                            t.getVertices().get(2).getX(), t.getVertices().get(2).getY(),
                            hex(t.getColor())));
                }
                else if (s instanceof Polyline p){
                    String info = "Polyline ";
                    for (Point point : p.getVertices()){
                        info += String.format("%.2f %.2f ", point.getX(), point.getY());
                    }
                    info += String.format("%s\n", hex(p.getColor()));
                    fw.write(info);
                }
            }
            System.out.println("info in file!");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
    /**
     * Reads shape definitions from a file and creates a list of Shape objects, the file should contain descriptions in a specific format
     * @param filename
     * @return an ArrayList of shape objects created from file's info
     * @throws IOException if an input/output error occurs while reading file
     */
    public static ArrayList<Shape> shapesFromFile(String filename){
        ArrayList<Shape> shapes = new ArrayList<>();
        
        try(Scanner sc = new Scanner(new File(filename))){
            
            //read until no more shapes left
            while(sc.hasNextLine()){
                
                //splitting information
                String temp = sc.nextLine();
                String[] info = temp.split(" ");
                String shape = info[0];
                
                switch(shape){
                    case "Rectangle":
                        
                        //retrieve necessary info to create a rectangle
                        double topLeftX = Double.parseDouble(info[1]);
                        double topLeftY = Double.parseDouble(info[2]);
                        double width = Double.parseDouble(info[3]);
                        double height = Double.parseDouble(info[4]);
                        Color cr = color(info[5]);
                        
                        //new rectangle
                        Rectangle r = new Rectangle(new Point(topLeftX,topLeftY), (int) width, (int)height);
                        r.setColor(cr);
                        shapes.add(r);
                        break;

                    //triangle
                    case "Triangle":
                        
                        //retrieve vertices
                        double x1 = Double.parseDouble(info[1]);
                        double y1 = Double.parseDouble(info[2]);
                        double x2 = Double.parseDouble(info[3]);
                        double y2 = Double.parseDouble(info[4]);
                        double x3 = Double.parseDouble(info[5]);
                        double y3 = Double.parseDouble(info[6]);
                        Color ct = color(info[7]);
                        
                        //create new triangle
                        Triangle t = new Triangle(new Point(x1, y1), 
                                new Point(x2, y2), new Point(x3, y3));
                        t.setColor(ct);
                        shapes.add(t);
                        break;
                        
                    case "Polyline":
                        
                        //retrieve all vertices from the file
                        ArrayList<Point> points = new ArrayList<>();
                        for(int i = 1; i < info.length-1; i+=2){
                            double x = Double.parseDouble(info[i]);
                            double y = Double.parseDouble(info[i+1]);
                            points.add(new Point(x,y));
                        }
                        Color cp = color(info[info.length-1]);
                        
                        //initializing a polyline and adding all vertices
                        Polyline p = new Polyline(points.get(0));
                        for (int i = 1; i < points.size(); i++){
                            p.addLast(points.get(i));
                        }
                        p.setColor(cp);
                        shapes.add(p);
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        return shapes;
    }
    
    /**
     * Converts color into hex string
     * @param color
     * @return hex codes
     */
    private static String hex(Color color){
        
        //formats numbers into 6 character hex code
        return String.format("%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());
    }
    
    /**
     * Converts hexadecimal string into Color object
     * @param hex
     * @return color object from hex
     */
    private static Color color(String hex){
        
        //radix: interpret as hexadecimal number
        return new Color(Integer.parseInt(hex.substring(0,2),16),
                Integer.parseInt(hex.substring(2,4),16),
                Integer.parseInt(hex.substring(4,6),16));
    }
    
}
