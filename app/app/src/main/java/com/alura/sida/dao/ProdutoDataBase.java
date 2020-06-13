package com.alura.sida.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.alura.sida.model.ProdutoObj;

import static com.alura.sida.ui.Const.NOME_BANCO_DE_DADOS;

@Database(entities = {ProdutoObj.class},version = 1)
public abstract class ProdutoDataBase extends RoomDatabase {

    public static ProdutoDataBase getInstance(Context context)
    {
        return Room.databaseBuilder(context,
                ProdutoDataBase.class,
                NOME_BANCO_DE_DADOS)
                .build();
    }

    public abstract ProdutoRoomDao getProdutoDao();

}
