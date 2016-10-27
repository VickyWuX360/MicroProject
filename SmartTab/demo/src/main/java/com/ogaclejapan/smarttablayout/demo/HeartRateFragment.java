package com.ogaclejapan.smarttablayout.demo;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
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


public class HeartRateFragment extends Fragment {

    private LinearLayout llBarChart;
    private View vChart;

    private String[][] Top10ErrCode = {{"ADFU1", "20"}, {"MBPW2", "19"}, {"ABCDE", "17"}, {"BLFU1", "16"}, {"LCVD3", "15"}, {"ADDK1", "11"}, {"CMFU3", "8"}, {"LCCR2", "7"}, {"QBLE1", "5"}, {"SPNS1", "2"}};
    private OnFragmentInteractionListener mListener;

    public HeartRateFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static HeartRateFragment newInstance(String param1, String param2) {
        HeartRateFragment fragment = new HeartRateFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }

       /* // 建立兩個資料串列
        XYSeries series = new XYSeries("London Temperature hourly");
        double[] te = {30, 28, 25, 29, 32, 24};

        XYSeries series2 = new XYSeries("Taipei Temperature hourly");
        double[] te2 = {25, 28, 32, 27, 25, 28};
        for (int i = 0; i < 6; i++) {
            series.add(i + 1, te[i]);
            series2.add(i + 1, te2[i]);
        }
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        dataset.addSeries(series);
        dataset.addSeries(series2);

        // 建立多串列的呈現方式
        int[] colors = new int[]{Color.RED, Color.BLUE};
        PointStyle[] styles = new PointStyle[]{PointStyle.CIRCLE, PointStyle.DIAMOND};
        XYMultipleSeriesRenderer mRenderer = buildRenderer(colors, styles, true);
        setChartSettings(mRenderer, "Line Chart Demo", "X", "Y", 0, 35);

        // 將建置好之chart顯示出來
        GraphicalView chartView = ChartFactory.getLineChartView(getActivity(), dataset, mRenderer);
        getActivity().setContentView(chartView);*/
    }


   /* private XYMultipleSeriesRenderer buildRenderer(int[] colors, PointStyle[] styles, boolean fill) {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        int num = colors.length;
        for (int i = 0; i < num; i++) {
            XYSeriesRenderer r = new XYSeriesRenderer();
            r.setColor(colors[i]);
            r.setPointStyle(styles[i]);
            r.setFillPoints(fill);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    protected void setChartSettings(XYMultipleSeriesRenderer m, String title,
                                    String xTitle, String yTitle, double yMin, double yMax) {
        m.setChartTitle(title);
        m.setXTitle(xTitle);
        m.setYTitle(yTitle);
        m.setYAxisMax(yMax);
        m.setYAxisMin(yMin);
        m.setAxesColor(Color.WHITE);
        m.setMarginsColor(Color.argb(0x00, 0xff, 0x00, 0x00)); // transparent margins
        // Disable Pan on two axis
        m.setPanEnabled(false, false);
        m.setShowGrid(true); // we show the grid
    }*/

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_heart_rate, container, false);
        return rootView;
    }

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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
