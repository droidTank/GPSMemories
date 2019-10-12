package com.example.gpsmemories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.gpsmemories.Adapter.MemoriesAdapter;
import com.example.gpsmemories.Database.Memory;
import com.example.gpsmemories.Database.MemoryDatabase;

import java.util.ArrayList;
import java.util.List;

public class Show extends AppCompatActivity implements SearchView.OnQueryTextListener {


    private RecyclerView recyclerView;
    private MemoriesAdapter adapter;
    private List<Memories_List> memories_list;


    public static MemoryDatabase memoryDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);








        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Show.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

            }
        });



        memoryDatabase = Room.databaseBuilder(getApplicationContext(),
                MemoryDatabase.class, "memorydb").allowMainThreadQueries().build();




        recyclerView = (RecyclerView) findViewById(R.id.RecycleVMemories);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        memories_list = new ArrayList<>();

        adapter = new MemoriesAdapter(memories_list, this);


        List <Memory>  memories =  Show.memoryDatabase.memoryDao().getAll();


        for (Memory memory : memories){


            memories_list.add(new Memories_List( memory.getTitle(),memory.getTime()
                    ,memory.getDescription(),memory.getId(),memory.getImage(),memory.getLatitude(),memory.getLongitude()));
        }


        adapter = new MemoriesAdapter(memories_list, this);





        recyclerView.setAdapter(adapter);







    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);



        SearchManager searchManager =
                (SearchManager) getSystemService(this.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Deletall) {
            //NoteDatabase.destroyInstance();

            new AlertDialog.Builder(this)
                    .setTitle("Delete All Memories")
                    .setMessage("Are you sure you want to delete all memories?")


                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Show.memoryDatabase.memoryDao().deleteall();
                            Intent intent = new Intent(Show.this, Show.class);
                            startActivity(intent);
                            Toast.makeText(Show.this,"All memories deleted successfully", Toast.LENGTH_SHORT).show();

                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }



        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {


        newText= newText.toLowerCase();
        List<Memories_List> newList= new ArrayList<>();

        for( Memories_List memory : memories_list){
            String noteString = memory.getT().toLowerCase();
            if(noteString.contains(newText)){
                newList.add(memory);
            }
        }

        adapter.updateList(newList);
        return true;
    }
}





