//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package kib.labs.weblab.validation;

public class RequestValidator {
    public RequestValidator() {
    }

    public boolean validateRequest(String x, String y, String r, String timezone) {
        return this.validateIsNum(x) && this.validateIsNum(y) && this.validateIsNum(r) && (timezone == null || this.validateIsNum(timezone));
    }

    private boolean validateIsNum(String stringToCheck) {
        return stringToCheck.matches("^([-+]?\\d+[.,]\\d+|[-+]?\\d+|[-+]?\\d+([.,]\\d+)?e[-+]?\\d+)$");
    }
}
