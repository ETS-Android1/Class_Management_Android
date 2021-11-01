package com.example.class_management_android.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.class_management_android.model.Student;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student>
{
    private Context m_context;
    private int m_resource_id;
    private List<Student> m_list_students;

    public StudentAdapter(Context context, int resource, List<Student> objects) {
        super(context, resource, objects);
        this.m_context = context;
        this.m_resource_id = resource;
        this.m_list_students = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        viewHolder holder;
        if(convertView == null || convertView.getTag() == null){
            convertView = View.inflate(m_context, m_resource_id,null);

            holder = new viewHolder();
//            holder.tvOrderStudent = (TextView) convertView.findViewById(R.id.tvOrderStudent);
//            holder.tvIdStudent = (TextView) convertView.findViewById(R.id.tvIdStudent);
//            holder.tvNameStudent = (TextView) convertView.findViewById(R.id.tvNameStudent);
//            holder.tvBirthdayStudent = (TextView) convertView.findViewById(R.id.tvBirthdayStudent);
//            holder.ivGenderStudent = (ImageView) convertView.findViewById(R.id.ivGenderStudent);
            convertView.setTag(holder);
        }else
            holder = (viewHolder) convertView.getTag();

        Student student = m_list_students.get(position);

        holder.tvOrderStudent.setText(String.valueOf(position + 1));
        holder.tvIdStudent.setText(student.getId());
        // search text effect
//        String name = student.getName();
//        if(ListStudents.mSearchText != null){
//            // equals ignore Case
//            int startIndex = name.toLowerCase().indexOf(ListStudents.mSearchText.toLowerCase());
//            if(startIndex >= 0){
//                int endIndex = ListStudents.mSearchText.length();
//                SpannableString textSpan = new SpannableString(name);
//                textSpan.setSpan(new BackgroundColorSpan(Color.RED),startIndex,endIndex,SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
//                holder.tvNameStudent.setText(textSpan);
//            }
//        }else
        holder.tvNameStudent.setText(student.getName());

        holder.tvBirthdayStudent.setText(student.getBirthday());
//        if(student.getGender() == 0)
//            holder.ivGenderStudent.setImageResource(R.drawable.girlicon);
//        else
//            holder.ivGenderStudent.setImageResource(R.drawable.boyicon);

        return convertView;
    }

    private class viewHolder{
        TextView tvOrderStudent, tvIdStudent, tvNameStudent, tvBirthdayStudent;
        ImageView ivGenderStudent;
    }
}
