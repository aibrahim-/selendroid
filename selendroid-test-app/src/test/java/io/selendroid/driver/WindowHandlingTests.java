/*
 * Copyright 2012-2013 eBay Software Foundation and selendroid committers.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.selendroid.driver;

import static io.selendroid.waiter.TestWaiter.waitFor;

import java.util.Set;

import io.selendroid.support.BaseAndroidTest;
import io.selendroid.waiter.WaitingConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WindowHandlingTests extends BaseAndroidTest {

  @Test
  public void assertsThatDriverIsAbleToGetCurrentNativeWindow() {
    String windowHandle = driver.getWindowHandle();
    Assert.assertEquals(windowHandle, NATIVE_APP);
  }

  @Test
  public void assertsThatDriverIsAbleToGetCurrentWebViewWindow() {
    openWebViewActivity();
    Assert.assertEquals(driver.getWindowHandle(), NATIVE_APP);
    driver.switchTo().window(WEBVIEW);
    Assert.assertEquals(driver.getWindowHandle(), WEBVIEW);
  }


  @Test
  public void assertsThatDriverIsAbleToGetWindowHandlesOnMainActivity() {
    Set<String> windowHandles = driver.getWindowHandles();
    Assert.assertEquals(windowHandles.iterator().next(), NATIVE_APP);
    Assert.assertEquals(windowHandles.size(), 1);
  }

  @Test
  public void assertsThatDriverIsAbleToGetWindowHandlesOnWebViewActivity() {
    openWebViewActivity();
    Set<String> windowHandles = driver.getWindowHandles();
    Assert.assertEquals(windowHandles.size(), 2);
    Assert.assertTrue(windowHandles.contains(NATIVE_APP), "Should be able to find native context");
    Assert.assertTrue(windowHandles.contains("WEBVIEW_0"), "Should be able to find webview context");
  }

  private void openWebViewActivity() {
    String activityClass = "io.selendroid.testapp." + "WebViewActivity";
    driver.switchTo().window(NATIVE_APP);
    driver.get("and-activity://" + activityClass);
    waitFor(WaitingConditions.driverUrlToBe(driver, "and-activity://WebViewActivity"));
  }
}
