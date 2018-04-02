package org.xbib.elasticsearch.jdbc.importer;

import org.elasticsearch.common.settings.Settings;
import org.xbib.tools.JDBCImporter;

import java.util.concurrent.TimeUnit;

public class JDBCImporterTest {

    public static void main(String[] args) throws Exception {
        new JDBCImporterTest().testImporter();
    }

    public void testImporter() throws Exception {
        final JDBCImporter importer = new JDBCImporter();
        Settings settings = Settings.settingsBuilder()
                //.put("url", "jdbc:mysql://localhost:3306/es-jdbc-test")
                .put("url", "jdbc:mysql://172.31.0.67:3306/who_brand")
                .put("user", "root")
                //.put("password", "root")
                .put("password", "l8ka65")
                .put("sql", "select *, rec_id as _id from who_wms_goods_stock_detail_log_1")
                .put("index", "wms")
                .put("type", "stock_detail_log")
                .build();
        importer.setSettings(settings);
        importer.run();
        //Thread.sleep(6000);
        importer.shutdown();
    }
}
