/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkQueue;

/**
 *
 * @author piyush
 */
public class WorkRequestCounter {
    
    private int workid = 100;

    public WorkRequestCounter() {
        workid++;
    }
    

    public int getWorkid() {
        return workid;
    }

    public void setWorkid(int workid) {
        this.workid = workid;
    }
    
}
