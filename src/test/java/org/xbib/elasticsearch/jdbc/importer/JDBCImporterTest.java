package org.xbib.elasticsearch.jdbc.importer;

import org.elasticsearch.common.settings.Settings;
import org.testng.annotations.Test;
import org.xbib.tools.JDBCImporter;

public class JDBCImporterTest {
    @Test
    public void testImporter() throws Exception {
        final JDBCImporter importer = new JDBCImporter();
        Settings settings = Settings.settingsBuilder()
                .put("url", "jdbc:mysql://localhost:3306/ES-data-sync")
                .put("password", "root")
                .put("username","root")
                .put("sql", "select * from article")
                .put("index", "jdbc")
                .put("type", "jdbc")
                .build();
        importer.setSettings(settings);
        importer.run();
        Thread.sleep(12000L);
        importer.shutdown();
    }
}
