package com.example.class_management_android;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.class_management_android.adapter.ClassroomNotificationAdapter;
import com.example.class_management_android.database.DbClassroomHelper;
import com.example.class_management_android.model.Classroom;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationFragment extends android.app.Fragment {
    CalendarView calendarView;
    TextView textView;
    private ListView lvListClassroom;
    private List<Classroom> mListClassroom; // mListClassroom - monitor of list classroom
    private  List<Classroom> listClassroomDay;
    private ClassroomNotificationAdapter mAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NotificationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notification, container, false);
        calendarView = (CalendarView) v.findViewById(R.id.calendar);
        textView = (TextView) v.findViewById(R.id.tvClassDay);
        lvListClassroom = (ListView) v.findViewById(R.id.lvListClassNotification);
        mListClassroom = new ArrayList<>();
        refreshListClassManagersData();

        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        listClassroomDay = sortByTimeStart(classroomDay1(mListClassroom, dayOfWeek));
        updateClassroomDay();
        addEventListener();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                            @Override

                            // In this Listener have one method
                            // and in this method we will
                            // get the value of DAYS, MONTH, YEARS
                            public void onSelectedDayChange(
                                    CalendarView view,
                                    int year,
                                    int month,
                                    int dayOfMonth)
                            {

                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, month, dayOfMonth);
                                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                                // Store the value of date with
                                // format in String type Variable
                                // Add 1 in month because month
                                // index is start with 0
                                listClassroomDay.clear();
                                listClassroomDay = sortByTimeStart(classroomDay1(mListClassroom, dayOfWeek));
                                updateClassroomDay();

                            }
                        });

        return v;
    }
    @Override
    public void onResume() {
        super.onResume();
        refreshListClassManagersData();
    }

    public void refreshListClassManagersData()
    {
        DbClassroomHelper dbClassManagerHelper = new DbClassroomHelper(getActivity(), null);
        mListClassroom.clear();
        mListClassroom.addAll(dbClassManagerHelper.getList());
        //mAdapter.notifyDataSetChanged();
    }

    public void updateClassroomDay(){
        if(listClassroomDay.size() > 0){
            textView.setText(" ");
            mAdapter = new ClassroomNotificationAdapter(this.getActivity(), R.layout.classroom_row_notification, listClassroomDay);
            lvListClassroom.setAdapter(mAdapter);
        }else{
            textView.setText("No classes today");
            if(mAdapter == null){
                mAdapter = new ClassroomNotificationAdapter(this.getActivity(), R.layout.classroom_row_notification, listClassroomDay);
                lvListClassroom.setAdapter(mAdapter);
            }
        }
        mAdapter.notifyDataSetChanged();
    }


    private void addEventListener() {
        lvListClassroom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Classroom classroom = listClassroomDay.get(position);
                Intent i = new Intent(getActivity(), StudentsListActivity.class);
                i.putExtra(DbClassroomHelper.COLUMN_ID, classroom.getId());
                startActivity(i);
            }
        });
        lvListClassroom.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (position >= 0) {
                    Classroom classroom = listClassroomDay.get(position);
                    Intent i = new Intent(getActivity(), EditClassroomActivity.class);
                    i.putExtra(DbClassroomHelper.COLUMN_ID, classroom.getId());
                    startActivity(i);
                }
                return true;
            }
        });
    }
    private List<Classroom> classroomDay1(List<Classroom> classrooms, int weekday){
        List<Classroom> result = new ArrayList<>();
        switch(weekday){
            case 2: result = clasroomDay2(classrooms, "Monday");
            break;
            case 3: result = clasroomDay2(classrooms, "Tuesday");
            break;
            case 4: result = clasroomDay2(classrooms, "Wednesday");
            break;
            case 5: result = clasroomDay2(classrooms, "Thursday");
            break;
            case 6: result = clasroomDay2(classrooms, "Friday");
            break;
            case 7: result = clasroomDay2(classrooms, "Saturday");
            break;
            case 1: result = clasroomDay2(classrooms, "Sunday");
            break;

        }
        return  result;
    }

    private List<Classroom> clasroomDay2(List<Classroom> classrooms, String weekday){
        List<Classroom> result = new ArrayList<>();
        for(Classroom i : classrooms){
            if(i.getWeekDay().toString().equals(weekday)){
                result.add(i);
            }
        }
        return  result;
    }

    private  List<Classroom> sortByTimeStart(List<Classroom> classrooms){
        int length = classrooms.size();
        for(int i = 0; i < length - 1; i++){
            int min = i;
            for(int j = i + 1; j < length; j++){
                if(convertTimeFromStringToInt(classrooms.get(j).getStartTime())
                        < convertTimeFromStringToInt(classrooms.get(min).getStartTime())){
                    min = j;
                }
            }
            Classroom swap = classrooms.get(i);
            classrooms.set(i, classrooms.get(min));
            classrooms.set(min, swap);
        }
        return classrooms;
    }

    private int convertTimeFromStringToInt(String time){
        int result = 0;
        int length = time.length();
        int index = time.indexOf(":");
        if(index == 1){
            result = result + (Character.getNumericValue(time.charAt(0)))*60;
            if(length == 3){
                result = result + Character.getNumericValue(time.charAt(2));
            }else {
                result = result + (Character.getNumericValue(time.charAt(2)))*10 + Character.getNumericValue(time.charAt(3));
            }
        }else{
            result = result + (Character.getNumericValue(time.charAt(0))*10 + Character.getNumericValue(time.charAt(1)))*60;
            if(length == 4){
                result = result + Character.getNumericValue(time.charAt(3));
            }else {
                result = result + (Character.getNumericValue(time.charAt(3)))*10 + Character.getNumericValue(time.charAt(4));
            }
        }
        return result;
    }

}