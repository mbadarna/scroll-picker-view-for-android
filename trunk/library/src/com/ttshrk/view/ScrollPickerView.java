package com.ttshrk.view;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class ScrollPickerView extends LinearLayout {
	private static final int lineWidth = 4;
	private static final int decorationWidth = 4;
	private static final BitmapFactory.Options options = new BitmapFactory.Options();
	private static final Paint[] slotPaints;
	static {
		options.inPreferredConfig = Config.ARGB_8888;
		slotPaints = new Paint[] { new Paint(),new Paint(), new Paint() };
		slotPaints[0].setColor(0xffffffff);
		slotPaints[1].setColor(0x2f1f2f2f);
		slotPaints[2].setColor(0xff0d0e0f);
	}
	public enum ScrollType {
		Ranged, Loop, None,
	}

	private Rect barSrcRect = new Rect();
	private Rect barDstRect = new Rect();
	private Rect srcRect = new Rect();
	private Rect dstRect = new Rect();
	private Bitmap backImage;
	private Bitmap barImage;
	private Rect[] slotRects = new Rect[0];
	private Rect[] decorationRects = new Rect[0];

	/**
	 * 
	 * @param context
	 */
	public ScrollPickerView(Context context) {
		super(context);
		init(context);
	}

	/**
	 * 
	 * @param context
	 * @param attrs
	 */
	public ScrollPickerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	/**
	 * 
	 * @param context
	 */
	private void init(Context context) {
		barImage = BitmapFactory.decodeResource(context.getResources(),
				com.ttshrk.view.R.drawable.com_ttshrk_view_scroll_picker_bar, options);
		this.barSrcRect.set(0, 0, barImage.getWidth(), barImage.getHeight());
		backImage = BitmapFactory.decodeResource(context.getResources(),
				com.ttshrk.view.R.drawable.com_ttshrk_view_scroll_picker_background, options);
		this.srcRect.set(0, 0, backImage.getWidth(), backImage.getHeight());

		this.setBackgroundColor(0xffffffff);		
	}
	
	/**
	 * 
	 * @param startId
	 * @param labels
	 * @param weight
	 * @param scrollType
	 */
	public void addSlot(String[] labels, float weight, ScrollType scrollType) {
		AbstractSlotView tv = SlotViewFactory.create(getContext(), scrollType);
		// tv.setBackgroundColor(Color.BLUE);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		lp.weight = weight;
		tv.setLabels(labels);
		this.addView(tv, lp);
	}

	/**
	 * 
	 * @return
	 */
	public String[] getSlotLabels(int slotIndex) {
		if(slotIndex >= getChildCount()) {
			return new String[0];
		}
		return ((AbstractSlotView)getChildAt(slotIndex)).getLabels();
	}

	/**
	 * 
	 * @return
	 */
	public int[] getAllSlotIndexes() {
		int count = getChildCount();
		int ret[] = new int[count];

		for (int i = 0; i < count; ++i) {
			AbstractSlotView sv = (AbstractSlotView) getChildAt(i);
			ret[i] = sv.getLabelIndex();
		}

		return ret;
	}

	/**
	 * 
	 * @param slotIndex
	 * @return
	 */
	public int getSlotIndex(int slotIndex) {
		if(slotIndex >= getChildCount())
			return 0;
		
		return ((AbstractSlotView)getChildAt(slotIndex)).getLabelIndex();	
	}
	
	/**
	 * 
	 * @param slotIndex
	 * @param labelIndex
	 */
	public void setSlotIndex(int slotIndex, int labelIndex) {
		int count = getChildCount();
		if(slotIndex >= count) {
			return;
		}
		((AbstractSlotView) getChildAt(slotIndex)).setLabelIndex(labelIndex);
	}

	/**
	 * 
	 * @param slotIndex
	 * @param labelIndex
	 */
	public void setSlotIndexByScroll(int slotIndex, int labelIndex) {
		int count = getChildCount();
		if(slotIndex >= count) {
			return;
		}
		((AbstractSlotView) getChildAt(slotIndex)).scrollToLabelIndex(labelIndex);
	}
	
	/**
	 * 
	 * @param w
	 * @param h
	 * @param oldw
	 * @param oldh
	 */
	@Override
	public void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		Log.d("Datetimepicker#onSizeChanged", "w:" + w + ", h:" + h);
		int destWidth = (int)((float)w / srcRect.width() * barSrcRect.width());
		int destHeight = (int)((float)h / srcRect.height() * barSrcRect.height());
		this.barDstRect.set(- destWidth / 2, -destHeight / 2, destWidth / 2, destHeight / 2);
		this.barDstRect.offset(w / 2, h / 2);
		this.dstRect.set(0, 0, w, h);
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		canvas.drawRect(dstRect, slotPaints[2]);

		for (int i = 0; i < slotRects.length; ++i) {
			canvas.drawRect(slotRects[i], slotPaints[0]);
		}
		for (int i = 0; i < decorationRects.length; ++i) {
			canvas.drawRect(decorationRects[i], slotPaints[1]);
		}
		canvas.drawBitmap(barImage, barSrcRect, barDstRect, null);

		int count = getChildCount();
		final long drawingTime = getDrawingTime();
		for (int i = 0; i < count; ++i) {
			drawChild(canvas, getChildAt(i), drawingTime);
		}

		canvas.drawBitmap(backImage, srcRect, dstRect, null);
	}

	/**
	 * 
	 * @param changed
	 * @param l
	 * @param t
	 * @param r
	 * @param b
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		Log.i("Datetimepicker#onLayout", "ch:" + changed + ", l:" + l + ", t:"
				+ t + ", r:" + r + ", b:" + b);
		if (changed) {
			layoutHorizontal(r - l, b - t);
		}
	}

	/**
	 * Position the children during a layout pass if the orientation of this
	 * LinearLayout is set to {@link #HORIZONTAL}.
	 * 
	 * @see #getOrientation()
	 * @see #setOrientation(int)
	 * @see #onLayout(boolean, int, int, int, int)
	 */
	void layoutHorizontal(int w, int h) {
		int count = getChildCount();
		Rect container = new Rect();
		container.top = measureByAspect(h);
		container.left = measureByAspect(w);
		container.bottom = h - container.top;
		container.right = w - container.left - lineWidth * count + lineWidth;

		float totalWeight = 0;
		for (int i = 0; i < count; ++i) {
			View child = getChildAt(i);
			LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)child.getLayoutParams();
			totalWeight += lp.weight;
		}

		int childLeft = container.left;
		int childTop = container.top;
		slotRects = new Rect[count];
		for (int i = 0; i < count; i++) {
			final View child = getChildAt(i);
			LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)child.getLayoutParams();

			int childWidth = measureByWeight(container.width(), lp.weight, totalWeight);
			int childHeight = container.height();

			child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
			// Log.i("onLayout",
			// "l:"+childLeft+", t:"+childTop+", r:"+(childLeft+childWidth)+", b:"+(childTop+childHeight));
			slotRects[i] = new Rect(childLeft, 0, childLeft + childWidth, h);
			childLeft += (childWidth + lineWidth);
		}
		refleshRectDecorations();
	}

	/**
	 * 
	 * @param size
	 * @return
	 */
	private int measureByAspect(int size) {
		return (int) (size * 0.06);
	}

	/**
	 * 
	 * @param size
	 * @param weight
	 * @param totalWeight
	 * @return
	 */
	private int measureByWeight(int size, float weight, float totalWeight) {
		final float weightRatio = 1f / 10f;
		if (weight <= 10) {
			float ratio = weight / totalWeight;
			return (int) (size * ratio);
		} else {
			return (int) (size * weightRatio);
		}
	}

	/**
     * 
     */
	private void refleshRectDecorations() {
		ArrayList<Rect> ret = new ArrayList<Rect>();
		for (int i = 0; i < slotRects.length; ++i) {
			Rect slot = slotRects[i];
			Rect lrect = new Rect(slot.left, slot.top, slot.left
					+ decorationWidth, slot.bottom);
			Rect rrect = new Rect(slot.right - decorationWidth, slot.top,
					slot.right, slot.bottom);

			ret.add(lrect);
			ret.add(rrect);
		}
		decorationRects = (Rect[]) ret.toArray(new Rect[0]);
	}
	
}
