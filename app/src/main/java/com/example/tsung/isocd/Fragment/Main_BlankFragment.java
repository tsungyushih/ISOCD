package com.example.tsung.isocd.Fragment;

import android.app.Fragment;        //不能選port.v4版，不然跟MainActivity不同版會出錯
import android.content.Context;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.tsung.isocd.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Main_BlankFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Main_BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Main_BlankFragment extends Fragment {
    WebView wv;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Main_BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Main_BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Main_BlankFragment newInstance(String param1, String param2) {
        Main_BlankFragment fragment = new Main_BlankFragment();
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
        return inflater.inflate(R.layout.fragment_main__blank, container, false);
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

//    自行寫入onActivityCreated，老師建議不要直接在fragment_a的onClick裡寫click拿來用
//    要了解franment的生命週期，view是在onActivityCreated建立的，而onActivityCreated是在onCreate回傳時使用
//    課本解釋:所依附的 Activity onCreated() 已經執行完畢並準備進入 Activity onStart() 時所回呼的函式，用來設定 Fragment ( 使用 getView()) 或取得 Activity ( 使用 getActivity()) Layout 上的 UI View 物件。

    //    取得Fragment的介面元件,並設定給對應的介面元件,其實就相等Activity的onCreate()
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //findViewByID時,必須先呼叫getView()取得Fragment的View物件才能呼叫findViewByID
        wv=getView().findViewById(R.id.webView);
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl("http://www.cwb.gov.tw/m/eq/recent.htm");
    }
}
