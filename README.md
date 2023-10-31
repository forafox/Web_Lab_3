# Лабораторная работа №3 по Веб-программированию


Left part             |  Right part
:-------------------------:|:-------------------------:
![](https://github.com/forafox/Web_Lab_3/blob/master/images/index.png)  |  ![](https://github.com/forafox/Web_Lab_3/blob/master/images/main.png)
![](https://github.com/forafox/Web_Lab_3/blob/master/images/test_values.png)   |  ![](https://github.com/forafox/Web_Lab_3/blob/master/images/error.png)


## Содержание проекта

Разработано приложение на базе JavaServer Faces Framework, которое осуществляет проверку попадания точки в заданную
область на координатной плоскости.

Приложение включает в себя 2 facelets-шаблона - стартовую страницу и основную страницу приложения, а также набор
управляемых бинов (managed beans), реализующих логику на стороне сервера.

### Стартовая страница содержит следующие элементы:

- "Шапку", содержащую ФИО студента, номер группы и номер варианта.
- Интерактивные часы, показывающие текущие дату и время, обновляющиеся раз в 10 секунд.
- Ссылку, позволяющую перейти на основную страницу приложения.

### Основная страница приложения содержит следующие элементы:

- Набор компонентов для задания координат точки и радиуса области. Приложение осуществляет валидацию данных, если
  компонент допускает ввод заведомо некорректных данных (таких, например, как буквы в координатах точки).
- Динамически обновляемую картинку, изображающую область на координатной плоскости в соответствии с номером варианта и
  точки, координаты которых были заданы пользователем. Клик по картинке иницилаизирует сценарий, осуществляющий
  определение координат новой точки и отправку их на сервер для проверки её попадания в область. Цвет точек зависит от
  факта попадания / непопадания в область. Смена радиуса также инициализирует перерисовку картинки.
- Таблицу со списком результатов предыдущих проверок.
- Ссылку, позволяющую вернуться на стартовую страницу.

### Дополнительные свойства приложения:

- Все результаты проверки сохраняются в базе данных под управлением СУБД PostgreSQL.
- Для доступа к БД используется ORM EclipseLink.
- Для управления списком результатов используется Application-scoped Managed Bean.
- Конфигурация управляемых бинов задана с помощью аннотаций.
- Правила навигации между страницами приложения заданы в отдельном конфигурационном файле.

### Help

1. Проброс порта для просмотра 
   ssh -L 3501:localhost:3501 helios
2. Проброс порта для настройки wildFly
   ssh -L 3503:localhost:3503 helios
3. Просмотр конфигурации wildFly на Helios
   cat /wildfly-29.0.1.Final/standalone/configuration/standalone.xml
4. Добавление модуля
   module add --name=org.postgresql --resources=postgresql-42.6.0.jar --dependencies=javax.api,javax.transaction.api
5. Скачивание
   wget https://jdbc.postgresql.org/download/postgresql-42.6.0.jar
6. Запуск wildFly standalone
   ./wildfly-29.0.1.Final/bin/standalone.sh
7. Новый адрес
   http://localhost:3501/lab3/
