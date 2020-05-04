package com.sensor.util.encoder;

public interface ApplicationCoder<T, N> {
    N encode(T param);

    T decode(N param);
}
