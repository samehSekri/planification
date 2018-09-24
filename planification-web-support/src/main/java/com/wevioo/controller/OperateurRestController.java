package com.wevioo.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import com.wevioo.utility.MessageUtil;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wevioo.dto.OperateurDto;
import com.wevioo.exception.ApiException;
import com.wevioo.model.Article;
import com.wevioo.model.Operateur;
import com.wevioo.model.Polyvalence;
import com.wevioo.model.Unite;
import com.wevioo.model.enumeration.TypeUnite;
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
	@Autowired
	private MessageUtil messageUtil;

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
		for (Operateur op : operateurs) {
			op.setChecked(true);
		}
		Type listType = new TypeToken<List<OperateurDto>>() {
		}.getType();

		return modelMapper.map(operateurs, listType);
	}

	@RequestMapping(value = "/qualification", method = RequestMethod.POST)
	public @ResponseBody List<Polyvalence> findPolyvalenceByOperateur(@RequestBody Operateur op) throws Exception {
		List<Polyvalence> polyvalences = polyvalenceService.findPolyvalenceByOperateur(op);
		// Type listType = new TypeToken<List<OperateurDto>>() {
		// }.getType();

		return polyvalences;
	}

	private boolean isOperateurMatriculeAlreadyExist(String matricule) {
		if (matricule != null && !matricule.isEmpty()) {
			Operateur op = operateurService.findOperateurByFirstname(matricule);
			return op != null && !op.getMatricule().isEmpty();
		}
		return false;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Object createOperateur(@RequestBody Operateur operateur, HttpServletRequest request)
			throws Exception {

		if (isOperateurMatriculeAlreadyExist(operateur.getMatricule())) {
			ApiException error = new ApiException(HttpStatus.CONFLICT,
					messageUtil.getMessage("error.operateur.matricule.exist"), "error.operateur.matricule.exist");
			return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
		}
		try {
			operateur = operateurService.createOperateur(operateur);

			return operateur;
		} catch (Exception e) {
			ApiException error = new ApiException(HttpStatus.CONFLICT,
					messageUtil.getMessage("error.unite.creation.error"), "error.unite.creation.error");
			return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
		}
	}

	@RequestMapping(value = "{matricule}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteOperateur(@PathVariable String matricule, HttpServletRequest request)
			throws Exception {
		operateurService.deleteOperateur(matricule);

	}

}
