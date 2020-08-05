package com.example.management_software.Callback;


public interface VolleyCallback <T>{
    void onReponse(T reponse);
    void onError(String err);
}
