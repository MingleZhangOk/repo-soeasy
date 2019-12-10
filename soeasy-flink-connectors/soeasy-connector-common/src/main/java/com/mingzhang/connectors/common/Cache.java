package com.mingzhang.connectors.common;

import java.util.Optional;

/**
 * @author MingZhang
 * @date 2019/5/22
 */
public interface Cache<T> {

    void put(String key ,Optional<T> value);

    Optional<T> get(String key);

}
