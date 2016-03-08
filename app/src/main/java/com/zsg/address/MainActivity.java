package com.zsg.address;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    @Bind(R.id.listView)
    ListView listView;
    ArrayList<AddressBook> data;
    AddressAdapter adapter;
    ContentResolver resolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //获得手机联系人
        resolver = getContentResolver();
        //addData();
        // initview();
        initview2();


    }

    private void initview() {
        data = new ArrayList<>();
        Iterator<AddressBook> books = AddressBook.findAll(AddressBook.class);
        while (books.hasNext()) {
            data.add(books.next());
        }
        adapter = new AddressAdapter(data, this);
        listView.setAdapter(adapter);
    }

    public void addData() {
        AddressBook book = new AddressBook("张三", "15755554444");
        AddressBook book2 = new AddressBook("李四", "15733334444");
        AddressBook book3 = new AddressBook("王五", "15755556666");

        book.save();
        book2.save();
        book3.save();
    }

    public void initview2() {
        data = new ArrayList<>();
        //统一资源标识 content://contract/phones
        Uri uri=ContactsContract.Contacts.CONTENT_URI;
        //HAS_PHONE_NUMBER 有号码返回1 无号码返回0
        String projection[]={
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.HAS_PHONE_NUMBER,

        };
        Cursor c=resolver.query(uri,projection,"has_phone_number=?",new String[]{String.valueOf(1)},null);
        while(c.moveToNext()){
            long id=c.getLong(0);
            String name=c.getString(1);
            int h=c.getInt(2);
            AddressBook book=new AddressBook();
            book.setName(name);
            book.setId(id);
            data.add(book);
        }
        adapter = new AddressAdapter(data, this);
        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
