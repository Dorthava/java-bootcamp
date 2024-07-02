package ex00;

public class Signature {
    private final String type;
    private final String signatureInByte;
    public Signature(String type, String signatureInByte) {
        this.type = type;
        this.signatureInByte = signatureInByte;
    }

    public String getType() {
        return type;
    }

    public String getSignatureInByte() {
        return signatureInByte;
    }
}
