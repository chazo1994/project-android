package com.example.thinh.designfilter;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.thinh.designfilter.Dao.Filter;
import com.example.thinh.designfilter.Util.DrawSignal;
import com.example.thinh.designfilter.Util.TestSignal;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Test.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Test#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Test extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private LinearLayout llChart;
    private DrawSignal drawSignal;
    private Filter filter;

    private LinearLayout tchart;
    private LinearLayout tchartAmplipute;
    private LinearLayout tchartOut;
    private LinearLayout tchartAmpliputeOut;


    private double wc1,wc2;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Test.
     */
    // TODO: Rename and change types and number of parameters
    public static Test newInstance(String param1, String param2) {
        Test fragment = new Test();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public Test() {
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
        View fragmentView = inflater.inflate(R.layout.fragment_test, container, false);

        tchart = (LinearLayout)fragmentView.findViewById(R.id.tchart);
        tchartAmplipute = (LinearLayout) fragmentView.findViewById(R.id.tchartAmplipute);
        tchartOut = (LinearLayout) fragmentView.findViewById(R.id.tchartOut);
        tchartAmpliputeOut= (LinearLayout) fragmentView.findViewById(R.id.tchartAmpliputeOut);


        if(filter.getWs2() == 0){
            wc1 = filter.getWs1()/2;
            wc2 =(Math.PI + filter.getWs1())/2;
        } else {
            wc1 = filter.getWs1()/2;
            wc2 = (filter.getWs1()+filter.getWs2())/2;
        }



        TestSignal test = new TestSignal(wc1,wc2);
        test.setMAXPOIT(filter.getM());
        filter.setTestSignal(test);

        DrawSignal drawTest = new DrawSignal((DrawFilterActivity)getActivity(),tchart);
        drawTest.DrawChart(test.drawTest(),TestSignal.MAXPOIT);

        DrawSignal drawATest = new DrawSignal((DrawFilterActivity)getActivity(),tchartAmplipute);
        // drawATest.drawApliputeForTestSine(wc1,wc2);
        drawATest.DrawFrequency(test.drawAmplipute(),TestSignal.MAXPOIT);


        DrawSignal drawOutputTest = new DrawSignal((DrawFilterActivity)getActivity(),tchartOut);
        drawOutputTest.DrawChart(filter.drawOutput(),TestSignal.MAXPOIT);

        DrawSignal drawAOutputTest = new DrawSignal((DrawFilterActivity)getActivity(),tchartAmpliputeOut);
        drawAOutputTest.DrawFrequency(filter.drawAmpliputeTest(),TestSignal.MAXPOIT);

        TextView tvTest = (TextView)fragmentView.findViewById(R.id.textView21);
        tvTest.setText(tvTest.getText() + "(cos(n*" + wc1 + ") + cos(n*" + wc2 + ")");


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
           /* throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");*/
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

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }
}
