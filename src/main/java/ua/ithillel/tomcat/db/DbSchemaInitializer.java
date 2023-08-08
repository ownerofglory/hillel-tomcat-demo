package ua.ithillel.tomcat.db;

import org.apache.ibatis.jdbc.ScriptRunner;
import ua.ithillel.tomcat.exception.DbInitException;

import java.io.*;
import java.sql.Connection;

public class DbSchemaInitializer {
    public static void init(Connection connection, String filePath) throws DbInitException {

//                (
//                Reader reader = new BufferedReader(new FileReader(filePath))
//        )
        {
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            String sql = "CREATE TABLE IF NOT EXISTS meal (\n" +
                    "  id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "  name TEXT NOT NULL,\n" +
                    "  image_url TEXT,\n" +
                    "  recipe TEXT,\n" +
                    "  video_url TEXT,\n" +
                    "  themealdb_id TEXT,\n" +
                    "  added_timestamp INTEGER\n" +
                    ");\n" +
                    "\n" +
                    "CREATE TABLE IF NOT EXISTS ingredient (\n" +
                    "  id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "  name TEXT NOT NULL,\n" +
                    "  measure TEXT NOT NULL,\n" +
                    "  meal_id INTEGER,\n" +
                    "  FOREIGN KEY (meal_id) REFERENCES meal(id)\n" +
                    ");\n";
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(sql.getBytes());
            InputStreamReader inputStreamReader = new InputStreamReader(byteArrayInputStream);
            scriptRunner.runScript(inputStreamReader);

//            scriptRunner.runScript(reader);
        }
    }
}
