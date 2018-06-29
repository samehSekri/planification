package com.wevioo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wevioo.dto.OperateurDto;
import com.wevioo.model.Operateur;
import com.wevioo.service.OperateurService;
import com.wevioo.utility.MessageUtil;

@RestController
@RequestMapping("/api/operateurs")
public class OperateurRestController {
	@Autowired
	private MessageUtil messageUtil;
	@Autowired
	private OperateurService operateurService;

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
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Object createOperateur(@RequestBody Operateur operateurDto, HttpServletRequest request) throws Exception {	//if (operateur.getMatricule() != null) {
			
		
			
		

			operateurDto = operateurService.createOperateur(operateurDto);
		
//	}
		return operateurDto;
	}

}
