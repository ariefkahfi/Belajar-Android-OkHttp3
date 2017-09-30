package com.kahfi.arief.belajarandroidlocalbroadcastmanager.httpservice;

import java.io.IOException;
import java.util.List;

/**
 * Created by arief on 30/09/17.
 */

public interface HttpServiceDAO<T,ID> {

    String save(T t) throws IOException;

    List<T> getAll() throws IOException;

    String delete(ID id) throws IOException;

    String update(T t) throws IOException;


}