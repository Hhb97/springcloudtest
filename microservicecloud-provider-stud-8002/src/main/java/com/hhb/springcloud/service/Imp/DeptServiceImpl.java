package com.hhb.springcloud.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhb.springcloud.dao.DeptDao;
import com.hhb.springcloud.service.DeptService;
import com.springcloud.pojo.Dept;

@Service
public class DeptServiceImpl implements DeptService
{
  @Autowired
  private DeptDao dao ;
  
  @Override
  public boolean add(Dept dept)
  {
   return dao.addDept(dept);
  }
 
  @Override
  public Dept get(Long id)
  {
   return dao.findById(id);
  }
 
  @Override
  public List<Dept> list()
  {
   return dao.findAll();
  }
 
}
 
