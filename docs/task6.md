## HW4:

> Работа должна быть оформлена следующим образом:
>- Необходимо подготовить GitHub репозиторий и предоставить доступ всем проверяющим - семинаристам и ассистентам. Разработка
   > должна вестись в соответствии с [GitFlow](https://www.atlassian.com/ru/git/tutorials/comparing-workflows/gitflow-workflow).
>- Под каждое домашнее задание необходимо заводить [PullRequest](https://docs.gitlab.com/ee/user/project/merge_requests/creating_merge_requests.html),
   > в список апрруверов необходимо добавить всех проверяющих. После првоедения ревью и простановки оценки или апррува от проверяющего
   > (если в задании не сказано иного).
>- Каждое домашнее задание должно повышать [минорную версию приложения](https://semver.org/lang/ru/).
>- В корне репозитория должен находиться файл [readme.md](https://www.markdownguide.org/basic-syntax/) в котором должны быть
   > описаны шаги по запуску и настройке проекта с "чистого листа". Так же должны быть интерактивные ссылки на папку **/docs**
   > с отдельными папками под описание проделанных работы по каждому домашнему заданию, в том же формате **.md**.

### Заданиe:

> ##### Сервис "VOgorode"
> ![./image.webp](./image.webp)  
> Проект представляет из себя систему предоставления сервиса по оказанию ландшафтных работ клиентам распределённым
> географически. Сервис должен подбирать исполинтелей на основании географических координат исполнителя и локации заказчика,
> списка оказываемых исполнителем работ, а так же ретинга исполнителя.
> - HandymanService - бэк для клиентской части, обслуживающий людей которые предоставляют сервис.
    > Есть набор характеристик - что умеет (копать, сажать картошку, поливать грядки), где находиться,
    > зафиксированное расписание, оплата за час
> - RancherService - бэк обслуживающий участки, координаты, размеры поля, что посажено - когда что созреет и т.п.
> - LandscapeService - Управление пользователями, назначение на работы, управление ценой за работы,
    > проставление рейтинга, сбор статистики

#### Необходимо подготовить видеоотчет, загрузить его в падлет

1. В сервисе **LandscapeService** необходимо реализовать REST эндпоинт для CRUD операций с пользователями
2. В сервисе **LandscapeService** необходимо добавить возможность хранить широту и долготу пользователей и участков. API
   должен предоставлять возможность указывать id сущности (UUID), широту и долготу (double), двумя отдельными
   параметрами, типа сущности (String)
3. К сервисах **HandymanService** и **RancherService** необходимо подключить Mongo DB + LiquidBase
4. В сервисах **HandymanService** и **RancherService** необходимо создть директорию "resources/db"
   для хранения соответствующих миграций
5. В сервисах **HandymanService** и **RancherService** необходимо реализовать эндпоинты предоставляющие CRUD операции
   для работы с профилем работника и садового участка соответственно. Они должны создавать сначала учетные записи в
   сервисе **LandscapeService**, а затем создавать локальные сущности. Соответственно, если не удалось создать
   пользователя или сервис **LandscapeService** недоступен, операцию создания необходимо прерывать и сообщать о ошибке.
6. Профиль работника должен содержать перечень работ которые он выполняет, широту и долготу.
7. Профиль участка должен быть привязан к профилю пользователя, владельца участка, должен иметь координаты, площадь, а
   так же список применимых к нему работ.
8. \* Данные об участке и работнике в сервисах **HandymanService** и **RancherService** должны предоставляться
   обогащенными данными из сервиса **LandscapeService**. Т.е. в сервисе **HandymanService** при запросе информации о
   работнике по ID должна выводиться информация о его почте, телефоне и логин. Аналогично для сервиса **RancherService**
   .

### Ресурсы для самостоятельного ознакомления

- [Mongo](https://hub.docker.com/_/mongo)
- [Mongo in Liquidbase](https://docs.liquibase.com/start/install/tutorials/mongodb.html)