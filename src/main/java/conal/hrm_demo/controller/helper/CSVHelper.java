package conal.hrm_demo.controller.helper;


import conal.hrm_demo.entity.Salary;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class CSVHelper {

    public static ByteArrayInputStream salariesToCSV(List<Salary> Salaries) {
        final CSVFormat format = CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim();
        String[] entries = {"ID", "Employee Code", "FullName","Department", "Amount", "Bonus", "Paid Date", "Note", "Created Date"};

        final String pattern = "dd/MM/yyyy HH:mm:ss";
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            csvPrinter.printRecord(Arrays.asList(entries));
            for (conal.hrm_demo.entity.Salary salary : Salaries) {
                DateFormat df = new SimpleDateFormat(pattern);
                List<String> data = Arrays.asList(
                        String.valueOf(salary.getId()),
                        String.valueOf(salary.getEmployee().getCode()),
                        (salary.getEmployee().getFirstName() + " " + salary.getEmployee().getLastName()),
                        salary.getEmployee().getDepartment().getName(),
                        String.valueOf(salary.getAmount()),
                        String.valueOf(salary.getBonus()),
                        df.format(salary.getDatePaid()),
                        salary.getNote(),
                        df.format(salary.getCreatedDate())
                );
                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}