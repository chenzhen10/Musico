package by.epam.kimbar.service;

import by.epam.kimbar.service.impl.ClientServiceImpl;
import by.epam.kimbar.service.impl.NewsServiceImpl;

public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();
    private ClientService clientService = new ClientServiceImpl();
    private NewsService newsService = new NewsServiceImpl();

    private ServiceProvider() {
    }


    public ClientService getClientService() {
        return clientService;
    }

    public NewsService getNewsService() {return newsService;}


    public static ServiceProvider getInstance() {
        return instance;
    }
}
