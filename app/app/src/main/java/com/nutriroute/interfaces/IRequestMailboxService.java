package com.nutriroute.interfaces;

import com.nutriroute.enums.RequestType;
import com.nutriroute.models.Request;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface IRequestMailboxService {

    void synchronize();

    void send(Request<String> request);

    List<Request<String>> receive(RequestType type);
}
