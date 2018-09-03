package rms.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import rms.model.FeaturesDropDown;
import rms.model.Features;
import rms.queries.FeatureQueries;

/**
 * Service class to provide room details
 * @author Re: Syntellions
 *
 */
@Controller 
public class RoomService 
{
	/**
	 * Service method called when RoomDetails page is reached.
	 * A new FeaturesDropDown list is created to call the getFeatureNameAndQuantityByResouceId method from the FeatureQueries class.
	 * The result is added to the ModelMap. 
	 * @param req HttpServletRequest
	 * @param res HttpServletResponse
	 * @param map ModelMap used to store room information
	 * @return The RoomDropDown.jsp view 
	 */
	@RequestMapping(value="/RoomDetails")
	public String defaultService(HttpServletRequest req, HttpServletResponse res, ModelMap map)
	{
		List<FeaturesDropDown> listOfFeatures = new FeatureQueries().getFeatureNameAndQuantityByResouceId();
		map.addAttribute("featData", listOfFeatures);
		
		return "RoomDropDown";  //view name
	}
	

	
 

}
