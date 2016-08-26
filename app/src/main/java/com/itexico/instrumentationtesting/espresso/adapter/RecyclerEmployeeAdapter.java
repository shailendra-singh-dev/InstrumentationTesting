package com.itexico.instrumentationtesting.espresso.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itexico.instrumentationtesting.R;
import com.itexico.instrumentationtesting.espresso.activities.EmployeeDetails;
import com.itexico.instrumentationtesting.espresso.database.Data;
import com.itexico.instrumentationtesting.espresso.utils.Employee;

/**
 * Created by iTexico Developer on 8/26/2016.
 */
public class RecyclerEmployeeAdapter extends RecyclerView.Adapter<RecyclerEmployeeAdapter.EmployeeRowHolder> {

    private static final String TAG = RecyclerEmployeeAdapter.class.getSimpleName();
    private LayoutInflater mLayoutInflater;

    public RecyclerEmployeeAdapter(final Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public EmployeeRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EmployeeRowHolder(mLayoutInflater.inflate(R.layout.layout_employee_item, parent, false));
    }


    @Override
    public void onBindViewHolder(EmployeeRowHolder holder, int position) {
        holder.bindModel(Data.EMPLOYEE_LIST.get(position));
    }


    @Override
    public int getItemCount() {
        Log.i(TAG,"Data.EMPLOYEE_LIST.size():"+Data.EMPLOYEE_LIST.size());
        return Data.EMPLOYEE_LIST.size();
    }

    public static final class EmployeeRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private TextView mEmployeeIDView;
        private TextView mEmployeeNameView;

        public EmployeeRowHolder(View rowView) {
            super(rowView);
            rowView.setOnClickListener(this);
            mEmployeeIDView = (TextView) rowView.findViewById(R.id.employee_id);
            mEmployeeNameView = (TextView) rowView.findViewById(R.id.employee_name);
        }

        public void bindModel(Employee employee) {
            Log.i(TAG, "bindModel(),employee:" + employee);
            mEmployeeIDView.setText("" + employee.getId());
            mEmployeeNameView.setText(employee.getName());
        }


        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), EmployeeDetails.class);
            final Employee employee = Data.EMPLOYEE_LIST.get(getAdapterPosition());
            intent.putExtra(EmployeeDetails.EMPLOYEE_ID, employee.getId());
            intent.putExtra(EmployeeDetails.EMPLOYEE_NAME, employee.getName());
            v.getContext().startActivity(intent);
        }
    }
}

