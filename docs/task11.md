## HW11:

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

1. В сервисах **HandymanService** и **RancherService** необходимо использовать [MongoDB](https://www.mongodb.com/)
   вместо PostgreSQL
2. В сервисе **LandscapeService** необходимо добавить возможность создания сводного отчета. Отчет должен содержать
   следующую информацию:
    + какие работы проводятся в огородах
    + список копаталей и их умения
    + существует ли дефицит работы (т.е. количество копателей превышает количество предоставленной работы или их умения
      не подходят к существующим заказам, поэтому они ожидают)
3. \* **Выполняйте данное задание максимально аккуратно, следите за используемой памятью.** Загрузите 100_000 документов
   в MongoDB, удалите загруженные документы.

+ Сделайте скриншот, используемой памяти MongoDB
+ Загрузите 100_000 документов в MongoDB. После загрузки сделайте заново скриншот, используемой памяти MongoDB
+ Удалите загруженные документы. Вам необходимо подумать над реализацией алгоритма очищения данных, чтобы память,
  занимаемая MongoDB, уменьшилась. После удаления сделайте заново скриншот, используемой памяти MongoDB

Все скриншоты потребления памяти приложите в отчете

### Ресурсы для самостоятельного ознакомления

- [Spring Data MongoDB by Baeldung](https://www.baeldung.com/spring-data-mongodb-tutorial)
- [Spring Boot + MongoDB](https://www.mongodb.com/compatibility/spring-boot)