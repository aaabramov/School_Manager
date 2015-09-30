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
    
    private int idChild;
    
    public Parent(Parent.Builder builder){
        
        super(builder);
        this.idChild = builder.id_child;
        
    }
    
    public static class Builder extends Person.Builder<Parent.Builder> {
        
       int id_child;
       
       public Builder idChild(int idChild){
           this.id_child = idChild; return this;
       }
       
       @Override
       public Parent build(){
           return (new Parent(this));
       }
       
    }
    
}
