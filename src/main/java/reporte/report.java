import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


Workbook workbook = new XSSFWorkbook();
Sheet sheet = workbook.createSheet("Reporte de Peso Ideal");


Row header = sheet.createRow(0);
header.createCell(0).setCellValue("Nombre");
header.createCell(1).setCellValue("Edad");
header.createCell(2).setCellValue("Peso Ideal");


List<Persona> personas = personaRepository.findAll();
int rowNum = 1;
for (Persona persona : personas) {
Row row = sheet.createRow(rowNum++);
    row.createCell(0).setCellValue(persona.getNombre());
        row.createCell(1).setCellValue(persona.getEdad());

double pesoIdeal = calcularPesoIdeal(persona); // Implementa esta lógica
    row.createCell(2).setCell Value(pesoIdeal);
}

ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
workbook.write(outputStream);
workbook.close();

byte[] bytes = outputStream.toByteArray();
HttpHeaders headers = new HttpHeaders();
headers.add("Content-Disposition", "attachment; filename=reporte_peso_ideal.xlsx");

return ResponseEntity.ok()
        .headers(headers)
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .body(bytes);
}