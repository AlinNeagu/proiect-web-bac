
package proiect.bac.export;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import proiect.bac.connection.CreateConnection;
import proiect.bac.controller.JSONController;
import proiect.bac.entities.DateElev;
import proiect.bac.entities.Disciplina;
import proiect.bac.entities.Proba;
import proiect.bac.entities.Specializare;
import proiect.bac.entities.UnitateDeInvatamant;
import proiect.bac.jackson.ElevPOJO;
import proiect.bac.jackson.EleviPOJO;

@Component
public class ExportTara {
	HSSFWorkbook workbook;
	@Autowired
	EleviUTIL eleviUTIL ;
	public  void main(String[] args) throws SQLException, FileNotFoundException {
		exportPeTara();
	}

	@SuppressWarnings("resource")
	public  byte[] exportPeTara() throws FileNotFoundException {

		try {

		

			System.out.println("The export starts here!");
			
			//FileOutputStream fileOut;
			ByteArrayOutputStream fileOut;
			//fileOut = new FileOutputStream("fisierExport-Tara.xls");
			fileOut = new ByteArrayOutputStream();
			workbook = new HSSFWorkbook();
			HSSFSheet worksheet = workbook.createSheet("Tara");

			HSSFCellStyle style = workbook.createCellStyle();
			HSSFFont font = workbook.createFont();
			font.setColor(HSSFColor.BLACK.index);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style.setFont(font);

			HSSFCellStyle style2 = workbook.createCellStyle();
			HSSFFont font2 = workbook.createFont();
			font2.setColor(HSSFColor.BLACK.index);
			style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style2.setFont(font2);
			
			
			//trial for coloured rows
			 HSSFCellStyle oddRowStyle;
			 HSSFCellStyle evenRowStyle;
			 HSSFCellStyle headerStyle;
			 
			 oddRowStyle  = createStyle(font2, HSSFCellStyle.ALIGN_CENTER,   HSSFColor.PALE_BLUE.index, true, HSSFColor.BLACK.index);
			 evenRowStyle = createStyle(font2, HSSFCellStyle.ALIGN_CENTER, HSSFColor.LIGHT_CORNFLOWER_BLUE.index, true, HSSFColor.BLACK.index);
			 headerStyle = createStyle(font, HSSFCellStyle.ALIGN_CENTER, HSSFColor.LIGHT_BLUE.index, true, HSSFColor.BLACK.index);
			
			
			HSSFRow header = worksheet.createRow(0);
			HSSFCell cell;
			cell = header.createCell(0);
			cell.setCellValue("       Nume        ");
			cell.setCellStyle(headerStyle);
			cell = header.createCell(1);
			cell.setCellValue("   Prenume   ");
			cell.setCellStyle(headerStyle);
			cell = header.createCell(2);
			cell.setCellValue("Initiala Tata");
			cell.setCellStyle(headerStyle);
			cell = header.createCell(3);
			cell.setCellValue("                                     Unitate de Invatamant                               ");
			cell.setCellStyle(headerStyle); 
			cell = header.createCell(4);
			cell.setCellValue("               Specializare                ");
			cell.setCellStyle(headerStyle); 
			worksheet.addMergedRegion(new CellRangeAddress(0, 0, 5, 7));
			cell = header.createCell(5);
			cell.setCellValue(" Limba Romana");
			cell.setCellStyle(headerStyle);
			cell = header.createCell(8);
			cell.setCellValue("Limba moderna");
			cell.setCellStyle(headerStyle);
			cell = header.createCell(9);
			cell.setCellValue("Nota");
			cell.setCellStyle(headerStyle);
			worksheet.addMergedRegion(new CellRangeAddress(0, 0, 10, 12));
			cell = header.createCell(10);
			cell.setCellValue(" Disciplina Obligatorie ");
			cell.setCellStyle(headerStyle);
			worksheet.addMergedRegion(new CellRangeAddress(0, 0, 13, 15));
			cell = header.createCell(13);
			cell.setCellValue("Disciplina la alegere");
			cell.setCellStyle(headerStyle);
			cell = header.createCell(16);
			cell.setCellValue("Media");
			cell.setCellStyle(headerStyle);
			cell = header.createCell(17);
			cell.setCellValue("Raspuns final");
			cell.setCellStyle(headerStyle);

			// ---------finish of  the header1---------------
			//Autosize columns
			for(int i=0; i<18; i++){
				worksheet.autoSizeColumn((short) (i));
			}
			

			HSSFRow header1 = worksheet.createRow(1);
			cell = header1.createCell(5);
			cell.setCellValue("Nota");
			cell.setCellStyle(headerStyle);
			cell = header1.createCell(6);
			cell.setCellValue("Contestatie");
			cell.setCellStyle(headerStyle);
			cell = header1.createCell(7);
			cell.setCellValue("Nota finala");
			cell.setCellStyle(headerStyle);
			cell = header1.createCell(10);
			cell.setCellValue("Nota");
			cell.setCellStyle(headerStyle);
			cell = header1.createCell(11);
			cell.setCellValue("Contestatie");
			cell.setCellStyle(headerStyle);
			cell = header1.createCell(12);
			cell.setCellValue("Nota finala");
			cell.setCellStyle(headerStyle);
			cell = header1.createCell(13);
			cell.setCellValue("Nota");
			cell.setCellStyle(headerStyle);
			cell = header1.createCell(14);
			cell.setCellValue("Contestatie");
			cell.setCellStyle(headerStyle);
			cell = header1.createCell(15);
			cell.setCellValue("Nota finala");
			cell.setCellStyle(headerStyle);
			//------finish header2------------
			//Autosize columns
			for(int i=0; i<18; i++){
				worksheet.autoSizeColumn((short) (i));
			}
			
			System.out.println("Autosize columns");
			
			worksheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
			worksheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 1));
			worksheet.addMergedRegion(new CellRangeAddress(0, 1, 2, 2));
			worksheet.addMergedRegion(new CellRangeAddress(0, 1, 3, 3));
			worksheet.addMergedRegion(new CellRangeAddress(0, 1, 4, 4));
			worksheet.addMergedRegion(new CellRangeAddress(0, 1, 8, 8));
			worksheet.addMergedRegion(new CellRangeAddress(0, 1, 9, 9));
			worksheet.addMergedRegion(new CellRangeAddress(0, 1, 16, 16));
			worksheet.addMergedRegion(new CellRangeAddress(0, 1, 17, 17));

