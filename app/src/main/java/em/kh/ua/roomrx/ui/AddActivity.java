package em.kh.ua.roomrx.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;


import em.kh.ua.roomrx.database.Note;
import em.kh.ua.roomrx.R;
import em.kh.ua.roomrx.viewmodel.AddViewModel;

public class AddActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText etName;
    EditText etContent;
    String strName;
    String strContent;
    AddViewModel addViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        setupToolbar();
        initViews();
    }

    private void setupToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initViews(){
        etName = findViewById(R.id.et_name);
        etContent = findViewById(R.id.et_content);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_done:
                addNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addNote() {
        strName = etName.getText().toString().trim();
        strContent = etContent.getText().toString().trim();

        if (strName.isEmpty() || strContent.isEmpty()) {
            Toast.makeText(this, R.string.toast_empty, Toast.LENGTH_SHORT).show();
        } else {
            addViewModel = new ViewModelProvider(this).get(AddViewModel.class);
            addViewModel.addNote(new Note(strName,strContent));
            Toast.makeText(this,R.string.toast_add,Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        }
    }
}
