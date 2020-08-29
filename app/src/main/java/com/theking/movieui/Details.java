package com.theking.movieui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Details extends View {
    movie m;
    int i=0;
    Paint detPaint,pPaint,nameP,ratP,desP;
    int h,w,centreX,centreY;
    int bSize;
    float factor,factor1,factor2;
    int d_h;
    Bitmap b1,b2,b;
    int centreImg=1;
    public Details(Context context,movie m) {
        super(context);
        this.m=m;
        init();
    }

    public Details(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Details(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void init() {
        float widthPixels=getResources().getDisplayMetrics().widthPixels;
        bSize=(int)(widthPixels-350);
        b= BitmapFactory.decodeResource(getResources(),m.getImages().get(centreImg==0?m.getImages().get(m.getImages().size()-1):(centreImg-1)));
        b1=BitmapFactory.decodeResource(getResources(),m.getImages().get(centreImg));
        b2=BitmapFactory.decodeResource(getResources(),m.getImages().get(centreImg==(m.getImages().size()-1)?0:(centreImg+1)));
        factor = (float)bSize/b.getWidth();
        factor1=(float)bSize/b1.getWidth();
        factor2=(float)bSize/b2.getWidth();
        d_h=getResources().getDrawable(R.drawable.br49,null).getIntrinsicHeight();
        b=Bitmap.createScaledBitmap(b,bSize,400,true);
        b1=Bitmap.createScaledBitmap(b1,bSize,400,true);
        b2=Bitmap.createScaledBitmap(b2,bSize,400,true);
        detPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        detPaint.setColor(Color.GRAY);
        detPaint.setStyle(Paint.Style.STROKE);
        detPaint.setStrokeWidth(5);
        pPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        nameP=new Paint();
        ratP=new Paint();
        desP =new Paint();
        pPaint.setColor(Color.WHITE);
        pPaint.setStyle(Paint.Style.FILL);
        nameP.setTextSize(70);
        nameP.setFakeBoldText(true);
        nameP.setColor(Color.DKGRAY);
        ratP.setTextSize(30);
        ratP.setColor(Color.BLACK);
        desP.setTextSize(30);
        desP.setColor(Color.GRAY);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        h=MeasureSpec.getSize(heightMeasureSpec);
        w=MeasureSpec.getSize(widthMeasureSpec);
        centreX=w/2;
        centreY=h/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(b,centreX-((float)(b1.getWidth())/2)-((float)b.getWidth())-20,centreY-((float)centreY*3/4),null);
        canvas.drawBitmap(b1,centreX-((float)(b1.getWidth())/2),centreY-((float)centreY*3/4),null);
        canvas.drawBitmap(b2,centreX+((float)(b1.getWidth())/2)+20,centreY-((float)centreY*3/4),null);
        canvas.drawRect(centreX-((float)(w-100)/2),20+centreY-((float)centreY*3/4)+b.getHeight(),
                centreX+((float)(w-100)/2),(centreY-((float)centreY*3/4)+b.getHeight())*2+1000,detPaint);
        canvas.drawRect(centreX-((float)(w-100)/2),20+centreY-((float)centreY*3/4)+b.getHeight(),
                centreX+((float)(w-100)/2),(centreY-((float)centreY*3/4)+b.getHeight())*2+1000,pPaint);
        canvas.drawText(m.getName(),centreX-((float)(w-100)/2)+20,100+centreY-((float)centreY*3/4)+
                90+b.getHeight(),nameP);
        canvas.drawText(m.getGenre(),centreX-((float)(w-100)/2)+20,100+centreY-((float)centreY*3/4)+
                170+b.getHeight(),ratP);
        canvas.drawText("Rating: "+m.getRating(),centreX-((float)(w-100)/2)+20,100+centreY-((float)centreY*3/4)+
                230+b.getHeight(),ratP);
        i=0;
        for(String s:lines(m.getDescription()).split("\n"))
        {
            canvas.drawText(s,centreX-((float)(w-100)/2)+20,100+centreY-((float)centreY*3/4)+
                    300+i*50+b.getHeight(),desP);
            i++;
        }

    }
    public String lines(String line) {
        int ems=(w-100)/15;
        StringBuilder lines=new StringBuilder();
        int i=0;
        for(char c: line.toCharArray()) {
            i++;
            lines.append(c);
            if(i==ems) {
                if(Character.isAlphabetic(c)) {
                    lines.append("-\n");
                    i=0;
                }
                else {
                    lines.append("\n");
                    i=0;
                }
            }
        }
        return lines.toString();
    }
}
