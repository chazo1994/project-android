package com.example.thinh.designfilter;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.thinh.designfilter.Dao.Filter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LowPassFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LowPassFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LowPassFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private double ws1;
    private double wp1;
    private double deltaS;
    private double deltaP;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LowPassFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LowPassFragment newInstance(String param1, String param2) {
        LowPassFragment fragment = new LowPassFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public LowPassFragment() {
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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View fragmentView = inflater.inflate(R.layout.fragment_low_pass, container, false);
        Button btLowpassResult = (Button) fragmentView.findViewById(R.id.btLowpassResult);
        Button btHightpassResult = (Button) fragmentView.findViewById(R.id.btHightpassResult);
       // Button btTrans = (Button) fragmentView.findViewById(R.id.btTrans);

        btLowpassResult.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                OnclickLowpassFilter(fragmentView);
            }
        });

        btHightpassResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnclickHightpassFilter(fragmentView);
            }
        });

        /*btTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //(MainActivity)getActivity().
            }
        });*/

        return fragmentView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    public void OnclickLowpassFilter(View fragmentView){
        Intent intent = new Intent(getActivity(),DrawFilterActivity.class);

        if(getDataView(fragmentView)){
            if(wp1>ws1){
                AlertDialog.Builder b = new AlertDialog.Builder(this.getActivity());
                b.setTitle("Error");
                b.setMessage(getResources().getString(R.string.notifation21));
                b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                b.create().show();
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putDouble("passFrequence1", wp1);
            bundle.putDouble("stopFrequence1", ws1);
            bundle.putDouble("deltaPass",deltaP);
            bundle.putDouble("deltaStop",deltaS);

            intent.putExtra("informLowpassFilter",bundle);
            startActivity(intent);
        }
    }

    public void OnclickHightpassFilter(View fragmentView){
        Intent intent = new Intent(getActivity(),DrawFilterActivity.class);

        if(getDataView(fragmentView)){
            if(wp1<ws1){
                AlertDialog.Builder b = new AlertDialog.Builder(this.getActivity());
                b.setTitle("Error");
                b.setMessage(getResources().getString(R.string.notifation22));
                b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                b.create().show();
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putDouble("passFrequence1", wp1);
            bundle.putDouble("stopFrequence1", ws1);
            bundle.putDouble("deltaPass",deltaP);
            bundle.putDouble("deltaStop",deltaS);
            intent.putExtra("informHightpassFilter",bundle);
            startActivity(intent);
        }
    }

    public boolean getDataView(View fragmentView){
        EditText etPassFequence = (EditText)fragmentView.findViewById(R.id.etPassFequence);
        EditText etStopFrequence = (EditText)fragmentView.findViewById(R.id.etStopFrequence);
        EditText etDeltaPass = (EditText)fragmentView.findViewById(R.id.etDeltaPass);
        EditText etDeltatStop = (EditText)fragmentView.findViewById(R.id.etDeltatStop);


        try{
            wp1 = Double.parseDouble(etPassFequence.getText().toString());
            ws1 = Double.parseDouble(etStopFrequence.getText().toString());
            deltaS = Double.parseDouble(etDeltaPass.getText().toString());
            deltaP = Double.parseDouble(etDeltatStop.getText().toString());
        } catch (Exception e){
            AlertDialog.Builder b = new AlertDialog.Builder(this.getActivity());
            b.setTitle("Error");
            b.setMessage(getResources().getString(R.string.notifation1));
            b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            b.create().show();
            return false;
        }

        return true;
    }
}
