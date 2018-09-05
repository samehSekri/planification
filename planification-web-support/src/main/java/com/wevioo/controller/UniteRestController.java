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
import com.wevioo.dto.NodeDataDto;
import com.wevioo.dto.NodeUniteDto;
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
	public @ResponseBody List<NodeUniteDto> findAllUnite() throws Exception {
		// List<Unite> unites = uniteService.findAllUnite();

		// 'label': 'Zodiac',
		// 'type': 'person',
		// 'styleClass': 'ui-person',
		// 'expanded': true,
		// 'data': { 'name': 'zodiac aerospace', 'avatar': 'walter.jpg' },
		NodeUniteDto nodeUniteDto = new NodeUniteDto();

		NodeDataDto data = new NodeDataDto();
		NodeUniteDto treeNode = new NodeUniteDto();
		treeNode.setLabel("Zodiac");
		treeNode.setType("person");
		treeNode.setStyleClass("ui-person");
		treeNode.setExpanded(true);
		data.setName("Zodiac Aerospace");
		data.setAvatar("walter.jpg");
		treeNode.setData(data);
		
		List<NodeUniteDto> uapNodes = new ArrayList<NodeUniteDto>();

		List<Unite> unites = uniteService.findUniteByType(TypeUnite.UAP);
		if (unites != null && unites.size() > 0) {
			for (Unite unite : unites) {
		
				NodeUniteDto uap = new NodeUniteDto();
				uap.setLabel(unite.getName());
				uap.setType("person");
				uap.setStyleClass("ui-person");
				uap.setExpanded(true);
				data = new NodeDataDto();
				data.setName(unite.getType().name());
				data.setAvatar("saul.jpg");
				uap.setData(data);

				List<NodeUniteDto> childrenUap = new ArrayList<NodeUniteDto>();
				

				// Get list atelier
				List<Unite> uniteAteliers = uniteService.findUniteByParent(unite);
				if (uniteAteliers != null && uniteAteliers.size() > 0) {
					for (Unite a : uniteAteliers) {
						NodeUniteDto atelier = new NodeUniteDto();
						atelier.setLabel(a.getName());
						atelier.setType("atelier");
						atelier.setStyleClass("department-cfo");
						atelier.setExpanded(true);
						data = new NodeDataDto();
						data.setName(a.getType().name());
						data.setAvatar("jesse.jpg");
						atelier.setData(data);

						List<NodeUniteDto> childrenAtelier = new ArrayList<NodeUniteDto>();
						// Get list Ilot
						List<Unite> uniteIlots = uniteService.findUniteByParent(a);
						if (uniteIlots != null && uniteIlots.size() > 0) {
							for (Unite i : uniteIlots) {
								NodeUniteDto ilot = new NodeUniteDto();
								ilot.setLabel(i.getName());
								ilot.setType("ilot");
								ilot.setStyleClass("department-cto");
								data = new NodeDataDto();
								data.setName(i.getType().name());
								data.setAvatar("jesse.jpg");
								ilot.setData(data);

								childrenAtelier.add(ilot);
							}
						}
						//Convert children atelier ArrayList to array 
						NodeUniteDto[] children = new NodeUniteDto[childrenAtelier.size()];
						children = childrenAtelier.toArray(children);
						atelier.setChildren(children);
						childrenUap.add(atelier);
						
					}
				}
				NodeUniteDto[] childrenUapNode = new NodeUniteDto[childrenUap.size()];
				childrenUapNode = childrenUap.toArray(childrenUapNode);
				uap.setChildren(childrenUapNode);
				uapNodes.add(uap);
				
			}
		}
		NodeUniteDto[] childrenUapNode = new NodeUniteDto[uapNodes.size()];
		childrenUapNode = uapNodes.toArray(childrenUapNode);

		treeNode.setChildren(childrenUapNode);

		// for (Unite ilot : ilots) {
		// Unite uap = ilot.getParent().getParent();
		//
		// ObjectNode data = null;
		// ArrayNode listAtelierNode = null;
		//
		// if (!uapsList.contains(uap)) {
		//
		// }
		// }
		//
		// String jsonString = convertToTreeNode(unites);
		//
		// ObjectMapper objectMapper = new ObjectMapper();
		//
		// List<NodeUniteDto> navigation = objectMapper.readValue(jsonString,
		// objectMapper.getTypeFactory().constructCollectionType(List.class,
		// NodeUniteDto.class));
		List<NodeUniteDto> navigation = new ArrayList<NodeUniteDto>();
		navigation.add(treeNode);
		return navigation;
		// Cast List<Unite> to List<UniteDto> without need to use for loop
		// Type listType = new TypeToken<List<UniteDto>>() {
		// }.getType();

		// return modelMapper.map(unites, listType);
	}

	private String convertToTreeNode(List<Unite> unites) {
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode arrayNode = mapper.createArrayNode();
		ObjectNode node = null; // mapper.createObjectNode();

		List<Unite> ilots = uniteService.findUniteByType(TypeUnite.ILOT);

		List<Unite> ateliersList = new ArrayList<Unite>();
		List<Unite> uapsList = new ArrayList<Unite>();

		List<Unite> selectedIlots = new ArrayList<Unite>();

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
							System.out.println("----------> " + atelier.getName() + " not found.");

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
							nodeIlot.put("expanded", true);
							listIlotsNode.add(nodeIlot);
							// Children des ateliers
							nodeAtelier.put("children", listIlotsNode);

							listAtelierNode.add(nodeAtelier);
						} else if (!selectedIlots.contains(ilotChild)) {
							selectedIlots.add(ilotChild);
							// Liste des children de l'atelier atelier
							ObjectNode nodeIlot = mapper.createObjectNode();
							nodeIlot.put("label", ilotChild.getName());
							nodeIlot.put("type", ilotChild.getType().name().toLowerCase());
							nodeIlot.put("styleClass", "department-cfo");
							nodeIlot.put("expanded", true);

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

		try {
			String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(arrayNode);
			System.out.println(json);
			return json;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
