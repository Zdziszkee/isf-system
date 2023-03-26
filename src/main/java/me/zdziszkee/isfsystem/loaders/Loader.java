package me.zdziszkee.isfsystem.loaders;

import java.io.InputStream;

public interface Loader<T> {
    T load(InputStream source);
}
