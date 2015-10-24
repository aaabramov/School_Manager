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
public class StudentOverview {

    private int id;
    private String initials;

    public StudentOverview(String initials, int id) {
        this.initials = initials;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String name) {
        this.initials = initials;
    }

    @Override
    public String toString() {
        return initials;
    }

}
