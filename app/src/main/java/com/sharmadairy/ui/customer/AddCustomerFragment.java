package com.sharmadairy.ui.customer;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.sharmadairy.DBHandler;
import com.sharmadairy.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddCustomerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddCustomerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddCustomerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddCustomerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddCustomerFragment newInstance(String param1, String param2) {
        AddCustomerFragment fragment = new AddCustomerFragment();
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
        //return inflater.inflate(R.layout.fragment_add_customer, container, false);
        View root = inflater.inflate(R.layout.fragment_add_customer, container, false);

        Button submit = root.findViewById(R.id.add_customer_submitbtn);
        EditText name = root.findViewById(R.id.add_customer_name);
        EditText mobile = root.findViewById(R.id.add_customer_mobile);
        EditText shift = root.findViewById(R.id.add_customer_shift);
        EditText status = root.findViewById(R.id.add_customer_status);
        EditText address = root.findViewById(R.id.add_customer_address);
        EditText startDate = root.findViewById(R.id.add_customer_startdate);
        EditText quantity = root.findViewById(R.id.add_customer_quantity);
        FloatingActionButton camera = root.findViewById(R.id.camera_button);

        DBHandler handler = new DBHandler(root.getContext());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTxt = name.getText().toString();
                String mobileTxt = mobile.getText().toString();
                String statusTxt = status.getText().toString();
                String shiftTxt = shift.getText().toString();
                String addressTxt = address.getText().toString();
                String startDateTxt = startDate.getText().toString();
                String quantityTxt = quantity.getText().toString();
                handler.addNewCustomer(nameTxt, mobileTxt, shiftTxt, startDateTxt, quantityTxt, addressTxt, statusTxt);
                System.out.println("Inserted successfully");

                //setting text to default
                name.setText("");
                mobile.setText("");
                status.setText("");
                shift.setText("");
                address.setText("");
                startDate.setText("");
                quantity.setText("");

             //   handler.addNewHazri("12/12/2022",3,"2");
             //   System.out.println("Hazri created");
                Snackbar.make(view, "Customer added successfully", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        return root;
    }
}