/**
 * 
 */
function smoothTransition(element_obj, element_obj2, element_obj3 ){
	element_obj.css("opacity", "0.0");
	element_obj2.css("opacity", "0.0");
	element_obj.fadeTo(800, 1, function(){});
	element_obj2.fadeTo(800, 1, function(){});
	
	element_obj.submit(function(e)
    		{
    		    e.preventDefault();
    		    element_obj2.fadeTo(100, 0, function(){
    		    	element_obj3.fadeTo(300, 0, function(){
    		    		element_obj.fadeTo(500, 0, function()
    			    	{
    		    			element_obj.unbind("submit").submit();
    			    	});
    		    	});
    		    });
    		    
    		});
}//smoothTransition() Ends 


function animateTitle(){
	anime.timeline({
		loop : false
	}).add({
		targets : '.ml15 .word',
		scale : [ 24, 1 ],
		opacity : [ 0, 1 ],
		easing : "easeOutCirc",
		duration : 900,
		delay : function(el, i) {
			return 120 * i;
		}
	});
}