package quizapp.ui;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import static org.junit.jupiter.api.Assertions.fail;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

/**
 * Every FXUI test extends this class for consitend running of Async Tests
 */
public class FxuiTest extends ApplicationTest {

  protected void clickOnButton(String butttonId) {
    try {
      WaitForAsyncUtils.waitFor(500, TimeUnit.MILLISECONDS, () -> {
        clickOn(butttonId);
        Thread.sleep(10);
        return true;
      });
    } catch (TimeoutException e) {
      fail("No appropriate node available");
    }
  }

  protected void clickOnMenuItem(String menuButtonId, String menuItemId) {
    try {
      WaitForAsyncUtils.waitFor(500, TimeUnit.MILLISECONDS, () -> {
        clickOn(menuButtonId).sleep(10).clickOn(menuItemId);
        Thread.sleep(100);
        return true;
      });
    } catch (TimeoutException e) {
      fail("No appropriate node available");
    }
  }
}
