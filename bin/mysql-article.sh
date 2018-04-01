#!/bin/sh

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
bin=${DIR}/../bin
lib=${DIR}/../lib

echo '
{
    "type" : "jdbc",
    "jdbc" : {
        // "url" : "jdbc:mysql://172.31.0.67:3306/who_brand",
        "url" : "jdbc:mysql://localhost:3306/es-jdbc-test",

        "statefile" : "statefile.json",
        "user" : "root",
        //"password" : "l8ka65",
        "password" : "root",

        "sql" : "select *, id as _id from article",

        "index" : "es-jdbc-test",
        "type" : "article",
        "metrics": {
            "enabled" : true
        },
        "elasticsearch" : {
             "cluster" : "elasticsearch",
             "host" : "localhost",
             "port" : 9300 
        }
        

    }
}
' | java \
    -cp "${lib}/*" \
    -Dlog4j.configurationFile=${bin}/log4j2.xml \
    org.xbib.tools.Runner \
    org.xbib.tools.JDBCImporter
