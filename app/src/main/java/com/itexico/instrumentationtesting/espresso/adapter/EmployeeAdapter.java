package com.itexico.instrumentationtesting.espresso.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.itexico.instrumentationtesting.R;
import com.itexico.instrumentationtesting.espresso.utils.Employee;

import java.util.List;

/**
 * Created by iTexico Developer on 8/24/2016.
 */
public class EmployeeAdapter extends BaseAdapter {

    private final List<Employee> mEmployeeList;
    private final Context mContext;

    public EmployeeAdapter(final Context context, List<Employee> list) {
        mEmployeeList = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return null == mEmployeeList || mEmployeeList.isEmpty() ? 0 : mEmployeeList.size();
    }

    @Override
    public Object getItem(int position) {
        return mEmployeeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mEmployeeList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_employee_item, parent, false);
        }
        final Employee employee = (Employee) getItem(position);
        TextView employeeIDView = (TextView) convertView.findViewById(R.id.employee_id);
        employeeIDView.setText(""+employee.getId());

        TextView employeeNameView = (TextView) convertView.findViewById(R.id.employee_name);
        employeeNameView.setText(employee.getName());

        return convertView;
    }
}
