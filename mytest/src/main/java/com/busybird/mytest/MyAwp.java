package com.busybird.mytest;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.SystemClock;
import android.text.LoginFilter;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Created by WuSihai on 2017/2/23.
 */

public class MyAwp extends AppWidgetProvider {
    public static final String TAG = "MyAwp";

    public static final String CLICK_ACTION = "com.test.action.CLICK";

    public MyAwp(){
        super();
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        super.onReceive(context, intent);
        String action = intent.getAction();
        Log.i(TAG,"onReceive:action=="+action);
        if(action.equals(CLICK_ACTION)){
            Toast.makeText(context,"点他！",Toast.LENGTH_SHORT);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.super_qq);
                    AppWidgetManager instance = AppWidgetManager.getInstance(context);
                    for (int i=0;i<37;i++){
                        float degree = (i*10)%360;
                        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
                        remoteViews.setImageViewBitmap(R.id.iv_widget,rotateBitmap(context,bitmap,degree));
                        Intent intentClick = new Intent();
                        intentClick.setAction(CLICK_ACTION);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intentClick, 0);
                        remoteViews.setOnClickPendingIntent(R.id.iv_widget,pendingIntent);
                        instance.updateAppWidget(new ComponentName(context,MyAwp.class),remoteViews);
                        SystemClock.sleep(30);

                    }
                }
            }).start();

        }
    }
    private Bitmap rotateBitmap(Context context,Bitmap bitmap,float degreee){
        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.setRotate(degreee);
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),matrix,true);
        return bitmap1;

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.i(TAG,"onUpdate");
        int length = appWidgetIds.length;
        Log.i(TAG,"length=="+length);
        for (int i=0;i<length;i++){
            int appWidgetId = appWidgetIds[i];
            onWidgetUpdate(context,appWidgetManager,appWidgetId);
        }
    }

    private void onWidgetUpdate(Context context,AppWidgetManager appWidgetManager,int appWidgetId){
        Log.i(TAG,"appWidgetId=="+appWidgetId);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);

        Intent intentClick = new Intent();
        intentClick.setAction(CLICK_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intentClick, 0);
        remoteViews.setOnClickPendingIntent(R.id.iv_widget,pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId,remoteViews);
    }
}
