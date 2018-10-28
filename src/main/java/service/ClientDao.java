package service;

import entity.Client;

public interface ClientDao {

    public boolean insert(Client client);

    public boolean update(Client client);

    public Client find(String cid);
}
