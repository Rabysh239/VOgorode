# VOgorode

## Рукводство по запуску:

<b>Переменные окружения:</b>

| Имя         | Значение          |
|-------------|-------------------|
| DB_URL      | database url      |
| DB_USER     | database username |
| DB_PASSWORD | database password |

1. Запустить каждый сервис из корня соответствующего проекта с помощью:

```
   ./gradlew bootRun
```

2. Сервисы запустятся на следующих портах:  
   <b>HandymanService: 8080 (gRPC: 8090)</b>  
   <b>RancherService: 8081 (gRPC: 8091)</b>  
   <b>LandscapeService: 8082</b>
3. [Запуск через docker](dev/readme.md)

Описание проделанных работ по разработке сервиса: [docs](./docs)
    