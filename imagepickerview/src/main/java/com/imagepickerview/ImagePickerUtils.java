package com.imagepickerview;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


/**
 * Created by Amit Patoliya on 12/2/18.
 */

public class ImagePickerUtils {

    private boolean isShowRemove;
    private Context mContext;

    private String cameraText;
    private String galleryText;
    private String removePhotoText;
    private String titleText;

    private String cancelText;


    private int cameraRes;
    private int galleryRes;
    private int removeRes;


    private int bottomSheeticonBg;

    public ImagePickerUtils(Builder mBuilder) {
        this.cameraRes = mBuilder.cameraRes;
        this.galleryRes = mBuilder.galleryRes;
        this.removeRes = mBuilder.removeRes;

        this.cameraText = mBuilder.cameraText;
        this.galleryText = mBuilder.galleryText;
        this.removePhotoText = mBuilder.removePhotoText;
        this.titleText = mBuilder.titleText;
        this.isShowRemove = mBuilder.isShowRemove;
        this.mContext = mBuilder.mContext;
        this.bottomSheeticonBg = mBuilder.bottomSheetIconBg;
        this.cancelText = mBuilder.cancelText;

    }

    public String getCancelText() {
        return cancelText;
    }

    public String getCameraText() {
        return cameraText;
    }

    public String getGalleryText() {
        return galleryText;
    }

    public String getRemovePhotoText() {
        return removePhotoText;
    }

    public String getTitleText() {
        return titleText;
    }

    public int getCameraRes() {
        return cameraRes;
    }

    public int getGalleryRes() {
        return galleryRes;
    }

    public int getRemoveRes() {
        return removeRes;
    }

    public boolean isShowRemove() {
        return isShowRemove;
    }

    public Context getmContext() {
        return mContext;
    }

    public int getBottomSheeticonBg() {
        return bottomSheeticonBg;
    }

    public ActionCallBack mActionCallBack;


    public interface ActionCallBack {

        void onCameraClick();

        void onGallaryClick();

        default void onRemovePhotoClick() {
        }

        ;
    }


    private AlertDialog alertDialog;

    public void showDialog(ActionCallBack mActionCallBack) {

        this.mActionCallBack = mActionCallBack;

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder.setTitle(getTitleText());

        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView = mInflater.inflate(R.layout.imgpicker_dialog, null);
        alertDialogBuilder.setView(mView);
        alertDialogBuilder.setNegativeButton(getCancelText(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        TextView txtCamera = mView.findViewById(R.id.txtCamera);
        txtCamera.setCompoundDrawablesWithIntrinsicBounds(getCameraRes(), 0, 0, 0);
        TextView txtGallary = mView.findViewById(R.id.txtGallary);
        txtGallary.setCompoundDrawablesWithIntrinsicBounds(getGalleryRes(), 0, 0, 0);
        TextView txtRemove = mView.findViewById(R.id.txtRemove);
        txtRemove.setCompoundDrawablesWithIntrinsicBounds(getRemoveRes(), 0, 0, 0);

        txtCamera.setText(getCameraText());
        txtGallary.setText(getGalleryText());
        txtRemove.setText(getRemovePhotoText());

        if (isShowRemove) {
            txtRemove.setVisibility(View.VISIBLE);
        } else {
            txtRemove.setVisibility(View.GONE);
        }

        txtCamera.setOnClickListener(mListener);
        txtGallary.setOnClickListener(mListener);
        txtRemove.setOnClickListener(mListener);


        alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }

    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (alertDialog != null) {
                alertDialog.dismiss();
            }

            if (v.getId() == R.id.txtCamera) {

                mActionCallBack.onCameraClick();


            } else if (v.getId() == R.id.txtGallary) {
                mActionCallBack.onGallaryClick();

            } else if (v.getId() == R.id.txtRemove) {
                mActionCallBack.onRemovePhotoClick();

            }


        }
    };


    public void showBottomSheet(FragmentManager manager, ActionCallBack actionCallBack) {
        this.mActionCallBack = actionCallBack;
        BottomSheetImgPickerFragment mFragment = BottomSheetImgPickerFragment.newInstance();
        mFragment.setActiobCallBack(actionCallBack);
        mFragment.setImageUtils(this);
        mFragment.show(manager, "SHOW");


    }

    public static class Builder {

        private int bottomSheetIconBg = R.drawable.bg_bottom_sheet_icon;

        private Context mContext = null;

        private boolean isShowRemove = false;

        private String cameraText = null;
        private String galleryText = null;
        private String removePhotoText = null;
        private String titleText = null;
        private String cancelText = null;

        private int cameraRes = R.drawable.ic_photo_camera;
        private int galleryRes = R.drawable.ic_photo_library;
        private int removeRes = R.drawable.ic_delete_avatar;


        public Builder(Context mContext) {
            this.mContext = mContext;
            cameraText = mContext.getResources().getString(R.string.default_lbl_camera);
            galleryText = mContext.getResources().getString(R.string.default_lbl_gallery);
            removePhotoText = mContext.getResources().getString(R.string.default_lbl_remove);
            titleText = mContext.getResources().getString(R.string.default_lbl_picker_title);
            cancelText = mContext.getResources().getString(R.string.default_lbl_alert_cancel);
        }

        public Builder setCancelText(String cancelText) {
            this.cancelText = cancelText;
            return this;
        }

        public Builder setCameraText(String cameraText) {
            this.cameraText = cameraText;
            return this;
        }

        public Builder setGalleryText(String galleryText) {
            this.galleryText = galleryText;
            return this;
        }

        public Builder setRemovePhotoText(String removePhotoText) {
            this.removePhotoText = removePhotoText;
            return this;
        }

        public Builder setTitleText(String titleText) {
            this.titleText = titleText;
            return this;
        }

        public Builder setCameraRes(int cameraRes) {
            this.cameraRes = cameraRes;
            return this;
        }

        public Builder setGalleryRes(int galleryRes) {
            this.galleryRes = galleryRes;
            return this;
        }

        public Builder setRemoveRes(int removeRes) {
            this.removeRes = removeRes;
            return this;
        }

        public Builder setShowRemove(boolean showRemove) {
            isShowRemove = showRemove;
            return this;
        }

        public Builder setBottomSheetIconBg(int bottomSheetIconBg) {
            this.bottomSheetIconBg = bottomSheetIconBg;
            return this;
        }

        public ImagePickerUtils build() {
            return new ImagePickerUtils(this);
        }
    }


}
