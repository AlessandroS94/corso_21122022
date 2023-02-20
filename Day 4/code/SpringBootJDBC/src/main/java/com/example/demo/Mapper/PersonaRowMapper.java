package com.example.demo.Mapper;

import com.example.demo.Domain.Persona;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonaRowMapper implements RowMapper<Persona> {
    @Override
    public Persona mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String nome = rs.getString("nome");
        String cognome = rs.getString("cognome");
        return new Persona(id, nome, cognome);
    }
}
