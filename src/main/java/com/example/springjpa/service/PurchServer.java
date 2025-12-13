package com.example.springjpa.service;

import com.example.springjpa.dto.response.PurchResponse;
import com.example.springjpa.dto.resquest.PurchRequest;

public interface   PurchServer {

  PurchResponse add(PurchRequest purchRequest);
   

}
