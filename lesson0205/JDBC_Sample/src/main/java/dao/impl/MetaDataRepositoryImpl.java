package dao.impl;

import model.metadata.ColumnMetaData;
import model.metadata.ForeignKeyMetaData;
import model.metadata.TableMetaData;
import persistant.ConnectionManager;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MetaDataRepositoryImpl {

    public List<String> findAllTableName() throws SQLException {
        List<String> tableNames = new ArrayList<>();
        String[] types = {"TABLE"};
        Connection connection = ConnectionManager.getConnection();
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSet resultSet = databaseMetaData.getTables(connection.getCatalog(), null,
                                                        "%", types);
        while (resultSet.next()) {
            String tableName = resultSet.getString("TABLE_NAME");
            tableNames.add(tableName);
        }
        return tableNames;
    }

    public List<TableMetaData> getTablesStructure() throws SQLException {
        List<TableMetaData> tableMetaDataList = new ArrayList<>();
        Connection connection = ConnectionManager.getConnection();
        DatabaseMetaData databaseMetaData = connection.getMetaData();

        String[] types = {"TABLE"};
        String databaseName = connection.getCatalog();
        ResultSet resultSet = databaseMetaData.getTables(databaseName, null, "%", types);

        while (resultSet.next()) {
            String tableName = resultSet.getString("TABLE_NAME");
            TableMetaData tableMetaData = new TableMetaData();
            tableMetaData.setDBName(databaseName);
            tableMetaData.setTableName(tableName);

            // Get Primary Keys
            List<String> pkList = new ArrayList<>();
            ResultSet primaryKeysRS = databaseMetaData
                    .getPrimaryKeys(connection.getCatalog(), null, tableName);
            while (primaryKeysRS.next()) {
                pkList.add(primaryKeysRS.getString("COLUMN_NAME"));
            }
            // Get Columns For Table
            List<ColumnMetaData> columnsMetaData = new ArrayList<>();
            ResultSet columnsRS = databaseMetaData.getColumns(databaseName, null,
                    tableName, "%");
            while (columnsRS.next()) {
                ColumnMetaData columnMetaData = new ColumnMetaData();
                columnMetaData.setColumnName(columnsRS.getString("COLUMN_NAME"));
                columnMetaData.setDataType(columnsRS.getString("TYPE_NAME"));
                columnMetaData.setColumnSize(columnsRS.getString("COLUMN_SIZE"));
                columnMetaData.setDecimalDigits(columnsRS.getString("DECIMAL_DIGITS"));
                boolean condition = columnsRS.getString("IS_NULLABLE").equals("YES");
                columnMetaData.setNullable(condition);
                condition = columnsRS.getString("IS_AUTOINCREMENT").equals("IS_AUTOINCREMENT");
                columnMetaData.setAutoIncrement(condition);

                columnMetaData.setPrimaryKey(false);
                for (String pkName : pkList) {
                    if (columnMetaData.getColumnName().equals(pkName)) {
                        columnMetaData.setPrimaryKey(true);
                        break;
                    }
                }
                columnsMetaData.add(columnMetaData);
            }
            tableMetaData.setColumnMetaDataList(columnsMetaData);

            // Get Foreign Keys
            List<ForeignKeyMetaData> fkMetaDataList = new ArrayList<>();
            ResultSet FKsRS = databaseMetaData.getImportedKeys(databaseName, null, tableName);
            while (FKsRS.next()) {
                ForeignKeyMetaData fk = new ForeignKeyMetaData();
                fk.setFkColumnName(FKsRS.getString("FKCOLUMN_NAME"));
                fk.setPkTableName(FKsRS.getString("PKTABLE_NAME"));
                fk.setPkColumnName(FKsRS.getString("PKCOLUMN_NAME"));
                fkMetaDataList.add(fk);
            }
            tableMetaData.setForeignKeyMetaDataList(fkMetaDataList);
            tableMetaDataList.add(tableMetaData);
        }
        return tableMetaDataList;
    }
}