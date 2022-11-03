//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package kib.labs.weblab.maths;

public class AreaChecker {
    public AreaChecker() {
    }

    public boolean checkIfPointInArea(float x, float y, float r) {
        return this.checkIfPointInFirstQuarter(x, y, r) || this.checkIfPointInSecondQuarter(x, y, r) || this.checkIfPointInFourthQuarter(x, y, r);
    }

    private boolean checkIfPointInFirstQuarter(float x, float y, float r) {
        return (double)y <= -0.5 * (double)x + 0.5 * (double)r && y <= r / 2.0F && y >= 0.0F && x >= 0.0F && x <= r;
    }

    private boolean checkIfPointInSecondQuarter(float x, float y, float r) {
        return y >= 0.0F && y <= r && x >= -r / 2.0F && x <= 0.0F;
    }

    private boolean checkIfPointInFourthQuarter(float x, float y, float r) {
        return x >= 0.0F && y <= 0.0F && x * x + y * y < r * r;
    }
}
