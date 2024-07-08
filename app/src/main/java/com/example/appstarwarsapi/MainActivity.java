package com.example.appstarwarsapi;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appstarwarsapi.models.Character;
import com.example.appstarwarsapi.models.SWApiResponse;
import com.example.appstarwarsapi.utils.RetrofitClient;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CharacterAdapter adapter;
    private List<Character> characterList = new ArrayList<>();
    private EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchEditText = findViewById(R.id.searchEditText);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // В адаптер передаётся лист персонажей и обработчик нажатия кнопки на элемент персонажа
        adapter = new CharacterAdapter(characterList, character -> {
            // В качестве вызываемого указываем объект с детальной информацией о персонаже
            Intent intent = new Intent(MainActivity.this, CharacterDetailActivity.class);
            // В качестве дополнительного параметра отправляем данные персонажа
            intent.putExtra("character", character);
            // Запуск интента
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);

        // Заполнение списка персонажей по поиску пустой строки (все персонажи)
        getCharacters("");

        // Определение обработчика изменения состояния пустой строки
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getCharacters(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void getCharacters(String name) {

        Call<SWApiResponse> call = RetrofitClient.getInstance().getApi().getCharacters(name);
        call.enqueue(new Callback<SWApiResponse>() {
            @Override
            public void onResponse(Call<SWApiResponse> call, Response<SWApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    characterList.clear();
                    characterList.addAll(response.body().getResults());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Ошибка парсинга персонажей!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SWApiResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Ошибка выполнения запроса!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
