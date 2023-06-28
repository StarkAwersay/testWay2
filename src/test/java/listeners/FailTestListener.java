package listeners;

import io.qameta.allure.Attachment;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.yandex.qatools.ashot.AShot;
import tests.UI.TestMain;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class FailTestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object currentClass = result.getInstance();
        WebDriver webDriver = ((TestMain) currentClass).getDriver();
        try {
            createAttachment(webDriver);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Attachment(value = "PNG Attachment {0}", type = "image/png")
    private byte[] createAttachment(WebDriver driver) throws IOException {
        BufferedImage image = new AShot().takeScreenshot(driver).getImage();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", byteArrayOutputStream);
        byteArrayOutputStream.flush();
        byte[] imageInByte = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return imageInByte;
    }
}
