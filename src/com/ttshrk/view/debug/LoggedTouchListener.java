package com.ttshrk.view.debug;

import android.util.Log;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

public class LoggedTouchListener extends SimpleOnGestureListener {
	@Override
	public boolean onDoubleTap(MotionEvent e) {
		Log.i("onDoubleTap", "e.x:" + e.getX() + ", e.y:" + e.getY());
		return false;
	}

	@Override
	public boolean onDoubleTapEvent(MotionEvent e) {
		Log.i("onDoubleTapEvent", "e.x:" + e.getX() + ", e.y:" + e.getY());
		return false;
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) {
		Log.i("onSingleTapConfirmed", "e.x:" + e.getX() + ", e.y:" + e.getY());
		return false;
	}

	@Override
	public boolean onDown(MotionEvent e) {
		Log.i("onDown", "e.x:" + e.getX() + ", e.y:" + e.getY());
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		Log.i("onFling", "vx:" + velocityX + ", vy:" + velocityY + ", e1.x:" + e1.getX() + ", e1.y:" + e1.getY() + ", e2.x:" + e2.getX() + ", e2.y:" + e2.getY());
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		Log.i("onLongPress", "e.x:" + e.getX() + ", e.y:" + e.getY());
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		Log.i("onScroll", "dx:"+distanceX + ", dy:" + distanceY + ", e1.x:" + e1.getX() + ", e1.y:" + e1.getY() + ", e2.x:" + e2.getX() + ", e2.y:" + e2.getY());
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		Log.i("onShowPress", "e.x:" + e.getX() + ", e.y:" + e.getY());
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		Log.i("onSingleTapUp", "e.x:" + e.getX() + ", e.y:" + e.getY());
		return false;
	}
}
