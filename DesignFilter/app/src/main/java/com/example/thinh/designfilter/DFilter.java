package com.example.thinh.designfilter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.thinh.designfilter.Dao.Filter;
import com.example.thinh.designfilter.Util.DrawSignal;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DFilter.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DFilter#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DFilter extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private LinearLayout llChart,llChartAmplupute,llPhase;
    private DrawSignal drawSignal, drawAmplipute, drawPhase;
    private Filter filter;

    private String ws1,ws2,wp1,wp2;
    /*
    * deltaS = delta stop band
    * deltaP =  delta pass band*/
    private String deltaS, deltaP;

    public DFilter() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DFilter.
     */
    // TODO: Rename and change types and number of parameters
    public static DFilter newInstance(String param1, String param2) {
        DFilter fragment = new DFilter();
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
        View fragmentView = inflater.inflate(R.layout.fragment_dfilter, container, false);
        llChart = (LinearLayout) fragmentView.findViewById(R.id.chart);
        llChartAmplupute = (LinearLayout) fragmentView.findViewById(R.id.chartAmplipute);
        llPhase = (LinearLayout)fragmentView.findViewById(R.id.chartPhase);

        drawSignal = new DrawSignal((DrawFilterActivity)getActivity(),llChart);
        drawSignal.DrawChart(filter.getFilters(),filter.getM());

        drawAmplipute = new DrawSignal((DrawFilterActivity)getActivity(),llChartAmplupute);
        drawAmplipute.DrawFrequency(filter.drawGAmplipute(),filter.getM());

        drawPhase = new DrawSignal((DrawFilterActivity)getActivity(),llPhase);
        drawPhase.DrawFrequency(filter.drawAmplipute(),filter.getM());

        return fragmentView;
    }




    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;




        } else {
            /*throw new RuntimeException(context.toString()
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
        void onFragmentInteraction(Uri uri);
    }


    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }
}
