package ru.evotor.external.integrations.library;

interface IAppRequest {
    String execute(String request, out Intent intent);
}