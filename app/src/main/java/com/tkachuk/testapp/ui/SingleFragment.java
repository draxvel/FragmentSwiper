package com.tkachuk.testapp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tkachuk.testapp.R;
import com.tkachuk.testapp.util.NotificationManager;

public class SingleFragment extends Fragment {

    private View root;
    private FloatingActionButton fab_plus;
    private FloatingActionButton fab_minus;
    private ImageView iv_create_new_notification;

    private int pageNumber;
    private int pageCount;

    private ICallBack iCallBack;

    public static SingleFragment newInstance(final int pageNumber, final int pageCount) {
        SingleFragment pageFragment = new SingleFragment();
        Bundle arguments = new Bundle();
        arguments.putInt("PAGE_NUMBER", pageNumber);
        arguments.putInt("PAGE_COUNT", pageCount);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment, container, false);
        initView();
        initListener();
        iCallBack = (ICallBack) getActivity();
        return root;
    }

    private void initView() {
        fab_plus = root.findViewById(R.id.fab_plus);
        fab_minus = root.findViewById(R.id.fab_minus);
        iv_create_new_notification = root.findViewById(R.id.iv_create_new_notification);
        TextView tv_id_fragment = root.findViewById(R.id.tv_id_fragment);

        if (getArguments() != null) {
            pageNumber = getArguments().getInt(getResources().getString(R.string.page_number));
            pageCount = getArguments().getInt(getResources().getString(R.string.page_count));
        }else {
            pageNumber = 1;
            pageCount = 1;
        }

        tv_id_fragment.setText(String.valueOf(pageNumber+1));
        if(pageNumber+1==pageCount && pageNumber!=0){
            fab_minus.setVisibility(View.VISIBLE);
        }else fab_minus.setVisibility(View.INVISIBLE);
    }

    private void initListener() {
        fab_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCallBack.createFragment();
            }
        });

        fab_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCallBack.deleteFragment();
            }
        });

        iv_create_new_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager.create(getActivity(), pageNumber, pageCount);
            }
        });
    }
}
