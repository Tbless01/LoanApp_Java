package africa.semicolon.loanAppSystem.data.models;

public enum PaymentMethod {
    CARD("CARD"),
    BANK_TRANSFER("BANK_TRANSFER"),
    USSD_CODE("USSD_CODE");
    private final String method;

    PaymentMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }
}
