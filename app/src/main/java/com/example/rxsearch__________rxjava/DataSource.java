package com.example.rxsearch__________rxjava;
import java.util.ArrayList;

import io.reactivex.Observable;



public class DataSource {

    public static ArrayList<String> getStudentList() {
        ArrayList<String> data = new ArrayList<String>();
        data.add("Jon");
        data.add("Jack");
        data.add("Rose");
        data.add("Rohim");
        data.add("Jacky");
        data.add("Thomus");
        data.add("Jesmin");
        data.add("Jecy");
        data.add("a");
        data.add("ab");
        data.add("abc");
        data.add("abcd");
        return data;
    }

    public static Observable<ArrayList<String>> getSearchData(String key) {
        ArrayList<String> data = getStudentList();
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).toUpperCase().contains(key.toUpperCase())) {
                result.add(data.get(i));
            }
        }
        Observable<ArrayList<String>> observable = Observable.just(result);
        return observable;
    }
}