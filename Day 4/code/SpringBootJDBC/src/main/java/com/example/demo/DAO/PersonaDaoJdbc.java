package com.example.demo.DAO;

import com.example.demo.Domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PersonaDaoJdbc {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonaDaoJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Persona> findAll() {
        return jdbcTemplate.query("SELECT * FROM persone", new PersonaRowMapper());
    }

    public Persona findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM persone WHERE id = ?", new Object[] { id }, new PersonaRowMapper());
    }

    public void save(Persona persona) {
        jdbcTemplate.update("INSERT INTO persone (nome, cognome) VALUES (?, ?)", persona.getNome(), persona.getCognome());
    }

    public void update(Persona persona) {
        jdbcTemplate.update("UPDATE persone SET nome = ?, cognome = ? WHERE id = ?", persona.getNome(), persona.getCognome(), persona.getId());
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM persone WHERE id = ?", id);
    }

    private static class PersonaRowMapper implements RowMapper<Persona> {
        @Override
        public Persona mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long id = rs.getLong("id");
            String nome = rs.getString("nome");
            String cognome = rs.getString("cognome");
            return new Persona(id, nome, cognome);
        }
    }
}

