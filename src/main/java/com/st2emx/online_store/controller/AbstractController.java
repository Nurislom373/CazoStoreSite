package com.st2emx.online_store.controller;

import com.st2emx.online_store.service.BaseService;
import lombok.RequiredArgsConstructor;

/**
 * Author : Qozoqboyev Ixtiyor
 * Time : 14.03.2022 10:23
 * Project : zakovat
 */
@RequiredArgsConstructor
public abstract class AbstractController<S extends BaseService>{
    protected final S service;
}
