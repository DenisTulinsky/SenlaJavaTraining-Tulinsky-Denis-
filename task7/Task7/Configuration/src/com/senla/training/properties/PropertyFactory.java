package com.senla.training.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertyFactory {

	private static Holder holder;
	private static Logger log = Logger.getLogger(PropertyFactory.class);

	public static Holder getProps() {

		InputStream inputStream = null;

		if (holder == null) {
			try {

				Properties prop = new Properties();
				inputStream = new FileInputStream(new File("Resources/config.properties"));

				if (inputStream != null) {
					prop.load(inputStream);

				}
				holder = new Holder();

				for (String key : prop.stringPropertyNames()) {
					String value = prop.getProperty(key);
					holder.setProp(key, value);
				}

			} catch (Exception e) {
				log.error(e.getMessage());
			} finally {
				try {
					inputStream.close();
				} catch (IOException e) {

					log.error(e.getMessage());
				}
			}
		}
		return holder;
	}
}
