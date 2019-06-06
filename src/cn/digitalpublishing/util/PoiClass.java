package cn.digitalpublishing.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.ss.usermodel.Sheet;  
import org.apache.poi.ss.usermodel.Workbook;  
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class PoiClass {
		
	private Workbook workbook = null; // 工作部对象

	private List<HashMap<String,Object>> mapData=null;
	
	private String[] header=null;
	
	private Sheet sheet = null; // 工作表

	public int totalRows = 0; // 总行数

	public int totalColumns = 0; // 总列数

	
	public String[] getHeader() {
		return header;
	}

	public void setHeader(String[] header) {
		this.header = header;
	}

	public List<HashMap<String,Object>> getDatas(){
		return mapData;
	}
	
	public PoiClass(String filename) throws Exception{
		this(new FileInputStream(filename));
	}
	public PoiClass(FileInputStream inputStream) throws Exception {
		workbook = WorkbookFactory.create(inputStream);
		if(workbook.getNumberOfSheets()>0){
			this.sheet = this.workbook.getSheetAt(0);
			this.totalRows=this.sheet.getPhysicalNumberOfRows();
			//System.out.println("Sheet0\"" + sheet + "\" has " + totalRows + " row(s).");
			if(totalRows>0){
				this.totalColumns=this.sheet.getRow(0).getPhysicalNumberOfCells();
				//System.out.println("Sheet0\"" + sheet + "\" has " + totalColumns + " colume(s).");
			}
		}
	}
	
	public PoiClass(InputStream inputStream) throws Exception {
		workbook = WorkbookFactory.create(inputStream);
		if(workbook.getNumberOfSheets()>0){
			this.sheet = this.workbook.getSheetAt(0);
			this.totalRows=this.sheet.getPhysicalNumberOfRows();
			//System.out.println("Sheet0\"" + sheet + "\" has " + totalRows + " row(s).");
			if(totalRows>0){
				this.totalColumns=this.sheet.getRow(0).getPhysicalNumberOfCells();
				//System.out.println("Sheet0\"" + sheet + "\" has " + totalColumns + " colume(s).");
			}
		}
	}
	public boolean Parse(Boolean hasheader)throws Exception{
		try {
			if(sheet!=null){
				Boolean headComplate=false;
				mapData=new ArrayList<HashMap<String,Object>>();
				Integer rows = sheet.getPhysicalNumberOfRows();	
				if(hasheader && !headComplate){
					header=new String[totalColumns];
				}
				for (int r = 0; r < rows; r++) {					
					// 表格首行为列标题，提取列标题---------------------					
					Row row = sheet.getRow(r);					
					if (row == null) {
						continue;
					}
					
					HashMap<String,Object> map=(hasheader && !headComplate)?null:new HashMap<String,Object>();
					for (int c = 0; c < totalColumns; c++) {
						String value=null;
						Cell cell = row.getCell(c);	
						if(cell!=null){
							
							if (cell.getCellType() == Cell.CELL_TYPE_STRING){
								value= cell.getStringCellValue();
							}else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
								if(HSSFDateUtil.isCellDateFormatted(cell)){
									SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 									
									value =sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
								}else{								
									DecimalFormat df = new DecimalFormat("0.0000");  
									value = df.format(cell.getNumericCellValue());
									value=trimZero(value);
								}
							}
						}
						
						if (hasheader && !headComplate){	
							if(value==null || "".equals(value.trim())){
								throw new Exception("标题行不能包含空值！");
							}
							header[c]=value.trim().toLowerCase();							
							if(c+1==totalColumns){
								headComplate=true;
							}
							continue;
						}
						if(map!=null){
							map.put(header[c], value);							
						}							
					}
					if(map!=null){
						mapData.add(map);
					}
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	private String trimZero(String value){
		String _value=value.trim();
		if(_value!=null && !"".equals(_value)){
			while(true){
				if(_value.indexOf(".")>-1 && _value.endsWith("0")){
					_value=_value.substring(0,_value.length()-1);
					if(_value.endsWith(".")){
						_value=_value.substring(0,_value.length()-1);
					}
				}else{
					break;
				}
			}
		}
		return _value;
	}
	public static void main(String[] args) throws Exception {
		
		String excel="C:\\Users\\jack\\Desktop\\date.xlsx";
		PoiClass pc=new  PoiClass(excel);
		pc.Parse(true);
//		List<String> excels=FileUtil.getFiles("C:\\Users\\jack\\Desktop\\upload\\ashgate", "*.xls", false);
//		if(excels!=null &&excels.size()>0){
//			for(String str:excels){
//				PoiClass pc=new PoiClass(str);
//				pc.Parse(true);
//				String[] header=pc.getHeader();
//				if(!pc.getDatas().get(0).containsKey("bic") && !pc.getDatas().get(0).containsKey("subjecttype(bic/bisac/dewey)")){
//					System.out.println(str);
//				}
//				if(header!=null &&header.length>0){
//					for(String h:header){
//						System.out.print(h + "\t");
//					}					
//				}else{
//					System.out.print("错误！" + str);
//				}
//				System.out.print("\n");
//			}
//			
//		}
	}
}

