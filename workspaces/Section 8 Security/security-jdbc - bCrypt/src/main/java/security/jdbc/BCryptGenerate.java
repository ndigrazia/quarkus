package security.jdbc;

import java.security.SecureRandom;
import java.util.Base64;

import org.wildfly.security.password.PasswordFactory;
import org.wildfly.security.password.interfaces.BCryptPassword;
import org.wildfly.security.password.spec.EncryptablePasswordSpec;
import org.wildfly.security.password.spec.IteratedSaltedPasswordAlgorithmSpec;
import org.wildfly.security.WildFlyElytronProvider;

public class BCryptGenerate {
    
    public static void main(String[] args) throws Exception {

        final WildFlyElytronProvider ELYTRON_PROVIDER = new WildFlyElytronProvider();
        final String PASSWORD = "quickstartPwd1!";

        PasswordFactory passwordFactory = PasswordFactory.getInstance(BCryptPassword.ALGORITHM_BCRYPT, ELYTRON_PROVIDER);
   
        int iterationCount = 10;
        
        byte[] salt = new byte[BCryptPassword.BCRYPT_SALT_SIZE];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
   
        IteratedSaltedPasswordAlgorithmSpec iteratedAlgorithmSpec = new IteratedSaltedPasswordAlgorithmSpec(iterationCount, salt);
        EncryptablePasswordSpec encryptableSpec = new EncryptablePasswordSpec(PASSWORD.toCharArray(), iteratedAlgorithmSpec);
   
        BCryptPassword original = (BCryptPassword) passwordFactory.generatePassword(encryptableSpec);
        byte[] hash = original.getHash();
        Base64.Encoder encoder = Base64.getEncoder();
        String encodedHash = encoder.encodeToString(hash);
        String encodedSalt = encoder.encodeToString(salt);
   
        System.out.println("Encoded Hash = " + encodedHash);
        System.out.println("Encoded Salt = " + encodedSalt);
   }
}