			HSSFRow row1;
			HSSFRow row2;
			//
			
			
			EleviPOJO eleviPOJO = eleviUTIL.getElevi();
			
			
			
			List<ExportObject> classes = new ArrayList<ExportObject>();
			
			for (ElevPOJO elevPOJO : eleviPOJO.getElevi()) {
				
			
				ExportObject exportClass = new ExportObject();
				DateElev elev = new DateElev();
				UnitateDeInvatamant unitate =  new UnitateDeInvatamant();  
				Specializare specializare = new Specializare();
				Proba proba = new Proba();
				Proba proba1 = new Proba();
				Proba proba2 = new Proba();
				Proba proba3 = new Proba();
				Disciplina disciplina = new Disciplina();
				Disciplina disciplina1 = new Disciplina();
				Disciplina disciplina2 = new Disciplina();
				
				elev.setNumeElev(elevPOJO.getNumeElev());
				elev.setPrenumeElev(elevPOJO.getPrenumeElev());
				elev.setInitialaTata(elevPOJO.getInitialaTata());
				unitate.setDenumireUnitate(elevPOJO.getLiceuElev());
				specializare.setDenumireSpecializare(elevPOJO.getSpecializare());

				exportClass.setElev(elev);
				exportClass.setUnitate(unitate);
				exportClass.setSpecializare(specializare);
				
				proba.setNota(elevPOJO.getNotaLimbaMaterna());
				proba.setNotaContestatie(elevPOJO.getNotaContestatieLimbaMaterna());
				// ----un block if cu nota finala column 8
				
				if(proba.getNotaContestatie()!=null){
					if ((Math.abs(proba.getNotaContestatie() - proba.getNota())) > 0.5) {
						proba.setNotaFinala(elevPOJO.getNotaContestatieLimbaMaterna());
					}
					else {
						proba.setNotaFinala(elevPOJO.getNotaLimbaMaterna());
					}
				} else {
					proba.setNotaFinala(elevPOJO.getNotaLimbaMaterna());
				}
				
				
				// proba.setNotaFinala(rs.getDouble(6));
				exportClass.setProba(proba);

				disciplina.setDenumireDisciplina(elevPOJO.getLimbaModerna());
				proba1.setNota(elevPOJO.getNotaLimbaModerna());// moderna
				disciplina1.setDenumireDisciplina(elevPOJO.getProbaObligatorie());
				proba2.setNota(elevPOJO.getNotaProbaObligatorie());
				proba2.setNotaContestatie(elevPOJO.getNotaContestatieProbaObligatorie());
				
				if(proba2.getNotaContestatie() != null){
					if ((Math.abs(proba2.getNotaContestatie() - proba2.getNota())) > 0.5) {
						proba2.setNotaFinala(elevPOJO.getNotaContestatieProbaObligatorie());
					}
					else {
						proba2.setNotaFinala(elevPOJO.getNotaProbaObligatorie());
					}
				}else {
					proba2.setNotaFinala(elevPOJO.getNotaProbaObligatorie());
				}

				disciplina2.setDenumireDisciplina(elevPOJO.getProbaAlegere());
				proba3.setNota(elevPOJO.getNotaProbaAlegere());
				proba3.setNotaContestatie(elevPOJO.getNotaContestatieProbaAlegere());
				// un block if cu nota finala pe coloana 15
				if(proba3.getNotaContestatie()!=null){
				if ((Math.abs(proba3.getNotaContestatie() - proba3.getNota())) > 0.5) {
					proba3.setNotaFinala(elevPOJO.getNotaContestatieProbaAlegere());
				}
				 else {
						proba3.setNotaFinala(elevPOJO.getNotaProbaAlegere());
					}
				} else {
					proba3.setNotaFinala(elevPOJO.getNotaProbaAlegere());
				}

				exportClass.setDisciplina(disciplina);
				exportClass.setDisciplina1(disciplina1);
				exportClass.setDisciplina2(disciplina2);
				exportClass.setProba1(proba1);
				exportClass.setProba2(proba2);
				exportClass.setProba3(proba3);

				classes.add(exportClass);
				System.out.println(classes);
			}
			
