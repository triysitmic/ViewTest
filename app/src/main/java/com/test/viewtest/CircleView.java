package com.test.viewtest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhengyanda on 2018/4/20.
 */

public class CircleView extends View {
    private static int DEFAULT_SIZE = 200;

    private int mCircleColor = Color.RED;
    private int mBackgroundColor = Color.WHITE;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Context mContext;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initialize(attrs);
    }

    private void initialize(AttributeSet attrs) {

        if (attrs != null) {
            @SuppressLint("Recycle")
            TypedArray array = mContext.obtainStyledAttributes(attrs, R.styleable.CircleView);
            mCircleColor = array.getColor(R.styleable.CircleView_circle_color, mCircleColor);
            mBackgroundColor = array.getColor(R.styleable.CircleView_background_color, mBackgroundColor);
            array.recycle();
        }
    }

    //    <!--场景1-->
    //    android:layout_width="match_parent"
    //    android:layout_height="match_parent"

    //    <!--场景2-->
    //    android:layout_width="100dp"
    //    android:layout_height="100dp"

    //    <!--场景3-->
    //    android:layout_width="wrap_content"
    //    android:layout_height="wrap_content"

    //UNSPECIFIED(未指定),父控件对子控件不加任何束缚，子元素可以得到任意想要的大小，这种MeasureSpec一般是由父控件自身的特性决定的。
    //比如ScrollView，它的子View可以随意设置大小，无论多高，都能滚动显示，这个时候，size一般就没什么意义。

    //EXACTLY(完全)，父控件为子View指定确切大小，希望子View完全按照自己给定尺寸来处理，跟上面的场景1跟2比较相似，
    //这时的MeasureSpec一般是父控件根据自身的MeasureSpec跟子View的布局参数来确定的。一般这种情况下size>0,有个确定值。

    //AT_MOST(至多)，父控件为子元素指定最大参考尺寸，希望子View的尺寸不要超过这个尺寸，跟上面场景3比较相似。
    //这种模式也是父控件根据自身的MeasureSpec跟子View的布局参数来确定的，一般是子View的布局参数采用wrap_content的时候。

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(DEFAULT_SIZE, DEFAULT_SIZE);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(DEFAULT_SIZE, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, DEFAULT_SIZE);
        }

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        int width = getWidth() - getPaddingLeft() - getPaddingRight();
        int height = getHeight() - getPaddingTop() - getPaddingBottom();

        mPaint.setColor(mBackgroundColor);
        canvas.drawRect(0, 0, width, height, mPaint);

        int radius = Math.min(width, height) / 2;
        mPaint.setColor(mCircleColor);
        canvas.drawCircle(paddingLeft + width / 2, paddingTop + height / 2, radius, mPaint);
    }
}
