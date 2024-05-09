package br.com.alura.screenmatchSpring.service;

public interface IConvertsData {
    <T> T getDatas(String json, Class<T> tClass);
}
