package capabilites;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Capabilities {

    public static DesiredCapabilities getCapabilities(String browserName) {
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setBrowserName(browserName);
        capability.setPlatform(Platform.WIN10);
        return capability;
    }

}
