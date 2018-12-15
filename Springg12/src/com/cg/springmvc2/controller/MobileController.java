package com.cg.springmvc2.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cg.springmvc2.dto.Mobile;
import com.cg.springmvc2.service.MobileService;

@Controller
public class MobileController {
	@Autowired
	MobileService service;
		@RequestMapping(value="/home")
		public String getAllMobile(@ModelAttribute("my") Mobile mob){
			return "AddMobile";
		}
		@RequestMapping(value="addata", method= RequestMethod.POST)
		public String addMobiledata(@Valid@ModelAttribute("my") Mobile mobile,BindingResult result){
			if(result.hasErrors()){
				return "AddMobile";
			}else{
				service.addMobile(mobile);
				//System.out.println(mobile.getMobid()+" " +mobile.getMobNmae()+" "+mobile.getMobPrice()+" " +mobile.getMobCategory()+ " "+mobile.getMobonline());
				return "Welcome";	
			}
	
		}
		
		@RequestMapping(value="showall", method=RequestMethod.GET)
		public ModelAndView showAllMobileData(){
			List<Mobile> allMobile = service.showAllMobile();
			return new ModelAndView("mobileshow", "data", allMobile);
			
		}
		@RequestMapping(value="searchmobile", method=RequestMethod.GET)
		public String searchData(@ModelAttribute("yy") Mobile mob){
			return "searchmobile";
		}
		
		@RequestMapping(value="mobilesearch", method=RequestMethod.POST)
		public ModelAndView dataSearch(@ModelAttribute("yy") Mobile mob){
			Mobile mobSearch = service.searchMobile(mob.getMobid());
			//System.out.println(mobSearch);
			return new ModelAndView("showsearch","temp", mobSearch);
			
		}
		
		@RequestMapping(value="deletemobile", method=RequestMethod.GET)
		public String deleteData(@ModelAttribute("zz") Mobile mob){
			return "deletemobile";
			
		}
		
		@RequestMapping(value="mobiledelete", method=RequestMethod.POST)
		public String dataDelete(@ModelAttribute("zz") Mobile mob){
			service.deleteMobile(mob.getMobid());
			//System.out.println(mobSearch);
			return "deletesucess";
			
}
		@RequestMapping(value="updatemobile", method=RequestMethod.GET)
		public String updatedata(@ModelAttribute("ee") Mobile mob){
			return "updatemobile";
		}
		
		@RequestMapping(value="mobileupdate", method=RequestMethod.POST)
		public String dataUpdate(@ModelAttribute("ee") Mobile mob){
			service.updateMobile(mob.getMobid());
			return "showupdate";
		}
		
}
