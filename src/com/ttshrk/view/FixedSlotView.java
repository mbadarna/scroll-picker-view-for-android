package com.ttshrk.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

class FixedSlotView extends AbstractSlotView {
	
	public FixedSlotView(Context context) {
		super(context);
	}

	/**
	 * 
	 * @return
	 */
	public int getLabelIndex() {
		return 0;
	}
	
	/**
	 * 
	 * @param index
	 */
	public void setLabelIndex(int labelIndex) {
		// nothing to do
	}
	
	/**
	 * 
	 * @param index
	 */
	public void scrollToLabelIndex(int labelIndex) {
		// nothing to do
	}
	
	protected void setInitializeIndex(int labelIndex, int type) {
		// nothing to do
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		
		scroller.setRange(0, labelHeight, false);
	}
	
    /**
     * 
     * @param canvas
     */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		canvas.drawText(labels[0], labelRight, fontOffset + centerOffset, textPaint);
	}
	
	//
	// scroll events
	//
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// nothing todo
		return super.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// nothing todo
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// nothing todo
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// nothing todo
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// nothing todo
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// nothing todo
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// nothing todo
		return false;
	}

	@Override
	protected void onFocusChanged(boolean gainFocus, int direction,
			Rect previouslyFocusedRect) {
		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
		// nothing todo
	}
}
