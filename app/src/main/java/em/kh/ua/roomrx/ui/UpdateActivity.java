package em.kh.ua.roomrx.ui;

import android.content.Context;
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
import em.kh.ua.roomrx.viewmodel.UpdateViewModel;
import em.kh.ua.roomrx.R;

public class UpdateActivity extends AppCompatActivity {

    final Context context = this;
    Toolbar toolbar;
    EditText etUpname;
    EditText etUpcontent;
    int itemId = 0;
    UpdateViewModel updateViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        setupToolbar();
        initViews();
        handleIntent();
    }

    private void setupToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initViews(){
        etUpname = findViewById(R.id.et_upname);
        etUpcontent = findViewById(R.id.et_upcontent);
    }

    private void handleIntent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String idNote = (String) bundle.get("note_id");
            String nameNote = (String) bundle.get("note_name");
            String contentNote = (String) bundle.get("note_content");

            if (idNote != null) {
                itemId = Integer.parseInt(idNote);
            }
            etUpname.setText(nameNote);
            etUpcontent.setText(contentNote);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_update,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_done) {
            getUpdateViewModel();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getUpdateViewModel() {
        String strName = etUpname.getText().toString().trim();
        String strContent = etUpcontent.getText().toString().trim();
        int idNote = itemId;

        if (strName.isEmpty() || strContent.isEmpty()) {
            Toast.makeText(this, R.string.toast_empty, Toast.LENGTH_SHORT).show();
        } else {
            updateViewModel = new ViewModelProvider(this).get(UpdateViewModel.class);
            updateViewModel.updateNote(new Note(idNote, strName, strContent));
            Toast.makeText(this, R.string.toast_update, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        }
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(context, MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        super.onBackPressed();
    }
}
