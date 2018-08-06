package com.wevioo.utility;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.SAXException;


/**
 * Utilities class for common methods.
 *
 */
public abstract class CommonUtil {

	/**
	 * Logger.
	 */
	private static final Log LOGGER = LogFactory.getLog(CommonUtil.class);
	
	/**
	 * Messages
	 */
	public static final ResourceBundle BUNDLE = ResourceBundle.getBundle("i18n/messages");
	public static final String PROJECT_FILE_PATH = "/application.properties";

	/**
	 * validate xml against xsd file.
	 *
	 * @param xml
	 * @param xsd
	 * @return boolean
	 * @throws SAXException
	 * @throws IOException
	 */
	public static boolean validateAgainstXSD(final InputStream xml,
			final InputStream xsd) throws IOException {

		try {
			final SchemaFactory factory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			final Schema schema = factory.newSchema(new StreamSource(xsd));
			final Validator validator = schema.newValidator();
			validator.validate(new StreamSource(xml));
			return true;
		} catch (final SAXException e) {
			LOGGER.error(e.getMessage(), e);
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * get properties file.
	 *
	 * @return Properties
	 */
	public static Properties getProjectProperties() throws IOException {

		final Properties props = new Properties();
		InputStream ios = null;
		try {
			ios = CommonUtil.class.getResourceAsStream(PROJECT_FILE_PATH);
			props.load(ios);

		} finally {
			if (ios != null) {
				ios.close();
			}
		}
		return props;
	}

	/**
	 * Get the current operating system name. return the name of the operating
	 * system such as : windows, Linux, Unix and so on. ;
	 **/
	public static String getOSName() {
		return System.getProperty("os.name").toLowerCase();
	}

	/**
	 * Get widnows card mac address. @ return mac address
	 */
	public static String getWindowsMACAddress () {
		String mac = null; ;
		BufferedReader bufferedReader = null; ;
		Process process = null; ;
		try { ;
		/**
		 * windows under final the command , the displayed final information includes mac address
		 */
		process = Runtime.getRuntime().exec("ipconfig /all");
		bufferedReader =  new BufferedReader (new InputStreamReader (process
				. getInputStream ()));
		String line = null;
		int index = -1 ;
		while (( line = bufferedReader.readLine ())!= null) {
			System.out.println(line + "+++++++++++++");
			/**
			 * Find final labeled string [final physical address]
			 */
			index = line.toLowerCase (). indexOf ("physical address");
			if (index != -1) {
				index = line.indexOf (":");
				if (index != -1) {
					/**
					 * remove the mac address and remove the two side spaces
					 */
					mac = line.substring (index + 1). trim ();
				}
				break;
			}
		} ;
		} catch (final IOException e) {
			e.printStackTrace ( ) ;
		} finally {
			try { ;
			if (bufferedReader != null) {
				bufferedReader.close ();
			}
			} catch ( final IOException e1) {
				e1.printStackTrace ();
			}
			bufferedReader = null;
			process = null;
		}

		return mac;
	}




	/**
	 * Get the IP address.
	 *
	 * @return
	 */
	public static String getIpAddress() {
		InetAddress ip;
		try {
			ip = InetAddress.getLocalHost();
			return ip.toString();
		} catch (final Exception e) {
			return null;
		}

	}

	/**
	 * get time diff.
	 *
	 * @param startDate
	 * @param endDate
	 * @return Long (millisecond)
	 */
	public static Long calculateDiffDate(final Date startDate,
			final Date endDate) {

		return (endDate.getTime() - startDate.getTime());
	}

	public static boolean containsZarchive(final File[] listFiles,
			final String zArchiveName) {
		for (final File file : listFiles) {

			if (file.isDirectory() && file.getName().equals(zArchiveName)) {
				return true;
			}
		}
		return false;
	}

	public static boolean containsVideo(final File[] listFiles,
			final String videoName) {
		for (final File file : listFiles) {

			if (file.isDirectory() && file.getName().equals(videoName)) {
				return true;
			}
		}
		return false;
	}

	public static boolean containsVersion(final File[] listFiles,
			final String ver) {
		for (final File file : listFiles) {

			if (file.isFile() && file.getName().equals(ver)) {
				return true;
			}
		}
		return false;

	}

	/**
	 * readAllFilesFromRep
	 *
	 * @param rep
	 * @return List<String>
	 */
	public static List<String> readAllFilesFromRep(final String rep) {
		final List<String> res = new ArrayList<String>();
		final File f = new File(rep);
		for (final File file : f.listFiles()) {
			res.add(file.getAbsolutePath());
		}
		return res;
	}
	
	
	/**
	 * Create temporary file to show
	 *
	 * @param request
	 * @param currentItem
	 * @return
	 */
	public static boolean createImageTempFile(final HttpServletRequest request, byte[] currentItem, String fileName, String directory) {
		String realPathTempFile = null;

		String s2 = "";
		try {
			
			
			if (currentItem!=null) {
				LOGGER.info("File to create : "+realPathTempFile);
				realPathTempFile = directory+fileName+".png";
				LOGGER.info("Emplacement réelle : "+realPathTempFile);
				File f = new File(realPathTempFile);
				LOGGER.info("begin create temporary file");
				final boolean res = f.createNewFile();
				if (res) {
					LOGGER.info(realPathTempFile + " :temporary file successfully created");
				} else {
					LOGGER.info(realPathTempFile + " :temporary file already exist");
				}
				FileOutputStream fos = null;
				try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream(currentItem.length);
				baos.write(currentItem, 0, currentItem.length);
				fos = new FileOutputStream (f); 
				baos.writeTo(fos);
				} catch(IOException ioe) {
					LOGGER.error(ioe.getMessage());
				    ioe.printStackTrace();
				} finally {
					if(fos!=null)
						fos.close();
				}
			} else {
				LOGGER.error(fileName + ".png : File not found or is a directory");
			}
		} catch (final IOException e) {
			LOGGER.error(e + s2 + "/" + fileName);
			return false;
		} finally {
			

		}
		return true;
	}
	
	public static byte[] readContentIntoByteArray(File file) throws IOException
	   {
	      FileInputStream fileInputStream = null;
	      byte[] bFile = new byte[(int) file.length()];
	      try
	      {
	         //convert file into array of bytes
	         fileInputStream = new FileInputStream(file);
	         fileInputStream.read(bFile);
	         
	         for (int i = 0; i < bFile.length; i++)
	         {
	            System.out.print((char) bFile[i]);
	         }
	      }
	      catch (Exception e)
	      {
	         e.printStackTrace();
	      }finally{
	    	 if(fileInputStream!=null)
				fileInputStream.close();
	      }
	      return bFile;
	   }
	
	public static String[] concat(String[]... arrays) {
		int length = 0;
		for (String[] array : arrays) {
			length += array.length;
		}
		String[] result = new String[length];
		int destPos = 0;
		for (String[] array : arrays) {
			System.arraycopy(array, 0, result, destPos, array.length);
			destPos += array.length;
		}
		return result;
	}
	
	/**
	 * get current Locale
	 * 
	 * @param context
	 * @return
	 */
	public static Locale getLocale(final HttpServletRequest request) {

		Locale locale = null;
		if (request != null) {

			locale = request.getLocale();

		}
		if (locale == null) {

			locale = Locale.getDefault();

		} // end of if (locale == null)

		return locale;

	} // end of getLocale()
	
	/**
	 * Get message with parameter
	 * 
	 * @param key
	 * @param params
	 * @return
	 */
	
	
	/**
	 * Get message with parameter
	 * 
	 * @param key
	 * @param params
	 * @return
	 */
	public static String getMessageResourceString(final String key, final Object params[]) {

		String text;
		try {
			text = BUNDLE.getString(key);
		} catch (final MissingResourceException e) {
			text = key;
		}

		if (params != null) {
			final MessageFormat formatter = new MessageFormat(text);
			return formatter.format(params);
		}

		return text;
	}
	
	
}
