/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harrypotter.web.jdbc;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.sql.DataSource;
 
public class GeneratePDF {
	
	private static Font TIME_ROMAN = new Font(Font.FontFamily.TIMES_ROMAN, 18,Font.BOLD);
	private static Font TIME_ROMAN_SMALL = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        
 
	/**
	 * @param args
	 */
	public static Document createPDF(String file, Order theOrder, Account theAccount, List<OrderDetails> theOrderDetailsList) {
 
		Document document = null;
 
		try {
			document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();
 
			addMetaData(document);
 
			addTitlePage(document, theOrder, theAccount);
 
			createTable(document, theOrderDetailsList);
 
			document.close();
 
		} catch (FileNotFoundException e) {
 
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
 
	}
 
	private static void addMetaData(Document document) {
		document.addTitle("Order Report");
		document.addSubject("Order Details");
		document.addAuthor("Harry Potter Store");
		document.addCreator("Harry Potter Store");
	}
 
	private static void addTitlePage(Document document, Order theOrder, Account theAccount)
			throws DocumentException {

                Paragraph preface = new Paragraph();
                creteEmptyLine(preface, 1);
                preface.add(new Paragraph("Order Number: " + theOrder.getId(), TIME_ROMAN));
                
                creteEmptyLine(preface, 1);
                preface.add(new Paragraph("Client Name: " + theAccount.getName(), TIME_ROMAN_SMALL));
                
                creteEmptyLine(preface, 1);
                preface.add(new Paragraph("Ship Address: " + theOrder.getShipAddress(), TIME_ROMAN_SMALL));
                
                creteEmptyLine(preface, 1);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                preface.add(new Paragraph("Date Ordered "
                        + simpleDateFormat.format(theOrder.getOrdered()), TIME_ROMAN_SMALL));
                document.add(preface);

 
	}
 
	private static void creteEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
 
	private static void createTable(Document document, List<OrderDetails> theOrderDetailsList) throws DocumentException {
                
                Paragraph paragraph = new Paragraph();
                creteEmptyLine(paragraph, 2);
                document.add(paragraph);
                PdfPTable table = new PdfPTable(3);
                
                PdfPCell c1 = new PdfPCell(new Phrase("Product Name"));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);
                
                c1 = new PdfPCell(new Phrase("Price"));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);
                
                c1 = new PdfPCell(new Phrase("Qty"));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);
                table.setHeaderRows(1);
                
                for (OrderDetails order : theOrderDetailsList) {
                    table.setWidthPercentage(100);
                    table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(order.getProductname());
                    table.addCell(Float.toString(order.getProductprice()));
                    table.addCell(Integer.toString(order.getQty()));
                }
                
                document.add(table);

	}
 
}
