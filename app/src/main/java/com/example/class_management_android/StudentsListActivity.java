package com.example.class_management_android;

import android.app.AlertDialog;
import android.arch.lifecycle.LifecycleObserver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.class_management_android.adapter.StudentAdapter;
import com.example.class_management_android.database.DbClassroomHelper;
import com.example.class_management_android.database.DbStudentHelper;
import com.example.class_management_android.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StudentsListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, LifecycleObserver
{
    private ListView lvListStudents;
    private List<Student> mListStudents;
    private StudentAdapter mAdapter;
    private TextView tvStudentCount;
    public static String mSearchText = null;
    String classID;
    Student sv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);
        classID = getIntent().getStringExtra(DbClassroomHelper.COLUMN_ID);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back_action);
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        // edit title in action bar
        actionBar.setTitle("Classroom's Code: " + classID);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1E313E"))); // dark_blue

        tvStudentCount =(TextView) findViewById(R.id.tvStudentCount);
        lvListStudents = (ListView) findViewById(R.id.lvListStudents);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mListStudents = new ArrayList<>();
        mAdapter = new StudentAdapter(this, R.layout.student_row, mListStudents);
        lvListStudents.setAdapter(mAdapter);
        refreshListStudentsData(classID);
        addEventListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("StudentsListActivity.classID = " + classID);
        refreshListStudentsData(classID);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) search.getActionView();
        searchView.setQueryHint("Insert name");
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                this.finish();
                return true;
            }
            case R.id.attendance: {
                attendancerandom();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }
    private void attendancerandom(){
        Random generator = new Random();
        int a=generator.nextInt(mListStudents.size());
        sv=mListStudents.get(a);
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(R.string.diemdanh).setMessage(sv.getName()+" "+sv.getId());
        b.create().show();
    }

    public void refreshListStudentsData(String classID) {
        DbStudentHelper dbStudentHelper = new DbStudentHelper(this, null);
        mListStudents.clear();
        mListStudents.addAll(dbStudentHelper.getListInClass(classID));
        tvStudentCount.setText(" " + Integer.toString(mListStudents.size()));
        mAdapter.notifyDataSetChanged();
    }

    public void addStudent(View v)
    {
        Intent i = new Intent(this, EditStudentActivity.class);
        i.putExtra(DbStudentHelper.COLUMN_CLASS_ID, classID);
        startActivity(i);
    }

    private void addEventListener()
    {
        lvListStudents.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id)
            {
                Student student = mListStudents.get(position);
                Intent i = new Intent(StudentsListActivity.this, EditStudentActivity.class);
                i.putExtra(DbStudentHelper.COLUMN_ID, student.getId());
                i.putExtra(DbStudentHelper.COLUMN_CLASS_ID, student.getClassId());
                startActivity(i);
                return true;
            }
        });
    }

    // delete a student in db
    private void deleteStudent(final String id)
    {

        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(R.string.delete).setMessage(getString(R.string.delete_student_message) + " - Student's code: " + id + " ?")
                .setIcon(android.R.drawable.ic_delete)
                .setNegativeButton(R.string.no, null)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DbStudentHelper dbStudentHelper = new DbStudentHelper(StudentsListActivity.this, null);
                        if (dbStudentHelper.delete(id) > 0) {
                            Toast.makeText(StudentsListActivity.this, getString(R.string.deleted), Toast.LENGTH_LONG).show();
                            refreshListStudentsData(classID);
                        } else {
                            Toast.makeText(StudentsListActivity.this, getString(R.string.error), Toast.LENGTH_LONG).show();
                        }
                    }
                });
        b.create().show();
    }



    @Override
    // based on search icon clicking in a virtual keyboard
    public boolean onQueryTextSubmit(String query)
    {
//        mSearchText = query;
//        DbStudentHelper dbHelper = new DbStudentHelper(this, null);
//        mListStudents.clear();
//        mListStudents.addAll(dbHelper.searchStudent(query,classID));
//        mAdapter.notifyDataSetChanged();
//        return true;
        return false;
    }

    @Override
    // based on typing text to searching automatically in "Insert name" on action bar
    // If newText is NULL, ignore this func
    public boolean onQueryTextChange(String newText)
    {
        mSearchText = newText;
        DbStudentHelper dbHelper = new DbStudentHelper(this, null);
        mListStudents.clear();
        mListStudents.addAll(dbHelper.searchStudent(newText,classID));
        mAdapter.notifyDataSetChanged();
        return true;
    }
}