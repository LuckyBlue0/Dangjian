/**
 * Created by IntelliJ IDEA.
 * User: hyf
 * Date: 12-4-20
 * Time: 下午6:30
 */
function DataTable($target) {
    this.data = $target.data;
    this.colsCount = $target.colsCount;
    this.fieldName = $target.fieldName;
    DataTable.prototype.createTable = function ($divTarget) {
        var tableHtml = "<table width='99%'><tbody><tr>";
        var tdWidth = Math.floor(100 / this.colsCount);
        for (var i = 0; i < this.data.length; i++) {
            if (i % this.colsCount == 0 && i != 0)
                tableHtml += "</tr><tr>";
            if (this.fieldName)
                tableHtml += "<td width='" + tdWidth + "%'>" + this.data[i][this.fieldName] + "</td>";
            else
                tableHtml += "<td width='" + tdWidth + "%'>" + this.data[i] + "</td>";
            if (i == this.data.length - 1) {
                for (var j = 0; j < this.colsCount - 1 - i % this.colsCount; j++) {
                    tableHtml += "<td width='" + tdWidth + "%'></td>";
                }
            }
        }
        tableHtml += "</tbody></table>";
        $("#" + $divTarget).html(tableHtml);
    }
}