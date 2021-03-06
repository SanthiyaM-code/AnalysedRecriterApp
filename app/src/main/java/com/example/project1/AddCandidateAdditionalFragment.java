package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddCandidateAdditionalFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class AddCandidateAdditionalFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String insertCandidate = "https://demotic-recruit.000webhostapp.com/candidate_insert.php";

    String firstName, lastName, statusItem, mainMail, contactNumber, address, city, zipcode, currentSalary, hourlyRatel, desiredSalary, hourlyRateh, title, companyName, skill, talent, degree, comments, availabilityDate;
    int stateId, countryId, candidateType, preference, sourceId, ownerId;
    Date date = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String strDate = dateFormat.format(date);
    EditText Comments;
    EditText AvailabilityDate;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddCandidateAdditionalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddCandidateAdditionalFragment newInstance(String param1, String param2) {
        AddCandidateAdditionalFragment fragment = new AddCandidateAdditionalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public AddCandidateAdditionalFragment() {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.candidate_additional_info, container, false);
        Button toCandAct = view.findViewById(R.id.button12);
        Comments = view.findViewById(R.id.editTextTextPersonName5);
        AvailabilityDate = view.findViewById(R.id.availability_date);

        Bundle bundle = this.getArguments();
        firstName = bundle.getString("firstName");
        lastName = bundle.getString("lastName");
        statusItem = bundle.getString("status");
        mainMail = bundle.getString("mainMail");
        contactNumber = bundle.getString("contactNumber");
        address = bundle.getString("address");
        city = bundle.getString("city");
        zipcode = bundle.getString("zipcode");
        stateId = bundle.getInt("stateId");
        countryId = bundle.getInt("countryId");
        title =  bundle.getString("title");
        companyName = bundle.getString("companyName");
        candidateType = bundle.getInt("type");
        preference = bundle.getInt("preference");
        sourceId = bundle.getInt("sourceId");
        ownerId = bundle.getInt("ownerId");
        currentSalary = bundle.getString("currentSalary");
        hourlyRatel = bundle.getString("hourlyRatel");
        desiredSalary = bundle.getString("desiredSalary");
        hourlyRateh = bundle.getString("hourlyRateh");
        talent = bundle.getString("talent");
        degree = bundle.getString("degree");
        skill = bundle.getString("skill");
        toCandAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();

            }
        });
        return view;
    }

    private void insertData() {
        comments = Comments.getText().toString().trim();
        availabilityDate= AvailabilityDate.getText().toString().trim();

        StringRequest insertRequest = new StringRequest(Request.Method.POST, insertCandidate, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Intent intent = new Intent(getContext(),CandidateSplashScreen.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String> param = new HashMap<String, String>();
                param.put("first_name", firstName);
                param.put("last_name", lastName);
                param.put("status", statusItem);
                param.put("email", mainMail);
                param.put("phone_number", contactNumber);
                param.put("address", address);
                param.put("city", city);
                param.put("zipcode", zipcode);
                param.put("type", String.valueOf(candidateType));
                param.put("preference", String.valueOf(preference));
                param.put("source_id", String.valueOf(sourceId));
                param.put("owner_id", String.valueOf(ownerId));
                param.put("current_salary", currentSalary);
                param.put("desired_salary", desiredSalary);
                param.put("state_id", String.valueOf(stateId));
                param.put("country_id", String.valueOf(countryId));
                param.put("talent", talent);
                param.put("company_name", companyName);
                param.put("hourly_rate_low", hourlyRatel);
                param.put("hourly_rate_high", hourlyRateh);
                param.put("talent", talent);
                param.put("skill", skill);
                param.put("degree", degree);
                param.put("comments",comments);
                param.put("availability_date", availabilityDate);
                param.put("job", String.valueOf(0));
                param.put("accessibility", String.valueOf(0));
                param.put("created_date", strDate);



                return param;
            }
        };




        Volley.newRequestQueue(getContext()).add(insertRequest);

    }
}