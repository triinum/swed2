/*
 * (C) Copyright 2021 Boni Garcia (https://bonigarcia.github.io/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package io.github.bonigarcia.webdriver.junit4.ch10.mobile;

import static io.github.bonigarcia.wdm.WebDriverManager.isOnline;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppiumJupiterTest {

    WebDriver driver;

    @Before
    public void setup() throws MalformedURLException {
        URL appiumServerUrl = new URL("http://localhost:4723/wd/hub/");
        assumeThat(isOnline(new URL(appiumServerUrl, "status"))).isTrue();

        ChromeOptions options = new ChromeOptions();
        options.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        options.setCapability(MobileCapabilityType.DEVICE_NAME,
                "Nexus 5 API 30");

        driver = new AppiumDriver(appiumServerUrl, options);
    }

    @After
    public void teardown() throws InterruptedException {
        if (driver != null) {
            // FIXME: pause for manual browser inspection
            Thread.sleep(Duration.ofSeconds(3).toMillis());

            driver.quit();
        }
    }

    @Test
    public void testAppium() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }

}
