# Описание проделанной работы:
1. Добавлено клиент-серверное взаимодействие через gRPC: Клиент - LandscapeService, сервера - HandymanService и Rancher.
2. С его помощью реализован эндпоинт /service/statuses, возвращающий хост, текущее состояние, версию и количество сервисов Handyman и Rancher.
3. Добавлена реализация эндпоинта /readiness через gRPC (включается в соответствующем application.yml: <em>status.grpc.enabled=true</em>)
4. Также добавлены тесты.