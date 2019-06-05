package method;


import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;


import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.Collection;

import java.util.HashMap;
import java.util.Map;

public class CreateExcelFile {
    int rownum = 0;
    HSSFSheet firstSheet;
    Collection<File> files;
    HSSFWorkbook workbook;
    File exactFile;

    {
        workbook = new HSSFWorkbook();
        firstSheet = workbook.createSheet("FIRST SHEET");
        Row headerRow = firstSheet.createRow(rownum);
        headerRow.setHeightInPoints(40);
    }

    public void createExcelFile() {
        FileOutputStream fos = null;
        try {

            //wpisz lokalizację
            fos = new FileOutputStream(new File("D:\\Profile\\x\\Desktop\\szkolenie_html\\" + LocalDate.now() + "dane.xls"));
            HSSFCellStyle hsfstyle = workbook.createCellStyle();
            hsfstyle.setBorderBottom((short) 1);
            hsfstyle.setFillBackgroundColor((short) 245);
            workbook.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  CreateExcelFile(HashMap l1) {
        try {

            for (Object key : l1.keySet()){
                Row row = firstSheet.createRow(rownum);
                Cell cell = row.createCell(0);
                cell.setCellValue(key  + "#" + l1.get(key)
                );
                rownum ++;
            }


            JOptionPane.showMessageDialog(null, "Dane zostały exportowane do Excela",
                    "Informacja", JOptionPane.INFORMATION_MESSAGE);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
