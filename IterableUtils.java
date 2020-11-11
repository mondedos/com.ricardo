package com.ricardo.trastero.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class IterableUtils {


    public static <T> T find(T[] array, Predicate<T> predicate) {
        final List<T> list = toList(array);

        return find(list, predicate);
    }

    public static <T> T find(Iterable<T> iterable, Predicate<T> predicate) {

        for (final T item : iterable) {
            if (predicate.test(item)) return item;
        }

        return null;
    }

    public static <T> Iterable<T> where(T[] array, Predicate<T> predicate) {
        final List<T> list = toList(array);

        return where(list, predicate);
    }

    public static <T> Iterable<T> where(Iterable<T> iterable, Predicate<T> predicate) {

        final List<T> list = new ArrayList<>();

        for (final T item : iterable) {
            if (predicate.test(item)) {
                list.add(item);
            }
        }

        return list;
    }

    public static <T, R> Iterable<R> select(T[] array, IFunction<T, R> function) {

        final List<R> list = new ArrayList<>();

        for (final T item : array) {
            list.add(function.select(item));
        }

        return list;
    }

    public static <T, R> Iterable<R> select(Iterable<T> iterable, IFunction<T, R> function) {

        final List<R> list = new ArrayList<>();

        for (final T item : iterable) {
            list.add(function.select(item));
        }

        return list;
    }


    public static <T> int count(T[] array, Predicate<T> predicate) {
        List<T> list = toList(array);

        return count(list, predicate);
    }

    public static <T> int count(Iterable<T> iterable, Predicate<T> predicate) {

        final List<T> list = new ArrayList<>();

        for (final T item : iterable) {
            if (predicate.test(item)) {
                list.add(item);
            }
        }

        return list.size();
    }

    public static <T> List<T> toList(T[] arraay) {

        ArrayList<T> ts = new ArrayList<T>();

        for (T item :
                arraay) {
            ts.add(item);
        }


        return ts;
    }

    public static <T> List<T> toList(Iterable<T> iterable) {

        List<T> list;
        if (iterable instanceof List<?>)
            return (List<T>) iterable;
        else {
            ArrayList<T> ts = new ArrayList<T>();

            for (T item :
                    iterable) {
                ts.add(item);
            }

            return ts;
        }

    }


    public static <T> T[] toArray(Iterable<T> iterable, Class<T> clazz) {
        List<T> ts = toList(iterable);

        return ts.toArray((T[]) Array.newInstance(clazz, ts.size()));
    }

    public static <T> void ForEach(Iterable<T> iterable,IAction<T> action){
        for (T item :
                iterable) {
            action.run(item);
        }
    }

    public interface IFunction<T, R> {
        R select(T expresion);
    }

    public interface IAction<T>{
        void run(T item);
    }

}
