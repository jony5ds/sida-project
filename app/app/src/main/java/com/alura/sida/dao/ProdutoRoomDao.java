package com.alura.sida.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.alura.sida.model.ProdutoObj;

import java.util.List;

@Dao
public interface ProdutoRoomDao {
    @Insert
     void salvar(ProdutoObj produto);

    @Query("SELECT * FROM produtoobj")
    List<ProdutoObj> obterTodosProdutos();

    @Delete
    void deletar(ProdutoObj produto);

    @Update
    void editar(ProdutoObj produto);
}
