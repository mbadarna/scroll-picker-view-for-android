package com.ttshrk.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

class SlotViewOld extends View implements GestureDetector.OnGestureListener/*, GestureDetector.OnDoubleTapListener*/ {
	protected static final int SHOW_LABEL_COUNT = 5;
	protected static final int SHOW_LABEL_PRE_IDX = -3;
	protected static final int SHOW_LABEL_LAST_IDX = 3;
	
	protected static final int INIT_INDEX_TYPE_NONE = 0;
	protected static final int INIT_INDEX_TYPE_SET = 1;
	protected static final int INIT_INDEX_TYPE_MOVE = 2;
	
	protected Handler handler = new Handler();
	private GestureDetector gestureDetector;
	protected TouchScroll scroller = new TouchScroll();
	protected Paint textPaint = new Paint();
	protected int centerOffset;
	protected int fontOffset;
	protected int labelHeight;
	protected int labelRight;
	protected String[] labels = {};
	private ScrollPickerView.ScrollType scrollType;
	private int initializeIndex = -1;
	private int initializeIndexType = INIT_INDEX_TYPE_NONE;
	
	protected Runnable scrollHandler = new Runnable(){
		@Override
		public void run() {
			boolean ret = scroller.update();
			SlotViewOld.this.invalidate();
			
			if(ret) {
				handler.postDelayed(scrollHandler, (long)TouchScroll.DELAY);
			}
		}
	};
	
	public SlotViewOld(Context context, ScrollPickerView.ScrollType scrollType) {
		super(context);
    	setClickable(true);	// ACTION_DOWN以外のイベントも取得
    	setFocusable(true);
    	gestureDetector = new GestureDetector(context, this);
		this.scrollType = scrollType;
		initializeIndexType = INIT_INDEX_TYPE_NONE;
    	
    	// create label paint
    	textPaint.setAntiAlias(true);
    	textPaint.setColor(0xff1f1f1f);
    	textPaint.setTextSize(20);
    	textPaint.setTextAlign(Paint.Align.RIGHT);
		textPaint.setAlpha(0xff);
		textPaint.setShadowLayer(1, 0, 1, Color.WHITE);
	}

	/**
	 * 
	 * @return
	 */
	public int getLabelIndex() {
		int index = scroller.getCurrentIndex();
		int labelCount = labels.length;
		if(scrollType == ScrollPickerView.ScrollType.Loop) {
			index %= labelCount;
			if(index < 0)
				index += labelCount;
			return index;
		} else {
			if(index < 0)
				return 0;
			if(index >= labelCount)
				return labelCount - 1;
			return index;
		}
	}
	
	/**
	 * 
	 * @param index
	 */
	public void setLabelIndex(int labelIndex) {
		handler.removeCallbacks(scrollHandler);
		if(isShown()) {
			scroller.setCurrentIndex(labelIndex);
			invalidate();
		} else {
			setInitializeIndex(labelIndex, INIT_INDEX_TYPE_SET);
		}
	}
	
	/**
	 * 
	 * @param index
	 */
	public void scrollToLabelIndex(int labelIndex) {
		handler.removeCallbacks(scrollHandler);
		if(isShown()) {
			scroller.moveToIndex(labelIndex);
			handler.post(scrollHandler);
		} else {
			setInitializeIndex(labelIndex, INIT_INDEX_TYPE_MOVE);
		}
	}
	
	protected void setInitializeIndex(int labelIndex, int type) {
		initializeIndex = labelIndex;
		initializeIndexType = type;
	}
	
	/**
	 * 
	 * @param labels
	 */
	public void setLabels(String[] labels) {
		this.labels = labels;
	}
	
