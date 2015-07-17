import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Alex on 10.07.15.
 */
public class ExcelPage {
    private XSSFSheet ws;

    public ExcelPage(String path,int sheetAt){
        try {
            XSSFWorkbook wb = new XSSFWorkbook(path);
            this.ws = wb.getSheetAt(sheetAt);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook( new FileInputStream(new File(path)));
            this.ws = wb.getSheetAt(sheetAt);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }

    public Object[] getValues(String colName)throws IOException{
        int colNum = ws.getRow(0).getLastCellNum();
        int j=0;
        for (int i = 0; i < colNum; i++) {
            if(ws.getRow(0).getCell(i).toString().equals(colName)) {
                j = i;
                break;
            }
        }
        HashSet<String> ret = new HashSet<String>();
        for(int i = 1; ws.getRow(i)!=null; i++) {
            ret.add(ws.getRow(i).getCell(j).toString());
        }
        return ret.toArray();
    }

    public String[] getRow(int i){
        if(ws.getRow(i)==null){
            return null;
        }
        int colNum = ws.getRow(i).getLastCellNum();
        String []ret = new String[colNum];
        for (int j = 0; j < colNum; j++) {
            XSSFCell xssfCell= ws.getRow(i).getCell(j);
            if(xssfCell==null){
                ret[j]="";
                continue;
            }
            ret[j]=xssfCell.getCellType()==0?xssfCell.getRawValue():xssfCell.toString();
        }
        return ret;
    }

    public String[] getCol(int j,int startIndex){
        if(ws.getRow(0)==null){
            return null;
        }
        if(ws.getRow(0).getCell(j)==null) {
            return null;
        }

        int n=0;
        for (int i = startIndex; ws.getRow(i)!=null&&ws.getRow(i).getCell(j).toString()!=""; i++) {
            n++;
        }
        String []ret = new String[n];
        for (int i = 0; i<ret.length; i++) {
            XSSFCell xssfCell= ws.getRow(i+startIndex).getCell(j);
            ret[i]=xssfCell.getCellType()==0?xssfCell.getRawValue():xssfCell.toString();
        }
        return ret;
    }

    public static HashMap<String,String> getVocabulary(ExcelPage excelPage,int startIndex,int keyCol,int valueCol){
        HashMap<String,String> ret = new HashMap<String, String>();
        for (int i = 0; true; i++) {
            String[]row = excelPage.getRow(i+startIndex);
            if(row==null){
                break;
            }
            ret.put(row[keyCol],row[valueCol]);
        }
        return ret;
    }

}
