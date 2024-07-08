package com.example.appstarwarsapi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appstarwarsapi.models.Character;
import java.util.List;

/*!
 *
 */
public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {
    private List<Character> characterList;              ///< список отображаемых элементов
    private OnItemClickListener onItemClickListener;    ///< обработчик нажатия на элемент

    /*!
     * Интерфейс обработчика нажатия на элемент
     */
    public interface OnItemClickListener {
        void onItemClick(Character character);
    }

    /*!
     * Конструктор принимает два параметра
     * - перечень объектов для отображения
     * - обработчик нажатия на объект
     */
    public CharacterAdapter(List<Character> characterList, OnItemClickListener onItemClickListener) {
        this.characterList = characterList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        Character character = characterList.get(position);
        holder.nameTextView.setText(character.getName());
        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(character));
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    /*!
     * Класс для отрисовки одного элемента списка
     */
    static class CharacterViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;

        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
        }
    }
}
