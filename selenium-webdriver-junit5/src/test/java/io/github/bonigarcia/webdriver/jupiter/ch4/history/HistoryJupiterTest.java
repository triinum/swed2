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
package io.github.bonigarcia.webdriver.jupiter.ch4.history;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

class HistoryJupiterTest {

    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = WebDriverManager.chromedriver().create();
    }

    @AfterEach
    void teardown() throws InterruptedException {
        // FIXME: active wait for manual browser inspection
        Thread.sleep(Duration.ofSeconds(3).toMillis());

        driver.quit();
    }

    @Test
    void testHistory() {
        String baseUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
        String firstPage = baseUrl + "navigation1.html";
        String secondPage = baseUrl + "navigation2.html";
        String thirdPage = baseUrl + "navigation3.html";

        driver.get(firstPage);

        driver.navigate().to(secondPage);
        driver.navigate().to(thirdPage);
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();

        assertThat(driver.getCurrentUrl()).isEqualTo(thirdPage);
    }

}