	/**
	 * 
	 * @return
	 */
	public String[] getLabels() {
		return labels;
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		
		Log.d("SlotView#onSizeChanged", "w:"+w+",h:"+h);
		// label
		labelHeight = (int)(h / SHOW_LABEL_COUNT);
		labelRight = (int)(w * 0.925f);	// ラベルを右寄せにするときの右マージン
		// font
		textPaint.setTextSize(measureFontSizeByLabels(labelHeight, w));

		fontOffset = (int)((- textPaint.ascent()) * 0.35f);	// フォントのたての中心オフセット
		// center
		centerOffset = (int)(h * 0.5f);	// インジケータレベル
		
		switch(scrollType) {
		case Loop:
			scroller.setRange(labelHeight * (labels.length - 1), labelHeight, true);
			break;
		case Ranged:
			scroller.setRange(labelHeight * (labels.length - 1), labelHeight, false);
			break;
		case None:
			scroller.setRange(0, labelHeight, false);
			break;
		}
		
		initialMove();
	}
	
	/**
	 * 
	 */
	protected void initialMove() {
		switch(initializeIndexType) {
		case INIT_INDEX_TYPE_MOVE:
			scroller.moveToIndex(initializeIndex);
			handler.post(scrollHandler);
			initializeIndexType = INIT_INDEX_TYPE_NONE;
			break;
		case INIT_INDEX_TYPE_SET:
			scroller.setCurrentIndex(initializeIndex);
			initializeIndexType = INIT_INDEX_TYPE_NONE;
			break;
		}
	}
	
	/**
	 * 
	 * @param labelHeight
	 * @param width
	 * @return
	 */
    private int measureFontSizeByLabels(int labelHeight, int width) {
    	int maxTextLength = 0;
    	for(String l : labels) {
        	int count = Misc.weightCount(l);
    		if(maxTextLength < count) {
    			maxTextLength = count;
    		}
    	}
    	maxTextLength *= 0.5f;
    	return (int)Math.min(labelHeight * 0.8f, width * 0.8f / maxTextLength);
    }
    
    /**
     * 
     * @param canvas
     */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		if(labels.length == 1) {
			canvas.drawText(labels[0], labelRight, fontOffset + centerOffset, textPaint);
		} else {
			int currentIndex = scroller.getCurrentIndex();
			int currentOffset = scroller.getCurrentLabelOffset();
			int labelCount = labels.length;
			// 前後3件表示
			for(int i = SHOW_LABEL_PRE_IDX; i <= SHOW_LABEL_LAST_IDX; ++i) {
				int idx = currentIndex + i;
				if(scrollType == ScrollPickerView.ScrollType.Loop) {
					idx %= labelCount;
					if(idx < 0)
						idx += labelCount;
					canvas.drawText(labels[idx], labelRight, fontOffset + centerOffset + i * labelHeight - currentOffset, textPaint);
				} else {
					if(idx >= 0 && idx < labels.length) {
						canvas.drawText(labels[idx], labelRight, fontOffset + centerOffset + i * labelHeight - currentOffset, textPaint);
					}		
				}
			}
		}
	}
	
	//
	// scroll events
	//
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(gestureDetector.onTouchEvent(event)) {
			return true;
		}
		if(event.getAction() == MotionEvent.ACTION_UP) {
			scroller.onUp();
			//Log.d("ACTION_UP", scroller.toString());
			if(scroller.isScrollable()) {
				handler.post(scrollHandler);
			}
		}
		return super.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		handler.removeCallbacks(scrollHandler);
		scroller.onDown();
		//Log.d("onDown", scroller.toString());
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		scroller.fling(velocityY);
		//Log.i("onFling", scroller.toString());
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// nothing todo
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		scroller.scroll(distanceY);
		//Log.d("onScroll", scroller.toString());
		invalidate();
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// nothing todo
		//Log.d("onShowPress", scroller.toString());
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// nothing todo
		//Log.d("onSingleTapUp", scroller.toString());
		return false;
	}

	@Override
	protected void onFocusChanged(boolean gainFocus, int direction,
			Rect previouslyFocusedRect) {
		if(!gainFocus) {
			handler.removeCallbacks(scrollHandler);
		}
		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
	}

}
