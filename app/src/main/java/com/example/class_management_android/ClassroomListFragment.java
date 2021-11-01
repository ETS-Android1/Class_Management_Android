package com.example.class_management_android;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.class_management_android.adapter.ClassroomAdapter;
import com.example.class_management_android.database.DbClassroomHelper;
import com.example.class_management_android.model.Classroom;

import java.util.ArrayList;
import java.util.List;
public class ClassroomListFragment extends Fragment implements SearchView.OnQueryTextListener
{
    // variables
    private ListView lvListClassroom;
    private List<Classroom> mListClassroom; // mListClassroom - monitor of list classroom
    private ClassroomAdapter mAdapter;
    public static String mSearchText = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_classroom_list, container, false);
        ImageButton button = (ImageButton) v.findViewById(R.id.btn_addi);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), EditClassroomActivity.class);
                startActivity(i);
                Toast.makeText(getActivity().getApplicationContext(),"you are adding an classroom item", Toast.LENGTH_SHORT).show();
            }
        });
        lvListClassroom = (ListView) v.findViewById(R.id.lvListCLassManagers);
        mListClassroom = new ArrayList<>();
        mAdapter = new ClassroomAdapter(this.getActivity(), R.layout.classroom_row, mListClassroom);
        lvListClassroom.setAdapter(mAdapter);
        refreshListClassManagersData();
        addEventListener();
        return v;
    }

    public void refreshListClassManagersData()
    {
        DbClassroomHelper dbClassManagerHelper = new DbClassroomHelper(getActivity(), null);
        mListClassroom.clear();
        mListClassroom.addAll(dbClassManagerHelper.getList());
        mAdapter.notifyDataSetChanged();
    }

    private void addEventListener()
    {
        lvListClassroom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
            {
//                Classroom classManager = mListClassroom.get(position);
//                Intent i = new Intent(getActivity(), ListStudents.class);
//                i.putExtra(DbClassManagerHelper.COLUMN_CLASS_ID, classManager.getClassID());
//                startActivity(i);
                Toast.makeText(getActivity().getApplicationContext(),
                        "you clicked classroom item with onItemShortClick", Toast.LENGTH_SHORT).show();
            }
        });
        lvListClassroom.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (position >= 0) {
                    Classroom classroom = mListClassroom.get(position);
                    Intent i = new Intent(getActivity(), EditClassroomActivity.class);
                    i.putExtra(DbClassroomHelper.COLUMN_ID, classroom.getId());
                    startActivity(i);
                }
                Toast.makeText(getActivity().getApplicationContext(),
                        "you clicked classroom item with onItemLongClick", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String new_text) {
        mSearchText = new_text;
        DbClassroomHelper dbHelper = new DbClassroomHelper(getActivity(), null);
        mListClassroom.clear();
        mListClassroom.addAll(dbHelper.search(new_text));
        mAdapter.notifyDataSetChanged();
        return true;
    }
}