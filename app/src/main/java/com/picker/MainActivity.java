package com.picker;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.imagepickerview.ImagePickerUtils;
import com.imagepickerview.ImagePickerUtils.ActionCallBack;
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo;
import com.miguelbcr.ui.rx_paparazzo2.entities.FileData;
import com.miguelbcr.ui.rx_paparazzo2.entities.Options;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


  private ImagePickerUtils imgUtils;

  private Options options = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    imgUtils = new ImagePickerUtils.Builder(MainActivity.this)
        .setShowRemove(false)
        .build();

    //for Crop feature & get customization
    options = new Options();
    options.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary));
    options.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
    options.setActiveWidgetColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
    options.setAspectRatio(1, 1);

    ImageView img = findViewById(R.id.img);
    img.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        onClickPickture();
      }
    });

  }


  public void onClickPickture() {

    imgUtils.showBottomSheet(getSupportFragmentManager(), new ActionCallBack() {
      @Override
      public void onCameraClick() {
        callCamera();
      }

      @Override
      public void onGallaryClick() {
        callGallary();
      }
    });


  }

  private void callGallary() {

    RxPaparazzo.single(this)
        .crop(options)
        .usingGallery()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(response -> {
          if (response.resultCode() != RESULT_OK) {
            response.targetUI().showUserCanceled();
            return;
          }

          response.targetUI().loadImage(response.data());
        });


  }

  private void callCamera() {

    RxPaparazzo.single(this)
        .crop(options)
        .usingCamera()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(response -> {
          if (response.resultCode() != RESULT_OK) {
            response.targetUI().showUserCanceled();
            return;
          }

          response.targetUI().loadImage(response.data());
        });
  }

  private void showUserCanceled() {

    //TODO When user cancel Action
  }

  private void loadImage(FileData data) {

    //TODO load file from file data

  }
}
