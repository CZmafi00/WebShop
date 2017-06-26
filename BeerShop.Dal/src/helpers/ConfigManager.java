package helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mario on 23.5.2017..
 */
public class ConfigManager {

    private String DBNAME = "dbName";
    private String HOST = "host";
    private String PORT = "port";
    private String COLLECTIONS = "collections";
    private String CONFIGFILE = "C:\\Users\\mario\\Desktop\\WebShop\\BeerShop.Dal\\config\\dbConfig.json";

    private String dbName;
    private String host;
    private int port;
    private ArrayList<String> collections;

    public ConfigManager() throws FileNotFoundException {

        JsonReader reader = new JsonReader(new FileReader(CONFIGFILE));

        Gson gson = new GsonBuilder().create();

        Map<String, Object> map = new HashMap<String, Object>();
        map = (HashMap<String, Object>)gson.fromJson(reader, map.getClass());

        initializeFields(map);
    }

    private void initializeFields(Map<String, Object> values) {

        dbName = values.get(DBNAME).toString();
        host = values.get(HOST).toString();
        port = Integer.parseInt(values.get(PORT).toString());
        collections = (ArrayList<String>) values.get(COLLECTIONS);
    }

    public String getDbName() {
        return dbName;
    }

    public String getHost() {
        return host;
    }

    public ArrayList<String> getCollections() {
        return collections;
    }

    public int getPort() {
        return port;
    }

}
