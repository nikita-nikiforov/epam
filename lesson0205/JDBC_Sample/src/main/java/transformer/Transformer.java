package transformer;

import model.annotation.Column;
import model.annotation.Table;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transformer<T> {
    private final Class<T> clazz;

    public Transformer(Class<T> clazz) {
        this.clazz = clazz;
    }

    public Object fromResultSetToEntity(ResultSet resultSet) throws SQLException {
        // Create new object
        Object entity = null;
        try {
            entity = clazz.getConstructor().newInstance();
            if (clazz.isAnnotationPresent(Table.class)) {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    // If field is annotated with @Column
                    if(field.isAnnotationPresent(Column.class)){
                        Column column = (Column) field.getAnnotation(Column.class);
                        String name = column.name();
                        int length = column.length();
                        field.setAccessible(true);
                        Class fieldType = field.getType();
                        if (fieldType == String.class) {
                            field.set(entity, resultSet.getString(name));
                        } else if (fieldType == int.class) {
                            field.set(entity, resultSet.getInt(name));
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return entity;
    }
}