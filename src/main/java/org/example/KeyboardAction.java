/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author ronni
 */
public class KeyboardAction extends AbstractAction {

    private ShapeEditor shapeEditor;
    private String key;
    
    /**
     * Constructs a instance to handle specific keyboard inputs
     * @param shapeEditor
     * @param key 
     */
    public KeyboardAction(ShapeEditor shapeEditor, String key){
        this.key = key;
        this.shapeEditor = shapeEditor;
    }
    
    /**
     * Handles action events triggered by user interaction, responds to the "ENTER" key press to complete poly lines
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (key.equals("ENTER")){
            
            //only shows dialog if polyline is being drawn
            if(shapeEditor.getCanvas().isDrawingPolyline()){
                shapeEditor.finishCurrentPolyline();
            }
            
            
        }
    }
    
    
    
    
}
