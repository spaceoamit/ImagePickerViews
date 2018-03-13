# ImagePickerViews


Add dependencies
  <code>
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

  dependencies {
	        compile 'com.github.spaceoamit:ImagePickerViews:-SNAPSHOT'
	}
  
</code>


Use code to create Object

ImagePickerUtils imgUtils = new ImagePickerUtils.Builder(MainActivity.this)
        .setShowRemove(false)
        .build();
        
Use code to create Dialog for image picker
imgUtils.showDialog(new ImagePickerUtils.ActionCallBack() {
      @Override
      public void onCameraClick() {
        callCamera();

      }

      @Override
      public void onGallaryClick() {

        callGallary();
      }
    });
    
Use code to create Bottomsheet for image picker

   imgUtils.showBottomSheet(getSupportFragmentManager(), new ActionCallBack() {
      @Override
      public void onCameraClick() {
        
      }

      @Override
      public void onGallaryClick() {

      }
    });
    
 Use methods for congiguration
        .setShowRemove(false)
        .setCameraText("Take image")
        .setGalleryText("Select image")
        .setCancelText("Cancel selection")
        .setBottomSheetIconBg(R.drawable...)
        .setCameraRes(R.drawable...)
        .....
        
        
        
 
