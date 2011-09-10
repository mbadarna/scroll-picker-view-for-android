package com.ttshrk.view;

import android.content.Context;

class SlotViewFactory {
	public static AbstractSlotView create(Context context, ScrollPickerView.ScrollType scrollType) {
		switch(scrollType) {
		case Loop:
			return new LoopSlotView(context);
		case Ranged:
			return new RangedSlotView(context);
		case None:
			return new FixedSlotView(context);
		}
		return null;
	}
}
