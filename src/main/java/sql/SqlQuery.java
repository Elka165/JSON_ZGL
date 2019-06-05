package sql;

import java.sql.ResultSet;


public class SqlQuery {
    private String query;
    private SqlConnection sqlConnection = new SqlConnection();
    private String findAll(String claim) {
        query = "SELECT * FROM [Skrypter].[dbo].[SkrypterLog] where [nr_szkody] in ('" + claim  + "') and zmienna='var__dane'";
        return query;
    }

    private String findValue(String claim) {
        query = "SELECT count(*) FROM [Skrypter].[dbo].[SkrypterLog] where [nr_szkody] in ('" + claim  + "') and zmienna='var__dane'";
        return query;
    }

    public String all_claim(String claim) {

        String dane="";

        try {
            query = findAll(claim);

            ResultSet resultSet = sqlConnection.connectUpdate().executeQuery(query);
            while (resultSet.next()) {

                dane =resultSet.getString(7);
            }

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    return dane;
    }


    public Integer findValuueSQL(String claim) {

        Integer dane=0;

        try {
            query = findValue(claim);

            ResultSet resultSet = sqlConnection.connectUpdate().executeQuery(query);
            while (resultSet.next()) {

                dane =resultSet.getInt(1);
            }

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return dane;
    }
}