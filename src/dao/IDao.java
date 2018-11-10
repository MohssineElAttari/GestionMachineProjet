/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author imadeddine
 */
public interface IDao<T> {
     boolean create(T o);
    boolean update(T o);
    boolean delete(T o);
    List<T> findAll();
    T findById(int id);
    
}
