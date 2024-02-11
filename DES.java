import javax.swing.*;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class DES {
    static byte[] raw;
    String inputMessage, encryptedData, decryptedMessage;

    public DES() {
        try {
            generateSymmetricKey();
            inputMessage = JOptionPane.showInputDialog(null, "Enter message to encrypt");
            byte[] ibyte = inputMessage.getBytes();
            byte[] ebyte = encrypt(raw, ibyte);
            encryptedData = new String(ebyte);
            System.out.println("Encrypted message: " + encryptedData);
            JOptionPane.showMessageDialog(null, "Encrypted Data:\n" + encryptedData);
            byte[] dbyte = decrypt(raw, ebyte);
            decryptedMessage = new String(dbyte);
            System.out.println("Decrypted message: " + decryptedMessage);
            JOptionPane.showMessageDialog(null, "Decrypted Data:\n" + decryptedMessage);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void generateSymmetricKey() {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("DES");
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            kgen.init(56, sr);
            SecretKey skey = kgen.generateKey();
            raw = skey.getEncoded();
            System.out.println("DES Symmetric key = " + bytesToHex(raw));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "DES");
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        return cipher.doFinal(clear);
    }

    private static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "DES");
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        return cipher.doFinal(encrypted);
    }

    public static void main(String args[]) {
        DES des = new DES();
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte aByte : bytes) {
            result.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }
}
