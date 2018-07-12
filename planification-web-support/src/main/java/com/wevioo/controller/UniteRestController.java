package com.wevioo.controller;

import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wevioo.dto.UniteDto;
import com.wevioo.model.Unite;
import com.wevioo.model.enumeration.TypeUnite;
import com.wevioo.service.UniteService;

@RestController
@RequestMapping("/api/unites")
public class UniteRestController {
	@Autowired
	private UniteService uniteService;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<UniteDto> findAllUnite() throws Exception {
		List<Unite> unites = uniteService.findAllUnite();

		// Cast List<Unite> to List<UniteDto> without need to use for loop
		Type listType = new TypeToken<List<UniteDto>>() {
		}.getType();

		return modelMapper.map(unites, listType);
	}

	@RequestMapping(value = "{type}", method = RequestMethod.GET)
	public @ResponseBody List<UniteDto> findUniteByType(@PathVariable TypeUnite type) throws Exception {
		List<Unite> unites = uniteService.findUniteByType(type);
		Type listType = new TypeToken<List<UniteDto>>() {
		}.getType();

		return modelMapper.map(unites, listType);
	}

	@RequestMapping(value = "/unite/{name}", method = RequestMethod.GET)
	public @ResponseBody Unite findUniteByName(@PathVariable String name) throws Exception {
		return uniteService.findUniteByName(name);
	}

	@RequestMapping(value = "/parents", method = RequestMethod.POST)
	public @ResponseBody List<UniteDto> findUniteByParent(@RequestBody Unite parent) throws Exception {
		// if(parent.getType()==TypeUnite.ATELIER) {
		List<Unite> unites = uniteService.findUniteByParent(parent);

		Type listType = new TypeToken<List<UniteDto>>() {
		}.getType();

		return modelMapper.map(unites, listType);

	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Object createUnite(@RequestBody Unite unite, HttpServletRequest request) throws Exception {
		Unite uniteExist = uniteService.findUniteByName(unite.getName().toLowerCase());

		if (uniteExist != null) {
			// Nom unite existe déjà renvoyer erreur
			return null;
		} else {
			return uniteService.createUnite(unite);
		}
	}

}
