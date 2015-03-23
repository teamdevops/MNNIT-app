package teamdevops.mnnit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;

/**
 * Created by Deepankar on 21-03-2015.
 */
public class ScrollViewFragment extends ScrollTabHolderFragment implements NotifyingScrollView.OnScrollChangedListener {

    private static final String ARG_POSITION = "position";
    private NotifyingScrollView mScrollView;


    private int mPosition;

    public static Fragment newInstance(int position) {
        ScrollViewFragment frag = new ScrollViewFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        frag.setArguments(b);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPosition = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scrollview, null);
        mScrollView = (NotifyingScrollView) view.findViewById(R.id.scrollview);
        mScrollView.setOnScrollChangedListener(this);

        Button b1 = (Button) view.findViewById(R.id.button1);
        Button b2 = (Button) view.findViewById(R.id.button2);
        Button b3 = (Button) view.findViewById(R.id.button3);
        Button b4 = (Button) view.findViewById(R.id.button4);
        Button b5 = (Button) view.findViewById(R.id.button5);
        Button b6 = (Button) view.findViewById(R.id.button6);
        Button b7 = (Button) view.findViewById(R.id.button7);
        Button b8 = (Button) view.findViewById(R.id.button8);
        Button b9 = (Button) view.findViewById(R.id.button9);
        Button b10 = (Button) view.findViewById(R.id.button10);
        Button b11 = (Button) view.findViewById(R.id.button11);
        Button b12 = (Button) view.findViewById(R.id.button12);
        Button b13 = (Button) view.findViewById(R.id.button13);
        Button b14 = (Button) view.findViewById(R.id.button14);
        Button b15 = (Button) view.findViewById(R.id.button15);

        if (mPosition == 0) {
            b1.setText("1. Button1");
            b2.setText("1. Button2");
            b3.setText("1. Button3");
            b4.setText("1. Button4");
            b5.setText("1. Button5");
            b6.setText("1. Button6");
            b7.setText("1. Button7");
            b8.setText("1. Button8");
            b9.setText("1. Button9");
            b10.setText("1. Button10");
            b11.setText("1. Button11");
            b12.setText("1. Button12");
            b13.setText("1. Button13");
            b14.setText("1. Button14");
            b15.setText("1. Button15");

        }
        if (mPosition == 0) {

            b1.setText("2. Button1");
            b2.setText("2. Button2");
            b3.setText("2. Button3");
            b4.setText("2. Button4");
            b5.setText("2. Button5");
            b6.setText("2. Button6");
            b7.setText("2. Button7");
            b8.setText("2. Button8");
            b9.setText("2. Button9");
            b10.setText("2. Button10");
            b11.setText("2. Button11");
            b12.setText("2. Button12");
            b13.setText("2. Button13");
            b14.setText("2. Button14");
            b15.setText("2. Button15");
        }
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onScrollChanged(ScrollView view, int l, int t, int oldl, int oldt) {
        if (mScrollTabHolder != null)
            mScrollTabHolder.onScroll(view, l, t, oldl, oldt, mPosition);
    }

    @Override
    public void adjustScroll(int scrollHeight, int headerTranslationY) {
        mScrollView.setScrollY(headerTranslationY - scrollHeight);
    }
}
