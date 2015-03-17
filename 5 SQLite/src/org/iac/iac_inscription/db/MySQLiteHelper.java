package org.iac.iac_inscription.db;

import java.util.ArrayList;
import java.util.List;

import org.iac.iac_inscription.models.Member;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	// Database Version
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "MembersDB";

	// Members table name
	private static final String TABLE_MEMBERS = "members";

	// Members Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_FIRST_NAME = "fistName";
	private static final String KEY_LAST_NAME = "lastName";
	private static final String KEY_WORKSHOP = "workshop";

	private static final String[] COLUMNS = { KEY_ID, KEY_FIRST_NAME,
			KEY_LAST_NAME, KEY_WORKSHOP };

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// SQL statement to create members table

		String CREATE_MEMBERS_TABLE = "CREATE TABLE IF NOT EXISTS "+TABLE_MEMBERS+" ( "
				+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ KEY_FIRST_NAME+" TEXT, "+KEY_LAST_NAME+" TEXT, "+KEY_WORKSHOP+" TEXT)";

		// create members table
		db.execSQL(CREATE_MEMBERS_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older members table if existed
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_MEMBERS);

		// create fresh members table
		this.onCreate(db);
	}

	// ---------------------------------------------------------------------

	/**
	 * CRUD operations (create "add", read "get", update, delete) member + get
	 * all members + delete all members
	 */

	public void addMember(Member member) {

		// 1. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();

		// 2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();
		values.put(KEY_FIRST_NAME, member.getFirstName());
		values.put(KEY_LAST_NAME, member.getLastName());
		values.put(KEY_WORKSHOP, member.getWorkShop());
		// 3. insert
		db.insert(TABLE_MEMBERS, // table
				null, // nullColumnHack
				values); // key/value -> keys = column names/ values = column
							// values

		// 4. close
		db.close();
	}

	public Member getMember(int id) {

		// 1. get reference to readable DB
		SQLiteDatabase db = this.getReadableDatabase();
		// 2. build query
		Cursor cursor = db.query(TABLE_MEMBERS, // a. table
				COLUMNS, // b. column names
				"id = ?", // c. selections
				new String[] { String.valueOf(id) }, // d. selections args
				null, // e. group by
				null, // f. having
				null, // g. order by
				null); // h. limit

		// 3. if we got results get the first one
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();

			// 4. build member object
			Member member = new Member();
			member.setId(cursor.getInt(0));
			member.setFirstName(cursor.getString(1));
			member.setLastName(cursor.getString(2));
			member.setWorkShop(cursor.getString(3));

			cursor.close();
			db.close();
			// 5. return member
			return member;
		}
		return null;

	}

	// Get All members
	public List<Member> getAllMembers() {
		List<Member> members = new ArrayList<Member>();

		// 1. build the query
		String query = "SELECT  * FROM " + TABLE_MEMBERS;

		// 2. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);

		// 3. go over each row, build member and add it to list
		Member member = null;
		if (cursor.moveToFirst()) {
			do {
				member = new Member();
				member.setId(cursor.getInt(0));
				member.setFirstName(cursor.getString(1));
				member.setLastName(cursor.getString(2));
				member.setWorkShop(cursor.getString(3));

				// Add member to members
				members.add(member);
			} while (cursor.moveToNext());
		}

		db.close();
		// return members
		return members;
	}

	public void trunccate() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("DELETE FROM "+TABLE_MEMBERS);
		db.close();
	}

	// Updating single member
	public int updateMember(Member member) {

		// 1. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();

		// 2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();
		values.put(KEY_FIRST_NAME, member.getFirstName()); // get title
		values.put(KEY_LAST_NAME, member.getLastName());
		values.put(KEY_WORKSHOP, member.getWorkShop());
		// 3. updating row
		int i = db.update(TABLE_MEMBERS, // table
				values, // column/value
				KEY_ID + " = ?", // selections
				new String[] { String.valueOf(member.getId()) }); // selection
																	// args

		// 4. close
		db.close();

		return i;

	}

	// Deleting single member
	public void deleteMember(Member member) {

		// 1. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();

		// 2. delete
		db.delete(TABLE_MEMBERS, KEY_ID + " = ?",
				new String[] { String.valueOf(member.getId()) });

		// 3. close
		db.close();

		Log.d("delete Member", member.toString());

	}
}