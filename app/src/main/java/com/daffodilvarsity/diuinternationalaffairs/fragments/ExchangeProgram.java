package com.daffodilvarsity.diuinternationalaffairs.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.daffodilvarsity.diuinternationalaffairs.Model.Event;
import com.daffodilvarsity.diuinternationalaffairs.R;
import com.daffodilvarsity.diuinternationalaffairs.adapter.Event_Adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExchangeProgram extends Fragment {

    static SpotsDialog progressDialog;
    String type;
    static Event_Adapter event_adapter;
    static RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    static View parentLayout;
    static List<Event> arrayList = new ArrayList<>();



    public ExchangeProgram() {

        // Required empty public constructor
        type = "Exchange";
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exchange_program, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = (RecyclerView)getActivity().findViewById(R.id.rec_list_exchange);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        parentLayout = getActivity().findViewById(R.id.swipelayoutexchange);

        swipeRefreshLayout = (SwipeRefreshLayout) getActivity().findViewById(R.id.swipelayoutexchange);




        // Toast.makeText(getContext(),list.get(0).getTitle(),Toast.LENGTH_LONG).show();

        progressDialog = new SpotsDialog(getActivity(), R.style.Custom);


        progressDialog.setMessage("please wait..");
        progressDialog.show();
        loaddatafromserver(getActivity());

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                progressDialog.show();
                loaddatafromserver(getActivity());
                swipeRefreshLayout.setRefreshing(false);
            }
        });




    }



    public static List<Event> loaddatafromserver(final Context context)
    {
        final String ShowURL = "http://rjchargesoft.000webhostapp.com/diu_int_affairs/PHP_FILES/dia_show_event.php";
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(context);

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,ShowURL,null,new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {


                    JSONArray eventtablearray = response.getJSONArray("event_info");
                    ;

                    arrayList.clear();

                    for(int i= 0; i<eventtablearray.length();i++) {

                        Event eventItemtemp = new Event();

                        JSONObject eventtableobj = eventtablearray.getJSONObject(i);

                        if(eventtableobj.getString("Type").toString().equals("exchange")){

                            eventItemtemp.setTitle(eventtableobj.getString("Title").toString());
                            eventItemtemp.setDescription(eventtableobj.getString("Description").toString());
                            eventItemtemp.setPicture(eventtableobj.getString("Image_Url"));
                            eventItemtemp.setDeadline(eventtableobj.getString("Deadline").toString());
                            eventItemtemp.setEventtype(eventtableobj.getString("Type").toString());

                            arrayList.add(eventItemtemp);
                        }
                        else {

                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("erorr",""+e);
                }

                event_adapter = new Event_Adapter(arrayList,context,progressDialog);

                recyclerView.setAdapter(event_adapter);

               // Toast.makeText(context,"responding",Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();

                Toast.makeText(context, "not responding", Toast.LENGTH_SHORT).show();
                Snackbar.make(parentLayout,"No Internet Connection",Snackbar.LENGTH_INDEFINITE)
                        .setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                progressDialog.show();
                                ExchangeProgram.loaddatafromserver(context);
                                EventProgram.loaddatafromserver(context);
                                ScholarshipProgram.loaddatafromserver(context);

                            }
                        }).show();

              //  Toast.makeText(context,"not responding" + error.getMessage(),Toast.LENGTH_LONG).show();

            }
        }){

        };

        requestQueue.add(jsonObjectRequest);







        return arrayList;


    }

}
