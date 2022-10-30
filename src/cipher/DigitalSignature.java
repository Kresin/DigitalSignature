package cipher;

import file.FileService;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

public class DigitalSignature {

    private static final String SIGNATURE_FILE = "./files/FileSignature.txt";

    public void generateSignature(byte[] fileToSign, String privateKeyPath) {
        FileService fileService = new FileService();
        RSACipher rsaCipher = new RSACipher();
        PrivateKey privateKey = rsaCipher.getPrivateKey(privateKeyPath);

        Signature signature;
        byte[] sign;
        try {
            signature = Signature.getInstance("SHA1WithRSA");
            signature.initSign(privateKey);
            signature.update(fileToSign);
            sign = signature.sign();
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            throw new RuntimeException(e);
        }

        fileService.saveFile(SIGNATURE_FILE, sign);
    }

    public void validateSignature(byte[] originalFile, byte[] signedFile, String publicKeyPath) {
        RSACipher rsaCipher = new RSACipher();
        PublicKey publicKey = rsaCipher.getPublicKey(publicKeyPath);

        Signature sign;
        try {
            sign = Signature.getInstance("SHA1withRSA");
            sign.initVerify(publicKey);
            sign.update(originalFile);
            if (sign.verify(signedFile)) {
                System.out.println("OK");
            } else {
                System.out.println("Não OK");
            }
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            throw new RuntimeException(e);
        }
    }

}
