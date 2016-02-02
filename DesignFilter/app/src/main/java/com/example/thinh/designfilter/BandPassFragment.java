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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BandPassFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BandPassFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BandPassFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private double ws1;
    private double wp1;
    private double ws2;
    private double wp2;
    private double deltaS;
    private double deltaP;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BandPassFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BandPassFragment newInstance(String param1, String param2) {
        BandPassFragment fragment = new BandPassFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public BandPassFragment() {
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
        final View fragmentView = inflater.inflate(R.layout.fragment_band_pass, container, false);

        Button btBandpassResult = (Button) fragmentView.findViewById(R.id.btBandpassResult);
        Button btBandstopResult = (Button) fragmentView.findViewById(R.id.btBandstopResult);

        btBandpassResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnclickBandpassFilter(fragmentView);
            }
        });
        btBandstopResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnclickBandstopFilter(fragmentView);
            }
        });

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

    public void OnclickBandpassFilter(View fragmentView){
        Intent intent = new Intent(getActivity(),DrawFilterActivity.class);

        if(getDataView(fragmentView)){
            if((wp1<ws1) || (wp2>ws2)){
                AlertDialog.Builder b = new AlertDialog.Builder(this.getActivity());
                b.setTitle("Error");
                b.setMessage(getResources().getString(R.string.notifation31));
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
            bundle.putDouble("passFrequence2", wp2);
            bundle.putDouble("stopFrequence2", ws2);
            bundle.putDouble("deltaPass",deltaP);
            bundle.putDouble("deltaStop",deltaS);

            intent.putExtra("informBandpassFilter",bundle);
            startActivity(intent);
        }
    }

    public void OnclickBandstopFilter(View fragmentView){
        Intent intent = new Intent(getActivity(),DrawFilterActivity.class);

        if(getDataView(fragmentView)){
            if((wp1>ws1) || (wp2<ws2)){
                AlertDialog.Builder b = new AlertDialog.Builder(this.getActivity());
                b.setTitle("Error");
                b.setMessage(getResources().getString(R.string.notifation32));
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
            bundle.putDouble("passFrequence2", wp2);
            bundle.putDouble("stopFrequence2", ws2);
            bundle.putDouble("deltaPass",deltaP);
            bundle.putDouble("deltaStop",deltaS);

            intent.putExtra("informBandstopFilter",bundle);
            startActivity(intent);
        }
    }

    public boolean getDataView(View fragmentView){
        EditText etPassFequence1 = (EditText)fragmentView.findViewById(R.id.etPassFequencePB);
        EditText etStopFrequence1 = (EditText)fragmentView.findViewById(R.id.etStopFrequencePB);
        EditText etPassFequence2 = (EditText)fragmentView.findViewById(R.id.etPassFequencePB2);
        EditText etStopFrequence2 = (EditText)fragmentView.findViewById(R.id.etStopFrequencePB2);
        EditText etDeltaPass = (EditText)fragmentView.findViewById(R.id.etDeltaPassPB);
        EditText etDeltatStop = (EditText)fragmentView.findViewById(R.id.etDeltatStopPB);

        try{
            wp1 = Double.parseDouble(etPassFequence1.getText().toString());
            ws1 = Double.parseDouble(etStopFrequence1.getText().toString());
            wp2 = Double.parseDouble(etPassFequence2.getText().toString());
            ws2 = Double.parseDouble(etStopFrequence2.getText().toString());
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
