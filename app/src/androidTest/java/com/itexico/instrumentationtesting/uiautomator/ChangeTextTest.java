package com.itexico.instrumentationtesting.uiautomator;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by iTexico Developer on 9/7/2016.
 */
@SdkSuppress(minSdkVersion = 18)
@RunWith(AndroidJUnit4.class)
public class ChangeTextTest {

    private static final String PACKAGE_NAME
            = "com.itexico.instrumentationtesting";

    private static final int LAUNCH_TIMEOUT = 5000;

    private static final String STRING_TO_BE_TYPED = "Hello UIAutomator!!";

    private UiDevice mDevice;

    @Before
    public void setUp() {
        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // Start from the home screen
        mDevice.pressHome();

        // Wait for launcher
        final String launcherPackage = getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);

        // Launch the blueprint app
        Context context = InstrumentationRegistry.getContext();
        Intent intentToResolve = new Intent(Intent.ACTION_MAIN);
        intentToResolve.addCategory(Intent.CATEGORY_LAUNCHER);
        intentToResolve.setPackage("com.android.launcher");
        ResolveInfo ri = context.getPackageManager().resolveActivity(intentToResolve, 0);
        if (ri != null) {
            Intent intent = new Intent(intentToResolve);
            intent.setClassName(ri.activityInfo.applicationInfo.packageName, ri.activityInfo.name);
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
        }

        // Wait for the app to appear
        mDevice.wait(Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)), LAUNCH_TIMEOUT);
    }

    /**
     * Uses package manager to find the package name of the device launcher. Usually this package
     * is "com.android.launcher" but can be different at times. This is a generic solution which
     * works on all platforms.`
     */
    private String getLauncherPackageName() {
        // Create launcher Intent
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);

        // Use PackageManager to get the launcher package name
        PackageManager pm = InstrumentationRegistry.getContext().getPackageManager();
        ResolveInfo resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo.activityInfo.packageName;
    }

    @Test
    public void changeText() {
//        // Verify the test is displayed in the Ui
//        UiObject2 homeUiautomatorButton = mDevice
//                .wait(Until.findObject(By.res(PACKAGE_NAME, "espresso_home_type_text_button")),
//                        500 /* wait 500ms */);
//        homeUiautomatorButton.click();

        mDevice.findObject(By.res(PACKAGE_NAME, "text_user_input")).setText(STRING_TO_BE_TYPED);
        mDevice.findObject(By.res(PACKAGE_NAME, "text_change_button_same_activity")).click();

        // Verify the test is displayed in the Ui
        UiObject2 changedText = mDevice
                .wait(Until.findObject(By.res(PACKAGE_NAME, "text_to_be_changed")),
                        500 /* wait 500ms */);
        assertThat(changedText.getText(), is(equalTo(STRING_TO_BE_TYPED)));
    }
}
