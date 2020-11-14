package quizapp.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class CryptoUtilTest {

    
  CryptoUtil cryptoUtil = new CryptoUtil();
  
    
  @Test
  public void encryptAndDecryptTest() {
    String testString = "testString";
    String encryptedString = cryptoUtil.encrypt(testString, "secret");
    assertNotEquals(testString, encryptedString);
    String decryptedString = cryptoUtil.decrypt(encryptedString, "secret");
    assertEquals(testString, decryptedString);
    }


  
  

}