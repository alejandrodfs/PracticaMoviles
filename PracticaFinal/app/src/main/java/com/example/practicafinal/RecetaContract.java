package com.example.practicafinal;
import android.provider.BaseColumns;

public class RecetaContract {

    public static final String DB_NAME ="receta.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE = "receta";
    public static final String DEFAULT_SORT = Column.ID + " DESC";

    public class Column {
        public static final String ID = BaseColumns._ID;
        public static final String titulo ="titulo";
        public static final String categoria ="categoria";
        public static final String tiempo = "tiempo";
        public static final String calorias = "calorias";
        public static final String ingredientes = "ingredientes";
        public static final String descripcion = "descripcion";

    }
}
