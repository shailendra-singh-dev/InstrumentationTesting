package com.itexico.instrumentationtesting.androidjunitrunner;

import android.os.Parcel;
import android.support.test.filters.RequiresDevice;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by iTexico Developer on 8/18/2016.
 */
@RequiresDevice
@RunWith(AndroidJUnit4.class)
@SmallTest
public class OperandsParcelableUnitTest {

    private static final double OPERAND_FIRST = 100;
    private static final double OPERAND_SECOND = 20;
    private OperandsParcelable mOperandsParcelable;

    @Before
    public void createOperandsParcelable() {
        mOperandsParcelable = new OperandsParcelable();
    }

    @Test
    public void operandsParcelableReadWrite() {
        // Set up the Parcelable object to send and receive.
        mOperandsParcelable.addOperand(OPERAND_FIRST);
        mOperandsParcelable.addOperand(OPERAND_SECOND);

        // Write the data
        Parcel parcel = Parcel.obtain();
        mOperandsParcelable.writeToParcel(parcel, mOperandsParcelable.describeContents());

        // After you're done with writing, you need to reset the parcel for reading.
        parcel.setDataPosition(0);

        // Read the data
        OperandsParcelable createdFromParcel = OperandsParcelable.CREATOR.createFromParcel(parcel);
        List<Double> createdFromParcelData = createdFromParcel.getOperands();

        // Verify that the received data is correct.
        assertThat(createdFromParcelData.size(), is(2));
        assertThat(createdFromParcelData.get(0), is(OPERAND_FIRST));
        assertThat(createdFromParcelData.get(1), is(OPERAND_SECOND));
    }

}
