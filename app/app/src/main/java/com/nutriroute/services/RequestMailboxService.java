package com.nutriroute.services;

import com.nutriroute.enums.RequestStatus;
import com.nutriroute.enums.RequestType;
import com.nutriroute.interfaces.IDataStore;
import com.nutriroute.interfaces.IRequestMailboxService;
import com.nutriroute.models.Request;
import com.nutriroute.models.User;
import com.nutriroute.stores.AuthStore;
import com.nutriroute.utils.ServiceLocator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RequestMailboxService implements IRequestMailboxService {
    // try to keep the lifetime same as the controller
    // this is basically a wrapper and filter around the database

    static IDataStore<String> dataStore = ServiceLocator.getDB();
    List<Request<String>> requests = new ArrayList<>();
    Integer st = 0;


    public void synchronize(){
        Collection<Request<String>> requests = dataStore.getRequests(); //expected instant ret
        this.requests = new ArrayList<>(requests);
        _increment();

    }

    // increment the finite-state machine state
    public void _increment()
    {
        // filter through and deque req with status acked
        requests = requests.stream().filter(request -> request.getStatus() != RequestStatus.ACK)
                .collect(Collectors.toList());

        // "protect" the db from getting malformed req with no status field which spoils the factory
        // gen on re-loading
        requests = requests.stream().filter(request -> request.getStatus() != null)
                .collect(Collectors.toList());
        //dataStore._updateObjectToDB(st++, "i");

    }

    public void send(Request<String> request){
        requests.add(request);
        synchronize();
        dataStore.setRequest(request, request.getId());
    }

    public List<Request<String>> receive(RequestType type){
        synchronize();
        if (requests.isEmpty()){
            return null;
        }
        return requests.stream().filter(request -> request.getType() == type)
                .collect(Collectors.toList());
    }
}
