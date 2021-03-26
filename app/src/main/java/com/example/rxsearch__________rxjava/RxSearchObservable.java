package com.example.rxsearch__________rxjava;

import android.widget.SearchView;

import io.reactivex.subjects.PublishSubject;

public class RxSearchObservable {
    public static PublishSubject<String> fromSearchView(SearchView searchView) {
        final PublishSubject<String> publisher = PublishSubject.create();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                publisher.onComplete();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                publisher.onNext(s);
                return true;
            }
        });
        return publisher;
    }
}