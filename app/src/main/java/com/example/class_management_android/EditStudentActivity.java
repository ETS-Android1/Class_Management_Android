package com.example.class_management_android;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.class_management_android.database.DbClassroomHelper;
import com.example.class_management_android.database.DbStudentHelper;
import com.example.class_management_android.model.Student;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class EditStudentActivity extends AppCompatActivity
{
    private TextView tvTitle;
    private EditText etID, etName, etBirthday, etPhoneNumber, etEmail;
    private RadioButton radMale, radFemale;
    private Date mBirthday = new Date();
    private String mId;
    private String classID;
    private static String TAG = "EditStudentActivity";
    private DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            etBirthday.setText(day + "/" + (month + 1) + "/" + year);
            Calendar cal = Calendar.getInstance();
            cal.set(year, month, day);
            mBirthday = cal.getTime();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back_action);
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        // edit title in action bar
        actionBar.setTitle("");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1E313E"))); // dark_blue

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvTitle = (TextView) findViewById(R.id.tvTitleStudent);
        etID = (EditText) findViewById(R.id.etIDStudent);
        etName = (EditText) findViewById(R.id.etNameStudent);
        etBirthday = (EditText) findViewById(R.id.etBirthdayStudent);
        etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
        etEmail = (EditText) findViewById(R.id.etEmail);
        radMale = (RadioButton) findViewById(R.id.radMaleStudent);
        radFemale = (RadioButton) findViewById(R.id.radFemaleStudent);
        classID = getIntent().getStringExtra(DbStudentHelper.COLUMN_CLASS_ID);
        mId = getIntent().getStringExtra(DbStudentHelper.COLUMN_ID);
        System.out.println("EditStudentActivity.classID: " + classID);
        System.out.println("EditStudentActivity.mID: " + mId);
        if (mId == null) {
            // ADD MODE
            setDefaultInfo();
        } else {
            // EDIT MODE
            DbStudentHelper dbHelper = new DbStudentHelper(this, null);
            Student student = dbHelper.get(mId);
            if (student != null) {
                tvTitle.setText(R.string.update_student);
                etID.setText(student.getId());
                etID.setEnabled(false);
                etName.setText(student.getName());
                etName.requestFocus();
                etPhoneNumber.setText(student.getPhoneNumber());
                etEmail.setText(student.getEmail());
                etBirthday.setText(student.getBirthday());
                if (student.getGender() == 0)
                    radFemale.setChecked(true);
                else
                    radMale.setChecked(true);
            }
        }

    }

    // create a menu for adding and editing mode
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mId == null)
            return super.onCreateOptionsMenu(menu);
        else {
            getMenuInflater().inflate(R.menu.menu_edit, menu);
            return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
            {
                this.finish();
                return true;
            }
            case R.id.action_delete:
            {
                deleteStudent(mId);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    // show a message to delete a student in db with student's id
    private void deleteStudent(final String id)
    {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(R.string.delete).setMessage(getString(R.string.delete_student_message) + " - Student's code: " + id + " ?")
                .setIcon(android.R.drawable.ic_delete)
                .setNegativeButton(R.string.no, null)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DbStudentHelper dbStudentHelper = new DbStudentHelper(EditStudentActivity.this, null);
                        if (dbStudentHelper.delete(id) > 0) {
                            showToastMessage(getString(R.string.deleted));
                            EditStudentActivity.this.finish();
                        } else {
                            showToastMessage(getString(R.string.error));
                        }
                    }
                });
        b.create().show();
    }

    public void showDatePickerDialog(View v) {
        String strDate = etBirthday.getText() + "";
        String strArrtmp[] = strDate.split("/");
        int day = Integer.parseInt(strArrtmp[0]);
        int month = Integer.parseInt(strArrtmp[1]) - 1;
        int year = Integer.parseInt(strArrtmp[2]);
        DatePickerDialog datePicker = new DatePickerDialog(this, callback, year, month, day);
        datePicker.setTitle(R.string.ngaysinh);
        datePicker.show();
    }

    public void clear(View v) {
        if (mId == null) {
            // ADD MODE
            etID.setText("");
            etName.setText("");
            etPhoneNumber.setText("");
            etEmail.setText("");
            setDefaultInfo();
        } else {
            // EDIT MODE
            etName.setText("");
            etPhoneNumber.setText("");
            etEmail.setText("");
            Calendar cal = Calendar.getInstance();
            etBirthday.setText(getDateFormat(cal.getTime()));
            radMale.setChecked(true);
            etName.requestFocus();
        }

    }

    public void save(View v) {
        if (mId == null) {
            // ADD MODE
            addStudent();
        } else {
            // EDIT MODE
            updateStudent();
        }
    }

    private void addStudent() {
        String mId = etID.getText().toString().trim();
        if (mId.length() == 0) {
            etID.setError("?");
            etID.requestFocus();
            return;
        }
        String name = etName.getText().toString().trim();
        String phoneNumber = etPhoneNumber.getText().toString();
        String email = etEmail.getText().toString();
        if (name.length() == 0) {
            etName.setError("?");
            etName.requestFocus();
            return;
        }
        Student student = new Student();
        student.setId(mId);
        student.setName(name);
        student.setBirthday(getDateFormat(mBirthday));
        if (radMale.isChecked())
            student.setGender(1);
        else
            student.setGender(0);
        student.setClassId(classID);
        student.setPhoneNumber(phoneNumber);
        student.setEmail(email);

        System.out.println("the saved student' infor: " +
                "id: " + student.getId() + "; id_class: " + student.getClassId()
                + "; name: " + student.getName() + "; gender: " + student.getGender()
                + "; phone_num: " + student.getPhoneNumber()
                + "; email: " + student.getEmail() + "; birthday: " + student.getBirthday());

        DbStudentHelper dbHelper = new DbStudentHelper(this, null);


        if (dbHelper.add(student) > 0) {
            showToastMessage(getString(R.string.saved));
            this.finish();
        } else {
            if (dbHelper.get(mId) != null)
                showToastMessage(getString(R.string.student_exists));
            else
                showToastMessage(getString(R.string.error));
        }
    }

    private void updateStudent() {
        String name = etName.getText().toString();
        String phoneNumber = etPhoneNumber.getText().toString();
        String email = etEmail.getText().toString();
        if (name.trim().length() == 0) {
            etName.setError("?");
            etName.requestFocus();
            return;
        }
        Student student = new Student();
        student.setId(mId);
        student.setName(name);
        student.setBirthday(getDateFormat(mBirthday));
        if (radMale.isChecked())
            student.setGender(1);
        else
            student.setGender(0);
        student.setClassId(classID);
        student.setPhoneNumber(phoneNumber);
        student.setEmail(email);

        DbStudentHelper dbHelper = new DbStudentHelper(this, null);
        if (dbHelper.update(student) > 0) {
            showToastMessage(getString(R.string.updated));
            this.finish();
        } else {
            showToastMessage(getString(R.string.error));
        }
    }


    private void showToastMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


    private void setDefaultInfo() {
        Calendar cal = Calendar.getInstance();
        etBirthday.setText(getDateFormat(cal.getTime()));
        radMale.setChecked(true);
        etID.requestFocus();
    }

    private String getDateFormat(Date date) {
        SimpleDateFormat dft = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return dft.format(date);
    }
}