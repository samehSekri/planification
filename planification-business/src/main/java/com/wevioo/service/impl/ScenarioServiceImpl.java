package com.wevioo.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wevioo.dao.HoraireRepository;
import com.wevioo.dao.JourFerieRepository;
import com.wevioo.dao.ScenarioRepository;
import com.wevioo.model.Affectation;
import com.wevioo.model.Article;
import com.wevioo.model.Horaire;
import com.wevioo.model.JourFerie;
import com.wevioo.model.JourOuvre;
import com.wevioo.model.Operateur;
import com.wevioo.model.Polyvalence;
import com.wevioo.model.Scenario;
import com.wevioo.model.Unite;
import com.wevioo.model.enumeration.StatutScenarioEnum;
import com.wevioo.model.enumeration.TypeUnite;
import com.wevioo.service.HoraireService;
import com.wevioo.service.ScenarioService;

@Service
@Transactional(readOnly = true)
public class ScenarioServiceImpl implements ScenarioService {

	@Autowired
	private ScenarioRepository scenarioRepository;
	@Autowired
	private HoraireRepository horaireRepository;
	@Autowired
	private HoraireService horaireService;
	@Autowired
	private ScenarioService scenarioService;
	@Autowired
	private JourFerieRepository jourFerieRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Scenario> findAllScenario() {
		return scenarioRepository.findAll();
	}

	/**
	 * 
	 */

