package em.kh.ua.roomrx.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import em.kh.ua.roomrx.adapter.MainAdapter;
import em.kh.ua.roomrx.database.Note;
import em.kh.ua.roomrx.viewmodel.MainViewModel;
import em.kh.ua.roomrx.R;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;


public class MainActivity extends AppCompatActivity implements View.OnLongClickListener,
        View.OnClickListener{

    private static final String DIALOG_DELETE = "DeleteDialog";
    Toolbar toolbar;
    private MainAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    private MainViewModel viewModel;
    private CompositeDisposable compositeDisposable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar();
        setupRecyclerView();
        initVars();
        showData();
    }

    private void setupToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);

        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty() || TextUtils.isEmpty(newText)){
                    showData();
                } else {
                    //String searchText = "%"+newText.trim()+"%";
                    String searchText = newText.trim()+"%";
                    showData(searchText);
                }
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_search:
                showData();
                return true;
            case R.id.menu_add:
                startActivity(new Intent(this, AddActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initVars(){
        compositeDisposable = new CompositeDisposable();
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    private void setupRecyclerView(){
        if (adapter == null){
            RecyclerView recyclerView = findViewById(R.id.note_list);
            linearLayoutManager = new LinearLayoutManager(MainActivity.this);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            adapter = new MainAdapter(new ArrayList<Note>(),this,this);
            recyclerView.setAdapter(adapter);
            // divider line between RecyclerView items
            RecyclerView.ItemDecoration dividerItemDecoration =
                    new DividerItemDecoration(recyclerView.getContext(),
                            linearLayoutManager.getOrientation());
            recyclerView.addItemDecoration(dividerItemDecoration);
        }else {
            adapter.notifyDataSetChanged();
        }
    }

    private void showData(){
        compositeDisposable.add(viewModel.fetchNotes()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<List<Note>>() {
                @Override
                public void accept(List<Note> notes) {
                    adapter.addListNotes(notes);
                }
            })
        );
    }

    private void showData(String search){
        compositeDisposable.add(viewModel.fetchNotes(search)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<List<Note>>() {
                @Override
                public void accept(List<Note> notes) {
                    adapter.addListNotes(notes);
                }
            })
        );
    }

    @Override
    public boolean onLongClick(View v) {
        Note note = (Note) v.getTag();
        DeleteDialog dialogDelete = new DeleteDialog();
        Bundle bundle = new Bundle();
        bundle.putParcelable("note_bundle",note);
        dialogDelete.setArguments(bundle);
        dialogDelete.show(getSupportFragmentManager(),DIALOG_DELETE);
        return true;
    }

    @Override
    public void onClick(View v) {
        Note note = (Note) v.getTag();
        Intent intent = (new Intent(this, DetailActivity.class));
        Bundle bundle = new Bundle();
        bundle.putParcelable("note_detail",note);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // clear all the subscriptions
        // compositeDisposable.clear();
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }
}
