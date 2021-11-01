package com.example.class_management_android;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.class_management_android.database.DbClassroomHelper;
import com.example.class_management_android.model.Classroom;

import java.util.Calendar;

public class EditClassroomActivity extends AppCompatActivity {
    private TextView tvTitle;
    private EditText etID, etName, etStart, etEnd, etRoom;
    private String mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_classroom);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvTitle = (TextView) findViewById(R.id.tvTitleClass);
        etID = (EditText) findViewById(R.id.etIDClass);
        etName = (EditText) findViewById(R.id.etNameClass);
        etStart = (EditText) findViewById(R.id.etStart);
        etEnd = (EditText) findViewById(R.id.etEnd);
        etRoom = (EditText) findViewById(R.id.etClassRoom);
        mId = getIntent().getStringExtra(DbClassroomHelper.COLUMN_ID);
        if (mId == null) {
            // ADD MODE
            etID.requestFocus();
        } else {
            // EDIT MODE
            DbClassroomHelper dbHelper = new DbClassroomHelper(this, null);
            Classroom classroom = dbHelper.get(mId);
            if (classroom != null) {
                tvTitle.setText(R.string.update_class);
                etID.setText(classroom.getId());
                etID.setEnabled(false);
                etName.setText(classroom.getSubjectName());
                etName.requestFocus();
                etStart.setText(classroom.getStartTime());
                etEnd.setText(classroom.getEndTime());
                etRoom.setText(classroom.getClassroomName());
            }
        }

        etStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(EditClassroomActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        etStart.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        etEnd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(EditClassroomActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        etEnd.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
    }

    // create a menu to edit
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_delete) {
            DbClassroomHelper dbHelper = new DbClassroomHelper(this, null);
            if (dbHelper.delete(mId) > 0) {
                Toast.makeText(this, getString(R.string.deleted), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, getString(R.string.error), Toast.LENGTH_LONG).show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void clear(View v) {
        etName.setText("");
        if (mId == null) {
            // ADD MODE
            etID.setText("");
        } else {
            // EDIT MODE
            etName.requestFocus();
        }
        etID.requestFocus();
    }

    public void save(View v) {
        if (mId == null) {
            // ADD MODE
            addClassroom();
        } else {
            // EDIT MODE
            updateClassroom();
        }
    }

    private void addClassroom() {
        String mId = etID.getText().toString().trim();
        if (mId.length() == 0) {
            etID.setError("?");
            etID.requestFocus();
            return;
        }
        String name = etName.getText().toString().trim();
        if (name.length() == 0) {
            etName.setError("?");
            etName.requestFocus();
            return;
        }
        Classroom classroom = new Classroom();
        classroom.setId(mId);
        classroom.setSubjectName(name);
        classroom.setStartTime(etStart.getText().toString());
        classroom.setEndTime(etEnd.getText().toString());
        classroom.setClassroomName(etRoom.getText().toString());
        DbClassroomHelper dbHelper = new DbClassroomHelper(this, null);
        float check = dbHelper.add(classroom);
        if (check > 0)
        {
            showToastMessage(getString(R.string.saved));
//            navigateToClassroomList();
            this.finish();
        }
        else
        {
            if (dbHelper.get(mId) != null)
                showToastMessage(getString(R.string.class_exits));
            else
                showToastMessage(getString(R.string.error));
        }
    }

    private void updateClassroom()
    {
        String mId = etID.getText().toString().trim();
        if (mId.length() == 0) {
            etID.setError("?");
            etID.requestFocus();
            return;
        }
        String name = etName.getText().toString().trim();
        if (name.length() == 0) {
            etName.setError("?");
            etName.requestFocus();
            return;
        }
        Classroom classroom = new Classroom();
        classroom.setId(mId);
        classroom.setSubjectName(name);
        classroom.setStartTime(etStart.getText().toString());
        classroom.setEndTime(etEnd.getText().toString());
        classroom.setClassroomName(etRoom.getText().toString());
        DbClassroomHelper dbHelper = new DbClassroomHelper(this, null);
        if (dbHelper.update(classroom) > 0) {
            showToastMessage(getString(R.string.saved));
//            navigateToClassroomList();
            this.finish();
        } else {
            showToastMessage(getString(R.string.error));
        }
    }

    private void showToastMessage(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    private void navigateToClassroomList()
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}