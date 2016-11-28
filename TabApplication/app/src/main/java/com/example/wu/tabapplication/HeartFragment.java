package com.example.wu.tabapplication;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class HeartFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private LinearLayout llChart;
    private View vChart;


    private TimeSeries series1;
    private XYMultipleSeriesDataset dataset1;
    private Handler handler;
    private Random random = new Random();
    private static final int SERIES_NR = 1;
    private static final String TAG = "message";
    private Timer timer = new Timer();
    private TimerTask task;
    private int addY = -1;
    private long addX;
    private GraphicalView chart;
    /**
     * 时间数据
     */
    Date[] xcache = new Date[20];
    /**
     * 数据
     */
    int[] ycache = new int[20];
    // TODO: Rename and change types of parameters

    public Animation zoomin, zoomout; //declared as public
    public ImageView imageView_heart;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_heart, container, false);
    }

    public HeartFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        setView();

        //  animation
        zoomin = AnimationUtils.loadAnimation(getActivity(), R.anim.zoomin);
        zoomout = AnimationUtils.loadAnimation(getActivity(), R.anim.zoomout);



    /* --------------- draw chart ---------------*/


        try {
//            String[][] Top10ErrCode = {{"ADFU1", "20"}, {"MBPW2", "19"}, {"ABCDE", "17"}, {"BLFU1", "16"}, {"LCVD3", "15"}, {"ADDK1", "11"}, {"CMFU3", "8"}, {"LCCR2", "7"}, {"QBLE1", "5"}, {"SPNS1", "2"}};
//            vChart = getBarChart("PQC Top 10 ErrCode", "ErrCode", "QTY", Top10ErrCode);
            vChart = getTrendChart();
            llChart.removeAllViews();
            //llChart.addView(vChart);
            llChart.addView(vChart, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 300));

        } catch (Exception e) {

        }

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                //刷新图表
                updateChart();
                super.handleMessage(msg);
            }
        };
        task = new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 200;
                handler.sendMessage(message);

            }
        };
        timer.schedule(task, 2 * 1000, 1000);
    /* --------------- draw chart ---------------*/


        /* ------------ animation -------------- */
        imageView_heart.setAnimation(zoomin);

        zoomin.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                imageView_heart.startAnimation(zoomout);
            }
        });
        zoomout.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                imageView_heart.startAnimation(zoomin);

            }
        });

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void setView() {
        imageView_heart = (ImageView) getActivity().findViewById(R.id.imageView_heart);
        llChart = (LinearLayout) getActivity().findViewById(R.id.llChart);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    /* --------------- draw chart ---------------*/

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

    private View getTrendChart() {
        //生成图表
        chart = ChartFactory.getTimeChartView(getActivity(), getDateDemoDataset(), getDemoRenderer(), "hh:mm:ss");
        return chart;
    }

    private void updateChart() {
        //设定长度为20
        int length = series1.getItemCount();
        if (length >= 20) length = 20;
        addY = random.nextInt() % 10;
        addX = new Date().getTime();

        //将前面的点放入缓存
        for (int i = 0; i < length; i++) {
            xcache[i] = new Date((long) series1.getX(i));
            ycache[i] = (int) series1.getY(i);
        }
        series1.clear();
        series1.add(new Date(addX), addY);
        for (int k = 0; k < length; k++) {
            series1.add(xcache[k], ycache[k]);
        }
        //在数据集中添加新的点集
        dataset1.removeSeries(series1);
        dataset1.addSeries(series1);
        //曲线更新
        chart.invalidate();
    }

    private XYMultipleSeriesRenderer getDemoRenderer() {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();

        renderer.setChartTitle("時間曲線");//标题
        renderer.setChartTitleTextSize(20);
        renderer.setXTitle("Time");    //x轴说明
        renderer.setAxisTitleTextSize(16);
        renderer.setAxesColor(Color.WHITE);
        renderer.setLabelsTextSize(15);    //数轴刻度字体大小
        renderer.setLabelsColor(Color.WHITE);
        renderer.setLegendTextSize(15);    //曲线说明
        renderer.setXLabelsColor(Color.WHITE);
        renderer.setYLabelsColor(0, Color.WHITE);
        renderer.setBackgroundColor(Color.BLACK);
        renderer.setShowLegend(false);
//        renderer.setMargins(new int[]{20, 30, 100, 0});
        XYSeriesRenderer r = new XYSeriesRenderer();
        r.setColor(Color.BLUE);
        r.setChartValuesTextSize(15);
        r.setChartValuesSpacing(3);
        r.setPointStyle(PointStyle.CIRCLE);
        r.setFillBelowLine(false);
//        r.setFillBelowLineColor(Color.WHITE);
//        r.setFillPoints(true);
        renderer.addSeriesRenderer(r);
//        renderer.setMarginsColor(Color.BLACK);
        renderer.setPanEnabled(false, false);
        renderer.setShowGrid(true);
        renderer.setYAxisMax(50);
        renderer.setYAxisMin(-30);
        renderer.setInScroll(true);  //调整大小
        return renderer;
    }

    /**
     * 数据对象
     *
     * @return
     */
    private XYMultipleSeriesDataset getDateDemoDataset() {
        dataset1 = new XYMultipleSeriesDataset();
        final int nr = 10;
        long value = new Date().getTime();
        Random r = new Random();
        for (int i = 0; i < SERIES_NR; i++) {
            series1 = new TimeSeries("Demo series " + (i + 1));
            for (int k = 0; k < nr; k++) {
                series1.add(new Date(value + k * 1000), 20 + r.nextInt() % 10);
            }
            dataset1.addSeries(series1);
        }
        Log.i(TAG, dataset1.toString());
        return dataset1;
    }
    /* --------------- draw chart ---------------*/


    @Override
    public void onDestroy() {
        //当结束程序时关掉Timer
        timer.cancel();
        super.onDestroy();
    }

    ;


}

