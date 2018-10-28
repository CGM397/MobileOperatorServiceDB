package service;

import entity.Package;

public interface PackageDao {

    public Package find(String pid);
}
