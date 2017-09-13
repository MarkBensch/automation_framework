package com.example.UI.pages

import com.example.util.GebHelperTrait
import geb.Page

class BasePage extends Page implements GebHelperTrait{

	/**
	 * This is a mouse inter action example for Sliding the mouse
	static content = {
		ratingSliderHandle { $(".ui-slider-handle") }
	}

	void moveRatingSlider(Integer rating) {
		// Slider is 400 pixels wide and starts at 1,
		// so each notch above 1 is 100 pixels apart
		Integer numPixelsX = (rating - 1) * 100

		interact {
			clickAndHold(ratingSliderHandle)
			moveByOffset(numPixelsX, 0)
			release()
		}
	}*/

}
