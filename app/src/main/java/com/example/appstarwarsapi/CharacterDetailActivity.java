package com.example.appstarwarsapi;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appstarwarsapi.models.Character;

public class CharacterDetailActivity extends AppCompatActivity {
    private TextView nameTextView, heightTextView, massTextView, hairColorTextView, eyeColorTextView, birthYearTextView, genderTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);

        nameTextView = findViewById(R.id.nameTextView);
        heightTextView = findViewById(R.id.heightTextView);
        massTextView = findViewById(R.id.massTextView);
        hairColorTextView = findViewById(R.id.hairColorTextView);
        eyeColorTextView = findViewById(R.id.eyeColorTextView);
        birthYearTextView = findViewById(R.id.birthYearTextView);
        genderTextView = findViewById(R.id.genderTextView);

        Character character = (Character) getIntent().getSerializableExtra("character");

        if (character != null) {
            nameTextView.setText(character.getName());
            heightTextView.setText("Height: " + character.getHeight());
            massTextView.setText("Mass: " + character.getMass());
            hairColorTextView.setText("Hair Color: " + character.getHairColor());
            eyeColorTextView.setText("Eye Color: " + character.getEyeColor());
            birthYearTextView.setText("Birth Year: " + character.getBirthYear());
            genderTextView.setText("Gender: " + character.getGender());
        }
    }
}
