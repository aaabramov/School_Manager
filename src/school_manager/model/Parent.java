/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.model;

/**
 *
 * @author abrasha
 */
public class Parent extends Person {
    
    private int childId;
    
    public Parent(Parent.Builder builder){
        
        super(builder);
        this.childId = builder.childId;
        
    }
    
    public static class Builder extends Person.Builder<Parent.Builder> {
        
       int childId;
       
       public Builder idChild(int childId){
           this.childId = childId; return this;
       }
       
       @Override
       public Parent build(){
           return (new Parent(this));
       }
       
    }
    
}
