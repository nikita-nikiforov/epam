package model.metadata;

import java.util.ArrayList;
import java.util.List;

public class TableMetaData {
    private String DBName;
    private String tableName;
    private List<ColumnMetaData> columnMetaDataList = new ArrayList<>();
    private List<ForeignKeyMetaData> foreignKeyMetaDataList = new ArrayList<>();

    public String getDBName() {
        return DBName;
    }

    public void setDBName(String DBName) {
        this.DBName = DBName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<ColumnMetaData> getColumnMetaDataList() {
        return columnMetaDataList;
    }

    public void setColumnMetaDataList(List<ColumnMetaData> columnMetaDataList) {
        this.columnMetaDataList = columnMetaDataList;
    }

    public List<ForeignKeyMetaData> getForeignKeyMetaDataList() {
        return foreignKeyMetaDataList;
    }

    public void setForeignKeyMetaDataList(List<ForeignKeyMetaData> foreignKeyMetaDataList) {
        this.foreignKeyMetaDataList = foreignKeyMetaDataList;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TABLE: " + tableName + "\n");
        for (ColumnMetaData column : columnMetaDataList) {
            stringBuilder.append(column + "\n");
        }
        for (ForeignKeyMetaData foreignKey : foreignKeyMetaDataList){
            stringBuilder.append(foreignKey + "\n");
        }
        return stringBuilder.toString();
    }
}