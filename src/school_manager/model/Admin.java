/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.model;

/**
 *
 * @author bepa
 */
public class Admin {

    private final int id;
    private String lastSeen;

    public int getId() {
        return id;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }

    public Admin(int id, String lastSeen) {
        this.id = id;
        this.lastSeen = lastSeen;
    }

    @Override
    public String toString() {
        return "Admin #" + id;
    }

}
