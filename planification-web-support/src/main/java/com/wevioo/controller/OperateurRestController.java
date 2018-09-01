package com.wevioo.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
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

import com.wevioo.dto.OperateurDto;
import com.wevioo.model.Operateur;
import com.wevioo.model.Polyvalence;
import com.wevioo.model.Unite;
import com.wevioo.service.OperateurService;
import com.wevioo.service.PolyvalenceService;

@RestController
@RequestMapping("/api/operateurs")
public class OperateurRestController {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private OperateurService operateurService;
	@Autowired
	private PolyvalenceService polyvalenceService;
	@Autowired
	private ModelMapper operateurMapper;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<OperateurDto> findAllOperateurs() throws Exception {
		List<Operateur> operateurs = operateurService.findAllOperateurs();
		List<OperateurDto> list = null;
		if (operateurs != null) {
			list = new ArrayList<OperateurDto>();
			for (Operateur op : operateurs) {
				OperateurDto dto = operateurMapper.map(op, OperateurDto.class);
				list.add(dto);
			}
		}
		return list;
	}

	@RequestMapping(value = "{firstname}", method = RequestMethod.GET)
	public @ResponseBody Operateur findOperateurByFirstname(@PathVariable String firstname) throws Exception {
		Operateur operateur = operateurService.findOperateurByFirstname(firstname);
		return operateur;
	}
	@RequestMapping(value = "/operators", method = RequestMethod.POST)
	public @ResponseBody List<OperateurDto> findOperateurByUnite(@RequestBody Unite unite) throws Exception {
	List<Operateur> operateurs = operateurService.findOperateurByUnite(unite);
		Type listType = new TypeToken<List<OperateurDto>>() {
		}.getType();

		return modelMapper.map(operateurs, listType);
	}
	@RequestMapping(value = "/qualification", method = RequestMethod.POST)
	public @ResponseBody List<Polyvalence> findPolyvalenceByOperateur(@RequestBody Operateur op) throws Exception {
	List<Polyvalence> polyvalences = polyvalenceService.findPolyvalenceByOperateur(op);
//		Type listType = new TypeToken<List<OperateurDto>>() {
//		}.getType();

		return polyvalences;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Object createOperateur(@RequestBody Operateur operateur, HttpServletRequest request)
			throws Exception { // if (operateur.getMatricule() != null) {

		operateur = operateurService.createOperateur(operateur);

		// }
		return operateur;
	}

	@RequestMapping(value = "{matricule}",method = RequestMethod.DELETE)
	public @ResponseBody void deleteOperateur(@PathVariable String matricule, HttpServletRequest request)
			throws Exception {
		operateurService.deleteOperateur(matricule);

	}

}
