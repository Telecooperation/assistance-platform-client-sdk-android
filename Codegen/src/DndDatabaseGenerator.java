import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class DndDatabaseGenerator {

    private static final String OUTPUT_PATH = "../DND2/src-gen";
    private static final String PACKAGE = "de.tudarmstadt.informatik.tk.kraken.dnd2.db";

    private static final int SCHEMA_VERSION = 8;

    public static void main(String[] args) throws Exception {
        generateSchema();
    }

    private static void generateSchema() throws Exception {

        Schema schema = new Schema(SCHEMA_VERSION, PACKAGE);

        // ------------ DND ------------
        Entity dnd = schema.addEntity("Dnd");
        dnd.addIdProperty();
        dnd.addDateProperty("start");
        dnd.addDateProperty("end");
        dnd.addIntProperty("type");

        // ------------ Notifications ------------
        Entity notification = schema.addEntity("Notification");
        notification.addIdProperty();
        notification.addDateProperty("time");
        notification.addStringProperty("title");
        notification.addStringProperty("text");
        notification.addStringProperty("summary");
        notification.addStringProperty("inboxText");
        notification.addStringProperty("packageName");
        notification.addIntProperty("priority");
        notification.addByteArrayProperty("smallIcon");
        notification.addByteArrayProperty("largeIcon");

        // ------------ App Usage Budgets ------------
        Entity appUsageBudget = schema.addEntity("AppUsageBudget");
        appUsageBudget.addIdProperty();
        appUsageBudget.addStringProperty("packageName");
        appUsageBudget.addIntProperty("maxMinutes");

        // ------------ App Usage ------------
        Entity appUsage = schema.addEntity("AppUsage");
        appUsage.addIdProperty();
        appUsage.addDateProperty("date");
        appUsage.addStringProperty("packageName");
        appUsage.addIntProperty("appUsage");
        appUsage.addIntProperty("eventAppUsage");
        appUsage.addIntProperty("appBudget");
        appUsage.addFloatProperty("moneyAmount");
        appUsage.addBooleanProperty("isFinal");


        Property dndId = notification.addLongProperty("dndId").getProperty();
        dnd.addToMany(notification, dndId);

        new DaoGenerator().generateAll(schema, OUTPUT_PATH);
    }
}
