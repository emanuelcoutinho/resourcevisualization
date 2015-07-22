/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

/**
 *
 * @author leleco
 */
public class Log {

    private double cpuUser;
    private double cpuSys;
    private double cpuIdle;
    private double memoriaTotal;
    private double memoriaUsed;
    private double memoriaFree;
    private double discoRead;
    private double discoWrite;
    private double redeIn;
    private double redeOut;

    public Log() {
    }

    public Log(double cpuUser,
            double cpuSys,
            double cpuIdle,
            double memoriaTotal,
            double memoriaUsed,
            double memoriaFree,
            double discoRead,
            double discoWrite,
            double redeIn,
            double redeOut) {
        this.cpuUser = cpuUser;
        this.cpuSys = cpuSys;
        this.cpuIdle = cpuIdle;
        this.memoriaTotal = memoriaTotal;
        this.memoriaUsed = memoriaUsed;
        this.memoriaFree = memoriaFree;
        this.discoRead = discoRead;
        this.discoWrite = discoWrite;
        this.redeIn = redeIn;
        this.redeOut = redeOut;
    }

    /**
     * @return the cpuUser
     */
    public double getCpuUser() {
        return cpuUser;
    }

    /**
     * @param cpuUser the cpuUser to set
     */
    public void setCpuUser(double cpuUser) {
        this.cpuUser = cpuUser;
    }

    /**
     * @return the cpuSys
     */
    public double getCpuSys() {
        return cpuSys;
    }

    /**
     * @param cpuSys the cpuSys to set
     */
    public void setCpuSys(double cpuSys) {
        this.cpuSys = cpuSys;
    }

    /**
     * @return the cpuIdle
     */
    public double getCpuIdle() {
        return cpuIdle;
    }

    /**
     * @param cpuIdle the cpuIdle to set
     */
    public void setCpuIdle(double cpuIdle) {
        this.cpuIdle = cpuIdle;
    }

    /**
     * @return the memoriaTotal
     */
    public double getMemoriaTotal() {
        return memoriaTotal;
    }

    /**
     * @param memoriaTotal the memoriaTotal to set
     */
    public void setMemoriaTotal(double memoriaTotal) {
        this.memoriaTotal = memoriaTotal;
    }

    /**
     * @return the memoriaUsed
     */
    public double getMemoriaUsed() {
        return memoriaUsed;
    }

    /**
     * @param memoriaUsed the memoriaUsed to set
     */
    public void setMemoriaUsed(double memoriaUsed) {
        this.memoriaUsed = memoriaUsed;
    }

    /**
     * @return the memoriaFree
     */
    public double getMemoriaFree() {
        return memoriaFree;
    }

    /**
     * @param memoriaFree the memoriaFree to set
     */
    public void setMemoriaFree(double memoriaFree) {
        this.memoriaFree = memoriaFree;
    }

    /**
     * @return the discoRead
     */
    public double getDiscoRead() {
        return discoRead;
    }

    /**
     * @param discoRead the discoRead to set
     */
    public void setDiscoRead(double discoRead) {
        this.discoRead = discoRead;
    }

    /**
     * @return the discoWrite
     */
    public double getDiscoWrite() {
        return discoWrite;
    }

    /**
     * @param discoWrite the discoWrite to set
     */
    public void setDiscoWrite(double discoWrite) {
        this.discoWrite = discoWrite;
    }

    /**
     * @return the redeIn
     */
    public double getRedeIn() {
        return redeIn;
    }

    /**
     * @param redeIn the redeIn to set
     */
    public void setRedeIn(double redeIn) {
        this.redeIn = redeIn;
    }

    /**
     * @return the redeOut
     */
    public double getRedeOut() {
        return redeOut;
    }

    /**
     * @param redeOut the redeOut to set
     */
    public void setRedeOut(double redeOut) {
        this.redeOut = redeOut;
    }

    public String toString() {
        return cpuUser + "|" + cpuSys + "|" + cpuIdle + "|" + memoriaTotal + "|" + memoriaUsed + "|" + memoriaFree + "|" + discoRead + "|" + discoWrite + "|" + redeIn + "|" + redeOut;
    }
}
