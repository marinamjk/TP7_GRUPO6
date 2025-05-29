package dao;

import java.util.List;

import entidades.Seguros;

public interface SegurosDao {
    public boolean insertarSeguro(Seguros seguro);
    public List<Seguros> listarSeguros();
    public String manejarCaracterEspecial(String texto);
}