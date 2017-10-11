#### 特点 

可以根据自己的需求传入相应字段进行创建

#### 使用方式
    
1.在你的项目的 build.gradle 添加以下代码 Add it in your root build.gradle at the end of repositories:
```
allprojects {

  repositories {
  	...
	  	maven { url 'https://jitpack.io' }
    }
} 
```

 
2.在你的 Library 中添加如下代码

``` 
dependencies { 

	   compile 'com.github.CuiBow:DataLoadingView:v1.2'
     
} 
```

3.书写xml
``` 
<com.example.mylibrary.view.baseView.DataShowLayout
       android:id="@+id/views"
       android:layout_width="match_parent"
       android:layout_height="match_parent" >

      <android.support.v7.widget.RecyclerView
          android:id="@+id/rec"
          android:layout_width="match_parent"
          android:layout_height="match_parent"/>
   </com.example.mylibrary.view.baseView.DataShowLayout> 
   ```
   
4.创建自定义构造器(不创建显示默认)
```
    new DataLayoutBuilder.Builder(getApplicationContext())
                .setBtnText("重试")
                .setTextColor(Color.BLACK)
                .setEmptyDrawable(R.drawable.connect)
                .setErrorDrawable(R.drawable.connect)
                .setNetErrorDrawable(R.drawable.connect)
                .setLoadingRes(R.drawable.loadding_image)
                .build(); 
	
   new DataLayoutBuilder.Builder(getApplicationContext())
                .setLoadingRes(view)
                .build(); 
		
   new DataLayoutBuilder.Builder(getApplicationContext())
                .setLoadingRes(null)
                .build(); 
```
  此构造器支持动画、自定义view、默认加载动画三种选项,可以根据自己的需求传入不同的参数
   
5.写法
``` 
        //状态切换
        layout.setStatus(ErrorType.EMPTY);
        layout.setBtnRetryListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //状态点击事件
            }
        });  
```                       
    



