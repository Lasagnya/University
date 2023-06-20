import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Passwords {
    Map<String, byte[]> passwords;
    public Passwords() {
        passwords = new TreeMap<>();
    }

    public void input() throws IOException, NoSuchAlgorithmException {
        Scanner sc = new Scanner(new File("users.txt"));
        sc.useDelimiter("[\\s\r\n\t]+");
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String login;
        String pass;
        while (sc.hasNext()) {
            md.reset();
            login = sc.next();
            pass = sc.next();
            md.update(pass.getBytes(StandardCharsets.UTF_8));
            passwords.put(login, md.digest());
        }
        sc.close();
    }

    public boolean verify(String login, String pass) throws NoSuchAlgorithmException {
        if (passwords.containsKey(login)) {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.reset();
            md.update(pass.getBytes(StandardCharsets.UTF_8));
            return Arrays.equals(passwords.get(login), md.digest());
        }
        return false;
    }

}
