package com.ttshrk.view;


/**
 * 
 * @author ttshrk
 *
 */
class TouchScroll {
	public static final float FLAME = 30;
	public static final float DELAY = 1000 / FLAME;
	private static final float MIN_FLING_SPEED = 7f;
	private static final float MOVE_SPEED = 80f;
	private static final float MOVE_BACK_SPEED = 50f;
	private static final float ADJUST_SPEED = 5f;
	private boolean isFling;
	private boolean isLoop;
	private boolean isMove;
	private float flingVelocity;
	private float minMoveDistance;
	private float moveVelocity;
	private float minPoint;
	private float scrollRange;
	private float adjustSize;
	private float currentPoint;
	private float targetPoint;

	/**
	 * 
	 */
	public TouchScroll() {
	}

	/**
	 * 
	 * @param minPoint
	 * @param maxPoint
	 * @param adjustSize
	 */
	public void setRange(float scrollRange, float adjustSize, boolean isLoop) {
		reset();
		this.isLoop = isLoop;
		this.minPoint = 0;
		this.adjustSize = adjustSize;
		this.scrollRange = scrollRange;
		if(isLoop) {
			this.scrollRange += (adjustSize - 1);
		}
	}
	
	/**
	 * 
	 */
	public void reset() {
		isFling = false;
		isMove = false;
		flingVelocity = 0;
		moveVelocity = 0;
	}
	
	/**
	 * 
	 * @param distance
	 */
	public void scroll(float distance) {
		isFling = false;
		isMove = false;
		updateCurrentPoint(currentPoint + distance);
		
		checkOverScrollRange();
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean checkOverScrollRange() {
		if(!isLoop) {
			if(currentPoint < minPoint) {
				moveVelocity = MOVE_BACK_SPEED;
				targetPoint = minPoint;
			} else if(currentPoint > scrollRange) {
				moveVelocity = -MOVE_BACK_SPEED;
				targetPoint = scrollRange;
			} else {
				return false;
			}
			minMoveDistance = Math.abs(moveVelocity);
			isMove = true;
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param velocity
	 */
	public void fling(float velocity) {
		if(isMove) {
			isFling = false;
			return;
		}
		float v = velocity / FLAME;
		if(Math.abs(v) < MIN_FLING_SPEED) {
			isFling = false;
			return;
		}
		
		this.flingVelocity = v;

		this.isFling = true;
	}
	
	/**
	 * 
	 * @return
	 */
	public void onDown() {
		isFling = false;
		isMove = false;
	}
	
	/**
	 * 
	 */
	public void onUp() {
		if(!isScrollable()) {
			checkAdjustLabel();
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isScrollable() {
		return isFling || isMove;
	}
	
	/**
	 * 
	 */
	private void checkAdjustLabel() {
		if(currentPoint < 0) {
			moveToIndex((int)((currentPoint - adjustSize / 2) / adjustSize), ADJUST_SPEED);
		} else {
			moveToIndex((int)((currentPoint + adjustSize / 2) / adjustSize), ADJUST_SPEED);
		}
	}
	
	/**
	 * 
	 * @param targetIndex
	 */
	public void moveToIndex(int targetIndex) {
		moveToIndex(targetIndex, Math.min(Math.abs((targetIndex * adjustSize - currentPoint) / 3), MOVE_SPEED));
	}

	/**
	 * 
	 * @param targetIndex
	 * @param speed
	 */
	private void moveToIndex(int targetIndex, float speed) {
		targetPoint = targetIndex * adjustSize;
		float target = targetPoint;
		
		if(isLoop) {
			targetPoint %= scrollRange;
			target %= scrollRange;
			float cp = currentPoint % scrollRange;
			float anotherTarget = target < cp ? target + scrollRange : target - scrollRange;
			if(Math.abs(cp - target) > Math.abs(cp - anotherTarget)) {
				target = anotherTarget;
			}
			if(targetPoint < 0)
				targetPoint += scrollRange;
		}
		if(target < currentPoint) {
			moveVelocity = -speed;
		} else if(target > currentPoint) {
			moveVelocity = speed;
		} else {
			isMove = false;
			return;
		}
		
		minMoveDistance = Math.abs(moveVelocity);
		isMove = true;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getCurrentIndex() {
		return (int)(currentPoint / adjustSize);
	}
	
	/**
	 * 
	 * @param pos
	 */
	public void setCurrentIndex(int currentIndex) {
		this.currentPoint = currentIndex * adjustSize;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getCurrentLabelOffset() {
		return (int)(currentPoint % adjustSize);
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean update() {
		// fling
		if(isFling) {
			if(Math.abs(flingVelocity) < MIN_FLING_SPEED) {
				isFling = false;
				if(!isScrollable()) {
					checkAdjustLabel();
				}
				return isScrollable();
			}
			
			updateCurrentPoint(currentPoint - flingVelocity);
			
			flingVelocity *= 0.92f;
			// check over scroll
			if(checkOverScrollRange()) {
				flingVelocity *= 0.6f;
			}
			return true;
		}
		// move to target
		if(isMove) {
			float distance = targetPoint - currentPoint;
			if(Math.abs(distance) < minMoveDistance) {
				updateCurrentPoint(targetPoint);
				isMove = false;
				return isScrollable();
			}
			
			updateCurrentPoint(currentPoint + moveVelocity);
			return true;
		}

		return false;
	}
	
	/**
	 * 
	 * @param point
	 */
	private void updateCurrentPoint(float point) {
		currentPoint = point;
		if(isLoop) {
			currentPoint %= scrollRange;
			if(currentPoint < 0)
				currentPoint += scrollRange;
		}
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "pos:"+currentPoint+", isFl:"+isFling+", isMv:"+isMove;
	}
}
