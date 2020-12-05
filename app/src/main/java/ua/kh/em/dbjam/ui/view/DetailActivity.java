package ua.kh.em.dbjam.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import ua.kh.em.dbjam.R;
import ua.kh.em.dbjam.data.model.Note;


public class DetailActivity extends AppCompatActivity {

    Toolbar toolbar;
    int itemId;
    TextView detailName;
    TextView detailContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setupToolbar();
        initViews();
        handleIntent();
    }

    private void setupToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initViews(){
        detailName = findViewById(R.id.detail_name);
        detailContent = findViewById(R.id.detail_content);
    }

    private void handleIntent(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null){
            Note note = bundle.getParcelable("note_detail");
            if (note != null) {
                detailName.setText(note.getNoteName());
                detailContent.setText(note.getNoteContent());
                itemId = note.getNoteId();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_edit) {
            Intent intent = new Intent(DetailActivity.this,
                    UpdateActivity.class);

            String strId = Integer.valueOf(itemId).toString();
            String strName = detailName.getText().toString();
            String strContent = detailContent.getText().toString();

            Bundle bundle = new Bundle();
            bundle.putString("note_id", strId);
            bundle.putString("note_name", strName);
            bundle.putString("note_content", strContent);

            intent.putExtras(bundle);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
