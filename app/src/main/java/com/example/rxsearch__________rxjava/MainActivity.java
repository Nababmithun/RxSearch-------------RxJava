package com.example.rxsearch__________rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SearchView;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.svKey)
    SearchView svKey;
    @BindView(R.id.rvStudentList)
    RecyclerView rvStudentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);
        StudentAdapter adapter = new StudentAdapter();
        rvStudentList.setLayoutManager(new LinearLayoutManager(this));
        rvStudentList.setAdapter(adapter);
        initSearchFeature(adapter);
    }

    private void initSearchFeature(StudentAdapter adapter) {
        RxSearchObservable.fromSearchView(svKey)
                .debounce(500, TimeUnit.MILLISECONDS)
                .filter(text -> !text.isEmpty())
                .distinctUntilChanged()
                .switchMap(new Function<String, ObservableSource<ArrayList<String>>>() {
                    @Override
                    public ObservableSource<ArrayList<String>> apply(@NonNull String key) throws Exception {
                        return DataSource.getSearchData(key);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(names -> {
                    adapter.removeAllNames();
                    adapter.addStudentNames(names);
                });
    }
}