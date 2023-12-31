import java.util.*;
import java.io.*;

public class Railfence {
    public static int key[] = new int[8];
    public char mat[][] = new char[11][8];
    public char cmat[][] = new char[11][8];
    int rows = 0;

    public String rfencryption(String text1) {
        int len = text1.length();
        String text = text1;
        for (int i = 0; i < len % 7; i++) {
            text += 'X';
        }
        len = text.length();
        rows = len / 7;
        int k = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= 7; j++) {
                mat[i][j] = text.charAt(k++);
            }
        }
        String enctxt = "";
        int j = 1;
        while (j <= 7) {
            for (int p = 0; p < 7; p++) {
                if (j == key[p]) {
                    j = p + 1;
                    break;
                }
            }
            for (int i = 1; i <= rows; i++) {
                enctxt += mat[i][j];
            }
            j++;
        }
        System.out.println(enctxt);
        return enctxt;
    }

    public String rfdecryption(String txt, int plength) {
        String dectxt = "";
        int q = 0;
        int j = 1;
        while (j <= 7) {
            for (int p = 0; p < 7; p++) {
                if (key[p] == j) {
                    j = p + 1;
                    break;
                }
            }
            for (int i = 1; i <= rows; i++) {
                cmat[i][j] = txt.charAt(q++);
            }
            j++;
        }
        for (int i = 1; i <= rows; i++) {
            for (j = 1; j <= 7; j++) {
                dectxt += cmat[i][j];
            }
        }
        if (plength < dectxt.length()) {
            dectxt = dectxt.substring(0, plength);
        }
        return dectxt;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Railfence rf = new Railfence();

        System.out.println("Enter key");
        for (int i = 0; i < 7; i++) {
            String c = br.readLine();
            try {
                key[i] = Integer.parseInt(c);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter integers only.");
                return;
            }
        }
        
        System.out.println("Enter PLAIN TEXT");
        String plain = sc.next();
        int k = plain.length();
        System.out.println(plain);

        String ctext = rf.rfencryption(plain);
        System.out.println("\nCIPHER TEXT: " + ctext);

        String plaintext = rf.rfdecryption(ctext, k);
        System.out.println("\nPLAIN TEXT: " + plaintext);

        sc.close();
        br.close();
    }
}
