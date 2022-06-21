package com.sharmadairy.ui.hazri;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sharmadairy.DBHandler;
import com.sharmadairy.R;
import com.sharmadairy.adaptor.CustomerAdaptor;
import com.sharmadairy.model.Customer;

import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomerListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomerListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    DBHandler handler;
    RecyclerView recyclerView;
    ArrayList<Customer> dataholder;
    TextView date;
    RadioGroup shift;
    RadioButton morning, evening;
    ImageView calender;
    int year,month,day;

    public CustomerListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CustomerListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CustomerListFragment newInstance(String param1, String param2) {
        CustomerListFragment fragment = new CustomerListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_list, container, false);
        handler = new DBHandler(view.getContext());
        date = view.findViewById(R.id.current_date);
        Calendar c = Calendar.getInstance();

        date.setText(c.get(Calendar.DAY_OF_MONTH)+" - "+(c.get(Calendar.MONTH)+1)+" - "+c.get(Calendar.YEAR));
        calender = view.findViewById(R.id.calenderBtn);
        shift = view.findViewById(R.id.shift);
        morning = view.findViewById(R.id.morning_radio);
        evening = view.findViewById(R.id.evening_radio);

        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        date.setText(i2+" - "+(i1+1)+" - "+i);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
      //  int checkedRadioButton = shift.getCheckedRadioButtonId();

      //  dataholder = handler.readFilteredDataOnShift("morning");


       // recyclerView.setAdapter(new CustomerAdaptor(dataholder));

        shift.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.morning_radio) {
                    dataholder = handler.readFilteredDataOnShift("morning");
                } else if(i == R.id.evening_radio) {
                    dataholder = handler.readFilteredDataOnShift("evening");
                }else{
                    dataholder = handler.readAllData();
                }
                recyclerView = view.findViewById(R.id.recview);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(new CustomerAdaptor(dataholder));
            }
        });

        return view;
    }
}