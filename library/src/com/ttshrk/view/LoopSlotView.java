package com.ttshrk.view;

import android.content.Context;
import android.graphics.Canvas;

class LoopSlotView extends AbstractSlotView {
	
	public LoopSlotView(Context context) {
		super(context);
	}

	/**
	 * 
	 * @return
	 */
	public int getLabelIndex() {
		int index = scroller.getCurrentIndex();
		int labelCount = labels.length;
		
		index %= labelCount;
		if(index < 0)
			index += labelCount;
		return index;
	}
	
	/**
	 * 
	 * @param index
	 */
	public void setLabelIndex(int labelIndex) {
		int labelCount = labels.length;
		super.setLabelIndex(labelIndex % labelCount);
	}
	
	/**
	 * 
	 * @param index
	 */
	public void scrollToLabelIndex(int labelIndex) {
		int labelCount = labels.length;
		super.scrollToLabelIndex(labelIndex % labelCount);
	}

	/**
	 * 
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		
		scroller.setRange(labelHeight * (labels.length - 1), labelHeight, true);
		
		initialMove();
	}
	
    /**
     * 
     * @param canvas
     */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		int currentIndex = scroller.getCurrentIndex();
		int currentOffset = scroller.getCurrentLabelOffset();
		int labelCount = labels.length;
		// 前後3件表示
		for(int i = SHOW_LABEL_PRE_IDX; i <= SHOW_LABEL_LAST_IDX; ++i) {
			int idx = currentIndex + i;
			idx %= labelCount;
			if(idx < 0)
				idx += labelCount;
			canvas.drawText(labels[idx], labelRight, fontOffset + centerOffset + i * labelHeight - currentOffset, textPaint);
		}
	}
	
}
