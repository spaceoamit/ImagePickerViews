# ImagePickerViews


Add dependencies
``` java
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

  dependencies {
	        compile 'com.github.spaceoamit:ImagePickerViews:-SNAPSHOT'
	}
```



Use code to create Object
``` java
ImagePickerUtils imgUtils = new ImagePickerUtils.Builder(MainActivity.this)
        .setShowRemove(false)
        .build();
```     
  
  
  
Use code to create Dialog for image picker



``` java
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
```
    
    
Use code to create Bottomsheet for image picker
``` java
   imgUtils.showBottomSheet(getSupportFragmentManager(), new ActionCallBack() {
      @Override
      public void onCameraClick() {
        
      }

      @Override
      public void onGallaryClick() {

      }
    });
```
    
    
    
 Use methods for congiguration
 
 ``` java
        .setShowRemove(false)
        .setCameraText("Take image")
        .setGalleryText("Select image")
        .setCancelText("Cancel selection")
        .setBottomSheetIconBg(R.drawable...)
        .setCameraRes(R.drawable...)
        .....
```
        
        
 
