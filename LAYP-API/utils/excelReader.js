const XLSX = require("xlsx");
const fs = require("fs");

class ExcelReader {
  constructor(filePath) {
    if (!fs.existsSync(filePath)) {
      throw new Error(`File not found: ${filePath}`);
    }
    this.workbook = XLSX.readFile(filePath);
  }

  getCellDataString(sheetName, rowIndex, columnIndex) {
    const sheet = this.workbook.Sheets[sheetName];
    if (!sheet) {
      throw new Error(`Sheet "${sheetName}" does not exist`);
    }

    // Convert rowIndex to Excel row (1-based) and columnIndex to column (A, B, ...)
    const cellAddress = XLSX.utils.encode_cell({ r: rowIndex, c: columnIndex });
    const cell = sheet[cellAddress];
    
    // If the cell doesn't exist, return an empty string
    return cell ? cell.v.toString() : "";
  }
}

module.exports = ExcelReader;