	@Override
	public Scenario findScenarioById(Long id) {
		if (id != null) {
			Scenario scenario = scenarioRepository.findOne(id);
			return scenario;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public Scenario saveAndGetScenario(Scenario scenario) {
		return scenarioRepository.saveAndFlush(scenario);
	}

	// @Override
	// @Transactional
	// public Article changeScenarioStatus(String reference) {
	// if (reference != null) {
	// Article article = articleRepository.findOne(reference);
	// if (article != null) {
	// if(article.isEtat()==true) {
	//
	// article.setEtat(false);
	//
	// }else {
	// article.setEtat(true);
	// }
	//
	// article = saveAndGetArticle(article);
	// return article;
	// }
	// }
	// return null;
	// }

	@Override
	public Scenario validerScenario(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Scenario rejeterScenario(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Scenario getUapByScenario(Long idScenario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Scenario> findScenarioByUnite(Unite unite) {
		List<Scenario> scenarios = scenarioRepository.findScenarioByUnite(unite);
		return scenarios;
	}

	@Override
	public List<Scenario> findScenarioByStatut(StatutScenarioEnum statut) {
		List<Scenario> scenarios = scenarioRepository.findScenarioByStatut(statut);
		return scenarios;
	}

	@Override
	public List<Scenario> findScenarioByDateCreation(Date dateCreation) {
		List<Scenario> scenarios = scenarioRepository.findScenarioByDateCreation(dateCreation);
		return scenarios;
	}

	@Override
	public double calculateTauxSatisfactionProduction(List<Article> articles) {
		double result;
		double realisationTotale = 0;
		int resteProduireTotale = 0;

		for (Article article : articles) {
			resteProduireTotale = resteProduireTotale + article.getEngagementSemaine();
		}

		// for (Affectation aff : affectations) {
		// if (aff.getArticle() != null && aff.getOperateur() != null) {
		// realisationTotale = realisationTotale + aff.getQuantite();
		// }
		// }
		if (resteProduireTotale == 0) {
			result = 1;
		} else {
			result = realisationTotale / resteProduireTotale;
		}
		return result;
	}

	@Override
	public List<JourOuvre> findJourOuvreByScenario(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public List<JourOuvre> findJourOuvreByUniteAndDate(Unite unite, Date dateBegin) {
		ArrayList<JourOuvre> listJourOuvre = new ArrayList<JourOuvre>();
		List<Horaire> phs = horaireRepository.findHoraireByUnite(unite);
		Calendar calendarBegin = Calendar.getInstance();

		calendarBegin.setTime(dateBegin);
		calendarBegin.set(Calendar.HOUR_OF_DAY, 0);
		calendarBegin.set(Calendar.MINUTE, 0);
		calendarBegin.set(Calendar.SECOND, 0);

		Date dateFin = new Date(dateBegin.getTime() + (6 - calendarBegin.get(Calendar.DAY_OF_WEEK)) * 86400000);

		Calendar calendarEnd = Calendar.getInstance();
		calendarEnd.setTime(dateFin);

		calendarEnd.set(Calendar.HOUR_OF_DAY, 23);
		calendarEnd.set(Calendar.MINUTE, 59);
		calendarEnd.set(Calendar.SECOND, 59);

		List<JourFerie> jf = jourFerieRepository.findJourFerieByDateDebutAndDateFin(calendarBegin.getTime(),
				calendarEnd.getTime());
		if (1 <= (calendarBegin.get(Calendar.DAY_OF_WEEK) - 1) && calendarBegin.get(Calendar.DAY_OF_WEEK) - 1 <= 5
				&& phs != null && phs.size() > 0) {
			Date lundi = new Date(dateBegin.getTime() - (calendarBegin.get(Calendar.DAY_OF_WEEK) - 2) * 86400000);
			for (int i = calendarBegin.get(Calendar.DAY_OF_WEEK) - 2; i < phs.size(); i++) {
				if (!isJourFerie(jf, new Date(lundi.getTime() + (i * 86400000)))) {
					if (i == calendarBegin.get(Calendar.DAY_OF_WEEK) - 2) {
						dateBegin = new Date(lundi.getTime() + (i * 86400000));

						calendarBegin.setTime(dateBegin);

						Calendar c = Calendar.getInstance();
						c.setTime(phs.get(i).getHeureDebut());

						calendarBegin.set(Calendar.HOUR, c.get(Calendar.HOUR_OF_DAY));
						calendarBegin.set(Calendar.HOUR, c.get(Calendar.MINUTE));
						calendarBegin.set(Calendar.HOUR, c.get(Calendar.SECOND));
					}
					JourOuvre jo = new JourOuvre();
					jo.setDate(new Date(lundi.getTime() + (i * 86400000)));
					jo.setHeureDebut(phs.get(i).getHeureDebut());
					jo.setHeureFin(phs.get(i).getHeureFin());
					listJourOuvre.add(jo);
				}
			}
		}
		return listJourOuvre;
	}

	private boolean isJourFerie(List<JourFerie> jf, Date date) {
		Calendar date1 = Calendar.getInstance();
		Calendar date2 = Calendar.getInstance();

		if (jf != null)
			for (int i = 0; i < jf.size(); i++) {
				date1.setTime(jf.get(i).getDate());
				date2.setTime(date);
				if (date1.get(Calendar.DAY_OF_WEEK) == date2.get(Calendar.DAY_OF_WEEK)
						&& date1.get(Calendar.MONTH) == date2.get(Calendar.MONTH)
						&& date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR))
					return true;
			}
		return false;
	}

	@Override
	public double calculateTauxSatisfactionClient(List<Affectation> affectations, List<Article> articles) {
		double result;
		double realisationTotale = 0;
		int resteProduireTotale = 0;

		for (Article article : articles) {
			resteProduireTotale = resteProduireTotale + article.getResteProduire();
		}

		for (Affectation aff : affectations) {
			if (aff.getArticle() != null && aff.getOperateur() != null) {
				realisationTotale = realisationTotale + aff.getQuantite();
			}
		}
		if (resteProduireTotale == 0) {
			result = 1;
		} else {
			result = realisationTotale / resteProduireTotale;
		}
		return result;
	}

	@Override
	public double calculateTauxOccupation(List<Affectation> affectations, List<Operateur> operateurs,
			double tempsOuverture) {
		double tauxOccupationTotale = 0;
		double chargeOpTotale = 0;

		for (Operateur op : operateurs) {
			chargeOpTotale = 0;
			for (Affectation aff : affectations) {
				if (aff.getOperateur() != null) {
					if (op.getMatricule().equals(aff.getOperateur().getMatricule())) {
						if (aff.getArticle() != null && aff.getOperateur() != null
								&& aff.getArticle().getEfficience() != 0 && tempsOuverture != 0) {
							chargeOpTotale = chargeOpTotale
									+ (aff.getArticle().getTempsStandard() / aff.getArticle().getEfficience())
											* aff.getQuantite();
						}
					}
				}
			}
			if (tempsOuverture != 0)
				tauxOccupationTotale = tauxOccupationTotale + chargeOpTotale / tempsOuverture;
		}
		if (operateurs.size() != 0)
			tauxOccupationTotale = tauxOccupationTotale / operateurs.size();
		return tauxOccupationTotale;
	}

	@Override
	public double calculateTauxOccupationParOperateur(Scenario scenario, Operateur operateur) {
		double tauxOccupationTotale = 0;
		double chargeOpTotale = 0;
		Unite uap = null;

		if (scenario != null) {
			if (TypeUnite.valueOf(scenario.getUnite().getType().toString()).equals(TypeUnite.ILOT)) {
				uap = scenario.getUnite().getParent().getParent();
			} else {
				uap = scenario.getUnite().getParent();
			}

			// Calcul du temps standards : Nbre jours ouvres * temps de travail
			// tempsTravail = Heure Fin - Heure Debut - durees de pauses
			Number tempsTravail = horaireService.getTempsTravail(uap);
			Double tempsOuverture = tempsTravail.doubleValue();
			List<JourOuvre> lists = scenarioService.findJourOuvreByUniteAndDate(uap, scenario.getDateDebut());
			// Temps standard nombre jour ouvre * temps de travail d'une journee
			tempsOuverture *= (lists != null) ? lists.size() : 0;

			for (Affectation aff : scenario.getAffectation()) {
				if (aff.getOperateur() != null) {
					if (operateur.getMatricule().equals(aff.getOperateur().getMatricule())) {
						if (aff.getArticle() != null && aff.getArticle().getEfficience() != 0 && tempsOuverture != 0) {
							chargeOpTotale = chargeOpTotale
									+ (aff.getArticle().getTempsStandard() / aff.getArticle().getEfficience())
											* aff.getQuantite();
						}
					}
				}
			}
			if (tempsOuverture != 0)
				tauxOccupationTotale = chargeOpTotale / tempsOuverture;
		}
		return tauxOccupationTotale;
	}



	public List<Affectation> lancerAlgorithme(List<Operateur> listOp, List<Article> listArt,
			Map<String, List<Polyvalence>> mapOpPoly, Map<String, List<Polyvalence>> mapArtPoly,
			List<List<Affectation>> result, int indiceArticle, double tempsTravaille, List<Operateur> allOperators) {

		List<Operateur> operateurs = new ArrayList<Operateur>();
		List<Article> articles = new ArrayList<Article>();
		Map<String, List<Polyvalence>> mapOperateursPolyvalences = new HashMap<String, List<Polyvalence>>();
		Map<String, List<Polyvalence>> mapArticlesPolyvalences = new HashMap<String, List<Polyvalence>>();

		operateurs.addAll(listOp);
		articles.addAll(listArt);
		mapOperateursPolyvalences.putAll(mapOpPoly);
		mapArticlesPolyvalences.putAll(mapArtPoly);

		List<Affectation> affectations = new ArrayList<Affectation>();
		for (int i = 0; i < articles.size(); i++) {
			Article myArticle = articles.get(i);
			// Calculer besoin en polyvalence
			// Besoin polyvalence = Charge Article / (Temps Travaillé *
			// efficience)
			// Charge article = TG * Reste à produire
			double besoinPolyvalence = (myArticle.getTempsStandard() * myArticle.getResteProduire())
					/ (tempsTravaille * myArticle.getEfficience());
			myArticle.setBesoinPolyvalence(Math.ceil(besoinPolyvalence));

			int nbreOpsAffectes = 0;
			int nbreOps = 0;
			for (int k = 0; k < myArticle.getEngagementSemaine(); k++) {
				if (myArticle.getBesoinPolyvalence() > mapArticlesPolyvalences.get(myArticle.getReference()).size()) {
					nbreOps = mapArticlesPolyvalences.get(myArticle.getReference()).size();
					// System.out
					// .println("Article ("
					// + myArticle.getReference()
					// +
					// ") : le nombre de polyvalence demandé est supérieur au
					// nombre des opérateurs disponibles ...");
				} else {
					nbreOps = myArticle.getBesoinPolyvalence().intValue();
				}
				String myOpMatricule;
				// for (int j = 0; j < nbreOps; j++) {

				if (!mapArticlesPolyvalences.get(myArticle.getReference()).isEmpty()) {

					if (nbreOpsAffectes < nbreOps) {
						/*  */
						myOpMatricule = getOperateurMoinsCharge(0,
								mapArticlesPolyvalences.get(myArticle.getReference()), operateurs);
						nbreOpsAffectes = nbreOpsAffectes + 1;
					} else {
						myOpMatricule = getOperateurMoinsChargeAffecte(0,
								mapArticlesPolyvalences.get(myArticle.getReference()), operateurs, affectations);
					}
					double chargeRestanteOp = (getIndiceOpByMatFromList(myOpMatricule, operateurs) == -1) ? 0.0
							: operateurs.get(getIndiceOpByMatFromList(myOpMatricule, operateurs)).getChargeRestante();
					Affectation aff = new Affectation();
					aff.setArticle(myArticle);

					if (myArticle.getTempsStandard() / myArticle.getEfficience() <= chargeRestanteOp) {
						// Operateur op = operateurDao.get(myOpMatricule);
						Operateur op = findOperatotByMatricule(allOperators, myOpMatricule);
						aff.setOperateur(op);
						operateurs.get(getIndiceOpByMatFromList(myOpMatricule, operateurs))
								.setChargeRestante(operateurs.get(getIndiceOpByMatFromList(myOpMatricule, operateurs))
										.getChargeRestante()
										- myArticle.getTempsStandard() / myArticle.getEfficience());
						operateurs.get(getIndiceOpByMatFromList(myOpMatricule, operateurs)).setChargeHoraireParScenario(
								operateurs.get(getIndiceOpByMatFromList(myOpMatricule, operateurs))
										.getChargeHoraireParScenario()
										+ myArticle.getTempsStandard() / myArticle.getEfficience());
						aff.setChargeHoraire(myArticle.getTempsStandard() / myArticle.getEfficience());
						operateurs.get(getIndiceOpByMatFromList(myOpMatricule, operateurs)).setAffected(true);

					} else {
						int quantiteRestante = articles
								.get(getIndiceArtByRefFromList(myArticle.getReference(), articles)).getResteProduire();
						articles.get(getIndiceArtByRefFromList(myArticle.getReference(), articles))
								.setResteProduire(quantiteRestante + 1);
						aff.setOperateur(null);
						aff.setChargeHoraire(0.0);

					}

					aff.setEngagementSemaine(myArticle.getEngagementSemaine());
					affectations.add(aff);
				} else {
					Affectation aff = new Affectation();
					aff.setArticle(myArticle);
					aff.setOperateur(null);
					aff.setChargeHoraire(0.0);
					int quantiteRestante = articles.get(getIndiceArtByRefFromList(myArticle.getReference(), articles))
							.getResteProduire();
					articles.get(getIndiceArtByRefFromList(myArticle.getReference(), articles))
							.setResteProduire(quantiteRestante + 1);
					aff.setEngagementSemaine(myArticle.getEngagementSemaine());
					affectations.add(aff);
				}
			}
		}
		for (Affectation affectation : affectations) {
			if (affectation.getOperateur() != null && affectation.getArticle() != null) {

				for (Operateur op : operateurs) {
					if (affectation.getOperateur().getMatricule().equals(op.getMatricule())) {
						affectation.setChargeOperateurTotale(new Double(op.getChargeHoraireParScenario()));
					}
				}
			}
		}
		ArrayList<Affectation> listAffectations = new ArrayList<Affectation>();
		boolean existe;
		for (int i = 0; i < affectations.size(); i++) {
			existe = false;
			for (int j = 0; j < listAffectations.size(); j++) {
				if (affectations.get(i).getOperateur() != null && affectations.get(i).getArticle() != null
						&& listAffectations.get(j).getOperateur() != null
						&& listAffectations.get(j).getArticle() != null) {
					if (affectations.get(i).getOperateur().getMatricule()
							.equals(listAffectations.get(j).getOperateur().getMatricule())
							&& affectations.get(i).getArticle().getReference()
									.equals(listAffectations.get(j).getArticle().getReference())) {
						existe = true;
						listAffectations.get(j).setQuantite(listAffectations.get(j).getQuantite() + 1);
						listAffectations.get(j).setChargeHoraire(
								listAffectations.get(j).getChargeHoraire() + affectations.get(i).getChargeHoraire());
						break;
					}
				}

				if (affectations.get(i).getOperateur() == null && affectations.get(i).getArticle() != null
						&& listAffectations.get(j).getOperateur() == null
						&& listAffectations.get(j).getArticle() != null) {
					if (affectations.get(i).getArticle().getReference()
							.equals(listAffectations.get(j).getArticle().getReference())) {
						existe = true;
						break;
					}
				}

				if (affectations.get(i).getOperateur() != null && affectations.get(i).getArticle() == null
						&& listAffectations.get(j).getOperateur() != null
						&& listAffectations.get(j).getArticle() == null) {
					if (affectations.get(i).getOperateur().getMatricule()
							.equals(listAffectations.get(j).getOperateur().getMatricule())) {
						existe = true;
						break;
					}
				}
			}
			if (!existe) {
				if (affectations.get(i).getArticle() != null && affectations.get(i).getOperateur() != null) {
					affectations.get(i).setQuantite(1);
				}
				listAffectations.add(affectations.get(i));
			}
		}
		return listAffectations;

	}

	public String getOperateurMoinsCharge(int indiceDebut, List<Polyvalence> listPolyvalences,
			List<Operateur> operateurs) {
		/*
		 * 1- Chercher dans la liste des opétateurs l'operateur qui possède le
		 * moins de polyvalence.
		 */

		Polyvalence p;
		if (indiceDebut > listPolyvalences.size()) {
			return null;
		} else {
			p = listPolyvalences.get(indiceDebut);
		}
		for (int i = indiceDebut + 1; i < listPolyvalences.size(); i++) {
			if (operateurs
					.get(getIndiceOpByMatFromList(listPolyvalences.get(i).getOperateur().getMatricule(), operateurs))
					.getChargeRestante() > operateurs
							.get(getIndiceOpByMatFromList(p.getOperateur().getMatricule(), operateurs))
							.getChargeRestante()) {
				p = listPolyvalences.get(i);
			}
		}
		return p.getOperateur().getMatricule();
	}

	public String getOperateurMoinsChargeAffecte(int indiceDebut, List<Polyvalence> listPolyvalences,
			List<Operateur> operateurs, List<Affectation> affectations) {
		Polyvalence p = null;

		for (int i = indiceDebut; i < listPolyvalences.size(); i++) {
			if (p == null) {
				if (isAffected(listPolyvalences.get(i).getArticle().getReference(),
						listPolyvalences.get(i).getOperateur().getMatricule(), affectations)) {
					p = listPolyvalences.get(i);
				}
			} else {
				if (operateurs.get(
						getIndiceOpByMatFromList(listPolyvalences.get(i).getOperateur().getMatricule(), operateurs))
						.getChargeRestante() > operateurs
								.get(getIndiceOpByMatFromList(p.getOperateur().getMatricule(), operateurs))
								.getChargeRestante()
						&& isAffected(listPolyvalences.get(i).getArticle().getReference(),
								listPolyvalences.get(i).getOperateur().getMatricule(), affectations)) {
					p = listPolyvalences.get(i);
				}
			}
			if (p == null) {
				// System.out.println("nulllle");
			}
		}

		return (p != null) ? p.getOperateur().getMatricule() : null;
	}

	public boolean isAffected(String reference, String matricule, List<Affectation> affectations) {

		for (Affectation aff : affectations) {
			if (aff.getOperateur() != null && aff.getArticle() != null) {
				if (aff.getArticle().getReference().equals(reference)
						&& aff.getOperateur().getMatricule().equals(matricule)) {
					return true;
				}
			}
		}
		return false;
	}

	public int getIndiceOpByMatFromList(String matricule, List<Operateur> operateurs) {
		for (int i = 0; i < operateurs.size(); i++) {
			if (operateurs.get(i).getMatricule().equals(matricule))
				return i;
		}
		return -1;
	}

	public int getIndiceArtByRefFromList(String ref, List<Article> articles) {
		for (int i = 0; i < articles.size(); i++) {
			if (articles.get(i).getReference().equals(ref))
				return i;
		}
		return -1;
	}

	public Operateur findOperatotByMatricule(List<Operateur> allOperators, String matricule) {
		if (allOperators != null && matricule != null) {
			for (int i = 0; i < allOperators.size(); i++) {
				if (allOperators.get(i).getMatricule().equals(matricule)) {
					Operateur op = new Operateur();
					op.setAffectations(allOperators.get(i).getAffectations());
					op.setAffected(allOperators.get(i).getAffected());
					op.setChargeHoraireParScenario(allOperators.get(i).getChargeHoraireParScenario());
					op.setChargeRestante(allOperators.get(i).getChargeRestante());
					op.setConges(allOperators.get(i).getConges());
					op.setFirstname(allOperators.get(i).getFirstname());
					op.setLastname(allOperators.get(i).getLastname());
					op.setMatricule(allOperators.get(i).getMatricule());
					op.setNbrePolyvalence(allOperators.get(i).getNbrePolyvalence());
					op.setPolyvalences(allOperators.get(i).getPolyvalences());
					op.setSeuilTolerance(allOperators.get(i).getSeuilTolerance());
					op.setStatut(allOperators.get(i).getStatut());
					op.setTauxOccupationMoyen(allOperators.get(i).getTauxOccupationMoyen());
					op.setTauxOccupationParScenario(allOperators.get(i).getTauxOccupationParScenario());
					op.setToleranceMax(allOperators.get(i).getToleranceMax());
					op.setToleranceMin(allOperators.get(i).getToleranceMin());
					op.setUnite(allOperators.get(i).getUnite());
					return op;
				}
			}
		}
		return null;
	}

}