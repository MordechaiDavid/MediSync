package com.medisync.util;


import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EntityMapper {

    public static <E, D> List<D> convertList(List<E> sourceList, Function<E, D> convertor){
        return sourceList.stream()
                .map(convertor)
                .collect(Collectors.toList());
    }
}
