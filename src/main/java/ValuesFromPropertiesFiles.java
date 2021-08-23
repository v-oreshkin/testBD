import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

    public class ValuesFromPropertiesFiles {

        public static String getValue(String valueFormProper, String pathToProperties){
            try {
                FileInputStream fileInputStream;
                Properties prop = new Properties();
                fileInputStream = new FileInputStream(pathToProperties);
                prop.load(fileInputStream);
                return prop.getProperty(valueFormProper);
            }catch (IOException e){
                e.getMessage();
                return null;
            }
        }
    }
