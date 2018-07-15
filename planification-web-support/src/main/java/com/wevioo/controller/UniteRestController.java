package com.wevioo.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wevioo.dto.UniteDto;
import com.wevioo.exception.ApiException;
import com.wevioo.model.Unite;
import com.wevioo.model.enumeration.TypeUnite;
import com.wevioo.service.UniteService;
import com.wevioo.utility.MessageUtil;


@RestController
@RequestMapping("/api/unites")
public class UniteRestController {
	@Autowired
	private UniteService uniteService;

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private MessageUtil messageUtil;


	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<UniteDto> findAllUnite() throws Exception {
		List<Unite> unites = uniteService.findAllUnite();

		convertToTreeNode(unites);
		// Cast List<Unite> to List<UniteDto> without need to use for loop
		Type listType = new TypeToken<List<UniteDto>>() {
		}.getType();

		return modelMapper.map(unites, listType);
	}

	private void convertToTreeNode(List<Unite> unites) {
		

		ObjectMapper mapper = new ObjectMapper();
		ArrayNode arrayNode = mapper.createArrayNode();
		ObjectNode node = null; // mapper.createObjectNode();

		List<Unite> ilots = uniteService.findUniteByType(TypeUnite.ILOT);

		ArrayNode children = mapper.createArrayNode();
		List<Unite> ateliersList = new ArrayList<Unite>();
		List<Unite> uapsList = new ArrayList<Unite>();

		ArrayNode atelierNode = mapper.createArrayNode();

		int index = 0;
		for (Unite ilot : ilots) {
			Unite uap = ilot.getParent().getParent();

			ObjectNode data = null;
			ArrayNode listAtelierNode = null;
			if (!uapsList.contains(uap)) {
				uapsList.add(uap);
				index++;
				node = mapper.createObjectNode();

				node.put("label", uap.getType().name() + index);
				node.put("type", "person");
				node.put("styleClass", "ui-person");
				node.put("expanded", true);
				// Consutruct data attribute
				data = mapper.createObjectNode();

				data.put("name", uap.getName());
				data.put("avatar", "saul.jpg");
				node.put("data", data);

				// get children uap ==> liste des ateliers
				listAtelierNode = mapper.createArrayNode();
				ArrayNode listIlotsNode = null;
				ObjectNode nodeAtelier = null;
				for (Unite ilotChild : ilots) {
					Unite atelier = ilotChild.getParent();
					if (atelier.getParent().getName().equals(uap.getName())) {
						if (!ateliersList.contains(atelier)) {
							ateliersList.add(atelier);
							nodeAtelier = mapper.createObjectNode();
							nodeAtelier.put("label", atelier.getName());
							nodeAtelier.put("type", atelier.getType().name().toLowerCase());
							nodeAtelier.put("styleClass", "department-cfo");
							nodeAtelier.put("expanded", true);

							// Liste des children de l'atelier atelier
							listIlotsNode = mapper.createArrayNode();
							ObjectNode nodeIlot = mapper.createObjectNode();
							nodeIlot.put("label", ilotChild.getName());
							nodeIlot.put("type", ilotChild.getType().name().toLowerCase());
							nodeIlot.put("styleClass", "department-cfo");

							listIlotsNode.add(nodeIlot);
							// Children des ateliers
							nodeAtelier.put("children", listIlotsNode);

							listAtelierNode.add(nodeAtelier);
						} else {
							// Liste des children de l'atelier atelier
							ObjectNode nodeIlot = mapper.createObjectNode();
							nodeIlot.put("label", ilotChild.getName());
							nodeIlot.put("type", ilotChild.getType().name().toLowerCase());
							nodeIlot.put("styleClass", "department-cfo");

							listIlotsNode.add(nodeIlot);
							// Children des ateliers
							nodeAtelier.put("children", listIlotsNode);
							listAtelierNode.add(nodeAtelier);
						}
					}
				}
				// Children de l'UAP
				node.put("children", listAtelierNode);
				arrayNode.add(node);
			}
		}

		//
		// if(unite.getType() == TypeUnite.UAP){
		// node = mapper.createObjectNode();
		//
		// node.put("label", unite.getName());
		// node.put("type", "person");
		// node.put("styleClass", "ui-person");
		// node.put("expanded", true);
		// //Consutruct data attribute
		// ObjectNode data = mapper.createObjectNode();
		// data.put("name", unite.getName());
		// data.put("avatar", "saul.jpg");
		// node.put("data", data);
		// node.put("children", mapper.createArrayNode() );
		//
		// arrayNode.add(node);
		//
		// }else if(unite.getType() == TypeUnite.ATELIER) {
		// arrayNode.forEach(flowJson -> {
		// ObjectNode nodeJson = (ObjectNode) flowJson;
		//
		// if(flowJson.findPath("name").equals(unite.getParent().getName())){
		// ArrayNode array = (ArrayNode)nodeJson.get("children");
		// ObjectNode nodeAtelier = mapper.createObjectNode();
		// nodeAtelier.put("label", unite.getName());
		// nodeAtelier.put("styleClass", "department-cfo");
		// array.add(nodeAtelier);
		// nodeJson.put("children", array);
		// }
		// });
		//
		// }else if(unite.getType() == TypeUnite.ILOT) {
		//
		// }
		//

		try {
			String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(arrayNode);
			System.out.println(json);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	@RequestMapping(value = "/{typeToAdd}", method = RequestMethod.POST)
	public @ResponseBody Object createUnite(@RequestBody Unite unite, @Valid @PathVariable String typeToAdd,
			HttpServletRequest request) throws Exception {
		TypeUnite type = TypeUnite.valueOf(typeToAdd);

		// Check if the iot exist
		if (isUniteNameAlreadyExist(unite.getName())) {

			// Nom unite existe déjà renvoyer erreur
			ApiException error = new ApiException(HttpStatus.CONFLICT, "error.unite.name.exist",
					messageUtil.getMessage("error.unite.name.exist"));
			return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
		} else {
			// if (unite.getType() == TypeUnite.ILOT) {
			// Check if atelier name exist
			Unite atelier = unite.getParent();
			Unite uap = atelier.getParent();

			if (type == TypeUnite.UAP) {

			}

			/*
			 * Check if uap name exist
			 */
			if (type == TypeUnite.UAP && isUniteNameAlreadyExist(uap.getName())) {
				/*
				 * Le nom de l'UAP existe déjà : Erreur
				 */
				return null;
			} else if ((type == TypeUnite.UAP && isUniteNameAlreadyExist(atelier.getName())
					|| type == TypeUnite.ATELIER && isUniteNameAlreadyExist(atelier.getName()))) {
				/*
				 * Le nom de l'atelier existe déjà : Erreur
				 */
				return null;
			} else {

				// 1- Save the uap
				if (type == TypeUnite.UAP) {
					uap = uniteService.createUnite(uap);
					// 2- update the parent of atelier with the persisted uap
					atelier.setParent(uap);
				}

				if (type == TypeUnite.UAP || type == TypeUnite.ATELIER) {
					// 3- Save the atelier
					atelier = uniteService.createUnite(atelier);
					// 4- Update the ilot with the persisted atelier
					unite.setParent(atelier);
				}

				// 5- Save the ilot
				unite = uniteService.createUnite(unite);
				return unite;
			}
		}
	}

	private boolean isUniteNameAlreadyExist(String unitName) {
		if (unitName != null && !unitName.isEmpty()) {
			Unite unite = uniteService.findUniteByName(unitName.toLowerCase());
			return unite != null && !unite.getName().isEmpty();
		}
		return false;
	}

}
