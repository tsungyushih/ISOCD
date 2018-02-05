package com.example.tsung.isocd.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.net.http.SslError;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.tsung.isocd.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Main_BlankFragment2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Main_BlankFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Main_BlankFragment2 extends Fragment {
    ImageView iv,iv2;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Main_BlankFragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Main_BlankFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static Main_BlankFragment2 newInstance(String param1, String param2) {
        Main_BlankFragment2 fragment = new Main_BlankFragment2();
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
        return inflater.inflate(R.layout.fragment_main__blank_fragment2, container, false);
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
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        new Thread()
        {
            @Override
            public void run() {
                super.run();

                String str_url = "https://www.epa.gov.tw/images/AqiForecast.png";
                String str_url2 = "http://www.cwb.gov.tw/V7/observe/UVI/Data/UVI.png";

                URL url;
                URL url2;

                try {
                    url = new URL(str_url);
                    url2 = new URL(str_url2);

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();

                    conn.connect();
                    conn2.connect();

                    InputStream inputStream = conn.getInputStream();
                    InputStream inputStream2 = conn2.getInputStream();

                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ByteArrayOutputStream bos2 = new ByteArrayOutputStream();

                    byte[] buf = new byte[1024];
                    byte[] buf2 = new byte[1024];

                    final int totalLength = conn.getContentLength();
                    final int totalLength2 = conn2.getContentLength();

                    int sum = 0;
                    int sum2 = 0;

                    int length,length2;


                    while( ((length = inputStream.read(buf)) != -1))
                    {
                        sum += length;
                        final  int tmp = sum;

                        bos.write(buf,0,length);

                        while( ((length2 = inputStream2.read(buf2)) != -1))
                        {
                            sum2 += length2;
                            final  int tmp2 = sum2;

                            bos2.write(buf2,0,length2);

                        }
                    }

                    iv=getView().findViewById(R.id.imageView);
                    iv2=getView().findViewById(R.id.imageView2);

                    byte[] results = bos.toByteArray();
                    byte[] results2 = bos2.toByteArray();

//                    在寫下面兩行時發生"Only the original thread that created a view hierarchy can touch its views"的錯誤，因為只有創建視圖層次結構的原始線程才能操作它的View
//                    經老師指點，要用20180115_02中教的runOnUiThread解決，解決方式如下
//                    final Bitmap bmp = BitmapFactory.decodeByteArray(results, 0, results.length);       //要把位元陣列轉成圖
//                    iv.setImageBitmap(bmp);

                    final Bitmap bmp = BitmapFactory.decodeByteArray(results, 0, results.length);
                    final Bitmap bmp2 = BitmapFactory.decodeByteArray(results2, 0, results2.length);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            iv.setImageBitmap(bmp);
                            iv2.setImageBitmap(bmp2);
                            iv.setVisibility(View.VISIBLE);
                            iv2.setVisibility(View.VISIBLE);
                        }
                    });


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }

}
