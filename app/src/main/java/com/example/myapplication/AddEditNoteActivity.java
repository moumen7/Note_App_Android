package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompatSideChannelService;

import android.content.Intent;
import android.os.Bundle;
import android.text.NoCopySpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {
    private EditText editTexttitle;
    private EditText editTextdescription;
    private NumberPicker numberPickerpriority;
    public static final String EXTRA_ID =
            "20";
    public static final String EXTRA_TITLE =
            "Moumen";
    public static final String EXTRA_DESCRIPTION =
            "Moumen tany";
    public static final String EXTRA_PRIORITY =
            "20";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        editTexttitle = findViewById(R.id.edit_text_title);
        editTextdescription = findViewById(R.id.edit_text_description);
        numberPickerpriority = findViewById(R.id.number_picker_priority);
        numberPickerpriority.setMaxValue(10);
        numberPickerpriority.setMinValue(1);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_ID))
        {
            setTitle("EDIT NOTE");
            editTexttitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextdescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPickerpriority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        }
        else {
            setTitle("ADD NOTE");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.addnotemenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    private void saveNote()
    {
        String title = editTexttitle.getText().toString();
        String desc = editTextdescription.getText().toString();
        int priority = numberPickerpriority.getValue();
        if(title.trim().isEmpty() || desc.trim().isEmpty())
        {
            Toast.makeText(this, "Invalid" , Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, desc);
        data.putExtra(EXTRA_PRIORITY, priority);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }
        setResult(RESULT_OK, data);
        finish();
    }
}
