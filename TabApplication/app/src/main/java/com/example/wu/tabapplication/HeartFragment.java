package com.example.wu.tabapplication;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;


public class HeartFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private LinearLayout llBarChart;
    private View vChart;

    private String[][] Top10ErrCode = {{"ADFU1", "20"}, {"MBPW2", "19"}, {"ABCDE", "17"}, {"BLFU1", "16"}, {"LCVD3", "15"}, {"ADDK1", "11"}, {"CMFU3", "8"}, {"LCCR2", "7"}, {"QBLE1", "5"}, {"SPNS1", "2"}};


    // TODO: Rename and change types of parameters



    public HeartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HeartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HeartFragment newInstance(String param1, String param2) {
        HeartFragment fragment = new HeartFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_heart, container, false);
    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        llBarChart = (LinearLayout) getActivity().findViewById(R.id.llBarChart);

        try {
            vChart = getBarChart("PQC Top 10 ErrCode", "ErrCode", "QTY", Top10ErrCode);
            llBarChart.removeAllViews();
            //llBarChart.addView(vChart);
            llBarChart.addView(vChart, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 300));

        } catch (Exception e) {

        }

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    private View getBarChart(String chartTitle, String XTitle, String YTitle, String[][] xy) {

        XYSeries Series = new XYSeries(YTitle);

        XYMultipleSeriesDataset Dataset = new XYMultipleSeriesDataset();
        Dataset.addSeries(Series);

        XYMultipleSeriesRenderer Renderer = new XYMultipleSeriesRenderer();
        XYSeriesRenderer yRenderer = new XYSeriesRenderer();
        Renderer.addSeriesRenderer(yRenderer);

        //Renderer.setApplyBackgroundColor(true);			//設定背景顏色
        //Renderer.setBackgroundColor(Color.BLACK);			//設定圖內圍背景顏色
        Renderer.setMarginsColor(Color.WHITE);                //設定圖外圍背景顏色
        Renderer.setTextTypeface(null, Typeface.BOLD);        //設定文字style

        Renderer.setShowGrid(true);                            //設定網格
        Renderer.setGridColor(Color.GRAY);                    //設定網格顏色

        Renderer.setChartTitle(chartTitle);                    //設定標頭文字
        Renderer.setLabelsColor(Color.BLACK);                //設定標頭文字顏色
        Renderer.setChartTitleTextSize(20);                    //設定標頭文字大小
        Renderer.setAxesColor(Color.BLACK);                    //設定雙軸顏色
        Renderer.setBarSpacing(0.5);                        //設定bar間的距離

        //Renderer.setXTitle(XTitle);						//設定X軸文字
        //Renderer.setYTitle(YTitle);						//設定Y軸文字
        Renderer.setXLabelsColor(Color.BLACK);                //設定X軸文字顏色
        Renderer.setYLabelsColor(0, Color.BLACK);            //設定Y軸文字顏色
        Renderer.setXLabelsAlign(Paint.Align.CENTER);                //設定X軸文字置中
        Renderer.setYLabelsAlign(Paint.Align.CENTER);                //設定Y軸文字置中
        Renderer.setXLabelsAngle(-25);                        //設定X軸文字傾斜度

        Renderer.setXLabels(0);                            //設定X軸不顯示數字, 改以程式設定文字
        Renderer.setYAxisMin(0);                            //設定Y軸文最小值

        yRenderer.setColor(Color.RED);                    //設定Series顏色
        //yRenderer.setDisplayChartValues(true);			//展現Series數值

        Series.add(0, 0);
        Renderer.addXTextLabel(0, "");
        for (int r = 0; r < xy.length; r++) {
            //Log.i("DEBUG", (r+1)+" "+xy[r][0]+"; "+xy[r][1]);
            Renderer.addXTextLabel(r + 1, xy[r][0]);
            Series.add(r + 1, Integer.parseInt(xy[r][1]));
        }
        Series.add(11, 0);
        Renderer.addXTextLabel(xy.length + 1, "");

        View view = ChartFactory.getBarChartView(getActivity(), Dataset, Renderer, BarChart.Type.DEFAULT);
        return view;
    }

}
