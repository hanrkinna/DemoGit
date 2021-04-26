package com.example.demo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		  System.out.println(11);
		  if(true){
			  System.out.println("我是测试的！！！！");
		  }
	}

	class MultiTouchView extends View {

		private float x1;
		private float y1;
		private float x2;
		private float y2;

		public MultiTouchView(Context context) {
			super(context);


		}

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			System.out.print("测试冲突");
			float size = event.getSize();

			int szi = (int) size;
			int dxi = szi >> 12;
			int dyit = ((1 << 12) - 1);
			int dyi = szi & dyit;

			DisplayMetrics metrics = getResources().getDisplayMetrics();
			float dx = metrics.widthPixels * dxi / (float) dyit;
			float dy = metrics.heightPixels * dyi / (float) dyit;

			x1 = event.getX();
			y1 = event.getY();

			x2 = x1 + dx;
			y2 = y1 + dy;

			invalidate();

			return true;
		}

		@Override
		protected void onDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			super.onDraw(canvas);

			float r = (float) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2)
					* (y1 - y2)) / 2;
			r = 50 >= r ? 50 : r;

			Paint paint = new Paint();
			paint.setColor(Color.BLUE);
			canvas.drawCircle(x1, y1, r, paint);

		}

	}

}
