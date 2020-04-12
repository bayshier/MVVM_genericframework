package com.demo.mine.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.demo.R;

/**
 * 自定义底部弹窗
 */
public class BottomDialog extends DialogFragment {

    private TextView mCancel;

    private LinearLayout mContentLayout;

    private Builder mBuilder;

    private static BottomDialog getInstance(Builder builder) {
        BottomDialog dialog = new BottomDialog();
        dialog.mBuilder = builder;
        return dialog;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().setBackgroundDrawableResource(mBuilder.mBackgroundShadowColor);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Holo_Light_NoActionBar);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_pop_up_dialog, null);
        initView(view);
        registerListener(view);
        setCancelable(true);
        return view;
    }


    private void initView(View view) {
        mContentLayout = (LinearLayout) view.findViewById(R.id.pop_dialog_content_layout);
        mCancel = (TextView) view.findViewById(R.id.cancel);
        initItemView();
    }


    private void registerListener(View view) {

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    dismiss();
                }
                return false;
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }


    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            super.show(manager, tag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initItemView() {
        //循环添加item
        for (int i = 0; i < mBuilder.mDataArray.length; i++) {
            final PopupDialogItem dialogItem = new PopupDialogItem(getContext());
            dialogItem.refreshData(mBuilder.mDataArray[i]);

            //最后一项隐藏分割线
            if (i == mBuilder.mDataArray.length - 1) {
                dialogItem.hideLine();
            }

            if (mBuilder.mLineColor != 0) {
                dialogItem.setLineColor(mBuilder.mLineColor);
            }

            mContentLayout.addView(dialogItem);

            dialogItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBuilder.mListener.onDialogClick(dialogItem.getItemContent());
                    if (mBuilder.mIsCallBackDismiss) dismiss();
                }
            });
        }
    }


    public static class Builder {

        private String[] mDataArray;

        private BottomDialogOnClickListener mListener;

        private int mLineColor;

        private boolean mIsCallBackDismiss = false;

        private int mBackgroundShadowColor = R.color.transparent_70;

        /**
         * 设置item数据
         */
        public Builder setDialogData(String[] dataArray) {
            mDataArray = dataArray;
            return this;
        }

        /**
         * 设置监听item监听器
         */
        public Builder setItemOnListener(BottomDialogOnClickListener listener) {
            mListener = listener;
            return this;
        }

        /**
         * 设置item分隔线颜色
         */
        public Builder setItemLineColor(int color) {
            mLineColor = color;
            return this;
        }

        /**
         * 设置是否点击回调取消dialog
         */
        public Builder setCallBackDismiss(boolean dismiss) {
            mIsCallBackDismiss = dismiss;
            return this;
        }

        /**
         * 设置dialog背景阴影颜色
         */
        public Builder setBackgroundShadowColor(int color) {
            mBackgroundShadowColor = color;
            return this;
        }

        public BottomDialog create() {
            return BottomDialog.getInstance(this);
        }

        public BottomDialog show(FragmentManager manager, String tag) {
            BottomDialog dialog = create();
            dialog.show(manager, tag);
            return dialog;
        }

    }

    public interface BottomDialogOnClickListener {
        /**
         * item点击事件回调
         *
         * @param tag item字符串 用于识别item
         */
        void onDialogClick(String tag);
    }

}
