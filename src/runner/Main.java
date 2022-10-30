package runner;

import cipher.DigitalSignature;
import cipher.RSACipher;
import file.FileService;

import java.util.Scanner;

public class Main {

    private static final String SIGNATURE_FILE = "./files/FileSignature.txt";

    public static void main(String[] args) {
        RSACipher rsaCipher = new RSACipher();
        FileService fileService = new FileService();
        DigitalSignature digitalSignature = new DigitalSignature();

        // Geração do primeiro par de chaves
        System.out.println("Informe o caminho para armazenar a chave pública:");
        Scanner scanner = new Scanner(System.in);
        String publicKeyA = scanner.nextLine();
        System.out.println("Informe o caminho para armazenar a chave privada:");
        scanner = new Scanner(System.in);
        String privateKeyA = scanner.nextLine();

        // Geração do segundo par de chaves
        System.out.println("\nInforme o caminho para armazenar a chave pública:");
        scanner = new Scanner(System.in);
        String publicKeyB = scanner.nextLine();
        System.out.println("Informe o caminho para armazenar a chave privada:");
        scanner = new Scanner(System.in);
        String privateKeyB = scanner.nextLine();

        rsaCipher.generateAndSaveKeyPair(publicKeyA, privateKeyA);
        rsaCipher.generateAndSaveKeyPair(publicKeyB, privateKeyB);

        // Assinatura do arquivo
        System.out.println("\nInforme o caminho do arquivo para realizar a assinatura:");
        scanner = new Scanner(System.in);
        String fileToSignPath = scanner.nextLine();
        byte[] fileToSign = fileService.loadFile(fileToSignPath);
        digitalSignature.generateSignature(fileToSign, privateKeyA);
        System.out.println("Assinatura gerada para o arquivo com a chave privada: " + privateKeyA);

        // Validação da assinatura
        byte[] signedFile = fileService.loadFile(SIGNATURE_FILE);
        System.out.println("\nValidando com a chave pública: " + publicKeyA);
        System.out.println("Resultado: ");
        digitalSignature.validateSignature(fileToSign, signedFile, publicKeyA);

        System.out.println("\nValidando com a chave pública: " + publicKeyB);
        System.out.println("Resultado: ");
        digitalSignature.validateSignature(fileToSign, signedFile, publicKeyB);
    }

}
