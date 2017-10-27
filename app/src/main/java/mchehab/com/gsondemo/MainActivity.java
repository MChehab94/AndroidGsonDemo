package mchehab.com.gsondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        List<Person> listPersons = createListFromFile();
        ArrayAdapter<Person> arrayAdapter = new ArrayAdapter<>(this, android.R.layout
                .simple_list_item_1, listPersons);
        listView.setAdapter(arrayAdapter);
    }

    private List<Person> createListFromFile(){
        Gson gson = new Gson();
        List<Person> list = new ArrayList<>();
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getAssets()
                    .open("persons.txt")));
            StringBuilder json = new StringBuilder();
            String line;
            while((line = bufferedReader.readLine()) != null){
                json.append(line);
            }
            list = gson.fromJson(json.toString(), new TypeToken<ArrayList<Person>>() {}.getType());
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
        return list;
    }
}
