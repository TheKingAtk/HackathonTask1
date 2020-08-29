package com.theking.movieui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.List;

public class SelectionUI extends View {
        private final float SWIPE_SENSITIVITY=300;
    int d_h;
    int extLength=0;
    boolean inAnimation=false;
    Path down;
    detailsIn details;
    float factor2 ,factor1, factor;
    int bSize;
    private final float TOUCH_SENSITIVITY=20;
        Bitmap b,b1,b2;
    int dx;
        float iniX,iniY;
        int i=0;
        int movieNumber=0;
        List<movie> movies;
        private int h,w,centreX,centreY,iniCX,iniCY;
        Context context;
        Paint detPaint,pPaint;
        Paint nameP,ratP,desP;
        public SelectionUI(Context context,List<movie> movies,detailsIn d) {
            super(context);
            this.movies=movies;
            init(context);
            details=d;
        }
        public SelectionUI(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            init(context);

        }
        public SelectionUI(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
            init(context);

        }


    public void init(Context c) {
            context=c;
            down=new Path();
            float widthPixels=getResources().getDisplayMetrics().widthPixels;
            bSize=(int)(widthPixels-90);
            b=BitmapFactory.decodeResource(getResources(),movies.get(movieNumber).getImgResource());
            b1=BitmapFactory.decodeResource(getResources(),movies.get((movieNumber+1==movies.size())?0:movieNumber+1).getImgResource());
            b2=BitmapFactory.decodeResource(getResources(),movies.get((movieNumber+1==movies.size())?
                    1:((movieNumber+2==movies.size())?0:movieNumber+2)).getImgResource());
            factor = (float)bSize/getResources().getDrawable(R.drawable.br49,null).getIntrinsicWidth();
            factor1 = (float)(bSize-20)/getResources().getDrawable(R.drawable.br49,null).getIntrinsicWidth();
            factor2= (float)(bSize-40)/getResources().getDrawable(R.drawable.br49,null).getIntrinsicWidth();
            d_h=getResources().getDrawable(R.drawable.br49,null).getIntrinsicHeight();
            b=Bitmap.createScaledBitmap(b,bSize,(int)(d_h*factor),true);
            b1=Bitmap.createScaledBitmap(b1,bSize-20,(int)(d_h*factor1),true);
            b2=Bitmap.createScaledBitmap(b2,bSize-40,(int)(d_h*factor2),true);
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
        centreX=iniCX=w/2;
        centreY=iniCY=h/2;

    }

