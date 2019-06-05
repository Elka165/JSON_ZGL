import method.CreateExcelFile;
import method.DateLoader;
import sql.SqlQuery;


import org.json.JSONObject;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        HashMap mapDamage = new HashMap();
        HashMap mapKierowalnosc = new HashMap();
        List lista = new ArrayList();
        DateLoader dateLoader = new DateLoader();
        lista = dateLoader.addToList();


        for (int x = 0; x < lista.size(); x++) {

            jsonDateDamages(lista.get(x).toString());

            mapKierowalnosc.put(lista.get(x).toString(), jsonDateKierowalność(lista.get(x).toString()));
            jsonDateKierowalność(lista.get(x).toString());
            mapDamage.put(lista.get(x).toString(), jsonDateDamages(lista.get(x).toString()));
        }
        CreateExcelFile cls = new CreateExcelFile(mapDamage);
        cls.createExcelFile();

    }

    public static String jsonDateDamages(String key) {
        String dane = "";
        Set key1 = new HashSet();
        SqlQuery sqlQuery = new SqlQuery();

        if (sqlQuery.findValuueSQL(key) > 0) {
            JSONObject obj = new JSONObject(sqlQuery.all_claim(key));

            key1.addAll(obj.keySet());

            if (keyInformation(key1, "varZdarzUszkodzeniaOpis") == 1 && keyInformation(key1, "varZdarzUszkodzenia") == 0) {
                dane = obj.getString("varZdarzUszkodzeniaOpis");

            } else if (keyInformation(key1, "varZdarzUszkodzenia") == 1) {

                dane = obj.getString("varZdarzUszkodzenia");

            } else if (keyInformation(key1, "varMajatekUszkodzeniaOpis") == 1) {
                dane = obj.getString("varMajatekUszkodzeniaOpis");
            } else if (keyInformation(key1, "varZdarzUszkodzeniaOpis") == 0 && keyInformation(key1, "uszkodzenia") == 1 && keyInformation(key1, "zdarzenie") == 0) {

                JSONObject obj2 = new JSONObject(obj.get("uszkodzenia").toString());

                if (obj2.getString("opisUszkodzen").length() > 3) {
                    dane = obj2.getString("opisUszkodzen");
                } else {
                    dane = obj2.getString("pojazd");
                }

            } else if (keyInformation(key1, "zdarzenie") == 1) {
                JSONObject obj2 = new JSONObject(obj.get("uszkodzenia").toString());
                Set key2 = new HashSet();
                key2.addAll(obj2.keySet());
                if (keyInformation(key2, "pojazd") == 1 && obj2.getString("pojazd").length()>3) {
                    dane = obj2.getString("pojazd");
                } else if (keyInformation(key2, "opisUszkodzen") == 1) {
                    dane = obj2.getString("opisUszkodzen");

                } else {
                    dane = "brak danych";
                }

            } else {
                dane = "brak danych";
            }
        }
        return dane;
    }

    public static String jsonDateKierowalność(String key) {
        String dane = "";
        Set key1 = new HashSet();
        SqlQuery sqlQuery = new SqlQuery();

        if (sqlQuery.findValuueSQL(key) > 0) {
            JSONObject obj = new JSONObject(sqlQuery.all_claim(key));
            key1.addAll(obj.keySet());
            if (keyInformation(key1, "tmp_eh24mail") == 1) {
                dane = obj.getString("tmp_eh24mail");
            } else if (keyInformation(key1, "varZdarzProceduraLikwidacji") == 1) {

                dane = obj.getString("varZdarzProceduraLikwidacji");

            } else {
                dane = "brak danych";
            }
        }
        return dane;
    }

//           ;
//           System.out.println("stary skrypter" +  obj.getString("varZdarzUszkodzeniaOpis"));
//            System.out.println(obj.getString("varZdarzPolicjaObecna")); --stary skrypter

    //nowy Skrypter MTU
    //dane ze zgłoszenia
    // System.out.println(obj.get("kierowalnosc"));

    // System.out.println( obj1.getString("EHP24"));


//           JSONObject obj1 = new JSONObject(obj.get("kierowalnosc").toString());
    //  JSONObject obj2 = new JSONObject(obj.get("uszkodzenia").toString());

    // dane= obj2.getString("opisUszkodzen");


    // dane uszkodzenie
//            JSONObject obj1 = new JSONObject(obj.get("kierowalnosc").toString());
//            JSONObject obj2 = new JSONObject(obj.get("uszkodzenia").toString());

    // System.out.println(obj.get("uszkodzenia"));
//
    //  System.out.println(obj2.getString("preferowanyWariantRozliczenia"));


    public static Long keyInformation(Set key1, String key) {
        return key1.stream().filter(s -> s.equals(key)).count();
    }


}
