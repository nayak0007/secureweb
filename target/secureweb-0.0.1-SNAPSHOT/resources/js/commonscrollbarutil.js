/**
 * common javaScript to add scrollBar to required element.
 * To be executed in document ready
 * input(s) array of elements
 */


function addScrollBar(id) {	
	$(id).enscroll({
	 	horizontalScrolling: false,
	 	//showOnHover: true,
	 	propagateWheelEvent:false,
	    verticalTrackClass: 'vertical-track2',
	    verticalHandleClass: 'vertical-handle2',
	    horizontalTrackClass: 'horizontal-track2',
	    horizontalHandleClass: 'horizontal-handle2',
	    cornerClass: 'corner2'
    	});	
}

function addVHScrollBar(id) {	
	$(id).enscroll({
		verticalScrolling:true,
	 	horizontalScrolling: true,
	 	//showOnHover: true,
	 	propagateWheelEvent:false,
	    verticalTrackClass: 'vertical-track2',
	    verticalHandleClass: 'vertical-handle2',
	    horizontalTrackClass: 'horizontal-track2',
	    horizontalHandleClass: 'horizontal-handle2',
	    cornerClass: 'corner2'
    	});	
}

function addHoverScrollBar(id) {	
	$(id).enscroll({
	 	horizontalScrolling: false,
	 	showOnHover: true,
	 	propagateWheelEvent:false,
	    verticalTrackClass: 'vertical-track2',
	    verticalHandleClass: 'vertical-handle2',
	    horizontalTrackClass: 'horizontal-track2',
	    horizontalHandleClass: 'horizontal-handle2',
	    cornerClass: 'corner2'
    	});	
}
