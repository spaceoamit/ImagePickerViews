package com.imagepickerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Amit Patoliya on 12/2/18.
 */

public class BottomSheetImgPickerFragment extends BottomSheetDialogFragment {

    private static final String SHOW_REMOVE = "show_remove_button";

    //private boolean isShowRemove = false;

    public ImagePickerUtils.ActionCallBack mActionCallBack;
    public ImagePickerUtils imageUtils;

    public static BottomSheetImgPickerFragment newInstance() {
        BottomSheetImgPickerFragment fragment = new BottomSheetImgPickerFragment();
        /*Bundle args = new Bundle();
        args.putBoolean(SHOW_REMOVE, isShowRemove);
        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);

    }


    private LinearLayout layCamera;
    private LinearLayout layGallary;
    private LinearLayout layRemove;


    private TextView txtCam;
    private TextView txtLib;
    private TextView txtDel;


    private ImageView imgCam;
    private ImageView imgLib;
    private ImageView imgDel;

    private TextView txtTitle;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.imgpicker_bottom_sheet, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layCamera = view.findViewById(R.id.layCamera);
        layGallary = view.findViewById(R.id.layGallary);
        layRemove = view.findViewById(R.id.layRemove);

        txtCam = view.findViewById(R.id.txtCam);
        txtCam.setText(imageUtils.getCameraText());
        txtLib = view.findViewById(R.id.txtLib);
        txtLib.setText(imageUtils.getGalleryText());
        txtDel = view.findViewById(R.id.txtDel);
        txtDel.setText(imageUtils.getRemovePhotoText());

        imgCam = view.findViewById(R.id.imgCam);
        imgCam.setImageResource(imageUtils.getCameraRes());
        imgLib = view.findViewById(R.id.imgLib);
        imgLib.setImageResource(imageUtils.getGalleryRes());
        imgDel = view.findViewById(R.id.imgDel);
        imgDel.setImageResource(imageUtils.getRemoveRes());


        imgCam.setBackgroundResource(imageUtils.getBottomSheeticonBg());
        imgLib.setBackgroundResource(imageUtils.getBottomSheeticonBg());
        imgDel.setBackgroundResource(imageUtils.getBottomSheeticonBg());


        txtTitle = view.findViewById(R.id.txtTitle);
        txtTitle.setText(imageUtils.getTitleText());

        if (imageUtils.isShowRemove()) {
            layRemove.setVisibility(View.VISIBLE);
        } else {
            layRemove.setVisibility(View.GONE);
        }


        layCamera.setOnClickListener(mListener);
        layGallary.setOnClickListener(mListener);
        layRemove.setOnClickListener(mListener);

    }

    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (v.getId() == R.id.layCamera) {

                mActionCallBack.onCameraClick();
                dismiss();

            } else if (v.getId() == R.id.layGallary) {

                mActionCallBack.onGallaryClick();
                dismiss();

            } else if (v.getId() == R.id.layRemove) {

                mActionCallBack.onRemovePhotoClick();
                dismiss();
            }

        }
    };

    public void setActiobCallBack(ImagePickerUtils.ActionCallBack actiobCallBack) {
        this.mActionCallBack = actiobCallBack;
    }

    public void setImageUtils(ImagePickerUtils imageUtils) {
        this.imageUtils = imageUtils;
    }
}
