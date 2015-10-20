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
    
    private String job;
    private int childId;
    
    public Parent(Parent.Builder builder){
        
        super(builder);
        this.childId = builder.childId;
        this.job = builder.job;
        
    }
    
    public static class Builder extends Person.Builder<Parent.Builder> {
        
       private String job;
       private int childId;
       
       public Builder idChild(int childId){
           this.childId = childId; return this;
       }
       
       public Builder job(String job){
           this.job = job; return this;
       }
       
       @Override
       public Parent build(){
           return (new Parent(this));
       }
       
    }
    
}
