//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package kib.labs.weblab.entities;

import java.time.Instant;

public class ResultTableRow {
    private float x;
    private float y;
    private float r;
    private Instant date;
    private boolean result;

    public ResultTableRow() {
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getR() {
        return this.r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public Instant getDate() {
        return this.date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public boolean isResult() {
        return this.result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String toString() {
        return "ResultTableRow{x=" + this.x + ", y=" + this.y + ", r=" + this.r + ", currentDate=" + this.date + ", result=" + this.result + "}";
    }
}
