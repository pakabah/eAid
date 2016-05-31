package com.overcoretech.studenteaid.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.overcoretech.studenteaid.db.DBHelper;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class DataService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.overcoretech.studenteaid.services.action.FOO";
    private static final String ACTION_BAZ = "com.overcoretech.studenteaid.services.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.overcoretech.studenteaid.services.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.overcoretech.studenteaid.services.extra.PARAM2";

    public DataService() {
        super("DataService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, DataService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, DataService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }

            LoadData();
            LoadQuestions();
        }
    }


    public void LoadData()
    {
        DBHelper dbHelper = new DBHelper(getApplicationContext());

        dbHelper.insert("ICT 234","Database Design and Management","Carlos Ankora","2nd year/ 2 sem","A database management system (DBMS) is a computer software application that interacts with the user, other applications, and the database itself to capture and analyze data. A general-purpose DBMS is designed to allow the definition, creation, querying, update, and administration of databases. Well-known DBMSs include MySQL, PostgreSQL, Microsoft SQL Server, Oracle, Sybase, SAP HANA, and IBM DB2");
        dbHelper.insert("INF 281","Introduction to C++","Carlos Ankora","2nd year/ 1 sem","C++ (pronounced as cee plus plus,  is a general-purpose programming language. It has imperative, object-oriented and generic programming features, while also providing facilities for low-level memory manipulation.");
        dbHelper.insert("INF 192","Information Technology Law","Carlos Ankora","2nd year/ 1 sem","Information technology law provides the legal framework for collecting, storing, and disseminating electronic information in the global marketplace. Attorneys practicing in this area of the law represent individuals and businesses from all different industries");
        dbHelper.insert("GTUB 209","Management Information System(business)","Carlos Ankora","2nd year/ 1 sem","A management information system (MIS) is a computerized database of financial information organized and programmed in such a way that it produces regular reports on operations for every level of management in a company.");
        dbHelper.insert("ICT 363","Artificial Intelligence","Akphetsi Kester Quist","3rd year/ 1 sem","Artificial intelligence (AI) is the intelligence exhibited by machines or software. It is also the name of the academic field of study which studies how to create computers and computer software that are capable of intelligent behavior");
        dbHelper.insert("IT 452","Computer Security","Ezer Osei Boateng","4th year/ 2 sem","Computer security, also known as cybersecurity or IT security, is the protection of information systems from theft or damage to the hardware, the software, and to the information on them, as well as from disruption or misdirection of the services they provide");
        dbHelper.insert("ICT 333","Database Design and Management","Carlos Ankora","3rd year/ 1 sem","A database management system (DBMS) is a computer software application that interacts with the user, other applications, and the database itself to capture and analyze data. A general-purpose DBMS is designed to allow the definition, creation, querying, update, and administration of databases. Well-known DBMSs include MySQL, PostgreSQL, Microsoft SQL Server, Oracle, Sybase, SAP HANA, and IBM DB2");
        dbHelper.insert("INF 372","Entrepreneurship","Amegashie Samuel","3rd year/ 2 sem","Entrepreneurship has traditionally been defined the process of designing, launching and running a new business, such as a startup company offering a product, process or service.[1] It has been defined as the \"...capacity and willingness to develop, organize, and manage a business venture along with any of its risks in order to make a profit.");
        dbHelper.insert("ICT 314","Java Programming","George Anni","3rd year/ 2 sem","Java is a general-purpose computer programming language that is concurrent, class-based, object-oriented,[13] and specifically designed to have as few implementation dependencies as possible. It is intended to let application developers \"write once, run anywhere\" (WORA),[14] meaning that compiled Java code can run on all platforms that support Java without the need for recompilation.[15] Java applications are typically compiled to bytecode that can run on any Java virtual machine (JVM) regardless of computer architecture");
        dbHelper.insert("ICT 464","Management Information System","Dominic Louis","4th year/ 2 sem","A management information system (MIS) is a computerized database of financial information organized and programmed in such a way that it produces regular reports on operations for every level of management in a company.");
        dbHelper.insert("IT305","Software Engineering","George Anni","3rd year/ 1 sem","Software engineering is the application of engineering to the design, development, implementation and maintenance of software in a systematic method");
        dbHelper.insert("INF 162","Information System","Anthony Angmor","1st year/ 2 sem","Information system is an academic study of systems with a specific reference to information and the complementary networks of hardware and software that people and organizations use to collect, filter, process, create and also distribute data");
    }

    public void LoadQuestions()
    {
        DBHelper dbHelper = new DBHelper(getApplicationContext());

        dbHelper.insertQuestion("INF 281","A statement that starts with a # is called a:","preprocessor directive","key word","comment","function","1","INF 2811","1");
        dbHelper.insertQuestion("INF 281","You want the user to enter the length, width, and height from the keyboard. Which cin statement is correctly written?","cin >> length, width, height;","cin << length; width; height;","cin.get(length, width, height);","cin >> length >> width >> height;","1","INF 2812","2");
        dbHelper.insertQuestion("INF 281","These are operators that add and subtract one from their operands.","conditional and relational","binary and unary","plus and minus","++ and --","1","INF 2813","3");
        dbHelper.insertQuestion("INF 281","The ________ of a variable is limited to the block in which it is declared.","precedence","branching ability","associativity","scope","1","INF 2814","4");
        dbHelper.insertQuestion("INF 281","You can use these to override the rules of operator precedence in a mathematical expression.","{braces}","[brackets]","the escape character ","(parentheses)","1","INF 2815","5");

        dbHelper.insertQuestion("ICT 234","Which of the following is not one of the phases for designing a database?","Requirement Analysis","Conceptual design","Physical design","Virtual design","1","ICT 2341","1");
        dbHelper.insertQuestion("ICT 234","A database administrator (DBA) is NOT responsible for: ","Choosing appropriate structures to represent and store this data","Authorizing access to the database","Coordinating and monitoring its use","Acquiring software and hardware resources","1","ICT 2342","2");
        dbHelper.insertQuestion("ICT 234","…… is a collection of concepts that describe the structure of a database","Data abstraction","Architecture","Data model","Entity","1","ICT 2343","3");
        dbHelper.insertQuestion("ICT 234","The DBMS language that allows retrieval, insertion, deletion and modification of data is ","Data manipulation language (DML)","Data definition language (DDL)","Storage definition language (SDL)","View definition language (VDL)","1","ICT 2344","4");
        dbHelper.insertQuestion("ICT 234","Each statement in SQL ends with a ………….","Semicolon","Comma","Full stop","None of the above","1","ICT 2345","5");

        dbHelper.insertQuestion("INF 192","When someone applies for a patent, an official called a …………. sets out to check whether the requirements are met.","Patent officer","Patent examiner","Patent checker","Patent manager","1","INF 1921","1");
        dbHelper.insertQuestion("INF 192","A person is guilty of fraud if he is in breach of any of the following except: ","fraud by false representation","fraud by failing to disclose information","fraud by extortion","fraud by abuse of position","1","INF 1922","2");
        dbHelper.insertQuestion("INF 192","Each of the following are Intellectual property rights except:","contracts","patents","trademarks","the law of confidence","1","INF 1923","3");
        dbHelper.insertQuestion("INF 192","All the following are forms of misrepresentation except","fraudulent","negligent","insolent","innocent","1","INF 1924","4");
        dbHelper.insertQuestion("INF 192","What is the copyright protection duration for corporate-owned works?","70 years","75 years","90 years","95 years","1","INF 1925","5");

        dbHelper.insertQuestion("GTUB 209","Which main business function is responsible for maintaining employee records?","human resources","sales and marketing","finance and accounting","manufacturing and production","1","GTUB 2091","1");
        dbHelper.insertQuestion("GTUB 209","Executive support systems are information systems that support the","decision making and administrative activities of middle managers.","day-to-day processes of production.","long-range planning activities of senior management.","knowledge and data workers in an organization.","1","GTUB 2092","2");
        dbHelper.insertQuestion("GTUB 209","All of the following are direct business benefits of collaboration except for","improved quality.","improved financial performance.","improved customer service.","improved compliance with government regulations.","1","GTUB 2093","3");
        dbHelper.insertQuestion("GTUB 209","Which of the following would not be considered a disruptive technology?","PCs","instant messaging","Internet telephony","e-mail","1","GTUB 2094","4");
        dbHelper.insertQuestion("GTUB 209","Which of the following industries has not been disrupted by the Internet?","clothing","air travel","newspapers","encyclopedias","1","GTUB 2095","5");


    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
