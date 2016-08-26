package com.itexico.instrumentationtesting.espresso.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.itexico.instrumentationtesting.R;
import com.itexico.instrumentationtesting.espresso.activities.EmployeeDetails;
import com.itexico.instrumentationtesting.espresso.adapter.EmployeeAdapter;
import com.itexico.instrumentationtesting.espresso.adapter.ViewPagerFragmentAdapter;
import com.itexico.instrumentationtesting.espresso.database.Data;
import com.itexico.instrumentationtesting.espresso.utils.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iTexico Developer on 8/26/2016.
 */
public class ViewPagerFragment extends Fragment {

    private int mPagerTabsIndex;
    private List<Employee> mEmployeeList = new ArrayList<Employee>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPagerTabsIndex = getArguments().getInt(ViewPagerFragmentAdapter.PAGER_TABS_INDEX);
        if (mPagerTabsIndex == 0) {
            mEmployeeList.addAll(Data.EMPLOYEE_LIST);
        } else {
            mEmployeeList.addAll(Data.HR_EMPLOYEE_LIST);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_view_fragment, container, false);
        ListView listView = (ListView) view.findViewById(R.id.view_pager_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ViewPagerFragment.this.getActivity(), EmployeeDetails.class);
                final Employee employee = (Employee) parent.getItemAtPosition(position);
                intent.putExtra(EmployeeDetails.EMPLOYEE_ID, employee.getId());
                intent.putExtra(EmployeeDetails.EMPLOYEE_NAME, employee.getName());
                ViewPagerFragment.this.getActivity().startActivity(intent);
            }
        });
        listView.setAdapter(new EmployeeAdapter(getActivity(), mEmployeeList));
        return view;
    }

}
