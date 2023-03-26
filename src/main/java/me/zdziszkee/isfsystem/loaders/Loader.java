package me.zdziszkee.isfsystem.loader;

import me.zdziszkee.isfsystem.model.StoreConfiguration;

import java.io.InputStream;

public interface Loader<T> {
    T load(InputStream source);
}
