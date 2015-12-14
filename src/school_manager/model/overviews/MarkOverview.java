/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package school_manager.model.overviews;

/**
 *
 * @author Shlimazl
 */
public class MarkOverview
{
    private final int mark;
    private final String MarkType;
    private final int presence;
    private final String Date;
    public MarkOverview(int mark,String type,int presence,String Date)
    {
        this.mark=mark;
        this.MarkType=type;
        this.presence=presence;
        this.Date = Date;
    }
    public int getMark()
    {
        return this.mark;
    }
    public String getMarkType()
    {
        return this.MarkType;
    }
    public int getPresence()
    {
        return this.presence;
    }
    public String getDate()
    {
        return this.Date;
    }
    
}