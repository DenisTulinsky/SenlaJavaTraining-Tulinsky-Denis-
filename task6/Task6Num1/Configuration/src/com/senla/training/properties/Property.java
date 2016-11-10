package com.senla.training.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Property {

	private static InputStream inputStream;
	private static Properties prop = new Properties();
	private static Holder holder;

	public static Holder getProps() throws IOException {
		try {
			inputStream = new FileInputStream(new File("src/config.properties"));

			if (inputStream != null) {
				prop.load(inputStream);

			} else {
				throw new FileNotFoundException("property file not found in the classpath");
			}

			holder = new Holder(Boolean.valueOf(prop.getProperty("executePreorders")),
					Integer.parseInt(prop.getProperty("monthsUnwanted")));


			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			inputStream.close();
		}

		return holder;
	}

}