			int index = 2;
			for (ExportObject s : classes) {
				int x ;
				x=index/2;
				row1 = worksheet.createRow((short) index);
				cell=row1.createCell(0);
				cell.setCellValue(s.getElev().getNumeElev());
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
				cell=row1.createCell(1);
				cell.setCellValue(s.getElev().getPrenumeElev());
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
				cell=row1.createCell(2);
				cell.setCellValue(s.getElev().getInitialaTata());
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
				cell=row1.createCell(3);
				cell.setCellValue(s.getUnitate().getDenumireUnitate());
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
				cell=row1.createCell(4);
				cell.setCellValue(s.getSpecializare().getDenumireSpecializare());
				//color
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
				cell=row1.createCell(5);
				cell.setCellValue("");
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
				cell = row1.createCell(8);
				cell.setCellValue(s.getDisciplina().getDenumireDisciplina());
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
				
				cell=row1.createCell(9);
				cell.setCellValue(s.getProba1().getNota()); // limba moderna 
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );															// moderna
				cell = row1.createCell(10);
				cell.setCellValue(s.getDisciplina1().getDenumireDisciplina());
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
				
				
				cell = row1.createCell(13);
				cell.setCellValue(s.getDisciplina2().getDenumireDisciplina());
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
				
				
				worksheet.addMergedRegion(new CellRangeAddress(index, index, 5, 7));
				cell.setCellStyle( x%2==0 ?  oddRowStyle : evenRowStyle );
				worksheet.addMergedRegion(new CellRangeAddress(index, index, 10, 12));
				cell.setCellStyle( x%2==0 ?  oddRowStyle : evenRowStyle );
				worksheet.addMergedRegion(new CellRangeAddress(index, index, 13, 15));
				cell.setCellStyle( x%2==0 ?  oddRowStyle : evenRowStyle );
				row2 = worksheet.createRow((short) index + 1);

