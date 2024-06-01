// IMyAidlInterface.aidl
package com.jerry.myapplication;

import com.jerry.myapplication.StudentBean;

// Declare any non-default types here with import statements

interface IMyAidlInterface {

    List<StudentBean> getStudents();
}
