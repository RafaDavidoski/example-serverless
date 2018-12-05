package com.example;


import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Data;

public class Function {

    DAO cidadeDao = new DAO();

    @FunctionName("criarCidade")
    public Cidade create(
            @HttpTrigger(name = "createcidade",
                    methods = {HttpMethod.POST}, route = "cidade") Cidade c) {
        //TO DO
        return cidadeDao.create(c);

    }

    @FunctionName("readCidade")
    public List<Cidade> read(
            @HttpTrigger(name = "readcidade",
                    methods = {HttpMethod.GET}, route = "cidade") String cidade) {

        return cidadeDao.read();

    }

    @FunctionName("updateCidade")
    public Cidade update(
            @HttpTrigger(name = "updatecidade", methods = {HttpMethod.PUT}, route = "cidade") Cidade c) {
        //TO DO
        return cidadeDao.update(c);
    }

    @FunctionName("deleteCidade") // DELETE,DELETE,OBSOLETE
    public int delete(
            @HttpTrigger(name = "deletecidade", methods = {HttpMethod.DELETE}, route = "cidade/{id}")
            @BindingName("id") Long id) {
        //TO DO
        return 200;
    }
}

class DAO {

    public Cidade create(Cidade c) {
        Cidade cidade = new Cidade();
        return c;
    }

    public List<Cidade> read() {
        return Stream.of(
                new Cidade(1L, "Apucarana", new Estado(1L, "PR")),
                new Cidade(2L, "Arapongas", new Estado(1L, "PR"))
        ).collect(Collectors.toList());
    }

    public Cidade update(Cidade c) {
        c.setId(c.getId() + 1L);
        c.setNome(c.getNome());
        return c;
    }

    public int delete(Long id) {
        return 200;
    }

}

@Data
class Cidade {

    private Long id;
    private String nome;
    private Estado estado;

    public Cidade(Long id, String nome, Estado estado) {
        this.setId(id);
        this.setNome(nome);
        this.setEstado(estado);
    }
}

@Data
class Estado {

    private Long id;
    private String nome;

    public Estado(Long id, String nome) {
        this.setId(id);
        this.setNome(nome);
    }
}
