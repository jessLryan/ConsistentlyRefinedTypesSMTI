package com.mycompany.consistentlyrefinedsmti;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jessica
 */
public class Triple {
    private int row;
    private int column;
    private double value;
    
    public Triple(int row, int column, double value){
        this.row = row;
        this.column = column;
        this.value = value;
    }
    
    public int getRow(){ 
        return row; 
    }
    
    public int getColumn(){ 
        return column; 
    }
    
    public double getValue() {
        return value;
    }


}