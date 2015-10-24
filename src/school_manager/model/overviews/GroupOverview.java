/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.model.overviews;

/**
 *
 * @author abrasha
 */
public class GroupOverview {
    
    private final int id;
    private final String code;

    public GroupOverview(int id, String code) {
        this.id = id;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code;
    }
    
    
    
}
