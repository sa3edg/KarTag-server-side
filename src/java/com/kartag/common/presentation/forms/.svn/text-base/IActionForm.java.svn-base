package com.kartag.common.presentation.forms;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.kartag.common.model.IModel;

public interface IActionForm {
   public ArrayList<DefaultActionForm> listItems(IModel bean, IActionForm form, LinkedHashMap<String, String> filters) throws Exception;
//   public DefaultActionForm[] selectItems(IModel bean, IActionForm form) throws Exception;
   public boolean add(IModel bean, DefaultActionForm actionForm) throws Exception;
   public void edit(IModel bean, DefaultActionForm actionForm, Object primaryKey) throws Exception;
   public boolean update(IModel bean, DefaultActionForm actionForm) throws Exception;
   public boolean delete(IModel bean, DefaultActionForm actionForm) throws Exception;
   public boolean deleteAll(IModel bean, DefaultActionForm actionForm) throws Exception;
}
