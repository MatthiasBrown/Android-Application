package e.irvingarcia.project;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

import java.sql.Array;
import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "usersDB.db";

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSOWRD = "password";
    public static final String COLUMN_FIRST = "first";
    public static final String COLUMN_ROLE = "role";
    public static final String COLUMN_LAST = "last";
    public static final String COLUMN_STREET = "street";
    public static final String COLUMN_POSTAL = "postal";
    public static final String COLUMN_CITY = "city";

    public static final String TABLE_SERVICE_PROVIDER = "service_provider";
    public static final String COLUMN_COMPLETED="completed";
    public static final String COLUMN_USER_ID="user_id";
    public static final String COLUMN_PHONE="phone";
    public static final String COLUMN_COMPANY="company";
    public static final String COLUMN_DESCRIPTION="description";
    public static final String COLUMN_DATE="date";
    public static final String COLUMN_TIME="time";
    public static final String COLUMN_RATE="rate";

    public static final String TABLE_SERVICE = "services";
    public static final String COLUMN_SERVICE="service";
    public static final String COLUMN_HOURLY="hour_rate";



    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " +
                TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_USERNAME
                + " TEXT," + COLUMN_PASSOWRD + " TEXT2,"+ COLUMN_FIRST+ " TEXT3," + COLUMN_LAST+ " TEXT4,"
                + COLUMN_STREET+ " TEXT5,"+ COLUMN_POSTAL+ " TEXT6,"+ COLUMN_CITY+ " TEXT7,"
                +COLUMN_ROLE+ " TEXT8,"+COLUMN_COMPANY+" TEXT9,"+COLUMN_SERVICE+ " TEXT10,"+ COLUMN_DATE+
                " TEXT11,"+ COLUMN_TIME+ " TEXT12"+")";
        db.execSQL(CREATE_USER_TABLE);

        String CREATE_SERVICE_PROVIDER_TABLE="CREATE TABLE " +
                TABLE_SERVICE_PROVIDER + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"+ COLUMN_COMPLETED+ " TEXT,"+
                COLUMN_USER_ID+ " INTEGER,"+ COLUMN_COMPANY+ " TEXT2,"+ COLUMN_PHONE+ " TEXT3,"+
                COLUMN_DESCRIPTION+ " TEXT4,"+ COLUMN_SERVICE+ " TEXT5,"+ COLUMN_HOURLY+ " TEXT6,"+
                COLUMN_DATE+ " TEXT7,"+ COLUMN_TIME+ " TEXT8,"+ COLUMN_RATE+ " TEXT9,"+
                " FOREIGN KEY ("+COLUMN_USER_ID+") REFERENCES "+TABLE_USERS+"("+COLUMN_USER_ID+"));";
        db.execSQL(CREATE_SERVICE_PROVIDER_TABLE);

        String CREATE_SERVICE_TABLE = "CREATE TABLE " +
                TABLE_SERVICE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_SERVICE
                + " STRING,"+COLUMN_HOURLY+ " DOUBLE"+")";
        db.execSQL(CREATE_SERVICE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE_PROVIDER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE);
        onCreate(db);
    }

    public void presentationUseCase(){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_SERVICE,"Service1");
        values.put(COLUMN_HOURLY,"10");
        db.insert(TABLE_SERVICE,null,values);

        values.put(COLUMN_SERVICE,"Service2");
        values.put(COLUMN_HOURLY,"11");
        db.insert(TABLE_SERVICE,null,values);

        values.put(COLUMN_SERVICE,"Service3");
        values.put(COLUMN_HOURLY,"12");
        db.insert(TABLE_SERVICE,null,values);

        values.put(COLUMN_SERVICE,"Service4");
        values.put(COLUMN_HOURLY,"13");
        db.insert(TABLE_SERVICE,null,values);

        values.put(COLUMN_SERVICE,"Service5");
        values.put(COLUMN_HOURLY,"14");
        db.insert(TABLE_SERVICE,null,values);

        values.put(COLUMN_SERVICE,"Service6");
        values.put(COLUMN_HOURLY,"15");
        db.insert(TABLE_SERVICE,null,values);

        values.put(COLUMN_SERVICE,"Service7");
        values.put(COLUMN_HOURLY,"16");
        db.insert(TABLE_SERVICE,null,values);

        values.put(COLUMN_SERVICE,"Service8");
        values.put(COLUMN_HOURLY,"17");
        db.insert(TABLE_SERVICE,null,values);

        values.put(COLUMN_SERVICE,"Service9");
        values.put(COLUMN_HOURLY,"18");
        db.insert(TABLE_SERVICE,null,values);

        values.put(COLUMN_SERVICE,"Service10");
        values.put(COLUMN_HOURLY,"19");
        db.insert(TABLE_SERVICE,null,values);

        db.close();
    }

    public void presentationUser(){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_USERNAME,"Test");
        values.put(COLUMN_PASSOWRD,"Test");
        values.put(COLUMN_FIRST, "First");
        values.put(COLUMN_LAST,"Last");
        values.put(COLUMN_STREET,"Street");
        values.put(COLUMN_POSTAL,"Postal");
        values.put(COLUMN_CITY,"City");
        values.put(COLUMN_ROLE,"HomeOwner");
        db.insert(TABLE_USERS,null,values);
        db.close();
    }
    public void addUser(Users user){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_USERNAME,user.getUser());
        values.put(COLUMN_PASSOWRD,user.getPass());
        values.put(COLUMN_FIRST, user.getFirst());
        values.put(COLUMN_LAST,user.getLast());
        values.put(COLUMN_STREET,user.getStreet());
        values.put(COLUMN_POSTAL,user.getPostal());
        values.put(COLUMN_CITY,user.getCity());
        values.put(COLUMN_ROLE,user.getRole());
        //values.put(COLUMN_COMPLETED, "false");

        db.insert(TABLE_USERS,null,values);
        db.close();
    }

    public Users getProfile(String user, String pass){
        SQLiteDatabase db= this.getReadableDatabase();
        String query="Select * FROM "+TABLE_USERS+" WHERE "+COLUMN_USERNAME+ " = \"" +user+ "\"";
        Cursor cursor= db.rawQuery(query,null);
        Users users=new Users();
        if(cursor.moveToFirst()){
            users.setUser(cursor.getString(1));
            users.setPass(cursor.getString(2));
            users.setFirst(cursor.getString(3));
            users.setLast(cursor.getString(4));
            users.setStreet(cursor.getString(5));
            users.setPostal(cursor.getString(6));
            users.setCity(cursor.getString(7));
            users.setRole(cursor.getString(8));
            cursor.close();
        }
        else{
            users=null;
        }

        db.close();
        return users;
    }

    public boolean findUser(String user, String pass){
        SQLiteDatabase db= this.getReadableDatabase();
        String query="Select * FROM "+TABLE_USERS+" WHERE "+COLUMN_USERNAME+ " = \"" +user+ "\"";
        Cursor cursor= db.rawQuery(query,null);
        Users users=new Users();
        if(cursor.moveToFirst()){
            String userRetrieve=(cursor.getString(1));
            String userPassword=cursor.getString(2);
            cursor.close();
            if(!userPassword.equals(pass)){
                return false;
            }
        }
        else{
            return false;
        }

        db.close();
        return true;
    }

    public void addAdmin(Admin admin){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_USERNAME,admin.getUsername());
        values.put(COLUMN_PASSOWRD,admin.getPassword());
        values.put(COLUMN_FIRST, admin.getFirstName());
        values.put(COLUMN_ROLE, "Admin");
        //values.put(COLUMN_LAST,user.getLast());

        db.insert(TABLE_USERS,null,values);
        db.close();
    }



    public void addService(Service newService){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_SERVICE, newService.getService());
        values.put(COLUMN_HOURLY,newService.getHour());
        db.insert(TABLE_SERVICE,null,values);
        db.close();
    }

    public Service findService(String service){
        SQLiteDatabase db= this.getReadableDatabase();
        String query="Select * FROM "+TABLE_SERVICE+" WHERE "+COLUMN_SERVICE+ " = \"" +service+ "\"";
        Cursor cursor= db.rawQuery(query,null);
        Service newService=new Service();
        if(cursor.moveToFirst()){
            newService.setService(cursor.getString(1));
            newService.setHour(Double.parseDouble(cursor.getString(2)));
            cursor.close();
        }
        else{
            newService=null;
        }

        db.close();
        return newService;

    }

    public void updateService(Service updatedService,Service oldService){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_SERVICE,updatedService.getService());
        values.put(COLUMN_HOURLY,updatedService.getHour());
        String query="Select * FROM "+TABLE_SERVICE+" WHERE "+COLUMN_SERVICE+
                " = \"" +oldService.getService()+ "\"";
        Cursor cursor= db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            System.out.println("Test");
            String idStr=cursor.getString(0);
            db.update(TABLE_SERVICE,values,COLUMN_ID +" = "+idStr,null);
            cursor.close();
        }
        db.close();
    }

    public boolean deleteService(String service){
        boolean result= false;
        SQLiteDatabase db= this.getWritableDatabase();
        String query="Select * FROM "+TABLE_SERVICE+" WHERE "+COLUMN_SERVICE+
                " = \"" +service+ "\"";
        Cursor cursor= db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            String idStr=cursor.getString(0);
            db.delete(TABLE_SERVICE,COLUMN_ID +" = "+idStr,null);
            cursor.close();
            result=true;
        }
        db.close();
        return result;
    }



    public boolean isServiceCompleted(Users user){
        SQLiteDatabase db= this.getReadableDatabase();
        String query="Select * FROM "+TABLE_USERS+" WHERE "+COLUMN_USERNAME+ " = \"" +user.getUser()+ "\"";
        Cursor cursor= db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            int id=(cursor.getInt(0));
            cursor.close();
            String query2="Select * FROM "+TABLE_SERVICE_PROVIDER+" WHERE "+COLUMN_USER_ID+ " = \" " + id + "\"";
            Cursor cursor2= db.rawQuery(query2,null);
            if(cursor2.moveToFirst()){
                System.out.println("found");
                String completed=(cursor2.getString(1));
                cursor2.close();
                return Boolean.parseBoolean(completed);
            }
            System.out.println("notfound");
        }

        db.close();

        return false;

    }

    public void completedForm(Users users,ServiceProvider serv){
        SQLiteDatabase db= this.getReadableDatabase();
        String query="Select * FROM "+TABLE_USERS+" WHERE "+COLUMN_USERNAME+ " = \"" +users.getUser()+ "\"";
        Cursor cursor= db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            int id=(cursor.getInt(0));
            cursor.close();
            ContentValues val=new ContentValues();
            val.put(COLUMN_STREET, users.getStreet());
            val.put(COLUMN_POSTAL,users.getPostal());
            val.put(COLUMN_CITY,users.getCity());
            db.update(TABLE_USERS,val,COLUMN_ID+" = "+id,null);

            String query2="Select * FROM "+TABLE_SERVICE_PROVIDER+" WHERE "+COLUMN_USER_ID+ " = \" " + id + "\"";
            Cursor cursor2= db.rawQuery(query2,null);
            if(cursor2.moveToFirst()){
                ContentValues values=new ContentValues();
                values.put(COLUMN_COMPLETED,"true");
                values.put(COLUMN_PHONE, serv.getPhone());
                values.put(COLUMN_COMPANY,serv.getCompany());
                values.put(COLUMN_DESCRIPTION,serv.getDescription());
                String idStr=cursor2.getString(0);
                System.out.println("found");
                db.update(TABLE_SERVICE_PROVIDER,values,COLUMN_ID +" = "+idStr,null);
                cursor2.close();
            }
        }
        db.close();

    }

    public void addServiceProvider(Users user){
        SQLiteDatabase db= this.getReadableDatabase();
        String query="Select * FROM "+TABLE_USERS+" WHERE "+COLUMN_USERNAME+ " = \"" +user.getUser()+ "\"";
        Cursor cursor= db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            int id=(cursor.getInt(0));
            cursor.close();
            ContentValues values=new ContentValues();
            values.put(COLUMN_USER_ID,id);
            values.put(COLUMN_COMPLETED,"false");
            values.put(COLUMN_RATE,"0");
            db.insert(TABLE_SERVICE_PROVIDER,null,values);
        }
        db.close();
    }

    public ServiceProvider getServiceProvider(int user_id){
        SQLiteDatabase db= this.getReadableDatabase();
        String query="Select * FROM "+TABLE_SERVICE_PROVIDER+" WHERE "+COLUMN_USER_ID+ " = \" " + user_id + "\"";
        Cursor cursor= db.rawQuery(query,null);
        ServiceProvider temp=new ServiceProvider();
        if(cursor.moveToFirst()){
            temp.setCompany(cursor.getString(3));
            temp.setPhone(cursor.getString(4));
            temp.setDescription(cursor.getString(5));
        }
        return temp;
    }

    public Users getUser(int user_id){
        SQLiteDatabase db= this.getReadableDatabase();
        String query="Select * FROM "+TABLE_USERS+" WHERE "+COLUMN_ID+ " = \" " + user_id + "\"";
        Cursor cursor= db.rawQuery(query,null);
        Users temp=new Users();
        if(cursor.moveToFirst()){
            temp.setUser(cursor.getString(1));
            temp.setPass(cursor.getString(2));
        }
        return temp;
    }



    public int getUserID(Users user){
        SQLiteDatabase db= this.getReadableDatabase();
        String query="Select * FROM "+TABLE_USERS+" WHERE "+COLUMN_USERNAME+ " = \"" +user.getUser()+ "\"";
        Cursor cursor= db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            int id=(cursor.getInt(0));
            cursor.close();
            return id;
        }
        return 0;
    }
    public int getUserID(String user){
        SQLiteDatabase db= this.getReadableDatabase();
        String query="Select * FROM "+TABLE_USERS+" WHERE "+COLUMN_USERNAME+ " = \"" +user+ "\"";
        Cursor cursor= db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            int id=(cursor.getInt(0));
            cursor.close();
            return id;
        }
        return 0;
    }

    public ArrayList<Service> getServices(){
        ArrayList<Service> temp=new ArrayList<Service>();
        SQLiteDatabase db= this.getReadableDatabase();
        String query="Select * FROM "+TABLE_SERVICE;
        Cursor cursor= db.rawQuery(query,null);
        System.out.println("i found");
        if(cursor.moveToFirst()) {
            Service temp2 = new Service();
            temp2.setService(cursor.getString(1));
            temp2.setHour(Double.parseDouble(cursor.getString(2)));
            temp.add(temp2);
            while (cursor.moveToNext()) {
                System.out.println("i found");
                Service temp3 = new Service();
                temp3.setService(cursor.getString(1));
                temp3.setHour(Double.parseDouble(cursor.getString(2)));
                temp.add(temp3);
            }
        }
        return temp;
    }

    public void addServicetoAccount(int id, Service service){
        SQLiteDatabase db= this.getReadableDatabase();
        String query="Select * FROM "+TABLE_SERVICE_PROVIDER+" WHERE "+COLUMN_USER_ID+ " = \" " +id+ "\"";
        Cursor cursor= db.rawQuery(query,null);
        String serviceFinal;
        String hourFinal;
        if(cursor.moveToFirst()){
            String serviceTemp=cursor.getString(6);
            String serviceHour=cursor.getString(7);
            if(serviceTemp == null) {
                serviceFinal = service.getService();
                hourFinal=Double.toString(service.getHour());
            }
            else {
                serviceFinal = service.getService() + "," + serviceTemp;
                hourFinal= Double.toString(service.getHour()) + "," + serviceHour;
            }
            ContentValues values=new ContentValues();
            values.put(COLUMN_SERVICE,serviceFinal);
            values.put(COLUMN_HOURLY,hourFinal);
            db.update(TABLE_SERVICE_PROVIDER,values,COLUMN_USER_ID + " = "+id,null);
            cursor.close();
        }
        db.close();
    }

    public ArrayList<Service> getServicesProfile(int id){
        ArrayList<Service> temp=new ArrayList<Service>();
        SQLiteDatabase db= this.getReadableDatabase();
        String query="Select * FROM "+TABLE_SERVICE_PROVIDER+" WHERE "+COLUMN_USER_ID+ " = \"" +id+ "\"";
        Cursor cursor= db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            String services=cursor.getString(6);
            String hour=cursor.getString(7);
            cursor.close();
            if(services==null){
                return temp;
            }
            String[] tempService=services.split(",");
            String[] tempHour=hour.split(",");
            for(int i=0;i<tempService.length;i++){
                Service tempVar=new Service();
                tempVar.setService(tempService[i]);
                tempVar.setHour(Double.parseDouble(tempHour[i]));
                temp.add(tempVar);
            }
        }
        else{
            return null;
        }
        db.close();
        return temp;
    }

    public ArrayList<String> getBookedServices(Users owner){
        ArrayList<String> temp=new ArrayList<String>();
        SQLiteDatabase db= this.getReadableDatabase();
        String query="Select * FROM "+TABLE_USERS+" WHERE "+COLUMN_USERNAME+ " = \"" +owner.getUser()+ "\"";
        Cursor cursor= db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            String tempCompany=cursor.getString(9);
            String tempDate=cursor.getString(11);
            String tempTime=cursor.getString(12);
            String tempService=cursor.getString(10);
            cursor.close();
            if(tempCompany==null){
                return temp;
            }
            String[] company=tempCompany.split(",");
            String[] date=tempDate.split(",");
            String[] time=tempTime.split(",");
            String[] service=tempService.split(",");
            int k=0;
            for(int i=0;i<company.length;i++){
                String added=date[i]+","+time[k]+","+time[k+1]+","+company[i]+","+service[i];
                temp.add(added);
                k+=2;
            }
        }
        else{
            return null;
        }
        db.close();
        return temp;
    }

    public ArrayList<Service> getServicesProfile(String company){
        ArrayList<Service> temp=new ArrayList<Service>();
        SQLiteDatabase db= this.getReadableDatabase();
        String query="Select * FROM "+TABLE_SERVICE_PROVIDER+" WHERE "+COLUMN_COMPANY+ " = \"" +company+ "\"";
        Cursor cursor= db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            System.out.println("Works");
            String services=cursor.getString(6);
            String hour=cursor.getString(7);
            cursor.close();
            if(services==null){
                return temp;
            }
            String[] tempService=services.split(",");
            String[] tempHour=hour.split(",");
            for(int i=0;i<tempService.length;i++){
                Service tempVar=new Service();
                tempVar.setService(tempService[i]);
                tempVar.setHour(Double.parseDouble(tempHour[i]));
                temp.add(tempVar);
            }
        }
        else{
            return null;
        }
        db.close();
        return temp;
    }

    public void deleteServicefromAccount(int id,Service serv){
        SQLiteDatabase db= this.getReadableDatabase();
        String query="Select * FROM "+TABLE_SERVICE_PROVIDER+" WHERE "+COLUMN_USER_ID+ " = \" " +id+ "\"";
        Cursor cursor= db.rawQuery(query,null);
        String tempService=serv.getService();
        //Double tempHour=serv.getHour();
        if(cursor.moveToFirst()){
            String serviceTemp=cursor.getString(6);
            String serviceHour=cursor.getString(7);
            String[] serviceList=serviceTemp.split(",");
            String[] hourList=serviceHour.split(",");
            ArrayList<String> newServiceList=new ArrayList<String>();
            ArrayList<String> newHourList=new ArrayList<String>();
            if(serviceList.length==1){
                ContentValues values = new ContentValues();
                values.putNull(COLUMN_SERVICE);
                values.putNull(COLUMN_HOURLY);
                db.update(TABLE_SERVICE_PROVIDER, values, COLUMN_USER_ID + " = " + id, null);
            }

            int found=0;
            for(int i=0;i<serviceList.length;i++){
                if(serviceList[i].equals(tempService)){
                    found=i;
                }
            }
            for(int i=0;i<serviceList.length;i++){
                if(i!=found){
                    newServiceList.add(serviceList[i]);
                    newHourList.add(hourList[i]);
                }
            }
            if(newServiceList.size()>0) {
                String newString = newServiceList.get(0);
                String newHour = newHourList.get(0);
                for (int i = 1; i < newServiceList.size(); i++) {
                    newString = newString + "," + newServiceList.get(i);
                    newHour = newHour + "," + newHourList.get(i);
                }
                ContentValues values = new ContentValues();
                values.put(COLUMN_SERVICE, newString);
                values.put(COLUMN_HOURLY, newHour);
                db.update(TABLE_SERVICE_PROVIDER, values, COLUMN_USER_ID + " = " + id, null);
            }
        }
        cursor.close();
        db.close();

    }

    public ArrayList<String> getTime(int id){
        ArrayList<String> temp=new ArrayList<String>();
        SQLiteDatabase db= this.getReadableDatabase();
        String query="Select * FROM "+TABLE_SERVICE_PROVIDER+" WHERE "+COLUMN_USER_ID+ " = \"" +id+ "\"";
        Cursor cursor= db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            String schedule =cursor.getString(8);
            String time=cursor.getString(9);
            if(schedule==null){
                return temp;
            }
            String[] listSchedule=schedule.split(",");
            String[] listTime=time.split(",");
            int k=0;
            for(int i=0;i<listSchedule.length;i++){
                String tempVar;
                tempVar = listSchedule[i] +","+ listTime[k]+"," + listTime[k+1];
                k=k+2;
                temp.add(tempVar);
            }
        }
        return temp;
    }
    public void addTime(int id,String date, String start,String end){
        SQLiteDatabase db= this.getReadableDatabase();
        String query="Select * FROM "+TABLE_SERVICE_PROVIDER+" WHERE "+COLUMN_USER_ID+ " = \"" +id+ "\"";
        Cursor cursor= db.rawQuery(query,null);
        String scheduleFinal;
        String timeFinal;
        if(cursor.moveToFirst()){
            String schedule =cursor.getString(8);
            String time=cursor.getString(9);
            if (schedule == null) {
                scheduleFinal=date;
                timeFinal=start+","+end;
            }
            else{
                scheduleFinal=date+","+schedule;
                timeFinal=start+","+end+","+time;
            }
            ContentValues values=new ContentValues();
            values.put(COLUMN_DATE,scheduleFinal);
            values.put(COLUMN_TIME,timeFinal);
            db.update(TABLE_SERVICE_PROVIDER,values,COLUMN_USER_ID + " = "+id,null);
            cursor.close();
        }
        db.close();

    }


    public ArrayList<ServiceProvider> searchServices(String searchService){
        ArrayList<ServiceProvider> returning=new ArrayList<ServiceProvider>();
        SQLiteDatabase db= this.getReadableDatabase();
        String query="Select * FROM "+TABLE_SERVICE_PROVIDER+" WHERE "+COLUMN_SERVICE+ " LIKE '%" + searchService + "%'";
        Cursor cursor= db.rawQuery(query,null);
        //int rate=0;
        if (cursor.moveToFirst()) {
            System.out.println("Works");
            while (!cursor.isAfterLast()) {
                ArrayList<String> tempSchedule=new ArrayList<String>();
                String userId= cursor.getString(2);
                String company= cursor.getString(3);
                String schedule= cursor.getString(8);
                String time=cursor.getString(9);
                String rate=cursor.getString(10);

                String[] listSchedule=schedule.split(",");
                String[] listTime=time.split(",");
                int k=0;
                for(int i=0;i<listSchedule.length;i++){
                    String tempVar;
                    tempVar = listSchedule[i] +","+ listTime[k]+"," + listTime[k+1];
                    k=k+2;
                    tempSchedule.add(tempVar);
                }

                ServiceProvider temp=new ServiceProvider();
                if(rate!=null) {
                    temp.setRate(Integer.parseInt(rate));
                }
                else{
                    temp.setRate(0);
                }
                temp.setSchedule(tempSchedule);
                temp.setUser(getUser(Integer.parseInt(userId)));
                temp.setCompany(company);
                returning.add(temp);
                cursor.moveToNext();
            }
            cursor.close();
            return returning;
        }
        return null;
    }
    public ArrayList<ServiceProvider> searchRate(String searchRate){
        ArrayList<ServiceProvider> returning=new ArrayList<ServiceProvider>();
        SQLiteDatabase db= this.getReadableDatabase();
        String query="Select * FROM "+TABLE_SERVICE_PROVIDER+" WHERE "+COLUMN_RATE+ " LIKE '%" + searchRate + "%'";
        Cursor cursor= db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            System.out.println("Works");
            while (!cursor.isAfterLast()) {
                ArrayList<String> tempSchedule=new ArrayList<String>();
                String userId= cursor.getString(2);
                String company= cursor.getString(3);
                String schedule= cursor.getString(8);
                String time=cursor.getString(9);
                String rate=cursor.getString(10);
                String[] listSchedule=schedule.split(",");
                String[] listTime=time.split(",");
                int k=0;
                for(int i=0;i<listSchedule.length;i++){
                    String tempVar;
                    tempVar = listSchedule[i] +","+ listTime[k]+"," + listTime[k+1];
                    k=k+2;
                    tempSchedule.add(tempVar);
                }

                ServiceProvider temp=new ServiceProvider();
                if(rate!=null) {
                    temp.setRate(Integer.parseInt(rate));
                }
                else{
                    temp.setRate(0);
                }
                temp.setSchedule(tempSchedule);
                temp.setUser(getUser(Integer.parseInt(userId)));
                temp.setCompany(company);
                returning.add(temp);
                cursor.moveToNext();
            }
            cursor.close();
            return returning;
        }
        return null;
    }
    public ArrayList<ServiceProvider> searchTime(String searchTime){
        ArrayList<ServiceProvider> returning=new ArrayList<ServiceProvider>();
        SQLiteDatabase db= this.getReadableDatabase();
        String query="Select * FROM "+TABLE_SERVICE_PROVIDER+" WHERE "+COLUMN_TIME+ " LIKE '%" + searchTime + "%'";
        Cursor cursor= db.rawQuery(query,null);
        //int rate=0;
        if (cursor.moveToFirst()) {
            System.out.println("Works");
            while (!cursor.isAfterLast()) {
                ArrayList<String> tempSchedule=new ArrayList<String>();
                String userId= cursor.getString(2);
                String company= cursor.getString(3);
                String schedule= cursor.getString(8);
                String time=cursor.getString(9);
                String rate=cursor.getString(10);
                String[] listSchedule=schedule.split(",");
                String[] listTime=time.split(",");
                int k=0;
                for(int i=0;i<listSchedule.length;i++){
                    String tempVar;
                    String[] check=listTime[k].split(":");
                    String[] check2=listTime[k].split(":");
                    if(check[0].contains(searchTime)||check2[0].contains(searchTime)) {
                        tempVar = listSchedule[i] + "," + listTime[k] + "," + listTime[k + 1];
                        tempSchedule.add(tempVar);
                    }
                    k=k+2;
                }

                ServiceProvider temp=new ServiceProvider();
                if(rate!=null) {
                    temp.setRate(Integer.parseInt(rate));
                }
                else{
                    temp.setRate(0);
                }
                temp.setSchedule(tempSchedule);
                temp.setCompany(company);
                temp.setUser(getUser(Integer.parseInt(userId)));
                returning.add(temp);
                cursor.moveToNext();
            }
            return returning;
        }
        cursor.close();
        return null;
    }

    public void bookService(Users owner,String bigString){
        SQLiteDatabase db= this.getReadableDatabase();
        String query="Select * FROM "+TABLE_USERS+" WHERE "+COLUMN_USERNAME+ " = \"" +owner.getUser()+ "\"";
        Cursor cursor= db.rawQuery(query,null);
        String[] slice=bigString.split(",");
        String date=slice[0];
        String timeStart=slice[1]+","+slice[2];
        String company=slice[3];
        String service=slice[6];
        if(cursor.moveToFirst()){
            String tempCompany=cursor.getString(9);
            int id = (cursor.getInt(0));
            if(tempCompany==null) {
                //cursor.close();
                ContentValues val = new ContentValues();
                val.put(COLUMN_COMPANY, company);
                val.put(COLUMN_DATE, date);
                val.put(COLUMN_TIME, timeStart);
                val.put(COLUMN_SERVICE, service);
                db.update(TABLE_USERS, val, COLUMN_ID + " = " + id, null);
            }
            else{
                ContentValues val = new ContentValues();
                tempCompany=company+","+tempCompany;
                String tempDate=date+","+cursor.getString(11);
                String tempTime=timeStart+","+cursor.getString(12);
                String tempService=service+","+cursor.getString(10);
                val.put(COLUMN_COMPANY, tempCompany);
                val.put(COLUMN_DATE, tempDate);
                val.put(COLUMN_TIME, tempTime);
                val.put(COLUMN_SERVICE, tempService);
                db.update(TABLE_USERS, val, COLUMN_ID + " = " + id, null);
            }
        }
        cursor.close();
        db.close();
    }
    public void rateServiceProvider(String company,int rating){
        SQLiteDatabase db= this.getReadableDatabase();
        String query="Select * FROM "+TABLE_SERVICE_PROVIDER+" WHERE "+COLUMN_COMPANY+ " = \"" +company+ "\"";
        Cursor cursor= db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            String rateString=cursor.getString(10);
            int id = (cursor.getInt(0));
            int rate=Integer.parseInt(rateString);
            ContentValues val = new ContentValues();
            val.put(COLUMN_RATE,(rate+rating)/2);
            db.update(TABLE_SERVICE_PROVIDER, val, COLUMN_ID + " = " + id, null);

        }

    }
}
