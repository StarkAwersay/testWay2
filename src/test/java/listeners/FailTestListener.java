package listeners;

import io.qameta.allure.Attachment;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.yandex.qatools.ashot.AShot;
import tests.TestMain;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class FailTestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        try {
            createAttachment();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Attachment(value = "PNG Attachment {0}", type = "image/png")
    private byte[] createAttachment() throws IOException {
        return captureScreenShot();
    }

    private byte[] captureScreenShot() throws IOException {
        BufferedImage image = new AShot().takeScreenshot(TestMain.getDriver()).getImage();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", byteArrayOutputStream);
        byteArrayOutputStream.flush();
        byte[] imageInByte = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return imageInByte;
    }
}
