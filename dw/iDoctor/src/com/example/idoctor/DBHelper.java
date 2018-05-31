package com.example.idoctor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
	public static String name = "course.db";
	private static int version = 1;
	
	private SQLiteDatabase database;
	
	public DBHelper(Context context) {
		super(context, name, null, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		this.database=db;
		String sql = "create table part(name varchar(100))";
		String sql1 = "create table symptom(part varchar(100),symptom varchar(100))";
		String sql2 = "create table disease(symptom varchar(100),name varchar(50),ratio varchar(30),info varchar(1000),treat varchar(1000))";
		String sql3 = "create table medicine(medicine varchar(100),info varchar(100))";
		String sql4 = "create table url(info varchar(100),url varchar(100))";	
		database.execSQL(sql);
		database.execSQL(sql1);
		database.execSQL(sql2);
		database.execSQL(sql3);
		database.execSQL(sql4);
		String[] a= new String[9];
		a[0] = "head";
		a[1] = "body";
		a[2] = "throat";
		a[3] = "chest";
		a[4] = "stomach";
		a[5] = "pelvis";
		a[6] = "limb";
		a[7] = "back";
		a[8] = "skin";
		
		for(int i=0;i<a.length;i++){
			ContentValues contentValues = new ContentValues();
			contentValues.put("name", a[i]);
			database.insert("part", null, contentValues);
		}
		String[] b = new String[10];
		b[0] = "Dizzy";
		b[1] = "Headache";
		b[2] = "sinus infection";
		b[3] = "Nasal obstruction";
		b[4] = "Tinnitus";
		b[5] = "Gingival bleeding";
		b[6] = "Obesity";
		b[7] = "Allergy";
		b[8] = "Systemic weakness";
		b[9] = "Poor sleep";
		
			
		for (int j=0;j<6;j++){
			ContentValues contentValues = new ContentValues();
			contentValues.put("part", a[0]);
			contentValues.put("symptom", b[j]);
			database.insert("symptom", null, contentValues);
		}
		for (int j=6;j<10;j++){
			ContentValues contentValues = new ContentValues();
			contentValues.put("part", a[1]);
			contentValues.put("symptom", b[j]);
			database.insert("symptom", null, contentValues);
		}
		
	
		String[] c = new String[11];
		c[0]="Arrhythmia";
		c[1]="Anxiety disorder";
		c[2]="Hypoglycemia";
		c[3] ="Hypertension";
		c[4] = "Chronic sinusitis";
		c[5] = "Acute sinusitis";
		c[6] = "Rhinallergosis";
		c[7] = "sleep disorder";
		c[8] = "mood  disorder";
		c[9] = "adjustment  disorder";
		c[10] = "schizophrenia";
		
		
		String[] d = new String[11];
		d[0]="25%";
		d[1]="36%";
		d[2]="18%";
		d[3]="17%";
		d[4] = "30%";
		d[5] = "20%";
		d[6] = "50%";
		d[7] = "49%";
		d[8] = "30%";
		d[9] = "15%";
		d[10] = "6%";
		
		
		String[] e = new String[11];
		e[0]="    Arrhythmia refers to the origin of cardiac impulse, heart beat frequency and rhythm, as well as any abnormalities in impulse conduction. It can be caused by various kinds of organic cardiovascular disease, drug poisoning, electrolyte and acid-base imbalance and other factors. Some arrhythmia can also be caused by the disturbance of plant nerve function.";
		e[1]="    Anxiety neurosis is the most common type of neurosis, and the main feature of anxiety is emotional experience. It can be divided into two types: chronic anxiety, namely generalized anxiety and acute anxiety, namely panic attack. The main manifestations are: nervous anxiety, fidgeting, and nervous dysfunction of plants, such as palpitation, trembling, sweating, frequency of urine, and sports uneasiness.severity of anxiety ";
		e[2]="    Hypoglycemia is an adult fasting plasma glucose level below 2.8mmol/L. Hypoglycemia can be diagnosed in patients with diabetes whose blood glucose level is less than 3.9 mmol/L. Hypoglycemia is a group of diseases caused by a variety of causes of venous plasma glucose (abbreviated blood glucose) is too low, clinical with the sympathetic nerve excitement and brain cell hypoxia as the main characteristics of the syndrome. ";
		e[3]="   Hypertension refers to the clinical syndrome that is characterized by the increase of blood pressure (systolic and / or diastolic pressure) as the main characteristic (systolic pressure > 140 mm Hg, diastolic pressure > 90 mm Hg), which can be accompanied by functional or organic damage in the organs of heart, brain and kidney. Hypertension is the most common chronic disease ";
		e[4]="   Chronic sinusitis is chronic suppurative inflammation of the paranasal sinuses. It is more common than acute ones, and is often involved in many paranasal sinuses. Chronic sinusitis affects the quality of life of the patients, aggravates the symptoms of respiratory tract infection in the patients, and the serious person has the possibility of causing craniophthalmic complications, which leads to visual change and even aggravation of infection.";
		e[5]="   Acute sinusitis is an acute suppurative inflammation of the paranasal sinuses, often secondary to acute rhinitis. Acute sinusitis is mainly caused by upper respiratory tract infection, and bacteria and virus infection can be concomitant simultaneously. Acute rhinosinusitis is common in all groups, especially in young, old and weak people.";
		e[6]="   Allergic rhinitis, allergic rhinitis, is a non infectious inflammatory disease of nasal mucosa that is mainly mediated by IgE mediators (mainly histamine) and has a variety of immune active cells and cytokines after allergic rhinitis. There are 3 necessary conditions for the occurrence of the specific antigen, that is, the substance that causes the immune response of the body; the idiosyncrasy is the so-called individual difference, ";	
		e[7]="   Abnormal sleep volume and abnormal behavior during sleep are also manifestations of dysrhythmic disorder in sleep and wakefulness. It can be caused by a variety of factors, often associated with somatic diseases, including sleep disorders and abnormal sleep. Sleep is closely related to human health.";
		e[8]="   Mood disorders, also known as affective disorders, refer to a group of diseases characterized by significant and lasting changes in mood or mood caused by various causes. The main clinical manifestations are high or low emotion, accompanied by corresponding cognitive and behavioral changes, and hallucinations, paranoia, and other psychotic symptoms.";
		e[9]="   Adaptation barrier refers to the short-term and mild state of worry and emotional disorder caused by obvious changes in life or changes in the environment, often with a certain degree of behavioral changes, but it does not appear to be psychosis. The typical life events are: bereavement, divorce, unemployment or change of jobs, relocation, transfer, serious illness, economic crisis, retirement, etc.";
		e[10]="  Schizophrenia is the most common mental disorder characterized by the change of basic personality, the split of thinking, emotion and behavior, and the incongruity of mental activity and the environment. The survey data of six areas in the United States showed that the annual incidence rate was from 0.43 to 0.69 per thousand and 0.30 per thousand to 1.20 per thousand (Babigian, 1975) over 15 years old, and 0.09 per thousand in some areas of China.";		
				
		String[] f = new String[11];
		f[0]="    Drugs for the treatment of slow arrhythmia usually use drugs that enhance myocardial self-discipline and / or accelerated conduction, such as parasympathetic nerve drugs (isoproterenol, etc.), vagus nerve inhibitors (atropine) or alkanes (sodium lactate or sodium bicarbonate)";
		f[1]="    Commonly used drugs: Laura Si (Laura), alprazolam, 2~3 times a day. It is a kind of short and moderate stability medicine, it has good anti anxiety effect, relatively less sedative effect and less influence on daytime work. The principle of use: intermittent medication.";
		f[2]="    Treatment includes two aspects: first, to relieve symptoms of hypoglycemia; two, to correct various underlying causes leading to hypoglycemia. For mild to moderate hypoglycemia, oral sugar, sugar drinks, or sweets, biscuits, bread and steamed bread can be alleviated.";
		f[3]="    Using a half life period of 24 hours or more, a daily dose of medicine can control 24 hours of blood pressure drugs, such as equals of amlol, and to avoid the poor control of iatrogenic early morning blood pressure due to improper treatment options.";
		f[4]="    Although macrolide antibiotics can not eliminate bacteria, they can reduce the toxicity of chronic bacterial infection and reduce cell damage. Selective treatment with long-term low-dose macrolide antibiotics is effective in cases of hormone failure.";
		f[5]="    A full amount of antibiotics are used to control infection. Because of the multi - coccal infection, penicillins and cephalosporins are selected as the first drugs. Drug treatment emphasizes the selection of sensitive antibiotics and the use of foot and foot treatment. ";
		f[6]="    Antihistamines, oral or nasal, with second generation or new H1 antihistamines, can effectively relieve nasal itching, sneezing and runny nose. It is suitable for mild intermittent and mild persistent allergic rhinitis and combined with nasal corticosteroids in the treatment of moderate severe allergic rhinitis.";
		f[7]="    The relationship between mental emotion and insomnia is close, and the insomnia is divided into annoyance type, suspicious type, tension type and depression type.";
		f[8]="    Drug therapy is the main treatment for moderate to above depression attacks. At present, the main clinical antidepressants include selective 5- serotonin reuptake inhibitor (SSRI), 5- serotonin and norepinephrine reuptake inhibitor (SNRI), norepinephrine and specific 5- serotonin antidepressant (NaSSA).";
		f[9]="    When the stressor disappeared, there was no significant improvement in emotional abnormality. Psychological treatment was needed. Psychological counseling, psychotherapy, crisis intervention, family therapy and group therapy can be used to treat adjustment disorders. The primary goal of psychotherapy should be to encourage patients to express their fears, anxieties, anger, despair, and helplessness caused by stressors, to determine what is the main dysfunction caused by stress,";
		f[10]="   Anti psychotic drugs, also known as nerve blockers, can effectively control the mental symptoms of schizophrenia, widely used in clinical, obviously improve the relief rate of mental symptoms and the discharge rate of mental patients. The most commonly used antipsychotic drugs were first found in 1950s in the phenothiazine (Phenothiazine) drug represented by chlorpromazine, followed by the presence of Butyrophenone, represented by haloperidol,";
		
		for(int i=0;i<4;i++){
			ContentValues contentValues = new ContentValues();
			contentValues.put("symptom", b[0]);
			contentValues.put("name", c[i]);
			contentValues.put("ratio", d[i]);
			contentValues.put("info", e[i]);
			contentValues.put("treat", f[i]);
			database.insert("disease", null, contentValues);
		}
		
		for(int i=4;i<7;i++){
			ContentValues contentValues = new ContentValues();
			contentValues.put("symptom", b[2]);
			contentValues.put("name", c[i]);
			contentValues.put("ratio", d[i]);
			contentValues.put("info", e[i]);
			contentValues.put("treat", f[i]);
			database.insert("disease", null, contentValues);
		}
		
		for(int i=7;i<11;i++){
			ContentValues contentValues = new ContentValues();
			contentValues.put("symptom", b[9]);
			contentValues.put("name", c[i]);
			contentValues.put("ratio", d[i]);
			contentValues.put("info", e[i]);
			contentValues.put("treat", f[i]);
			database.insert("disease", null, contentValues);
		}
		String[] a1 =new  String[20];
		String[] b1 = new  String[20];
			a1[0]=	"Eating a healthy breakfast is a great way to jump-start your day";
			b1[0]=	"https://atlanticgeneral.netreturns.biz/HealthInfo/Story.aspx?StoryID=356176e2-2d13-4e05-969e-bd2eeb6a02c8#.WwgkkEiFNPY";
			a1[1]="A guide to fats";
			b1[1]="https://atlanticgeneral.netreturns.biz/HealthInfo/Story.aspx?StoryID=24a0a6c5-032c-49de-af99-06f8fa06a4df#.WwgkxUiFNPY";
			a1[2]="Do you have a sleep disorder? ";
			b1[2]="https://atlanticgeneral.netreturns.biz/HealthInfo/Story.aspx?StoryID=522e9926-c0bb-46ec-8ccd-7d7c102c9775#.Wwgk6UiFNPY";
			a1[3] = "Get your child to sleep";
			b1[3] = "https://atlanticgeneral.netreturns.biz/HealthInfo/Story.aspx?StoryID=72fcb0d0-1b6c-40b9-a727-1df5074a68a6#.WwglIkiFNPY";
			a1[4] = "Time for a sleep study";
			b1[4] = "https://atlanticgeneral.netreturns.biz/HealthInfo/Story.aspx?StoryID=34e2a588-4eac-4a68-b096-4df0f7f4f1d5#.WwglhEiFNPY";
			a1[5] = "How many colories burn?";
			b1[5] = "https://atlanticgeneral.netreturns.biz/HealthInfo/Story.aspx?StoryID=806ab1ee-629a-46e5-a5de-6ed5b594520f#.WwgltkiFNPY";
			a1[6] = "Sore muscles";
			b1[6] = "https://atlanticgeneral.netreturns.biz/HealthInfo/Story.aspx?StoryID=08e5f4a5-5d6f-434d-aa6b-d9383ad86502#.Wwgl00iFNPY";
			a1[7] = "Bicycle safe tips";
			b1[7] = "https://atlanticgeneral.netreturns.biz/HealthInfo/Story.aspx?StoryID=f5ed9af3-cbfc-474a-ab2f-db67d65a3a10#.Wwgl7UiFNPY";
			
		for(int i=0;i<8;i++){
			ContentValues contentValues1 = new ContentValues();
			contentValues1.put("Info", a1[i]);
			contentValues1.put("url", b1[i]);
			database.insert("url", null, contentValues1);
		}
		
		
		
		
		
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void insert(ContentValues values){
		SQLiteDatabase database = getWritableDatabase();
			database.insert("medicine", null, values);
			database.close();
	}
	
	
	public Cursor query(int number) {
		SQLiteDatabase database=getWritableDatabase();
		Cursor cursor = null;
		if (number==1){
			cursor = database.query("part", null, null,null, null, null, null);
			
		}
		else{
			cursor = database.query("part", null, null,null, null, null, null);
		}
		return cursor;
	}
	
	public Cursor query1(int number,String x) {
		SQLiteDatabase database=getWritableDatabase();
		Cursor cursor = null;
		if (number==1){
			cursor = database.query("symptom", null, null,null, null, null, null);
			
		}
		else{
			cursor = database.query("symptom", null, "part=?", new String[]{x}, null, null, null);
		}
		return cursor;
	}
	
	public Cursor query2(int number,String x) {
		SQLiteDatabase database=getWritableDatabase();
		Cursor cursor = null;
		if (number==1){
			cursor = database.query("disease", null, null,null, null, null, null);
			
		}
		else{
			cursor = database.query("disease", null, "symptom=?", new String[]{x}, null, null, null);
		}
		return cursor;
	}
	
	public Cursor query3(int number,String x) {
		SQLiteDatabase database=getWritableDatabase();
		Cursor cursor = null;
		if (number==1){
			cursor = database.query("disease", null, null,null, null, null, null);
			
		}
		else{
			cursor = database.query("disease", null, "name=?", new String[]{x}, null, null, null);
		}
		return cursor;
	}
	
	public Cursor query4(int number) {
		SQLiteDatabase database=getWritableDatabase();
		Cursor cursor = null;
		if (number==1){
			cursor = database.query("medicine", null, null,null, null, null, null);
			
		}
		return cursor;
	}
	
	public Cursor query5(int number,String x) {
		SQLiteDatabase database=getWritableDatabase();
		Cursor cursor = null;
		if (number==1){
			cursor = database.query("medicine", null, "medicine=?",new String[]{x}, null, null, null);
			
		}
		return cursor;
	}
	
	public Cursor query6(int number) {
		SQLiteDatabase database=getWritableDatabase();
		Cursor cursor = null;
		if (number==1){
			cursor = database.query("url", null, null,null, null, null, null);
			
		}
		return cursor;
	}
	
	public void delete(String name){
		if(database==null)
		{
			database=getWritableDatabase();
			database.delete("medicine", "medicine=?", new String[]{name});
			
		}
	
	}
	
	

}
