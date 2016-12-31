# ImageSlider

A android library for Sliding Image. It also includes a custom circular tab indicator. Support for Android 4.0.3 (API 15) and up.

# Demo :
<img src="screenshots/sample_demo.gif" width="50%">

# Implementation :
Only 2 steps are required to implement this into your project :
- Create an instance of SliderFragment.
- Show the fragment into your layout through FragmentTransaction.

### Step 1 :
  A instance of the SliderFragment can be created through 2 methods
  - createWithPath(ArrayList<String> images)
  Takes an ArrayList of String(s) which represents the URL of the image(s) to be shown.
  ```java
  ArrayList<String> mImagesUrl = new ArrayList<>();
  
  mImagesUrl.add("http://buyersguide.caranddriver.com/media/assets/submodel/7757.jpg");
  mImagesUrl.add("http://buyersguide.caranddriver.com/media/assets/submodel/6956.jpg");
  mImagesUrl.add("https://buyersguide.caranddriver.com/media/assets/submodel/1470.jpg");
 
  SliderFragment mSliderFragment = SliderFragment.createWithPath(mImagesUrl);
  ```
  
  - createWithRes(ArrayList<Integer> imagesRes)
  Takes an ArrayList of Integer(s) which represents the drawable Resource ID(s) of the image(s) to be shown.
  ```java
  ArrayList<Integer> mImagesRes = new ArrayList<>();
  
  mImagesRes.add(R.drawable.mac);
  mImagesRes.add(R.drawable.acura);
  mImagesRes.add(R.drawable.mercedes);
  
  SliderFragment mSliderFragment = SliderFragment.createWithRes(mImagesRes);
  ```
  
  ### Step 2 :
    Framgent Trasaction
    
    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@+id/activity_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        tools:context="cameo.code.slider.MainActivity">

        <FrameLayout
            android:id="@+id/slider_panel"
            android:layout_width="match_parent"
            android:layout_height="196dp"/>
    </LinearLayout>    
    ```
    
    ```java
    final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.add(R.id.slider_panel, mSliderFragment);
    transaction.commit();
    ```
    
# Gradle

Step 1. Add the JitPack repository to your build file. Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}
```

Step 2. Add the dependency
```groovy
dependencies {
	compile 'com.github.codecameo:ImageSlider:1.0'
}
```
