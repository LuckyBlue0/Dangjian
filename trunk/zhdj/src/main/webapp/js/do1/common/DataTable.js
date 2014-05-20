/**
 * Created by IntelliJ IDEA.
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
        $("#" + $divTarget).html(tableHtml);
    }
    
    
    DataTable.prototype.createTableNew = function ($divTarget) {
        var tableHtml = "<table style=\"width: 500px;\" ><tbody><tr>";
        var tdWidth = Math.floor(100 / this.colsCount);
        for (var i = 0; i < this.data.length; i++) {
            if (i % this.colsCount == 0 && i != 0)
                tableHtml += "</tr><tr style=\"height: 35px;\">";
            if (this.fieldName)
                tableHtml += "<td width='" + tdWidth + "%'>" + this.data[i][this.fieldName] + "</td>";
            else
                tableHtml += "<td align=\"center\" width='" + tdWidth + "%'>" + this.data[i] + "</td>";
            if (i == this.data.length - 1) {
                for (var j = 0; j < this.colsCount - 1 - i % this.colsCount; j++) {
                    tableHtml += "<td width='" + tdWidth + "%'></td>";
                }
            }
        }
        if(this.data.length==0){
        	tableHtml += "</tr><tr><td>暂无人员数据</td></tr>";
        }
        tableHtml += "</tbody></table>";
        $("#" + $divTarget).html(tableHtml);
    }
    

    DataTable.prototype.createUserList = function ($divTarget) {
        var tableHtml = "<table style=\"border:1px solid #E0E0E0;width: 900px;\" ><tbody><tr>";
        var tdWidth = Math.floor(100 / this.colsCount);
      	var keys = this.data.keys();
      	var vlues = this.data.values();
        for (var i = 0; i < vlues.length; i++) {
            if (i % this.colsCount == 0 && i != 0)
                tableHtml += "</tr><tr style=\"height: 35px;\">";
            if (this.fieldName)
                tableHtml += "<td width='" + tdWidth + "%'>" + vlues[i][this.fieldName] + "</td>";
            else
                tableHtml += "<td align=\"left\" width='" + tdWidth + "%'>" + vlues[i];
            	tableHtml += "<img id="+ keys[i]+" name=\"imgs\" onclick=\"delUser(this)\" src=\"../../../../themes/default/images/u122_normal.png\"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
            	tableHtml += "</td>";
            if (i == vlues.length - 1) {
                for (var j = 0; j < this.colsCount - 1 - i % this.colsCount; j++) {
                    tableHtml += "<td width='" + tdWidth + "%'></td>";
                }
            }
        }
        if(vlues.length==0){
        	tableHtml += "</tr><tr><td>暂无选择用户</td></tr>";
        }
        tableHtml += "</tbody></table>";
        $("#" + $divTarget).html(tableHtml);
    }
}



