package com.wevioo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wevioo.model.JourOuvre;

/***
 * 
 * @author WAT
 *
 */

public interface JourOuvreService {

public List<JourOuvre> findJoursOuvresByScenario(Long scenarioId);
}
