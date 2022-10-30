package cipher;

import file.FileService;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSACipher {

    public void generateAndSaveKeyPair(String publicKeyPath, String privateKeyPath) {
        FileService fileService = new FileService();
        KeyPairGenerator keyPairGenerator;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar a KeyPairGenerator: ", e);
        }
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        fileService.saveKey(publicKeyPath, Hex.encodeHexString(keyPair.getPublic().getEncoded()));
        fileService.saveKey(privateKeyPath, Hex.encodeHexString(keyPair.getPrivate().getEncoded()));
    }

    public PrivateKey getPrivateKey(String privateKeyPath) {
        FileService fileService = new FileService();
        String fileContent = fileService.getFileContent(privateKeyPath);
        try {
            byte[] rsaPrivateKeyBytes = Hex.decodeHex(fileContent);
            PKCS8EncodedKeySpec keyEncoded = new PKCS8EncodedKeySpec(rsaPrivateKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keyEncoded);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | DecoderException e) {
            throw new RuntimeException(e);
        }
    }

    public PublicKey getPublicKey(String publicKeyPath) {
        FileService fileService = new FileService();
        try {
            String fileContent = fileService.getFileContent(publicKeyPath);
            byte[] rsaPublicKeyBytes = Hex.decodeHex(fileContent);
            X509EncodedKeySpec keyEncoded = new X509EncodedKeySpec(rsaPublicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keyEncoded);
        } catch (NoSuchAlgorithmException | DecoderException | InvalidKeySpecException e) {
            throw new RuntimeException("Erro ao criptografar a chave: ", e);
        }
    }

}
