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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

		convertToTreeNode(unites);
		// Cast List<Unite> to List<UniteDto> without need to use for loop
		Type listType = new TypeToken<List<UniteDto>>() {
		}.getType();

		return modelMapper.map(unites, listType);
	}

	private void convertToTreeNode(List<Unite> unites) {
		// children: [
		// {
		// label: 'UAP1',
		// type: 'person',
		// styleClass: 'ui-person',
		// expanded: true,
		// data: { name: 'Saul Goodman', 'avatar': 'saul.jpg' },
		// children: [{
		// label: 'Atelier1',
		//
		// styleClass: 'department-cfo'
		// },
		// {
		// label: 'Atelier2',
		//
		// styleClass: 'department-cfo'
		//
		// }],
		// }
		// ]

		// children:[{
		// label: 'Ilot1',
		// type: 'ilot',
		// styleClass: 'department-cto'
		// }]

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
				for (Unite ilotChild : ilots) {
					Unite atelier = ilotChild.getParent();
					if (atelier.getParent().getName().equals(uap.getName())) {
						if (!ateliersList.contains(atelier)) {
							ateliersList.add(atelier);
							ObjectNode nodeAtelier = mapper.createObjectNode();
							nodeAtelier.put("label", atelier.getName());
							nodeAtelier.put("type", atelier.getType().name().toLowerCase());
							nodeAtelier.put("styleClass", "department-cfo");
							nodeAtelier.put("expanded", true);
							
							//Liste des children de l'atelier atelier
							listIlotsNode = mapper.createArrayNode();
							ObjectNode nodeIlot = mapper.createObjectNode();
							nodeIlot.put("label", ilotChild.getName());
							nodeIlot.put("type", ilotChild.getType().name().toLowerCase());
							nodeIlot.put("styleClass", "department-cfo");
							
							listIlotsNode.add(nodeIlot);
							//Children des ateliers
							nodeAtelier.put("children", listIlotsNode);
							
							listAtelierNode.add(nodeAtelier);
						}else {
							//Liste des children de l'atelier atelier
							ObjectNode nodeIlot = mapper.createObjectNode();
							nodeIlot.put("label", ilotChild.getName());
							nodeIlot.put("type", ilotChild.getType().name().toLowerCase());
							nodeIlot.put("styleClass", "department-cfo");
							
							listIlotsNode.add(nodeIlot);
						}
					}
				}
//Children de l'UAP
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
