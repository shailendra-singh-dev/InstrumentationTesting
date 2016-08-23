package com.itexico.instrumentationtesting.androidjunitrunner;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iTexico Developer on 8/18/2016.
 */
public class OperandsParcelable implements Parcelable {
    // Used to store the data to be used by the activity.
    private final List<Double> mData = new ArrayList<>();

    protected OperandsParcelable(Parcel in) {
        int length = in.readInt();
        double[] operands = new double[length];
        // Read the arrays in a specific order.
        in.readDoubleArray(operands);
        // Reset the data container and update the data.
        mData.clear();
        for (int i = 0; i < length; i++) {
            mData.add(operands[i]);
        }
    }

    public static final Creator<OperandsParcelable> CREATOR = new Creator<OperandsParcelable>() {
        @Override
        public OperandsParcelable createFromParcel(Parcel in) {
            return new OperandsParcelable(in);
        }

        @Override
        public OperandsParcelable[] newArray(int size) {
            return new OperandsParcelable[size];
        }
    };

    public OperandsParcelable() {
    }

    public List<Double> getOperands() {
        return mData;
    }

    public void addOperand(final double operand) {
        mData.add(operand);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        double[] operands = new double[mData.size()];
        for (int i = 0; i < operands.length; i++) {
            operands[i] = mData.get(i);
        }
        out.writeInt(operands.length);
        out.writeDoubleArray(operands);
    }
}
