package com.futvia.mapper;

import org.mapstruct.*;

import com.futvia.model.club.*;
import com.futvia.model.competicion.*;
import com.futvia.model.contenido.*;
import com.futvia.model.evento.*;
import com.futvia.model.geo.*;
import com.futvia.model.partido.*;
import com.futvia.model.rbac.*;

@Mapper(config = MapStructConfig.class)
public interface RefMapper {

    // ---------- Factories de referencia por ID (evita hits a DB en mapeos a Entity) ----------
    default Usuario toUsuario(Long id) { if (id == null) return null; var e = new Usuario(); e.setId(id); return e; }
    default Rol toRol(Long id) { if (id == null) return null; var e = new Rol(); e.setId(id); return e; }
    default Club toClub(Long id) { if (id == null) return null; var e = new Club(); e.setId(id); return e; }
    default Equipo toEquipo(Long id) { if (id == null) return null; var e = new Equipo(); e.setId(id); return e; }
    default Jugador toJugador(Long id) { if (id == null) return null; var e = new Jugador(); e.setId(id); return e; }
    default Temporada toTemporada(Long id) { if (id == null) return null; var e = new Temporada(); e.setId(id); return e; }
    default Categoria toCategoria(Long id) { if (id == null) return null; var e = new Categoria(); e.setId(id); return e; }
    default Competicion toCompeticion(Long id) { if (id == null) return null; var e = new Competicion(); e.setId(id); return e; }
    default Estadio toEstadio(Long id) { if (id == null) return null; var e = new Estadio(); e.setId(id); return e; }
    default Jornada toJornada(Long id) { if (id == null) return null; var e = new Jornada(); e.setId(id); return e; }
    default Partido toPartido(Long id) { if (id == null) return null; var e = new Partido(); e.setId(id); return e; }
    default Arbitro toArbitro(Long id) { if (id == null) return null; var e = new Arbitro(); e.setId(id); return e; }
    default Archivo toArchivo(Long id) { if (id == null) return null; var e = new Archivo(); e.setId(id); return e; }
    default Pais toPais(Long id) { if (id == null) return null; var e = new Pais(); e.setId(id); return e; }
    default Departamento toDepartamento(Long id) { if (id == null) return null; var e = new Departamento(); e.setId(id); return e; }
    default Municipio toMunicipio(Long id) { if (id == null) return null; var e = new Municipio(); e.setId(id); return e; }

    // ---------- Helpers para nombres visibles ----------
    default String nombreCompleto(Usuario u) {
        if (u == null) return null;
        var n = (u.getNombre() == null ? "" : u.getNombre().trim());
        var a = (u.getApellido() == null ? "" : u.getApellido().trim());
        return (n + " " + a).trim();
    }
    default String nombreCompleto(Jugador j) {
        if (j == null) return null;
        var n = (j.getNombre() == null ? "" : j.getNombre().trim());
        var a = (j.getApellido() == null ? "" : j.getApellido().trim());
        return (n + " " + a).trim();
    }
}