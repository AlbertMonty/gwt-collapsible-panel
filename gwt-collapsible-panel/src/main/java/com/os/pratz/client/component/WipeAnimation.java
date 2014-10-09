package com.os.pratz.client.component;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Widget;

/**
 * A show hide animation for widgets/elements in a wipe style
 */
   class WipeAnimation extends Animation {

		private int initialSize;
		private int endSize;

		private Element element;

		public WipeAnimation(Widget widget){
			this.element = widget.getElement();
			element.getStyle().setOverflow(Overflow.HIDDEN);
		}
		
		public WipeAnimation(Element element) {
			this.element = element;
			element.getStyle().setOverflow(Overflow.HIDDEN);
		}

		@Override
		protected void onComplete() {
			if(endSize==0){
				element.getStyle().setDisplay(Display.NONE);
				return;
			}
			element.getStyle().setHeight(endSize, Unit.PX);
		}

		@Override
		protected void onUpdate(double progress) {
			double delta = (endSize - initialSize)*progress;
			double newSize = initialSize+delta;
			element.getStyle().setHeight(newSize, Unit.PX);
		}

		/**
		 * Hides the provided widget/element
		 * 
		 * @param duration the time for the animation in <b>ms</b>
		 * @param height the height from which the animation starts in <b>px</b>
		 */
		public void animatedHide(int duration, int height){
			this.initialSize = height;
			this.endSize = 0;
			if(duration==0){
				onComplete();
				return;
			}
			run(duration);
		}

		/**
		 * Shows the provided widget/element
		 * 
		 * @param duration the time for the animation in <b>ms</b>
		 * @param height the height till which the animation works in <b>px</b>
		 */
		public void animatedShow(int duration, int height){
			element.getStyle().setDisplay(Display.BLOCK);					
			this.endSize = height;
			this.initialSize = 0;

			if(duration==0){
				onComplete();
				return;
			}
			run(duration);
		}

	}
