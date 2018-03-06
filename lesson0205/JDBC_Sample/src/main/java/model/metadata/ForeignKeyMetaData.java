package model.metadata;

public class ForeignKeyMetaData {
    private String pkColumnName;
    private String pkTableName;
    private String fkColumnName;

    public String getPkColumnName() {
        return pkColumnName;
    }

    public void setPkColumnName(String pkColumnName) {
        this.pkColumnName = pkColumnName;
    }

    public String getPkTableName() {
        return pkTableName;
    }

    public void setPkTableName(String pkTableName) {
        this.pkTableName = pkTableName;
    }

    public String getFkColumnName() {
        return fkColumnName;
    }

    public void setFkColumnName(String fkColumnName) {
        this.fkColumnName = fkColumnName;
    }

    @Override
    public String toString() {
        return "FK_COLUMN: " + fkColumnName + ", PK_TABLE: " + pkTableName
                + ", PK_COLUMN_NAME: " + pkColumnName;
    }
}