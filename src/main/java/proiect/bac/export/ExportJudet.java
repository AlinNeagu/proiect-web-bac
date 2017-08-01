/*package proiect.bac.export;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

import proiect.bac.connection.CreateConnection;
import proiect.bac.entities.DateElev;
import proiect.bac.entities.Disciplina;
import proiect.bac.entities.Proba;

public class ExportJudet {

	private static Connection con;

	public static void main(String[] args) throws SQLException {
		exportpeJudet();

	}

	public static void exportpeJudet() {

		try {

			con = CreateConnection.getConnection();

			System.out.println("The database connection was successful");
			Statement statement = con.createStatement();
			FileOutputStream fileOut;
			fileOut = new FileOutputStream("fisierExport-Judet.xls");
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet worksheet = workbook.createSheet("Judet Brasov");

			HSSFCellStyle style = workbook.createCellStyle();
			HSSFFont font = workbook.createFont();
			font.setColor(HSSFColor.BLACK.index);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style.setFont(font);

			HSSFCellStyle style2 = workbook.createCellStyle();
			HSSFFont font2 = workbook.createFont();
			font.setColor(HSSFColor.BLACK.index);
			style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style2.setFont(font2);

			HSSFRow header = worksheet.createRow(0);
			HSSFCell cell;
			cell = header.createCell(0);
			cell.setCellValue("Nume");
			cell.setCellStyle(style);
			cell = header.createCell(1);
			cell.setCellValue("Prenume");
			cell.setCellStyle(style);
			cell = header.createCell(2);
			cell.setCellValue("initialaTata");
			cell.setCellStyle(style);
			cell = header.createCell(3);
			cell.setCellValue("Unitate de Invatamant");
			cell.setCellStyle(style);
			cell = header.createCell(4);
			cell.setCellValue("Specializare");
			cell.setCellStyle(style);
			worksheet.addMergedRegion(new CellRangeAddress(0, 0, 5, 7));
			cell = header.createCell(5);
			cell.setCellValue(" Limba Romana");
			cell.setCellStyle(style);
			cell = header.createCell(8);
			cell.setCellValue("Limba moderna");
			cell.setCellStyle(style);
			cell = header.createCell(9);
			cell.setCellValue("Nota");
			cell.setCellStyle(style);
			worksheet.addMergedRegion(new CellRangeAddress(0, 0, 10, 12));
			cell = header.createCell(10);
			cell.setCellValue("Disciplina obligatorie");
			cell.setCellStyle(style);
			worksheet.addMergedRegion(new CellRangeAddress(0, 0, 13, 15));
			cell = header.createCell(13);
			cell.setCellValue("Disciplina la alegere");
			cell.setCellStyle(style);
			cell = header.createCell(16);
			cell.setCellValue("Media");
			cell.setCellStyle(style);
			cell = header.createCell(17);
			cell.setCellValue("Raspuns final");
			cell.setCellStyle(style);

			// ---------finish the attaching of the
			// style---------------------------

			HSSFRow header1 = worksheet.createRow(1);
			cell = header1.createCell(5);
			cell.setCellValue("Nota");
			cell.setCellStyle(style);
			cell = header1.createCell(6);
			cell.setCellValue("Contestatie");
			cell.setCellStyle(style);
			cell = header1.createCell(7);
			cell.setCellValue("Nota finala");
			cell.setCellStyle(style);
			cell = header1.createCell(10);
			cell.setCellValue("Nota");
			cell.setCellStyle(style);
			cell = header1.createCell(11);
			cell.setCellValue("Contestatie");
			cell.setCellStyle(style);
			cell = header1.createCell(12);
			cell.setCellValue("Nota finala");
			cell.setCellStyle(style);
			cell = header1.createCell(13);
			cell.setCellValue("Nota");
			cell.setCellStyle(style);
			cell = header1.createCell(14);
			cell.setCellValue("Contestatie");
			cell.setCellStyle(style);
			cell = header1.createCell(15);
			cell.setCellValue("Nota finala");
			cell.setCellStyle(style);

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
			
			ResultSet rs = statement
					.executeQuery("select numeElev, prenumeElev, initialaTata, denumireUnitate, denumireSpecializare, "
							+ "notaRom, contestRom, LimbaMod, notaLimbaMod, Obligatorie, "
							+ "notaObl, contestObl, Alegere, notaAlegere, contestAlegere " + "from Export "
							+ " where judet ='Brasov' order by denumireUnitate asc");
	
			 		
			List<ExportObject> classes = new ArrayList<ExportObject>();
			
			while (rs.next()) {
				ExportObject exportClass = new ExportObject();
				DateElev elev = new DateElev();
				Proba proba = new Proba();
				Proba proba1 = new Proba();
				Proba proba2 = new Proba();
				Proba proba3 = new Proba();
				Disciplina disciplina = new Disciplina();
				Disciplina disciplina1 = new Disciplina();
				Disciplina disciplina2 = new Disciplina();
				elev.setNumeElev(rs.getString(1));
				elev.setPrenumeElev(rs.getString(2));
				elev.setInitialaTata(rs.getString(3));
				//elev.(rs.getString(4));
			//	elev.setSpecializare(rs.getString(5));

				exportClass.setElev(elev);

				proba.setNota(rs.getDouble(6));
				proba.setNotaContestatie(rs.getDouble(7));
				// ----un block if cu nota finala column 8

				if ((proba.getNotaContestatie() - proba.getNota()) > 0.5) {
					proba.setNotaFinala(proba.getNotaContestatie());
				} else {
					proba.setNotaFinala(proba.getNota());
				}
				// proba.setNotaFinala(rs.getDouble(6));
				exportClass.setProba(proba);

				disciplina.setDenumireDisciplina(rs.getString(8));
				proba1.setNota(rs.getDouble(9));// moderna
				disciplina1.setDenumireDisciplina(rs.getString(10));
				proba2.setNota(rs.getDouble(11));
				proba2.setNotaContestatie(rs.getDouble(12));
				// un block if cu nota finala
				if ((proba2.getNotaContestatie() - proba2.getNota()) > 0.5) {
					proba2.setNotaFinala(proba2.getNotaContestatie());
				} else {
					proba2.setNotaFinala(proba2.getNota());
				}

				disciplina2.setDenumireDisciplina(rs.getString(13));
				proba3.setNota(rs.getDouble(14));
				proba3.setNotaContestatie(rs.getDouble(15));
				// un block if cu nota finala pe coloana 15
				if ((proba3.getNotaContestatie() - proba3.getNota()) > 0.5) {
					proba3.setNotaFinala(proba3.getNotaContestatie());
				} else {
					proba3.setNotaFinala(proba3.getNota());
				}

				exportClass.setDisciplina(disciplina);
				exportClass.setDisciplina1(disciplina1);
				exportClass.setDisciplina2(disciplina2);
				exportClass.setProba1(proba1);
				exportClass.setProba2(proba2);
				exportClass.setProba3(proba3);

				classes.add(exportClass);

			}
			int index = 2;
			for (ExportObject s : classes) {

				row1 = worksheet.createRow((short) index);
				row1.createCell(0).setCellValue(s.getElev().getNumeElev());
				row1.createCell(1).setCellValue(s.getElev().getPrenumeElev());
				row1.createCell(2).setCellValue(s.getElev().getInitialaTatalui());
				row1.createCell(3).setCellValue(s.getElev().getID_Unitate());
				row1.createCell(4).setCellValue(s.getElev().getID_Specializare());

				cell = row1.createCell(8);
				cell.setCellValue(s.getDisciplina().getDenumireDisciplina());
				cell.setCellStyle(style2);
				row1.createCell(9).setCellValue(s.getProba1().getNota()); // limba
																			// moderna
				cell = row1.createCell(10);
				cell.setCellValue(s.getDisciplina1().getDenumireDisciplina());
				cell.setCellStyle(style2);
				cell = row1.createCell(13);
				cell.setCellValue(s.getDisciplina2().getDenumireDisciplina());
				cell.setCellStyle(style2);

				worksheet.addMergedRegion(new CellRangeAddress(index, index, 5, 7));
				worksheet.addMergedRegion(new CellRangeAddress(index, index, 10, 12));
				worksheet.addMergedRegion(new CellRangeAddress(index, index, 13, 15));

				row2 = worksheet.createRow((short) index + 1);

				// note romana
				row2.createCell(5).setCellValue(s.getProba().getNota());
				row2.createCell(6).setCellValue(s.getProba().getNotaContestatie());
				row2.createCell(7).setCellValue(s.getProba().getNotaFinala());

				// note proba Obligatorie
				row2.createCell(10).setCellValue(s.getProba2().getNota());
				row2.createCell(11).setCellValue(s.getProba2().getNotaContestatie());
				row2.createCell(12).setCellValue(s.getProba2().getNotaFinala());

				// proba alegere
				row2.createCell(13).setCellValue(s.getProba3().getNota());
				row2.createCell(14).setCellValue(s.getProba3().getNotaContestatie());
				row2.createCell(15).setCellValue(s.getProba3().getNotaFinala());

				double medie = ((s.getProba().getNotaFinala() + s.getProba1().getNota() + s.getProba2().getNotaFinala()
						+ s.getProba3().getNotaFinala()) / 4);

				row1.createCell(16).setCellValue(medie);
				
				if (medie > 6) {
					cell = row1.createCell(17);
					cell.setCellValue("Admis");
					cell.setCellStyle(style2);
				} else {
					cell = row1.createCell(17);
					cell.setCellValue("Respins");
					cell.setCellStyle(style2);
				}

				// imbinarea celulelor

				worksheet.addMergedRegion(new CellRangeAddress(index, index + 1, 0, 0));
				worksheet.addMergedRegion(new CellRangeAddress(index, index + 1, 1, 1));
				worksheet.addMergedRegion(new CellRangeAddress(index, index + 1, 2, 2));
				worksheet.addMergedRegion(new CellRangeAddress(index, index + 1, 3, 3));
				worksheet.addMergedRegion(new CellRangeAddress(index, index + 1, 4, 4));
				worksheet.addMergedRegion(new CellRangeAddress(index, index + 1, 8, 8));
				worksheet.addMergedRegion(new CellRangeAddress(index, index + 1, 9, 9));
				worksheet.addMergedRegion(new CellRangeAddress(index, index + 1, 16, 16));
				worksheet.addMergedRegion(new CellRangeAddress(index, index + 1, 17, 17));

				index += 2;
				System.out.println(index);
			}

			// -------------------sheet 2--------Judet
			// Prahova-----------------------------
			HSSFSheet worksheet2 = workbook.createSheet("Judet Prahova");

			HSSFRow header3 = worksheet2.createRow(0);
			cell = header3.createCell(0);
			cell.setCellValue("Nume");
			cell.setCellStyle(style);
			cell = header3.createCell(1);
			cell.setCellValue("Prenume");
			cell.setCellStyle(style);
			cell = header3.createCell(2);
			cell.setCellValue("initialaTata");
			cell.setCellStyle(style);
			cell = header3.createCell(3);
			cell.setCellValue("Unitate de Invatamant");
			cell.setCellStyle(style);
			cell = header3.createCell(4);
			cell.setCellValue("Specializare");
			cell.setCellStyle(style);
			worksheet2.addMergedRegion(new CellRangeAddress(0, 0, 5, 7));
			cell = header3.createCell(5);
			cell.setCellValue(" Limba Romana");
			cell.setCellStyle(style);
			cell = header3.createCell(8);
			cell.setCellValue("Limba moderna");
			cell.setCellStyle(style);
			cell = header3.createCell(9);
			cell.setCellValue("Nota");
			cell.setCellStyle(style);
			worksheet2.addMergedRegion(new CellRangeAddress(0, 0, 10, 12));
			cell = header.createCell(10);
			cell.setCellValue("Disciplina obligatorie");
			cell.setCellStyle(style);
			worksheet2.addMergedRegion(new CellRangeAddress(0, 0, 13, 15));
			cell = header3.createCell(13);
			cell.setCellValue("Disciplina la alegere");
			cell.setCellStyle(style);
			cell = header3.createCell(16);
			cell.setCellValue("Media");
			cell.setCellStyle(style);
			cell = header3.createCell(17);
			cell.setCellValue("Raspuns final");
			cell.setCellStyle(style);

			HSSFRow header4 = worksheet2.createRow(1);
			cell = header4.createCell(5);
			cell.setCellValue("Nota");
			cell.setCellStyle(style);
			cell = header4.createCell(6);
			cell.setCellValue("Contestatie");
			cell.setCellStyle(style);
			cell = header4.createCell(7);
			cell.setCellValue("Nota finala");
			cell.setCellStyle(style);
			cell = header4.createCell(10);
			cell.setCellValue("Nota");
			cell.setCellStyle(style);
			cell = header4.createCell(11);
			cell.setCellValue("Contestatie");
			cell.setCellStyle(style);
			cell = header4.createCell(12);
			cell.setCellValue("Nota finala");
			cell.setCellStyle(style);
			cell = header4.createCell(13);
			cell.setCellValue("Nota");
			cell.setCellStyle(style);
			cell = header4.createCell(14);
			cell.setCellValue("Contestatie");
			cell.setCellStyle(style);
			cell = header4.createCell(15);
			cell.setCellValue("Nota finala");
			cell.setCellStyle(style);

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
			ResultSet rs2 = statement
					.executeQuery("select numeElev, prenumeElev, initialaTata, denumireUnitate, denumireSpecializare, "
							+ "notaRom, contestRom, LimbaMod, notaLimbaMod, Obligatorie, "
							+ "notaObl, contestObl, Alegere, notaAlegere, contestAlegere " + "from Export "
							+ " where judet ='Prahova' order by denumireUnitate asc");

			List<ExportObject> classes1 = new ArrayList<ExportObject>();
			while (rs2.next()) {
				ExportObject exportClass = new ExportObject();
				DateElev elev = new DateElev();
				Proba proba = new Proba();
				Proba proba1 = new Proba();
				Proba proba2 = new Proba();
				Proba proba3 = new Proba();
				Disciplina disciplina = new Disciplina();
				Disciplina disciplina1 = new Disciplina();
				Disciplina disciplina2 = new Disciplina();
				elev.setNumeElev(rs2.getString(1));
				elev.setPrenumeElev(rs2.getString(2));
				elev.setInitialaTatalui(rs2.getString(3));
				elev.setID_Unitate(rs2.getString(4));
				elev.setID_Specializare(rs2.getString(5));

				exportClass.setElev(elev);

				proba.setNota(rs2.getDouble(6));
				proba.setNotaContestatie(rs2.getDouble(7));
				// ----un block if cu nota finala

				if ((proba.getNotaContestatie() - proba.getNota()) > 0.5) {
					proba.setNotaFinala(proba.getNotaContestatie());
				} else {
					proba.setNotaFinala(proba.getNota());
				}
				// proba.setNotaFinala(rs.getDouble(6));
				exportClass.setProba(proba);

				disciplina.setDenumireDisciplina(rs2.getString(8));

				proba1.setNota(rs2.getDouble(9));// moderna
				disciplina1.setDenumireDisciplina(rs2.getString(10));
				proba2.setNota(rs2.getDouble(11));
				proba2.setNotaContestatie(rs2.getDouble(12));
				// un block if cu nota finala
				if ((proba2.getNotaContestatie() - proba2.getNota()) > 0.5) {
					proba2.setNotaFinala(proba2.getNotaContestatie());
				} else {
					proba2.setNotaFinala(proba2.getNota());
				}

				disciplina2.setDenumireDisciplina(rs2.getString(13));
				proba3.setNota(rs2.getDouble(14));
				proba3.setNotaContestatie(rs2.getDouble(15));
				// un block if cu nota finala pe coloana 15
				if ((proba3.getNotaContestatie() - proba3.getNota()) > 0.5) {
					proba3.setNotaFinala(proba3.getNotaContestatie());
				} else {
					proba3.setNotaFinala(proba3.getNota());
				}

				exportClass.setDisciplina(disciplina);
				exportClass.setDisciplina1(disciplina1);
				exportClass.setDisciplina2(disciplina2);
				exportClass.setProba1(proba1);
				exportClass.setProba2(proba2);
				exportClass.setProba3(proba3);

				classes1.add(exportClass);

			}
			int index2 = 2;
			for (ExportObject s : classes1) {

				row3 = worksheet2.createRow((short) index2);
				row3.createCell(0).setCellValue(s.getElev().getNumeElev());
				row3.createCell(1).setCellValue(s.getElev().getPrenumeElev());
				row3.createCell(2).setCellValue(s.getElev().getInitialaTatalui());
				row3.createCell(3).setCellValue(s.getElev().getID_Unitate());
				row3.createCell(4).setCellValue(s.getElev().getID_Specializare());

				cell = row3.createCell(8);
				cell.setCellValue(s.getDisciplina().getDenumireDisciplina());
				cell.setCellStyle(style2);
				row3.createCell(9).setCellValue(s.getProba1().getNota()); // limba
																			// moderna
				cell = row3.createCell(10);
				cell.setCellValue(s.getDisciplina1().getDenumireDisciplina());
				cell.setCellStyle(style2);
				cell = row3.createCell(13);
				cell.setCellValue(s.getDisciplina2().getDenumireDisciplina());
				cell.setCellStyle(style2);

				worksheet2.addMergedRegion(new CellRangeAddress(index2, index2, 5, 7));
				worksheet2.addMergedRegion(new CellRangeAddress(index2, index2, 10, 12));
				worksheet2.addMergedRegion(new CellRangeAddress(index2, index2, 13, 15));
				row4 = worksheet2.createRow((short) index2 + 1);

				// note romana
				row4.createCell(5).setCellValue(s.getProba().getNota());
				row4.createCell(6).setCellValue(s.getProba().getNotaContestatie());
				row4.createCell(7).setCellValue(s.getProba().getNotaFinala());

				// note proba Obligatorie
				row4.createCell(10).setCellValue(s.getProba2().getNota());
				row4.createCell(11).setCellValue(s.getProba2().getNotaContestatie());
				row4.createCell(12).setCellValue(s.getProba2().getNotaFinala());

				// proba alegere
				row4.createCell(13).setCellValue(s.getProba3().getNota());
				row4.createCell(14).setCellValue(s.getProba3().getNotaContestatie());
				row4.createCell(15).setCellValue(s.getProba3().getNotaFinala());

				double medie = ((s.getProba().getNotaFinala() + s.getProba1().getNota() + s.getProba2().getNotaFinala()
						+ s.getProba3().getNotaFinala()) / 4);

				row3.createCell(16).setCellValue(medie);

				if (medie > 6) {
					cell = row3.createCell(17);
					cell.setCellValue("Admis");
					cell.setCellStyle(style2);
				} else {
					cell = row3.createCell(17);
					cell.setCellValue("Respins");
					cell.setCellStyle(style2);
				}

				// imbinarea celulelor

				worksheet2.addMergedRegion(new CellRangeAddress(index2, index2 + 1, 0, 0));
				worksheet2.addMergedRegion(new CellRangeAddress(index2, index2 + 1, 1, 1));
				worksheet2.addMergedRegion(new CellRangeAddress(index2, index2 + 1, 2, 2));
				worksheet2.addMergedRegion(new CellRangeAddress(index2, index2 + 1, 3, 3));
				worksheet2.addMergedRegion(new CellRangeAddress(index2, index2 + 1, 4, 4));
				worksheet2.addMergedRegion(new CellRangeAddress(index2, index2 + 1, 8, 8));
				worksheet2.addMergedRegion(new CellRangeAddress(index2, index2 + 1, 9, 9));
				worksheet2.addMergedRegion(new CellRangeAddress(index2, index2 + 1, 16, 16));
				worksheet2.addMergedRegion(new CellRangeAddress(index2, index2 + 1, 17, 17));

				index2 += 2;
				System.out.println(index2);
			}
			
			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
			rs.close();
			rs2.close();
			// rs3.close();
			// workbook.close();
			statement.close();
			con.close();
			System.out.println("Export Success");

		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
*/