        @Override
        protected void onDraw(Canvas canvas) {

            canvas.drawBitmap(b2,iniCX-((float)(w-100)/2)+20,iniCY-((float)iniCY*3/4)+20,null);
            canvas.drawRect(iniCX-((float)(w-90)/2)+20,iniCY-((float)iniCY*3/4)+20+b.getHeight(),
                    iniCX+((float)(w-100)/2)-20,(iniCY-((float)iniCY*3/4)+20+b.getHeight())*2,detPaint);
            canvas.drawRect(iniCX-((float)(w-100)/2)+20,iniCY-((float)iniCY*3/4)+20+b.getHeight(),
                    iniCX+((float)(w-100)/2)-20,(iniCY-((float)iniCY*3/4)+20+b.getHeight())*2,pPaint);
            canvas.drawBitmap(b1,iniCX-((float)(w-100)/2)+10,iniCY-((float)iniCY*3/4)+10,null);
            canvas.drawRect(iniCX-((float)(w-100)/2)+10,iniCY-((float)iniCY*3/4)+10+b.getHeight(),
                    iniCX+((float)(w-100)/2)-10,(iniCY-((float)iniCY*3/4)+10+b.getHeight())*2,detPaint);
            canvas.drawRect(iniCX-((float)(w-100)/2)+10,iniCY-((float)iniCY*3/4)+10+b.getHeight(),
                    iniCX+((float)(w-100)/2)-10,(iniCY-((float)iniCY*3/4)+10+b.getHeight())*2,pPaint);
            canvas.drawText(movies.get((movieNumber+1==movies.size())?0:movieNumber+1).getName(),iniCX-((float)(w-100)/2)+20,iniCY-((float)centreY*3/4)+
                    90+b.getHeight(),nameP);
            canvas.drawText(movies.get((movieNumber+1==movies.size())?0:movieNumber+1).getGenre(),iniCX-((float)(w-100)/2)+20,iniCY-((float)centreY*3/4)+
                    170+b.getHeight(),ratP);
            canvas.drawText("Rating: "+movies.get((movieNumber+1==movies.size())?0:movieNumber+1).getRating(),iniCX-((float)(w-100)/2)+20,iniCY-((float)centreY*3/4)+
                    230+b.getHeight(),ratP);
            i=0;
            for(String s:lines(movies.get((movieNumber+1==movies.size())?0:movieNumber+1).getDescription()).split("\n"))
            {
                canvas.drawText(s,iniCX-((float)(w-100)/2)+20,iniCY-((float)centreY*3/4)+
                        300+i*50+b.getHeight(),desP);
                i++;
            }

            canvas.drawBitmap(b,centreX-((float)(w-90)/2),centreY-((float)centreY*3/4),null);
            canvas.drawRect(centreX-((float)(w-100)/2),centreY-((float)centreY*3/4)+b.getHeight(),
                    centreX+((float)(w-100)/2),(centreY-((float)centreY*3/4)+b.getHeight())*2+extLength,detPaint);
            canvas.drawRect(centreX-((float)(w-100)/2),centreY-((float)centreY*3/4)+b.getHeight(),
                    centreX+((float)(w-100)/2),(centreY-((float)centreY*3/4)+b.getHeight())*2+extLength,pPaint);
            canvas.drawText(movies.get(movieNumber).getName(),centreX-((float)(w-100)/2)+20,centreY-((float)centreY*3/4)+
                    90+b.getHeight(),nameP);
            canvas.drawText(movies.get(movieNumber).getGenre(),centreX-((float)(w-100)/2)+20,centreY-((float)centreY*3/4)+
                    170+b.getHeight(),ratP);
            canvas.drawText("Rating: "+movies.get(movieNumber).getRating(),centreX-((float)(w-100)/2)+20,centreY-((float)centreY*3/4)+
                    230+b.getHeight(),ratP);
            i=0;
            for(String s:lines(movies.get(movieNumber).getDescription()).split("\n"))
            {
                canvas.drawText(s,centreX-((float)(w-100)/2)+20,centreY-((float)centreY*3/4)+
                        300+i*50+b.getHeight(),desP);
                i++;
            }
            down.moveTo(centreX-30,(centreY-((float)centreY*3/4)+b.getHeight())*2-60);
            down.lineTo(centreX,(centreY-((float)centreY*3/4)+b.getHeight())*2-30);
            down.lineTo(centreX+30,(centreY-((float)centreY*3/4)+b.getHeight())*2-60);
            canvas.drawPath(down,detPaint);
            down.reset();
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x=event.getX();
        float y=event.getY();

        ValueAnimator animator=ValueAnimator.ofInt(0,500);
        animator.setDuration(200);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                inAnimation=true;
                centreX=centreX+dx*(int)animation.getAnimatedValue();
                invalidate();
                if((int)animation.getAnimatedValue()==500) {

                    b.recycle();
                    b2.recycle();
                    b1.recycle();
                    if(movieNumber==movies.size()-1) movieNumber=0;
                    else movieNumber++;
                    b=BitmapFactory.decodeResource(getResources(),movies.get(movieNumber).getImgResource());
                    b1=BitmapFactory.decodeResource(getResources(),movies.get((movieNumber+1==movies.size())?0:movieNumber+1).getImgResource());
                    b2=BitmapFactory.decodeResource(getResources(),movies.get((movieNumber+1==movies.size())?
                            1:((movieNumber+2==movies.size())?0:movieNumber+2)).getImgResource());
                    b=Bitmap.createScaledBitmap(b,bSize,(int)(d_h*factor),true);
                    b1=Bitmap.createScaledBitmap(b1,bSize-20,(int)(d_h*factor1),true);
                    b2=Bitmap.createScaledBitmap(b2,bSize-40,(int)(d_h*factor2),true);
                    centreX=iniCX;
                    invalidate();
                    inAnimation=false;
                }
            }

        });
        if(!inAnimation) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                iniX=x;
                iniY=y;
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(y-iniY)>50)
                {   extLength=(int)(y-iniY);
                    invalidate();
                    if(Math.abs(y-iniY)>400) details.movie(movieNumber);

                }
                else if(Math.abs(x-iniX)>SWIPE_SENSITIVITY) {
                    dx=(x-iniX)>0?1:-1;
                    animator.start();}
                else if (Math.abs(x-iniX)>TOUCH_SENSITIVITY)
                    {
                        dx=(int)(x-iniX)>0?30:-30;
                         centreX+=dx;
                         invalidate();
                    }


                break;
                case MotionEvent.ACTION_UP:
                    centreX=iniCX;
                    extLength=0;
                    invalidate();
        }}
            return true;
    }
    interface detailsIn {
            public void movie(int movie);
    }
}