				// note romana
				cell=row2.createCell(5);
				cell.setCellValue(s.getProba().getNota());
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
				cell=row2.createCell(6);
				if(s.getProba().getNotaContestatie()==null){
					cell.setCellValue("");
				}
				else{
				cell.setCellValue(s.getProba().getNotaContestatie());
				}
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
				cell=row2.createCell(7);
				cell.setCellValue(s.getProba().getNotaFinala());
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
	
				// note proba Obligatorie
				cell=row2.createCell(10);
				cell.setCellValue(s.getProba2().getNota());
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
				cell=row2.createCell(11);
				if(s.getProba2().getNotaContestatie()==null){
					cell.setCellValue("");
				}
				else{
				cell.setCellValue(s.getProba2().getNotaContestatie());
				}
			
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
				cell=row2.createCell(12);
				cell.setCellValue(s.getProba2().getNotaFinala());
				//color
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );

				// proba alegere
				cell=row2.createCell(13);
				cell.setCellValue(s.getProba3().getNota());
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
				cell=row2.createCell(14);
				if(s.getProba3().getNotaContestatie()==null){
					cell.setCellValue("");
				}
				else{
				cell.setCellValue(s.getProba3().getNotaContestatie());
				}
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
				cell=row2.createCell(15);
				cell.setCellValue(s.getProba3().getNotaFinala());
				//color
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
				double medie = ((s.getProba().getNotaFinala() + s.getProba1().getNota() + s.getProba2().getNotaFinala()
						+ s.getProba3().getNotaFinala()) / 4);

				cell=row1.createCell(16);
				cell.setCellValue(medie);
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );

				if (medie > 6) {
					cell = row1.createCell(17);
					cell.setCellValue("Admis");
					cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
					
				} else {
					cell = row1.createCell(17);
					cell.setCellValue("Respins");
					cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
					//color
					
				}

				// imbinarea celulelor

				worksheet.addMergedRegion(new CellRangeAddress(index, index + 1, 0, 0));
				cell.setCellStyle( x%2==0 ?  oddRowStyle : evenRowStyle );
				worksheet.addMergedRegion(new CellRangeAddress(index, index + 1, 1, 1));
				cell.setCellStyle( x%2==0 ?  oddRowStyle : evenRowStyle );
				worksheet.addMergedRegion(new CellRangeAddress(index, index + 1, 2, 2));
				cell.setCellStyle( x%2==0 ?  oddRowStyle : evenRowStyle );
				worksheet.addMergedRegion(new CellRangeAddress(index, index + 1, 3, 3));
				cell.setCellStyle( x%2==0 ?  oddRowStyle : evenRowStyle );
				worksheet.addMergedRegion(new CellRangeAddress(index, index + 1, 4, 4));
				cell.setCellStyle( x%2==0 ?  oddRowStyle : evenRowStyle );
				worksheet.addMergedRegion(new CellRangeAddress(index, index + 1, 8, 8));
				cell.setCellStyle( x%2==0 ?  oddRowStyle : evenRowStyle );
				worksheet.addMergedRegion(new CellRangeAddress(index, index + 1, 9, 9));
				cell.setCellStyle( x%2==0 ?  oddRowStyle : evenRowStyle );
				worksheet.addMergedRegion(new CellRangeAddress(index, index + 1, 16, 16));
				cell.setCellStyle( x%2==0 ?  oddRowStyle : evenRowStyle );
				worksheet.addMergedRegion(new CellRangeAddress(index, index + 1, 17, 17));
				cell.setCellStyle( x%2==0 ?  oddRowStyle : evenRowStyle );
				
				// coloured rows
				//cell.setCellStyle(evenRowStyle);
				//cell.setCellStyle( x%2==0 ?  oddRowStyle : evenRowStyle );
				
				
				
				//Autosize columns
				for(int i=0; i<18; i++){
					worksheet.autoSizeColumn(i);
				}
				


				index += 2;
				System.out.println(index);
			
			}
		/////
		
			HSSFSheet worksheet2 = workbook.createSheet("Dupa Medie");

			HSSFRow header2 = worksheet2.createRow(0);
			
			cell = header2.createCell(0);
			cell.setCellValue("        Nume        ");
			cell.setCellStyle(headerStyle);
			cell = header2.createCell(1);
			cell.setCellValue("   Prenume   ");
			cell.setCellStyle(headerStyle);
			cell = header2.createCell(2);
			cell.setCellValue("Initiala Tata");
			cell.setCellStyle(headerStyle);
			cell = header2.createCell(3);
			cell.setCellValue("                                     Unitate de Invatamant                                ");
			cell.setCellStyle(headerStyle); 
			cell = header2.createCell(4);
			cell.setCellValue("               Specializare                ");
			cell.setCellStyle(headerStyle); 
			worksheet2.addMergedRegion(new CellRangeAddress(0, 0, 5, 7));
			cell = header2.createCell(5);
			cell.setCellValue(" Limba Romana");
			cell.setCellStyle(headerStyle);
			cell = header2.createCell(8);
			cell.setCellValue("Limba moderna");
			cell.setCellStyle(headerStyle);
			cell = header2.createCell(9);
			cell.setCellValue("Nota");
			cell.setCellStyle(headerStyle);
			worksheet2.addMergedRegion(new CellRangeAddress(0, 0, 10, 12));
			cell = header2.createCell(10);
			cell.setCellValue(" Disciplina Obligatorie ");
			cell.setCellStyle(headerStyle);
			worksheet2.addMergedRegion(new CellRangeAddress(0, 0, 13, 15));
			cell = header2.createCell(13);
			cell.setCellValue("Disciplina la alegere");
			cell.setCellStyle(headerStyle);
			cell = header2.createCell(16);
			cell.setCellValue("Media");
			cell.setCellStyle(headerStyle);
			cell = header2.createCell(17);
			cell.setCellValue("Raspuns final");
			cell.setCellStyle(headerStyle);

			// ---------finish of  the header1---------------
			//Autosize columns
			for(int i=0; i<18; i++){
				worksheet2.autoSizeColumn((short) (i));
			}
			

			HSSFRow header3 = worksheet2.createRow(1);
			cell = header3.createCell(5);
			cell.setCellValue("Nota");
			cell.setCellStyle(headerStyle);
			cell = header3.createCell(6);
			cell.setCellValue("Contestatie");
			cell.setCellStyle(headerStyle);
			cell = header3.createCell(7);
			cell.setCellValue("Nota finala");
			cell.setCellStyle(headerStyle);
			cell = header3.createCell(10);
			cell.setCellValue("Nota");
			cell.setCellStyle(headerStyle);
			cell = header3.createCell(11);
			cell.setCellValue("Contestatie");
			cell.setCellStyle(headerStyle);
			cell = header3.createCell(12);
			cell.setCellValue("Nota finala");
			cell.setCellStyle(headerStyle);
			cell = header3.createCell(13);
			cell.setCellValue("Nota");
			cell.setCellStyle(headerStyle);
			cell = header3.createCell(14);
			cell.setCellValue("Contestatie");
			cell.setCellStyle(headerStyle);
			cell = header3.createCell(15);
			cell.setCellValue("Nota finala");
			cell.setCellStyle(headerStyle);
			//------finish header2------------
			//Autosize columns
			for(int i=0; i<18; i++){
				worksheet2.autoSizeColumn((short) (i));
			}
			
			System.out.println("Autosize columns");
			
			worksheet2.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
			worksheet2.addMergedRegion(new CellRangeAddress(0, 1, 1, 1));
			worksheet2.addMergedRegion(new CellRangeAddress(0, 1, 2, 2));
			worksheet2.addMergedRegion(new CellRangeAddress(0, 1, 3, 3));
			worksheet2.addMergedRegion(new CellRangeAddress(0, 1, 4, 4));
			worksheet2.addMergedRegion(new CellRangeAddress(0, 1, 8, 8));
			worksheet2.addMergedRegion(new CellRangeAddress(0, 1, 9, 9));
			worksheet2.addMergedRegion(new CellRangeAddress(0, 1, 16, 16));
			worksheet2.addMergedRegion(new CellRangeAddress(0, 1, 17, 17));

			HSSFRow row3;
			HSSFRow row4;

			EleviPOJO eleviPOJO2 = eleviUTIL.getElevi();
			
			
			
			List<ExportObject> classes2 = new ArrayList<ExportObject>();
			
			for (ElevPOJO elevPOJO : eleviPOJO.getElevi()) {
				
			
				ExportObject exportClass = new ExportObject();
				DateElev elev = new DateElev();
				UnitateDeInvatamant unitate =  new UnitateDeInvatamant();  
				Specializare specializare = new Specializare();
				Proba proba = new Proba();
				Proba proba1 = new Proba();
				Proba proba2 = new Proba();
				Proba proba3 = new Proba();
				Disciplina disciplina = new Disciplina();
				Disciplina disciplina1 = new Disciplina();
				Disciplina disciplina2 = new Disciplina();
				
				elev.setNumeElev(elevPOJO.getNumeElev());
				elev.setPrenumeElev(elevPOJO.getPrenumeElev());
				elev.setInitialaTata(elevPOJO.getInitialaTata());
				unitate.setDenumireUnitate(elevPOJO.getLiceuElev());
				specializare.setDenumireSpecializare(elevPOJO.getSpecializare());

				exportClass.setElev(elev);
				exportClass.setUnitate(unitate);
				exportClass.setSpecializare(specializare);
				
				proba.setNota(elevPOJO.getNotaLimbaMaterna());
				proba.setNotaContestatie(elevPOJO.getNotaContestatieLimbaMaterna());
				// ----un block if cu nota finala column 8
				if(proba.getNotaContestatie()!=null){
					if ((Math.abs(proba.getNotaContestatie() - proba.getNota())) > 0.5) {
						proba.setNotaFinala(elevPOJO.getNotaContestatieLimbaMaterna());
					}
					else {
						proba.setNotaFinala(elevPOJO.getNotaLimbaMaterna());
					}
				} else {
					proba.setNotaFinala(elevPOJO.getNotaLimbaMaterna());
				}
				// proba.setNotaFinala(rs.getDouble(6));
				exportClass.setProba(proba);

				disciplina.setDenumireDisciplina(elevPOJO.getLimbaModerna());
				proba1.setNota(elevPOJO.getNotaLimbaModerna());// moderna
				disciplina1.setDenumireDisciplina(elevPOJO.getProbaObligatorie());
				proba2.setNota(elevPOJO.getNotaProbaObligatorie());
				proba2.setNotaContestatie(elevPOJO.getNotaContestatieProbaObligatorie());
				
				if(proba2.getNotaContestatie() != null){
					if ((Math.abs(proba2.getNotaContestatie() - proba2.getNota())) > 0.5) {
						proba2.setNotaFinala(elevPOJO.getNotaContestatieProbaObligatorie());
					}
					else {
						proba2.setNotaFinala(elevPOJO.getNotaProbaObligatorie());
					}
				}else {
					proba2.setNotaFinala(elevPOJO.getNotaProbaObligatorie());
				}

				disciplina2.setDenumireDisciplina(elevPOJO.getProbaAlegere());
				proba3.setNota(elevPOJO.getNotaProbaAlegere());
				proba3.setNotaContestatie(elevPOJO.getNotaContestatieProbaAlegere());
				// un block if cu nota finala pe coloana 15
				if(proba3.getNotaContestatie()!=null){
				if ((Math.abs(proba3.getNotaContestatie() - proba3.getNota())) > 0.5) {
					proba3.setNotaFinala(elevPOJO.getNotaContestatieProbaAlegere());
				}
				 else {
						proba3.setNotaFinala(elevPOJO.getNotaProbaAlegere());
					}
				} else {
					proba3.setNotaFinala(elevPOJO.getNotaProbaAlegere());
				}

				exportClass.setDisciplina(disciplina);
				exportClass.setDisciplina1(disciplina1);
				exportClass.setDisciplina2(disciplina2);
				exportClass.setProba1(proba1);
				exportClass.setProba2(proba2);
				exportClass.setProba3(proba3);

				classes.add(exportClass);
				System.out.println(classes2);
			


			
			
				// ---------------sortare ---------
				// media finala
				exportClass.setMedie(
						(proba.getNotaFinala() + proba1.getNota() + proba2.getNotaFinala() + proba3.getNotaFinala())
								/ 4);
				classes2.add(exportClass);

			}
			// lista de comparator
			Comparator<ExportObject> comparator = new Comparator<ExportObject>() {

				@Override

				public int compare(ExportObject c1, ExportObject c2) {

					if (c2.getMedie() > c1.getMedie()) // use your logic
						return 1;
					else if (c1.getMedie() > c2.getMedie()) {
						return -1;
					} else
						return 0;
				}
			};
			// sortare
			Collections.sort(classes2, comparator);
			int index2 = 2;
			for (ExportObject s : classes2) {

				int x ;
				x=index2/2;
				row3 = worksheet2.createRow((short) index2);
				cell=row3.createCell(0);
				cell.setCellValue(s.getElev().getNumeElev());
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
				cell=row3.createCell(1);
				cell.setCellValue(s.getElev().getPrenumeElev());
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
				cell=row3.createCell(2);
				cell.setCellValue(s.getElev().getInitialaTata());
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
				cell=row3.createCell(3);
				cell.setCellValue(s.getUnitate().getDenumireUnitate());
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
				cell=row3.createCell(4);
				cell.setCellValue(s.getSpecializare().getDenumireSpecializare());
				//color
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
				cell=row3.createCell(5);
				cell.setCellValue("");
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
				cell = row3.createCell(8);
				cell.setCellValue(s.getDisciplina().getDenumireDisciplina());
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
				
				cell=row3.createCell(9);
				cell.setCellValue(s.getProba1().getNota()); // limba moderna 
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );															// moderna
				cell = row3.createCell(10);
				cell.setCellValue(s.getDisciplina1().getDenumireDisciplina());
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
				
				
				cell = row3.createCell(13);
				cell.setCellValue(s.getDisciplina2().getDenumireDisciplina());
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
				
				
				worksheet2.addMergedRegion(new CellRangeAddress(index2, index2, 5, 7));
				cell.setCellStyle( x%2==0 ?  oddRowStyle : evenRowStyle );
				worksheet2.addMergedRegion(new CellRangeAddress(index2, index2, 10, 12));
				cell.setCellStyle( x%2==0 ?  oddRowStyle : evenRowStyle );
				worksheet2.addMergedRegion(new CellRangeAddress(index2, index2, 13, 15));
				cell.setCellStyle( x%2==0 ?  oddRowStyle : evenRowStyle );
				row4 = worksheet2.createRow((short) index2 + 1);

				// note romana
				cell=row4.createCell(5);
				cell.setCellValue(s.getProba().getNota());
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
				cell=row4.createCell(6);
				if(s.getProba().getNotaContestatie()==null){
					cell.setCellValue("");
				}
				else{
				cell.setCellValue(s.getProba().getNotaContestatie());
				}
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
				cell=row4.createCell(7);
				cell.setCellValue(s.getProba().getNotaFinala());
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
	
				// note proba Obligatorie
				cell=row4.createCell(10);
				cell.setCellValue(s.getProba2().getNota());
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
				cell=row4.createCell(11);
				if(s.getProba2().getNotaContestatie()==null){
					cell.setCellValue("");
				}
				else{
				cell.setCellValue(s.getProba2().getNotaContestatie());
				}
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
				cell=row4.createCell(12);
				cell.setCellValue(s.getProba2().getNotaFinala());
				//color
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );

				// proba alegere
				cell=row4.createCell(13);
				cell.setCellValue(s.getProba3().getNota());
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
				cell=row4.createCell(14);
				if(s.getProba3().getNotaContestatie()==null){
					cell.setCellValue("");
				}
				else{
				cell.setCellValue(s.getProba3().getNotaContestatie());
				}
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
				cell=row4.createCell(15);
				cell.setCellValue(s.getProba3().getNotaFinala());
				//color
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
				double medie = ((s.getProba().getNotaFinala() + s.getProba1().getNota() + s.getProba2().getNotaFinala()
						+ s.getProba3().getNotaFinala()) / 4);

				cell=row3.createCell(16);
				cell.setCellValue(medie);
				cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );

				if (medie > 6) {
					cell = row3.createCell(17);
					cell.setCellValue("Admis");
					cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
					
				} else {
					cell = row3.createCell(17);
					cell.setCellValue("Respins");
					cell.setCellStyle( x % 2 == 0 ? oddRowStyle : evenRowStyle );
					//color
					
				}

				// imbinarea celulelor

				worksheet2.addMergedRegion(new CellRangeAddress(index2, index2 + 1, 0, 0));
				cell.setCellStyle( x%2==0 ?  oddRowStyle : evenRowStyle );
				worksheet2.addMergedRegion(new CellRangeAddress(index2, index2 + 1, 1, 1));
				cell.setCellStyle( x%2==0 ?  oddRowStyle : evenRowStyle );
				worksheet2.addMergedRegion(new CellRangeAddress(index2, index2 + 1, 2, 2));
				cell.setCellStyle( x%2==0 ?  oddRowStyle : evenRowStyle );
				worksheet2.addMergedRegion(new CellRangeAddress(index2, index2 + 1, 3, 3));
				cell.setCellStyle( x%2==0 ?  oddRowStyle : evenRowStyle );
				worksheet2.addMergedRegion(new CellRangeAddress(index2, index2 + 1, 4, 4));
				cell.setCellStyle( x%2==0 ?  oddRowStyle : evenRowStyle );
				worksheet2.addMergedRegion(new CellRangeAddress(index2, index2 + 1, 8, 8));
				cell.setCellStyle( x%2==0 ?  oddRowStyle : evenRowStyle );
				worksheet2.addMergedRegion(new CellRangeAddress(index2, index2 + 1, 9, 9));
				cell.setCellStyle( x%2==0 ?  oddRowStyle : evenRowStyle );
				worksheet2.addMergedRegion(new CellRangeAddress(index2, index2 + 1, 16, 16));
				cell.setCellStyle( x%2==0 ?  oddRowStyle : evenRowStyle );
				worksheet2.addMergedRegion(new CellRangeAddress(index2, index2 + 1, 17, 17));
				cell.setCellStyle( x%2==0 ?  oddRowStyle : evenRowStyle );

				index2 += 2;
				System.out.println(index2);
			}
			
			
		System.out.println("am ajuuns la sfarsit inainte de a-l scrie");
		workbook.write(fileOut);
		System.out.println("il scriu");
		

		
		fileOut.flush();
	    fileOut.close();
		workbook.close();
		System.out.println("Export Success");
		 return fileOut.toByteArray();
		 
		 
		 

	} catch (Exception e) {
		e.printStackTrace();
	}
		return null;
	}

	private HSSFCellStyle createStyle(HSSFFont font, short cellAlign, short cellColor, boolean cellBorder, short cellBorderColor) {

		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		style.setAlignment(cellAlign);
		style.setFillForegroundColor(cellColor);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		if (cellBorder) {
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			
			style.setTopBorderColor(cellBorderColor);
			style.setLeftBorderColor(cellBorderColor);
			style.setRightBorderColor(cellBorderColor);
			style.setBottomBorderColor(cellBorderColor);
		}
		
		return style;
}
	
}

	

