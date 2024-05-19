package com.example.demo.service;


import java.io.ByteArrayInputStream;

import java.io.ByteArrayOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

	

@Service
public class payrollServiceImpl implements payrollService {

	private EmployeeService employeeService;
	private Logger logger = LoggerFactory.getLogger(payrollServiceImpl.class);
	
	public payrollServiceImpl(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	@Override
	public ByteArrayInputStream generatePaySlip(int theId) {
		
		Employee employeeDetail = this.employeeService.findById(theId);
		Department getRole =  employeeDetail.getDepartment();
        String totalHours = this.employeeService.attendanceHoursCount(employeeDetail.getName());
        
		int totalHoursInt = Integer.parseInt(totalHours);
		
		String hourseReteS = employeeDetail.getSalaryHourlyRet();
		double hourlyRateD = Double.parseDouble(hourseReteS);
		
		double totalSalary = totalHoursInt * hourlyRateD;
		
		String HouseRents = employeeDetail.getHouseRentAllowances();
		double HouseRentD = Double.parseDouble(HouseRents);
		
		String medicaleS = employeeDetail.getMedicalAllowances();
		double medicaleD = Double.parseDouble(medicaleS);
		
		String specialS = employeeDetail.getSpecialAllowances();
		double specialD = Double.parseDouble(specialS);
		
		double grossSalary = totalSalary + HouseRentD + medicaleD + specialD;
		
		String healthS = employeeDetail.getHealthInsurance();
		double healthD = Double.parseDouble(healthS);
		
		String tdsS = employeeDetail.getTDS();
		double tdsD = Double.parseDouble(tdsS);
		
		double netPay = grossSalary + healthD + tdsD;
		
		logger.info("Virag Malani BharatBhai");
		
		String companyName = "CyPeople";
		String content = "Salary Slip";
		
		ByteArrayOutputStream outPut = new ByteArrayOutputStream();
		Document document = new Document(PageSize.A4);
		
		PdfWriter.getInstance(document, outPut);
		
		document.open();
		
        float width = document.getPageSize().getWidth();
        float height = document.getPageSize().getHeight();
		
		Font font26 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 26, Font.BOLD);
		Font font16 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16);
		Font font12 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12);
		
		Paragraph companyParagraph = new Paragraph(companyName, font26);
		companyParagraph.setAlignment(Element.ALIGN_CENTER);
		document.add(companyParagraph);
		
		Paragraph contentParagraph = new Paragraph(content, font16);
		contentParagraph.setAlignment(Element.ALIGN_CENTER);
		document.add(contentParagraph);
		
		PdfPTable nameDetails = new PdfPTable(1);
		nameDetails.setTotalWidth(width -75);
		nameDetails.setLockedWidth(true);
		nameDetails.getDefaultCell().setPaddingTop(10);
		nameDetails.getDefaultCell().setBorder(0);
		
		nameDetails.addCell(new Phrase("Name: "+ employeeDetail.getName(), font12));
		nameDetails.addCell(new Phrase("Employee Id: " + employeeDetail.getEmployeeId(), font12));
		nameDetails.addCell(new Phrase("Email: " + employeeDetail.getEmail(), font12));
		nameDetails.addCell(new Phrase("Phone: " + employeeDetail.getPhone(), font12));
		
		nameDetails.getDefaultCell().setPaddingBottom(20);
		nameDetails.addCell(new Phrase("Department: " + getRole.getDepartmentName(), font12));

		document.add(nameDetails);
		
		Font font12Bold = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD);
        
        PdfPTable table = new PdfPTable(4);
        table.setTotalWidth(width -75);
        table.setLockedWidth(true);
        
        PdfPCell cell1 = new PdfPCell(new Phrase("Earning", font12Bold));
        cell1.setPaddingBottom(10);
        cell1.setColspan(2);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
//        cell1.setBorderColor(Color.green);
        table.addCell(cell1);
        
        PdfPCell cell2 = new PdfPCell(new Phrase("Deduction", font12Bold));
        cell2.setPaddingBottom(10);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setColspan(2);
        table.addCell(cell2);

        PdfPTable earningTable = new PdfPTable(4);
        earningTable.getDefaultCell().setBorder(0);
        earningTable.getDefaultCell().setPaddingBottom(5);
        
        earningTable.addCell(new Phrase("Earning", font12Bold));
        earningTable.addCell(new Phrase("Rate", font12Bold));
        earningTable.addCell(new Phrase("Hours", font12Bold));
        earningTable.addCell(new Phrase("Total", font12Bold));
        
        earningTable.addCell(new Phrase("Basic Salary", font12Bold));
        earningTable.addCell(new Phrase(employeeDetail.getSalaryHourlyRet(),font12));
        earningTable.addCell(new Phrase(totalHours,font12));
        earningTable.addCell(new Phrase(totalSalary + "", font12));
        
        earningTable.addCell(new Phrase("House Rent Allowances", font12Bold));
        earningTable.addCell(new Phrase(" - "));
        earningTable.addCell(new Phrase(" - "));
        earningTable.addCell(new Phrase(employeeDetail.getHouseRentAllowances(), font12));
        
        earningTable.addCell(new Phrase("Medicale Allowances", font12Bold));
        earningTable.addCell(new Phrase(" - "));
        earningTable.addCell(new Phrase(" - "));
        earningTable.addCell(new Phrase(employeeDetail.getMedicalAllowances(), font12));
        
        earningTable.addCell(new Phrase("Special Allowances", font12Bold));
        earningTable.addCell(new Phrase(" - "));
        earningTable.addCell(new Phrase(" - "));
        earningTable.addCell(new Phrase(employeeDetail.getSpecialAllowances(), font12));
        
        earningTable.addCell(new Phrase("Gross Salary", font12Bold));
        earningTable.addCell(new Phrase(" - "));
        earningTable.addCell(new Phrase(" - "));
        earningTable.addCell(new Phrase(grossSalary + "", font12Bold));

        PdfPCell earingCell = new PdfPCell(earningTable);
        earingCell.setColspan(2);
//        earingCell.setBorderColor(Color.green);
        table.addCell(earingCell);

        PdfPTable deductionTable = new PdfPTable(2);
        deductionTable.getDefaultCell().setBorder(0);
        
        deductionTable.addCell(new Phrase("Health Insurance", font12Bold));
        deductionTable.addCell(new Phrase(employeeDetail.getHealthInsurance(), font12));
        
        deductionTable.addCell(new Phrase("TDS", font12Bold));
        deductionTable.addCell(new Phrase(employeeDetail.getTDS(), font12));
        
        PdfPCell deductionCell = new PdfPCell(deductionTable);
        deductionCell.setColspan(2);
        table.addCell(deductionCell);
        
        PdfPCell netPayCell = new PdfPCell(new Phrase("Net Pay", font12Bold));
        netPayCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        netPayCell.setColspan(2);
        table.addCell(netPayCell);
        
        PdfPCell totalAmountCell = new PdfPCell(new Phrase(netPay + "", font12Bold));
        totalAmountCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        totalAmountCell.setColspan(2);
        table.addCell(totalAmountCell);
        
        document.add(table);
		document.close();
		
		return new ByteArrayInputStream(outPut.toByteArray());
	}

}
