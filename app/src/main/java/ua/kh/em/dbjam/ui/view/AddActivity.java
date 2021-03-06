package ua.kh.em.dbjam.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import ua.kh.em.dbjam.data.model.Note;
import ua.kh.em.dbjam.R;
import ua.kh.em.dbjam.ui.viewmodel.AddViewModel;

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
        if (id == R.id.menu_done) {
            addNote();
            return true;
        }
        return super.onOptionsItemSelected(item);
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
