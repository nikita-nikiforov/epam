package service;

import dao.impl.MetaDataRepositoryImpl;
import model.metadata.TableMetaData;
import java.sql.SQLException;
import java.util.List;

public class MetaDataService {
    public List<String> findAllTableName() throws SQLException {
        return new MetaDataRepositoryImpl().findAllTableName();
    }

    public List<TableMetaData> getTablesStructure() throws SQLException {
        return new MetaDataRepositoryImpl().getTablesStructure();
    }
}