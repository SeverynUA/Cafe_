package brainacad.org.Busines;

import brainacad.org.Busines.Proper.DatabaseUtil;

public class CafeDB_Initializer {
    public static void initializeDatabase() {
        DatabaseUtil.resetDatabase();
        System.out.println("Database initialized successfully.");
    }
}