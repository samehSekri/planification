package com.wevioo.utility.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.wevioo.dao.ArticleRepository;
import com.wevioo.dao.OperateurRepository;
import com.wevioo.dao.PolyvalenceRepository;
import com.wevioo.exception.DataNotFoundException;
import com.wevioo.exception.RestException;
import com.wevioo.model.Article;
import com.wevioo.model.Operateur;
import com.wevioo.model.Polyvalence;
import com.wevioo.model.PolyvalenceOperateurArticle;
import com.wevioo.model.Unite;
import com.wevioo.model.enumeration.DegrePolyvalenceEnum;
import com.wevioo.service.PolyvalenceService;
import com.wevioo.utility.ApplicationConstants;
import com.wevioo.utility.DateUtils;
import com.wevioo.utility.LoggerUtility;

@Component
public class ExcelFileUtil {
	@Autowired
	PolyvalenceService polyvalenceService;
	@Autowired
	PolyvalenceRepository polyvalenceRepository;
	@Autowired
	OperateurRepository operateurRepository;
	@Autowired
	ArticleRepository articleRepository;

	/**
	 * Le loggeur LOG4J.
	 */
	private final LoggerUtility logger = new LoggerUtility(this.getClass());

	public static String readStringFromFile(final Cell cell, final FormulaEvaluator evaluator, CellValue cellValue)
			throws RestException {

		try {
			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				return cell.getStringCellValue();
			} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
				cellValue = evaluator.evaluate(cell);
				return cellValue.getNumberValue() + "";
			} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (DateUtil.isCellDateFormatted(cell)) {
					return DateUtils.formatDate(cell.getDateCellValue(), DateUtils.DATE_FORMATTER_SECOND);
				} else {
					return cell.getNumericCellValue() + "";
				}
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
				return null;
			}
			return null;
		} catch (Exception e) {
			throw e;
		}

	}

	private List<Polyvalence> parsingPolyvalencesFromFile(FileInputStream fileInputStream, String filePath)
			throws RestException {
		logger.info("Begin parsingPolyvalencesFromFile(" + filePath + ")");

		final List<Polyvalence> listePloyvalences = new ArrayList<Polyvalence>();
		final int sheetNumber = 0;
		Row row = null;
		Map<Integer, Operateur> mapMatricules = null;
		final Iterator<Row> rowIterator;

		String[] tab = filePath.split("\\.");
		String extension = tab[tab.length - 1].toUpperCase();

		int sizeList = 0;
		final FormulaEvaluator formulaEvaluator;

		if (extension.equals("XLSX")) {
			// Finds the workbook instance for XLSX file
			XSSFWorkbook workBook_xlsx = null;
			try {
				workBook_xlsx = new XSSFWorkbook(fileInputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Return first sheet from the XLSX workbook
			final XSSFSheet sheet = workBook_xlsx.getSheetAt(sheetNumber);
			rowIterator = sheet.iterator();
			formulaEvaluator = workBook_xlsx.getCreationHelper().createFormulaEvaluator();
		} else if (extension.equals("XLS")) {
			HSSFWorkbook workBook_xls = null;
			try {
				workBook_xls = new HSSFWorkbook(fileInputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			final HSSFSheet sheet = workBook_xls.getSheetAt(sheetNumber);
			rowIterator = sheet.iterator();
			formulaEvaluator = workBook_xls.getCreationHelper().createFormulaEvaluator();
		} else {
			// LOGGER.error("Le format du fichier est inacceptable");
			try {
				fileInputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			throw new RestException(ApplicationConstants.ERROR_WRONG_FILE);
		}

		if (rowIterator.hasNext()) {
			row = rowIterator.next();
		}

		while (rowIterator.hasNext()) {
			// row = rowIterator.next();

			logger.info(" row.getRowNum() = " + row.getRowNum());

			// First Line of the File ...
			if (row.getRowNum() <= 3 || row.getRowNum() == 5 || row.getRowNum() == 6) {
				row = rowIterator.next();
				continue;
			} else if (row.getRowNum() == 4) {
				mapMatricules = getMatriculesFromPolyvalence(row, formulaEvaluator);
				logger.info("mapMatricules size = " + mapMatricules.size());
				for (Integer key : mapMatricules.keySet()) {
					logger.info("key:" + key + ", value:" + mapMatricules.get(key).getMatricule());
				}

				sizeList = row.getPhysicalNumberOfCells() - 2;
			} else if (row.getRowNum() > 6 && rowIterator.hasNext()) {
				List<Polyvalence> polyvalences = getPolyvalenceByArticle(row, formulaEvaluator, mapMatricules,
						sizeList);
				if (polyvalences != null) {
					for (Polyvalence p : polyvalences) {
						if (p.getArticle() == null || p.getArticle().getReference() == null
								|| p.getArticle().getReference().isEmpty() || p.getOperateur() == null
								|| p.getOperateur().getMatricule() == null
								|| p.getOperateur().getMatricule().isEmpty()) {
							polyvalences.remove(p);
						}
					}
					if (polyvalences != null) {
						listePloyvalences.addAll(polyvalences);
					}
				} else {

					return listePloyvalences;
				}
			}

			row = rowIterator.next();
		}
		return listePloyvalences;

	}

	@Transactional
	public void savePolyvalences(FileInputStream fileInputStream, String filePath) throws RestException {
		List<Polyvalence> list = parsingPolyvalencesFromFile(fileInputStream, filePath);

		logger.info("***********  function  savePolyvalences  ***************");

		insertListPolyvalence(list);

		// for (Polyvalence p : list) {
		// logger.info("save");
		// // polyvalenceRepository.saveAndFlush(p);
		// //logger.info(p.toString());
		// logger.info(p.getArticle().toString());
		// logger.info(p.getOperateur().toString());
		// logger.info(p.getDegre().toString());
		// }

	}

	private Map<Integer, Operateur> getMatriculesFromPolyvalence(Row row, FormulaEvaluator evaluator)
			throws RestException {

		final Iterator<Cell> cellIterator = row.cellIterator();
		Map<Integer, Operateur> mapMatricules = new HashMap<>();
		Cell cell;
		CellValue cellValue = null;
		int i = 0;

		// LOGGER.info("Nombre Colonne =" + row.getLastCellNum());

		logger.info("***********  function getMatriculesFromPolyvalence( ) **************");

		while (i < row.getPhysicalNumberOfCells() - 3) {
			if (cellIterator.hasNext()) {
				cell = cellIterator.next();

				i = cell.getColumnIndex();

				// Colonne 1 : non utilisee
				if (i == 0 || i == 1 || (i == (row.getPhysicalNumberOfCells() - 3))) {
					continue;
				} else {
					String matricule = ExcelFileUtil.readStringFromFile(cell, evaluator, cellValue);
					logger.info("-----> matricule = " + matricule);
					if (matricule != null && !matricule.isEmpty()) {
						matricule = Double.valueOf(matricule).intValue() + "";

						logger.info("Colonne = " + i + ", valeur : " + matricule + ", index: " + cell.getColumnIndex());

						// check if operator exist
						Operateur op = operateurRepository.findOne(matricule);

						if (op != null) {
							mapMatricules.put(cell.getColumnIndex(), op);
						} else {
							// HttpStatus : 428 Precondition Required
							throw new RestException("PRECONDITION_REQUIRED", "Aucun opérateur n'existe avec la matricule " + matricule);

							// throw error;
							// throw new Exception(
							// CommonUtil
							// .getMessageResourceString(
							// request,
							// Constants.ERROR_MESSAGE_OPERATOR_MATRICULE_NOT_EXIST,
							// new String[] { matricule })
							// );

						}
					}
				}
			}
		}

		logger.info("Liste des matricules traitée avec succés");
		return mapMatricules;
	}

	private List<Polyvalence> getPolyvalenceByArticle(final Row row, FormulaEvaluator evaluator,
			Map<Integer, Operateur> mapMatricules, int sizeList) throws RestException {

		final Iterator<Cell> cellIterator = row.cellIterator();
		Map<String, Article> mapReference = new HashMap<>();
		Cell cell;
		CellValue cellValue = null;
		int i = 0;
		logger.info("begin getArticleObjectFromCadence (row=" + row.getRowNum() + ")");

		List<Polyvalence> listePolyvalences = new ArrayList<Polyvalence>();
		Polyvalence polyvalence;
		String lastReference = null;

		Article article = new Article();
		logger.info("******* sizeList = " + sizeList);
		while (i < sizeList - 2) {
			if (cellIterator.hasNext()) {
				cell = cellIterator.next();

				i = cell.getColumnIndex();

				// Colonne 1 : non utilisee
				if (i == 0) {
					continue;
				} else if (i == 1) {
					String articleString = ExcelFileUtil.readStringFromFile(cell, evaluator, cellValue);
					logger.info("Colonne =" + i + ", valeur:" + articleString);

					if (articleString != null && !articleString.trim().isEmpty()) {
						lastReference = articleString.trim();
						article.setReference(lastReference);
					} else {

						return null;
					}
					Article searchedArticle = articleRepository.findOne(lastReference);
					// Unite uniteArticle = null;

					if (searchedArticle == null) {
						logger.info("**** Error : article [" + lastReference + "] not found in database");

					}
				} else {
					String value = ExcelFileUtil.readStringFromFile(cell, evaluator, cellValue);
					logger.info("--> article = " + lastReference + ", valeur = " + value);

					if (value != null && !value.isEmpty() && !value.trim().equals("")) {
						if (article.getReference() != null && !article.getReference().isEmpty()) {
							logger.info("cell.getColumnIndex()=" + cell.getColumnIndex() + ", mapMatricules.get("
									+ cell.getColumnIndex() + ") = " + mapMatricules.get(cell.getColumnIndex()));

							if (mapMatricules.get(cell.getColumnIndex()) != null) {
								// mapMatricules.get(cell.getColumnIndex()).setStatut(StatutOperateurEnum.ACTIF);
								polyvalence = new Polyvalence();
								polyvalence.setArticle(article);
								Operateur op = mapMatricules.get(cell.getColumnIndex());
								polyvalence.setOperateur(op);

								PolyvalenceOperateurArticle polyvalenceId = new PolyvalenceOperateurArticle();
								polyvalenceId.setMatricule(op.getMatricule());
								polyvalenceId.setReference(article.getReference());

								polyvalence.setPolyvalenceoperateurarticle(polyvalenceId);
								try {

									DegrePolyvalenceEnum degre = DegrePolyvalenceEnum.valueOf(value);
									polyvalence.setDegre(degre);
									logger.info(degre.toString());

								} catch (Exception ex) {
									// throw new
									// Exception(CommonUtil.getMessageResourceString(request,
									// Constants.ERROR_MESSAGE_DEGRE_ERRONE,
									// new String[] { (op.getMatricule()),
									// article.getReference() }));
								}

								// polyvalence.setDateAffectation(new Date());

								listePolyvalences.add(polyvalence);
							} else {
								// // Rien à faire
								logger.info("Colonne vide : " + cell.getColumnIndex());
							}
						} else {
							return null;
						}
					}

				}
			}

		}
		// LOGGER.info("Fin de lecture des articles...");
		return listePolyvalences;

	}

	public List<Polyvalence> insertListPolyvalence(List<Polyvalence> list) {
		return polyvalenceRepository.save(list);
	}

}
