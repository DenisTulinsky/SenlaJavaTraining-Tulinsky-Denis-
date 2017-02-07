package com.senla.training.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * Privides instruments for reading properties from the file and setting them into the map in the Holder.
 * 
 * @author Denis
 *
 */
public class PropertyFactory {

	private static Holder holder;
	//private static Logger log = Logger.getLogger(PropertyFactory.class);

	
	/**
	 * Instantiates a holder if was not previously created, reads properties from file,
	 * sets the map in the holder with the properties values 
	 * 
	 * @return Holder
	 *  @throws  IOException 
	 */
	public static Holder getProps() {

		InputStream inputStream = null;
		if (holder == null) {
			try {
				Properties prop = new Properties();
				inputStream = new FileInputStream(new File("Resources/config.properties"));
				//inputStream = new FileInputStream(new File(path));
				if (inputStream != null) {
					prop.load(inputStream);
				}
				holder = new Holder();

				for (String key : prop.stringPropertyNames()) {
					String value = prop.getProperty(key);
					holder.setProp(key, value);
				}
			} catch (Exception e) {
				//log.error(e.getMessage());
				e.printStackTrace();
			} finally {
				try {
					inputStream.close();
				} catch (IOException e) {
					//log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		}
		return holder;
	}
}
